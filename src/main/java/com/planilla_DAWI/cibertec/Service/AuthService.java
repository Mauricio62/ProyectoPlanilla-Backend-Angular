package com.planilla_DAWI.cibertec.Service;

import com.planilla_DAWI.cibertec.Dto.JwtResponseDto;
import com.planilla_DAWI.cibertec.Dto.LoginRequestDto;
import com.planilla_DAWI.cibertec.Dto.RegisterRequestDto;

public interface AuthService {
    JwtResponseDto authenticateUser(LoginRequestDto loginRequest);
    String registerUser(RegisterRequestDto registerRequest);
}
