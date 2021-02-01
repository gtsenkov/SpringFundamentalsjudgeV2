package com.softun.service.impl;

import com.softun.model.entity.Role;
import com.softun.model.entity.RoleNameEnum;
import com.softun.repository.RoleRepository;
import com.softun.service.RoleService;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;

    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public void initRoles() {
        if (roleRepository.count() == 0) {
            Role admin = new Role(RoleNameEnum.ADMIN);
            Role user = new Role(RoleNameEnum.USER);

            roleRepository.save(admin);
            roleRepository.save(user);
        }

    }

    @Override
    public Role findRole(RoleNameEnum roleNameEnum) {
        return roleRepository.findByName(roleNameEnum)
                .orElse(null);
    }
}
