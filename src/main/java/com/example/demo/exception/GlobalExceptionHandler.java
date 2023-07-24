package com.example.demo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
	
	@ExceptionHandler(UsernameAlreadyExistException.class)
    public ResponseEntity<ErrorResponse> handleUsernameAlreadyExistException(UsernameAlreadyExistException ex) {
        ErrorResponse errorResponse = new ErrorResponse(false, ex.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.ALREADY_REPORTED);
    }
	
	@ExceptionHandler(EmailAlreadyExistException.class)
    public ResponseEntity<ErrorResponse> handleEmailAlreadyExistException(EmailAlreadyExistException ex) {
        ErrorResponse errorResponse = new ErrorResponse(false, ex.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.ALREADY_REPORTED);
    }
	
	@ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleResourceNotFoundException(ResourceNotFoundException ex) {
        ErrorResponse errorResponse = new ErrorResponse(false, ex.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }
	
	@ExceptionHandler(QuestionNotValidError.class)
    public ResponseEntity<ErrorResponse> handleQuestionNotValidError(QuestionNotValidError ex) {
        ErrorResponse errorResponse = new ErrorResponse(false, ex.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }
	
	private static class ErrorResponse {
        private final boolean status;
        private final String message;

        public ErrorResponse(boolean status, String message) {
            this.status = status;
            this.message = message;
        }

        public boolean getStatus() {
            return status;
        }

        public String getMessage() {
            return message;
        }
    }
}
