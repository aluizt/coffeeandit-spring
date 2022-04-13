package br.com.transactionbff.api.annotation.swagger;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@Operation(description = "API para criar uma transação financeira")
@ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Retorno OK com a transação criada."),
        @ApiResponse(responseCode = "401", description = "Erro de autenticação dessa API"),
        @ApiResponse(responseCode = "403", description = "Erro de autorização dessa API"),
        @ApiResponse(responseCode = "404", description = "Recurso não encontrado")
})
public @interface EnviarTransacaoSwagger {
}
