package com.softun.service;

import com.softun.model.entity.RoleNameEnum;
import com.softun.model.entity.User;
import com.softun.model.service.UserServiceModel;

import java.util.List;

public interface UserService {
    void registerUser(UserServiceModel userServiceModel);

    UserServiceModel findUserByUserNameAndPassword(String username, String password);

    void login(UserServiceModel userServiceModel);

    void logout();

    List<String> findAllUsernames();

    void changeRole(String username, RoleNameEnum roleNameEnum);

    User findById(Long id);
}
