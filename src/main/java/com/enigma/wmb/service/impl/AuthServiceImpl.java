package com.enigma.wmb.service.impl;

import com.enigma.wmb.constant.UserRole;
import com.enigma.wmb.dto.authentication.AuthRequest;
import com.enigma.wmb.dto.response.LoginResponse;
import com.enigma.wmb.dto.response.RegisterResponse;
import com.enigma.wmb.entity.Customer;
import com.enigma.wmb.entity.Role;
import com.enigma.wmb.entity.UserCredential;
import com.enigma.wmb.repository.UserCredentialRepository;
import com.enigma.wmb.service.AuthService;
import com.enigma.wmb.service.CustomerService;
import com.enigma.wmb.service.RoleService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final UserCredentialRepository userCredentialRepository;
    private final RoleService roleService;
    private final CustomerService customerService;
    private final PasswordEncoder passwordEncoder;

    @Transactional(rollbackOn = Exception.class)
    @Override
    public RegisterResponse register(AuthRequest request) {
        try {
            Role role = roleService.getOrSave(UserRole.ROLE_CUSTOMER);
            String passwordEncode = passwordEncoder.encode(request.getPassword());

            UserCredential account = UserCredential.builder()
                    .username(request.getUsername())
                    .password(passwordEncode)
                    .role(role)
                    .status(true)
                    .build();
            userCredentialRepository.saveAndFlush(account);

            Customer customer = Customer.builder()
                    .member(true)
                    .userCredential(account)
                    .build();
            customerService.create(customer);

            List<String> userRole = account.getAuthorities().stream().map(GrantedAuthority::getAuthority).toList();
            return RegisterResponse.builder()
                    .username(account.getUsername())
                    .role(userRole)
                    .build();
        }catch (DataIntegrityViolationException e){
            throw new DataIntegrityViolationException(e.getMessage());
        }
    }

    @Override
    public RegisterResponse registerAdmin(AuthRequest request) {
        return null;
    }

    @Override
    public LoginResponse login(AuthRequest request) {
        return null;
    }
}
