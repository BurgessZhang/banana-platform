package com.burgess.banana.config.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import org.hibernate.validator.constraints.NotBlank;

import java.io.Serializable;

/**
 * @author tom.zhang
 * @project banana-platform
 * @package com.burgess.banana.config.entity
 * @file BananaSystemConfigEntity.java
 * @time 2018-05-24 11:27
 * @desc 系统配置Entity
 */
@TableName("sys_config")
public class BananaSystemConfigEntity implements Serializable {

    @TableId
    private Long id;
    @NotBlank(message = "参数名不能为空")
    private String key;
    @NotBlank(message = "参数值不能为空")
    private String value;
    private String remark;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
