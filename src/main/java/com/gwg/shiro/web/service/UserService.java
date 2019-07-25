package com.gwg.shiro.web.service;

import com.gwg.shiro.web.vo.UserVo;

/**
 * Created by
 */
public interface UserService{

    /**
     * 获取用户角色信息
     */
    public UserVo queryUserVoByUserCode(String userCode);

}
