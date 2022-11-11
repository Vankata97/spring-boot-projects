package codeacademy.loanrequest.exceptions;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ExceptionHandler extends ResponseEntityExceptionHandler
{

  @org.springframework.web.bind.annotation.ExceptionHandler({ConstraintViolationException.class})
  public ResponseEntity<Map<String, Object>> handleConstraintExceptions(ConstraintViolationException exception)
  {
    Map<String, Object> exceptedBody = new HashMap<>();
    for (ConstraintViolation<?> e : exception.getConstraintViolations()) {
      exceptedBody.put("message: ", e.getMessage());
    }
    return new ResponseEntity<>(exceptedBody, HttpStatus.BAD_REQUEST);
  }
@Override
  protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers,
      HttpStatus status, WebRequest request)
  {
    Map<String, String> validationErrors = new HashMap<>();
    List<ObjectError> objectErrorList = ex
        .getBindingResult()
        .getAllErrors();
    for (ObjectError objectError : objectErrorList) {
      validationErrors.put(((FieldError) objectError).getField(), objectError.getDefaultMessage());
    }
    return new ResponseEntity<>(validationErrors, HttpStatus.BAD_REQUEST);
  }

  @org.springframework.web.bind.annotation.ExceptionHandler(TeamOneException.class)
  public ResponseEntity<String> handlerTeamOneException(TeamOneException ex)
  {
    return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
  }

}
