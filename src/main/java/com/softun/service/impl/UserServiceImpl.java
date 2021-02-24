package com.softun.service.impl;

import com.softun.model.entity.RoleNameEnum;
import com.softun.model.entity.User;
import com.softun.model.service.UserServiceModel;
import com.softun.repository.UserRepository;
import com.softun.security.CurrentUser;
import com.softun.service.RoleService;
import com.softun.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final CurrentUser currentUser;
    private final RoleService roleService;

    public UserServiceImpl(UserRepository userRepository, ModelMapper modelMapper, CurrentUser currentUser, RoleService roleService) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
        this.currentUser = currentUser;
        this.roleService = roleService;
    }

    @Override
    public void registerUser(UserServiceModel userServiceModel) {
        User user = modelMapper.map(userServiceModel, User.class);
        if (userRepository.findAll().isEmpty()) {
            user.setRole(roleService.findRole(RoleNameEnum.ADMIN));
        } else {
            user.setRole(roleService.findRole(RoleNameEnum.USER));
        }

        userRepository.save(user);
    }

    @Override
    public UserServiceModel findUserByUserNameAndPassword(String username, String password) {
        return userRepository
                .findByUsernameAndPassword(username, password)
                .map(user -> modelMapper.map(user, UserServiceModel.class))
                .orElse(null);
    }

    @Override
    public void login(UserServiceModel userServiceModel) {
        currentUser
                .setId(userServiceModel.getId())
                .setUsername(userServiceModel.getUsername())
                .setRole(userServiceModel.getRole().getName());
    }

    @Override
    public void logout() {
        currentUser
                .setId(null)
                .setUsername(null)
                .setRole(null);
    }

    @Override
    public List<String> findAllUsernames() {
        return userRepository
                .findAllUsernames();
    }

    @Override
    public void changeRole(String username, RoleNameEnum roleNameEnum) {
        User user = userRepository
                .findByUsername(username)
                .orElse(null);


        if (user.getRole().getName() != roleNameEnum) {
            user.setRole(roleService.findRole(roleNameEnum));

            userRepository.save(user);
        }

    }

    @Override
    public User findById(Long id) {
        return userRepository
                .findById(id)
                .orElse(null);
    }
}
