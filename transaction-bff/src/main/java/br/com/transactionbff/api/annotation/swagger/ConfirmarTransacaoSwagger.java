package br.com.transactionbff.api.annotation.swagger;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@Operation(description = "API para autorizar a transaçao financeira")
@ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Retorno OK da remoçao"),
        @ApiResponse(responseCode = "401", description = "Erro de autenticação dessa API"),
        @ApiResponse(responseCode = "403", description = "Erro de autorização dessa API"),
        @ApiResponse(responseCode = "404", description = "Recurso não encontrado")})
@Parameters(value = {@Parameter(name = "id", in = ParameterIn.PATH)})
public @interface ConfirmarTransacaoSwagger {
}
