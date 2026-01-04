package br.java.yvascs.userprofileservice.exception;

import java.time.LocalDateTime;
import java.util.List;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class ErrorResponse {
    
    private int status;
    private String message;
    private String errorCode;
    private String path;
    private String method;
    private LocalDateTime timestamp;
    private List<String> details;

}
