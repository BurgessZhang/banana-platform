package com.burgess.banana.system.mapper;

import com.burgess.banana.system.entity.BananaSysCaptcha;

public interface BananaSysCaptchaMapper {
    int deleteByPrimaryKey(String uuid);

    int insert(BananaSysCaptcha record);

    int insertSelective(BananaSysCaptcha record);

    BananaSysCaptcha selectByPrimaryKey(String uuid);

    int updateByPrimaryKeySelective(BananaSysCaptcha record);

    int updateByPrimaryKey(BananaSysCaptcha record);
}