package com.burgess.banana.system.controller;

import com.burgess.banana.common.util.BananaConstant;
import com.burgess.banana.common.util.BananaPageUtils;
import com.burgess.banana.common.util.BananaResult;
import com.burgess.banana.common.validator.BananaAssert;
import com.burgess.banana.common.validator.BananaValidatorUtils;
import com.burgess.banana.common.validator.group.BananaAddGroup;
import com.burgess.banana.common.validator.group.BananaUpdateGroup;
import com.burgess.banana.log.annotation.BananaSystemLog;
import com.burgess.banana.system.entity.BananaSystemUserEntity;
import com.burgess.banana.system.form.BananaPasswordForm;
import com.burgess.banana.system.service.BananaSystemUserRoleService;
import com.burgess.banana.system.service.BananaSystemUserService;
import org.apache.commons.lang.ArrayUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.crypto.hash.Sha256Hash;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * @author burgess.zhang
 * @project banana-platform
 * @package com.burgess.banana.system.controller
 * @file BananaSystemUserController.java
 * @time 2018-05-23 22:04
 * @desc 系统用户
 */
@RestController
@RequestMapping("/sys/user")
public class BananaSystemUserController extends BananaAbstractController {


    @Resource(name = "sysUserService")
    private BananaSystemUserService sysUserService;

    @Resource(name = "sysUserRoleService")
    private BananaSystemUserRoleService sysUserRoleService;


    /**
     * 所有用户列表
     */
    @GetMapping("/list")
    @RequiresPermissions("sys:user:list")
    public BananaResult list(@RequestParam Map<String, Object> params) {
        //只有超级管理员，才能查看所有管理员列表
        if (getUserId() != BananaConstant.SUPER_ADMIN) {
            params.put("createUserId", getUserId());
        }
        BananaPageUtils page = sysUserService.queryPage(params);

        return BananaResult.ok().put("page", page);
    }

    /**
     * 获取登录的用户信息
     */
    @GetMapping("/info")
    public BananaResult info() {
        return BananaResult.ok().put("user", getUser());
    }

    /**
     * 修改登录用户密码
     */
    @BananaSystemLog("修改密码")
    @PostMapping("/password")
    public BananaResult password(@RequestBody BananaPasswordForm form) {
        BananaAssert.isBlank(form.getNewPassword(), "新密码不为能空");

        //sha256加密
        String password = new Sha256Hash(form.getPassword(), getUser().getSalt()).toHex();
        //sha256加密
        String newPassword = new Sha256Hash(form.getNewPassword(), getUser().getSalt()).toHex();

        //更新密码
        boolean flag = sysUserService.updatePassword(getUserId(), password, newPassword);
        if (!flag) {
            return BananaResult.error("原密码不正确");
        }

        return BananaResult.ok();
    }

    /**
     * 用户信息
     */
    @GetMapping("/info/{userId}")
    @RequiresPermissions("sys:user:info")
    public BananaResult info(@PathVariable("userId") Long userId) {
        BananaSystemUserEntity user = sysUserService.selectById(userId);

        //获取用户所属的角色列表
        List<Long> roleIdList = sysUserRoleService.queryRoleIdList(userId);
        user.setRoleIdList(roleIdList);

        return BananaResult.ok().put("user", user);
    }

    /**
     * 保存用户
     */
    @BananaSystemLog("保存用户")
    @PostMapping("/save")
    @RequiresPermissions("sys:user:save")
    public BananaResult save(@RequestBody BananaSystemUserEntity user) {
        BananaValidatorUtils.validateEntity(user, BananaAddGroup.class);

        user.setCreateUserId(getUserId());
        sysUserService.save(user);

        return BananaResult.ok();
    }

    /**
     * 修改用户
     */
    @BananaSystemLog("修改用户")
    @PostMapping("/update")
    @RequiresPermissions("sys:user:update")
    public BananaResult update(@RequestBody BananaSystemUserEntity user) {
        BananaValidatorUtils.validateEntity(user, BananaUpdateGroup.class);

        user.setCreateUserId(getUserId());
        sysUserService.update(user);

        return BananaResult.ok();
    }

    /**
     * 删除用户
     */
    @BananaSystemLog("删除用户")
    @PostMapping("/delete")
    @RequiresPermissions("sys:user:delete")
    public BananaResult delete(@RequestBody Long[] userIds) {
        if (ArrayUtils.contains(userIds, 1L)) {
            return BananaResult.error("系统管理员不能删除");
        }

        if (ArrayUtils.contains(userIds, getUserId())) {
            return BananaResult.error("当前用户不能删除");
        }

        sysUserService.deleteBatch(userIds);

        return BananaResult.ok();
    }
}
