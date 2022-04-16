package br.com.transactionbff.api.exception;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CustomErrorResponse {
    private String errorCode;
    private String errorMsg;
    private String error;
    private int httpStatus;
    private String fileName;
    private List<FieldsError> fieldsErrors;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
    private LocalDateTime timestamp;

    public CustomErrorResponse(String errorCode, String errorMsg) {
        super();
        this.errorCode = errorCode;
        this.errorMsg = errorMsg;
    }

    public CustomErrorResponse(String errorCode, String errorMsg, String error) {
        super();
        this.errorCode = errorCode;
        this.errorMsg = errorMsg;
        this.error = error;
    }
}
