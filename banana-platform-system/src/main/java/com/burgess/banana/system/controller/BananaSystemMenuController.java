package com.burgess.banana.system.controller;

import com.burgess.banana.common.util.BananaConstant;
import com.burgess.banana.common.util.BananaResult;
import com.burgess.banana.log.annotation.BananaSystemLog;
import com.burgess.banana.system.entity.BananaSystemMenuEntity;
import com.burgess.banana.system.exception.RRException;
import com.burgess.banana.system.service.BananaShiroService;
import com.burgess.banana.system.service.BananaSystemMenuService;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.Set;

/**
 * @author burgess.zhang
 * @project banana-platform
 * @package com.burgess.banana.system.controller
 * @file BananaSystemMenuController.java
 * @time 2018-05-23 21:59
 * @desc 系统菜单Controller
 */
@RestController
@RequestMapping("/sys/menu")
public class BananaSystemMenuController extends BananaAbstractController {


    @Resource(name = "sysMenuService")
    private BananaSystemMenuService sysMenuService;
    @Resource(name = "shiroService")
    private BananaShiroService shiroService;

    /**
     * 导航菜单
     */
    @GetMapping("/nav")
    public BananaResult nav() {
        List<BananaSystemMenuEntity> menuList = sysMenuService.getUserMenuList(getUserId());
        Set<String> permissions = shiroService.getUserPermissions(getUserId());
        return BananaResult.ok().put("menuList", menuList).put("permissions", permissions);
    }

    /**
     * 所有菜单列表
     */
    @GetMapping("/list")
    @RequiresPermissions("sys:menu:list")
    public List<BananaSystemMenuEntity> list() {
        List<BananaSystemMenuEntity> menuList = sysMenuService.selectList(null);
        for (BananaSystemMenuEntity sysMenuEntity : menuList) {
            BananaSystemMenuEntity parentMenuEntity = sysMenuService.selectById(sysMenuEntity.getParentId());
            if (parentMenuEntity != null) {
                sysMenuEntity.setParentName(parentMenuEntity.getName());
            }
        }

        return menuList;
    }

    /**
     * 选择菜单(添加、修改菜单)
     */
    @GetMapping("/select")
    @RequiresPermissions("sys:menu:select")
    public BananaResult select() {
        //查询列表数据
        List<BananaSystemMenuEntity> menuList = sysMenuService.queryNotButtonList();

        //添加顶级菜单
        BananaSystemMenuEntity root = new BananaSystemMenuEntity();
        root.setMenuId(0L);
        root.setName("一级菜单");
        root.setParentId(-1L);
        root.setOpen(true);
        menuList.add(root);

        return BananaResult.ok().put("menuList", menuList);
    }

    /**
     * 菜单信息
     */
    @GetMapping("/info/{menuId}")
    @RequiresPermissions("sys:menu:info")
    public BananaResult info(@PathVariable("menuId") Long menuId) {
        BananaSystemMenuEntity menu = sysMenuService.selectById(menuId);
        return BananaResult.ok().put("menu", menu);
    }

    /**
     * 保存
     */
    @BananaSystemLog("保存菜单")
    @PostMapping("/save")
    @RequiresPermissions("sys:menu:save")
    public BananaResult save(@RequestBody BananaSystemMenuEntity menu) {
        //数据校验
        verifyForm(menu);

        sysMenuService.insert(menu);

        return BananaResult.ok();
    }

    /**
     * 修改
     */
    @BananaSystemLog("修改菜单")
    @PostMapping("/update")
    @RequiresPermissions("sys:menu:update")
    public BananaResult update(@RequestBody BananaSystemMenuEntity menu) {
        //数据校验
        verifyForm(menu);

        sysMenuService.updateById(menu);

        return BananaResult.ok();
    }

    /**
     * 删除
     */
    @BananaSystemLog("删除菜单")
    @PostMapping("/delete/{menuId}")
    @RequiresPermissions("sys:menu:delete")
    public BananaResult delete(@PathVariable("menuId") long menuId) {
        if (menuId <= 31) {
            return BananaResult.error("系统菜单，不能删除");
        }

        //判断是否有子菜单或按钮
        List<BananaSystemMenuEntity> menuList = sysMenuService.queryListParentId(menuId);
        if (menuList.size() > 0) {
            return BananaResult.error("请先删除子菜单或按钮");
        }

        sysMenuService.delete(menuId);

        return BananaResult.ok();
    }

    /**
     * 验证参数是否正确
     */
    private void verifyForm(BananaSystemMenuEntity menu) {
        if (StringUtils.isBlank(menu.getName())) {
            throw new RRException("菜单名称不能为空");
        }

        if (menu.getParentId() == null) {
            throw new RRException("上级菜单不能为空");
        }

        //菜单
        if (menu.getType() == BananaConstant.MenuType.MENU.getValue()) {
            if (StringUtils.isBlank(menu.getUrl())) {
                throw new RRException("菜单URL不能为空");
            }
        }

        //上级菜单类型
        int parentType = BananaConstant.MenuType.CATALOG.getValue();
        if (menu.getParentId() != 0) {
            BananaSystemMenuEntity parentMenu = sysMenuService.selectById(menu.getParentId());
            parentType = parentMenu.getType();
        }

        //目录、菜单
        if (menu.getType() == BananaConstant.MenuType.CATALOG.getValue() ||
                menu.getType() == BananaConstant.MenuType.MENU.getValue()) {
            if (parentType != BananaConstant.MenuType.CATALOG.getValue()) {
                throw new RRException("上级菜单只能为目录类型");
            }
            return;
        }

        //按钮
        if (menu.getType() == BananaConstant.MenuType.BUTTON.getValue()) {
            if (parentType != BananaConstant.MenuType.MENU.getValue()) {
                throw new RRException("上级菜单只能为菜单类型");
            }
            return;
        }
    }
}
