package exception.advice;

import exception.controller.ApiController;
import exception.dto.Error;
import exception.dto.ErrorResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Path;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

// @ControllerAdvice : 여러 컨트롤러에 대해 전역적으로 ExceptionHandler를 적용해준다.

@RestControllerAdvice(basePackageClasses = ApiController.class)
// @RestControllerAdvice = @ResponseBody + @ControllerAdvice
// @RestControllerAdvice는 전역적으로 예외를 처리할 수 있는 어노테이션입니다.
public class ApiControllerAdvice {

    // @ExceptionHandler : 특정 Cotroller의 예외를 처리한다.
    @ExceptionHandler(value = Exception.class)
    public ResponseEntity exception(Exception e){
        System.out.println(e.getClass().getName()); // 어떤 클래스에서 에러가 발생했는지 출력.
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("");
    }

    // MethodArgumentNotValidException 에러 처리하는 메소드
    // MethodArgumentNotValidException :
    @ExceptionHandler(value = MethodArgumentNotValidException.class)    // 해당 패키지 하위클래스를 지정하여 해당되는 특정예외를 처리.
    public ResponseEntity methodArgumentNotValidException(MethodArgumentNotValidException e, HttpServletRequest httpServletRequest){
    // 에러결과를 매개변수로 사용 가능.

        // Error 타입의 리스트 생성
        List<Error> errorList = new ArrayList<>();

        // BindingResult 객체 생성
        BindingResult bindingResult = e.getBindingResult();

        // 람다식 forEach로 에러의 갯수만큼 반복하며 리스트에 삽입
        bindingResult.getAllErrors().forEach(error -> {
            FieldError field = (FieldError) error;

            String fieldName = field.getField();
            String message = field.getDefaultMessage();
            String value = field.getRejectedValue().toString();

            Error errorMessage = new Error();
            errorMessage.setField(fieldName);
            errorMessage.setMessage(message);
            errorMessage.setInvalidValue(value);

            errorList.add(errorMessage);
        });

        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setErrorList(errorList);
        errorResponse.setMessage("");
        errorResponse.setRequestUrl(httpServletRequest.getRequestURI());
        errorResponse.setStatusCode(HttpStatus.BAD_REQUEST.toString());
        errorResponse.setResultCode("FAIL");

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).   body(errorResponse);
    }

    // ConstraintViolationException 에러 처리하는 메소드
    @ExceptionHandler(value = ConstraintViolationException.class)
    public ResponseEntity constraintViolationException(ConstraintViolationException e, HttpServletRequest httpServletRequest){

        // Error 타입의 리스트 생성
        List<Error> errorList = new ArrayList<>();

        // getConstraintViolations() : 에러가 담겨있는 메소드 => forEach 를 통해서 결과를
        e.getConstraintViolations().forEach(error ->{
            Stream<Path.Node> stream = StreamSupport.stream(error.getPropertyPath().spliterator(), false);
            List<Path.Node> list = stream.collect(Collectors.toList());

            String field = list.get(list.size() -1).getName();
            String message = error.getMessage();
            String invalidValue = error.getInvalidValue().toString();

            Error errorMessage = new Error();
            errorMessage.setField(field);
            errorMessage.setMessage(message);
            errorMessage.setInvalidValue(invalidValue);

            errorList.add(errorMessage);

        });

        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setErrorList(errorList);
        errorResponse.setMessage("");
        errorResponse.setRequestUrl(httpServletRequest.getRequestURI());
        errorResponse.setStatusCode(HttpStatus.BAD_REQUEST.toString());
        errorResponse.setResultCode("FAIL");

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
    }

    // MissingServletRequestParameterException 에러 처리하는 메소드
    @ExceptionHandler(value = MissingServletRequestParameterException.class)
    public ResponseEntity missingServletRequestParameterException(MissingServletRequestParameterException e, HttpServletRequest httpServletRequest){

        List<Error> errorList = new ArrayList<>();

        String fieldName = e.getParameterName();
        String invalidValue = e.getMessage();

        Error errorMessage = new Error();
        errorMessage.setField(fieldName);
        errorMessage.setMessage(e.getMessage());


        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setErrorList(errorList);
        errorResponse.setMessage("");
        errorResponse.setRequestUrl(httpServletRequest.getRequestURI());
        errorResponse.setStatusCode(HttpStatus.BAD_REQUEST.toString());
        errorResponse.setResultCode("FAIL");

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
    }

}