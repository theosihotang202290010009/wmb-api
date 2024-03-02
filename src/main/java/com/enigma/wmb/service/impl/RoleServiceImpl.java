package com.enigma.wmb.service.impl;

import com.enigma.wmb.constant.UserRole;
import com.enigma.wmb.entity.Role;
import com.enigma.wmb.repository.RoleRepository;
import com.enigma.wmb.service.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {
    private final RoleRepository roleRepository;
    @Override
    public Role getOrSave(UserRole role) {
        return roleRepository.findByRole(role)
                .orElseGet(()->roleRepository.saveAndFlush(Role.builder() //jika role tidak ada pada databbase maka buat role, jika tidak langsung di kerjakan oleh ElseGet.
                        .role(role)
                        .build())) ;
    }
}
