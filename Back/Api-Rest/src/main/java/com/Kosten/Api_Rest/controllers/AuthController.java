package com.Kosten.Api_Rest.controllers;

import com.Kosten.Api_Rest.dto.BaseResponse;
import com.Kosten.Api_Rest.dto.ExtendedBaseResponse;
import com.Kosten.Api_Rest.dto.user.*;
import com.Kosten.Api_Rest.service.AuthService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Autenticación", description = "Gestionar todos los puntos finales relacionados con la autenticación de usuarios.")
@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @Operation(summary = "Iniciar sesión",
            description = "Autentica a un usuario con sus credenciales y devuelve un token de autenticación.")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Inicio de sesión exitoso.",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = ExtendedBaseResponse.class))
                    }),
            @ApiResponse(responseCode = "400", description = "Credenciales inválidas proporcionadas.", content = {@Content}),
            @ApiResponse(responseCode = "401", description = "No autorizado (credenciales incorrectas o expiradas).", content = {@Content}),
            @ApiResponse(responseCode = "500", description = "Error del servidor.", content = {@Content})
    })
    @PostMapping(value = "login")
    public ResponseEntity<ExtendedBaseResponse<AuthResponseDto>> login(@Valid @RequestBody LoginRequestDto request) {
        ExtendedBaseResponse<AuthResponseDto> authResponse = authService.login(request);
        return ResponseEntity.ok(authResponse);
    }

    @Operation(summary = "Registrar un nuevo usuario",
            description = "Registra un nuevo usuario en el sistema con los detalles proporcionados.")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Registro exitoso.",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = ExtendedBaseResponse.class))
                    }),
            @ApiResponse(responseCode = "400", description = "El usuario ya existe o entrada no válida.", content = {@Content}),
            @ApiResponse(responseCode = "500", description = "Error del servidor.", content = {@Content})
    })
    @PostMapping(value = "register")
    public ResponseEntity<ExtendedBaseResponse<AuthResponseDto>> register(@Valid @RequestBody RegisterRequestDto request) {
        ExtendedBaseResponse<AuthResponseDto> authResponse = authService.register(request);
        return ResponseEntity.ok(authResponse);
    }

    /**
     * Endpoint para generar un token de reseteo de contraseña.
     * El token se enviará al correo electrónico del usuario.
     *
     * @param email Información del email del usuario
     * @return Respuesta con el token de reseteo de contraseña
     */
    @Operation(summary = "Generar token de reseteo de contraseña",
            description = "Genera un token para el reseteo de contraseña y lo envía al email del usuario.")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Token de reseteo de contraseña generado exitosamente.",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = ExtendedBaseResponse.class))
                    }),
            @ApiResponse(responseCode = "400", description = "Email inválido o no registrado.", content = {@Content}),
            @ApiResponse(responseCode = "404", description = "Usuario no encontrado con ese email.", content = {@Content}),
            @ApiResponse(responseCode = "500", description = "Error del servidor.", content = {@Content})
    })
    @PostMapping("/generate-reset-token")
    public ResponseEntity<ExtendedBaseResponse<String>> generateResetToken(@Valid @RequestBody EmailDto email) {
        var response = authService.generatePasswordResetToken(email);
        return ResponseEntity.ok(response);
    }

    /**
     * Endpoint para restablecer la contraseña de un usuario.
     * Requiere el token de reseteo y la nueva contraseña.
     *
     * @param request Información del token de reseteo y la nueva contraseña
     * @return Respuesta indicando que la contraseña fue restablecida con éxito
     */
    @Operation(summary = "Restablecer contraseña",
            description = "Restablece la contraseña de un usuario utilizando un token de reseteo y una nueva contraseña.")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Contraseña restablecida exitosamente.",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = BaseResponse.class))
                    }),
            @ApiResponse(responseCode = "400", description = "Token inválido o expirado.", content = {@Content}),
            @ApiResponse(responseCode = "404", description = "Usuario no encontrado o token no válido.", content = {@Content}),
            @ApiResponse(responseCode = "500", description = "Error del servidor.", content = {@Content})
    })
    @PostMapping("/reset-password")
    public ResponseEntity<BaseResponse> resetPassword(@RequestBody ResetPasswordRequest request) {
        authService.resetPassword(request);
        return ResponseEntity.ok(BaseResponse.ok("Contraseña restablecida exitosamente."));
    }
}





