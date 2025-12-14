package com.planilla_DAWI.cibertec.Service;

import com.planilla_DAWI.cibertec.Dto.JwtResponseDto;
import com.planilla_DAWI.cibertec.Dto.LoginRequestDto;
import com.planilla_DAWI.cibertec.Dto.RegisterRequestDto;
import com.planilla_DAWI.cibertec.Dto.RegisterResponseDto;

public interface AuthService {
    JwtResponseDto authenticateUser(LoginRequestDto loginRequest);
    RegisterResponseDto registerUser(RegisterRequestDto registerRequest);
}
