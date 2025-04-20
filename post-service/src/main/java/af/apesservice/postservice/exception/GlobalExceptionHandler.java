package af.apesservice.postservice.exception;

import af.apesservice.postservice.utils.ApiResponse;
import af.apesservice.postservice.utils.ResponseUtil;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiResponse<Map<String, String>>> handleValidationExceptions(
            MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(ResponseUtil.error("Validation failed", errors));
    }

    @ExceptionHandler(PostalCodeNotFoundException.class)
    public ResponseEntity<ApiResponse<Object>> handlePostalCodeNotFoundException(PostalCodeNotFoundException ex) {
        ApiResponse<Object> errorResponse = ResponseUtil.error(ex.getMessage(), HttpStatus.NOT_FOUND.value());
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }
    
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ApiResponse<Object>> handleIllegalArgumentException(IllegalArgumentException ex) {
        ApiResponse<Object> errorResponse = ResponseUtil.error(ex.getMessage(), HttpStatus.BAD_REQUEST.value());
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }
    
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse<Object>> handleGenericException(Exception ex) {
        ApiResponse<Object> errorResponse = ResponseUtil.error("An unexpected error occurred: " + ex.getMessage(), 
                HttpStatus.INTERNAL_SERVER_ERROR.value());
        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<ApiResponse<Object>> handleDataIntegrityViolation(DataIntegrityViolationException ex) {
        ApiResponse<Object> errorResponse = ResponseUtil.error("A record with this unique identifier already exists",
                HttpStatus.CONFLICT.value());
        return new ResponseEntity<>(errorResponse, HttpStatus.CONFLICT);


        
    }


//    @ExceptionHandler(CityIdNotFoundExeption.class)
//    public ResponseEntity<ApiResponse<Object>> handleCityIdNotFoundException(CityIdNotFoundExeption ex) {
//        ApiResponse<Object> errorResponse = ResponseUtil.error(ex.getMessage(), HttpStatus.NOT_FOUND.value());
//        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
//    }

    @ExceptionHandler(jakarta.validation.ConstraintViolationException.class)
    public ResponseEntity<Object> handleConstraintViolationException(jakarta.validation.ConstraintViolationException ex) {
        List<String> errors = ex.getConstraintViolations()
                .stream()
                .map(violation -> {
                    String propertyPath = violation.getPropertyPath().toString();
                    String message = violation.getMessage();
                    return propertyPath + ": " + message;
                })
                .collect(Collectors.toList());

        ApiResponse<Object> apiResponse = new ApiResponse<>(
                "error",
                "Validation failed: " + String.join(", ", errors),
                HttpStatus.BAD_REQUEST.value()
        );

        return new ResponseEntity<>(apiResponse, HttpStatus.BAD_REQUEST);
    }
}