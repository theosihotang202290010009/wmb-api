package com.enigma.wmb.service;

import com.enigma.wmb.dto.authentication.AuthRequest;
import com.enigma.wmb.dto.response.LoginResponse;
import com.enigma.wmb.dto.response.RegisterResponse;

public interface AuthService {
    RegisterResponse register(AuthRequest request);
    RegisterResponse registerAdmin(AuthRequest request);
    LoginResponse login(AuthRequest request);
}
