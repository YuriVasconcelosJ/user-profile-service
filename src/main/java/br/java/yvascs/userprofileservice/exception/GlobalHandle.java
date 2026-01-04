package br.java.yvascs.userprofileservice.exception;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import br.java.yvascs.userprofileservice.exception.domain.EmailAlreadyExistsException;
import br.java.yvascs.userprofileservice.exception.domain.UserNotFoundException;
import jakarta.servlet.http.HttpServletRequest;

@RestControllerAdvice
public class GlobalHandle {

        @ExceptionHandler(MethodArgumentNotValidException.class)
        public ResponseEntity<ErrorResponse> handleMethodArgumentNotValid(
                        MethodArgumentNotValidException ex,
                        HttpServletRequest request) {

                List<String> details = ex.getBindingResult()
                                .getFieldErrors()
                                .stream()
                                .map(error -> error.getField() + ": " + error.getDefaultMessage())
                                .toList();

                ErrorResponse errorResponse = buildErrorResponse(
                                HttpStatus.BAD_REQUEST,
                                "Erro de validação nos campos da requisição",
                                "VALIDATION_ERROR",
                                request,
                                details);

                return ResponseEntity.badRequest().body(errorResponse);
        }

        // User não encontrado
        @ExceptionHandler(UserNotFoundException.class)
        public ResponseEntity<ErrorResponse> handleUserNotFound(
                        UserNotFoundException userNotFoundException,
                        HttpServletRequest httpServletRequest) {
                ErrorResponse errorResponse = buildErrorResponse(
                                HttpStatus.NOT_FOUND,
                                userNotFoundException.getMessage(),
                                userNotFoundException.getCode(),
                                httpServletRequest,
                                List.of());

                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
        }

        // Email já cadastrador
        @ExceptionHandler(EmailAlreadyExistsException.class)
        public ResponseEntity<ErrorResponse> handleEmailAlreadyExistsException(
                        EmailAlreadyExistsException emailAlreadyExistsException,
                        HttpServletRequest httpServletRequest) {
                ErrorResponse errorResponse = buildErrorResponse(
                                HttpStatus.CONFLICT,
                                emailAlreadyExistsException.getMessage(),
                                emailAlreadyExistsException.getCode(),
                                httpServletRequest,
                                List.of());
                return ResponseEntity.status(HttpStatus.CONFLICT).body(errorResponse);
        }

        // Fallback global:
        @ExceptionHandler(Exception.class)
        public ResponseEntity<ErrorResponse> handleGenericException(
                        Exception ex,
                        HttpServletRequest httpServletRequest) {

                ErrorResponse errorResponse = buildErrorResponse(
                                HttpStatus.INTERNAL_SERVER_ERROR,
                                "Erro interno do servidor",
                                "INTERNAL_SERVER_ERROR",
                                httpServletRequest,
                                List.of());

                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
        }

        /**
         * 
         * @param status
         * @param message
         * @param errorCode
         * @param request
         * @param details
         * @return
         */
        private ErrorResponse buildErrorResponse(
                        HttpStatus status,
                        String message,
                        String errorCode,
                        HttpServletRequest request,
                        List<String> details) {

                return ErrorResponse.builder()
                                .status(status.value())
                                .message(message)
                                .errorCode(errorCode)
                                .path(request.getRequestURI())
                                .method(request.getMethod())
                                .timestamp(LocalDateTime.now())
                                .details(details)
                                .build();
        }

}
