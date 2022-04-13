package br.com.transactionsvc.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Getter
@Setter
@ToString
public class Conta implements Serializable {

    private static final long serialVersionUID = 1L;

    @NotNull(message = "Informar o código da Agência")
    private Long codigoAgencia;
    @NotNull(message = "Informar o código da conta")
    private Long codigoConta;
}
