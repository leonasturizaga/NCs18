package com.Kosten.Api_Rest.service;

import com.Kosten.Api_Rest.dto.ExtendedBaseResponse;
import com.Kosten.Api_Rest.dto.user.*;

public interface AuthService {
    ExtendedBaseResponse<AuthResponseDto> login(LoginRequestDto request);

    ExtendedBaseResponse<AuthResponseDto> register(RegisterRequestDto request);

    ExtendedBaseResponse<String> generatePasswordResetToken(EmailDto email);

    void resetPassword(ResetPasswordRequest request);

}
