package com.gwg.shiro.web.service.impl;

import com.gwg.shiro.web.config.jdbc.DataSource;
import com.gwg.shiro.web.config.jdbc.DataSourceNames;
import com.gwg.shiro.web.mapper.RoleMapper;
import com.gwg.shiro.web.mapper.UserMapper;
import com.gwg.shiro.web.mapper.UserRoleMapper;
import com.gwg.shiro.web.model.*;
import com.gwg.shiro.web.service.UserService;
import com.gwg.shiro.web.vo.UserVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 
 * @author gwg
 *
 */
@Component
public class UserServiceImpl implements UserService {

	private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

	@Autowired
    private UserMapper userMapper;

	@Autowired
    private UserRoleMapper userRoleMapper;

	@Autowired
    private RoleMapper roleMapper;

	@Autowired
    UserServiceImpl userService;


    @Override
    public UserVo queryUserVoByUserCode(String userCode) {

        List<User> userList = this.queryUserInfoByCode(userCode);
        if(CollectionUtils.isEmpty(userList)){
            return null;
        }
        User user = userList.get(0);
        UserVo userVo = new UserVo();
        userVo.setUserCode(user.getUserCode());
        userVo.setUsername(user.getUsername());
        List<UserRole> userRoleList = this.queryUserRoleInfoByUserCode(userCode);
        if(CollectionUtils.isEmpty(userRoleList)){
            return userVo;
        }
        List<String> roleCodeList = userRoleList.stream().map(i -> i.getRoleCode()).collect(Collectors.toList());
        List<Role> roleList = this.queryRoleInfoByCode(roleCodeList);
        userVo.setRoleList(roleList);
        return userVo;
    }

	public List<User> queryUserInfoByCode(String userCode){
        UserExample userExample = new UserExample();
        UserExample.Criteria criteria = userExample.createCriteria();
        criteria.andUserCodeEqualTo(userCode);
        criteria.andValidFlagEqualTo(true);
        return userMapper.selectByExample(userExample);
    }


    @DataSource(name = DataSourceNames.ROLE)
    public List<UserRole> queryUserRoleInfoByUserCode(String userCode){
        UserRoleExample example = new UserRoleExample();
        UserRoleExample.Criteria criteria = example.createCriteria();
        criteria.andUserCodeEqualTo(userCode);
        return userRoleMapper.selectByExample(example);
    }

    @DataSource(name = DataSourceNames.ROLE)
    public List<Role> queryRoleInfoByCode(List<String> roleCodeList){
        RoleExample example = new RoleExample();
        RoleExample.Criteria criteria = example.createCriteria();
        criteria.andRoleCodeIn(roleCodeList);
        criteria.andValidFlagEqualTo(true);
        return roleMapper.selectByExample(example);
    }




}
