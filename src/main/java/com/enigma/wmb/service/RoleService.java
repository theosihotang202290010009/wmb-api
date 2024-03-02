package com.enigma.wmb.service;

import com.enigma.wmb.constant.UserRole;
import com.enigma.wmb.entity.Role;

public interface RoleService {
    Role getOrSave(UserRole role);
}
