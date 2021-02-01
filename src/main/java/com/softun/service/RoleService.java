package com.softun.service;

import com.softun.model.entity.Role;
import com.softun.model.entity.RoleNameEnum;

public interface RoleService {
    void initRoles();

    Role findRole(RoleNameEnum roleNameEnum);

}
