package com.softun.service;

import com.softun.model.service.UserServiceModel;

public interface UserService {
    void registerUser(UserServiceModel userServiceModel);

    UserServiceModel findUserByUserNameAndPassword(String username, String password);
}
