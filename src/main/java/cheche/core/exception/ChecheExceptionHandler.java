package cheche.core.exception;

import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import cheche.controller.vo.BaseResponse;

/**
 * Exception Handler
 * 
 * @author jieli
 */
@RestControllerAdvice
public class ChecheExceptionHandler {
    /** handle @Valid exception */
    @SuppressWarnings("rawtypes")
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public BaseResponse exceptionHandler(MethodArgumentNotValidException e) {
        BaseResponse response = new BaseResponse();
        response.fail(e.getBindingResult().getFieldError().getDefaultMessage());
        return response;
    }

    /** handle un-catch exception */
    @SuppressWarnings("rawtypes")
    @ExceptionHandler(Exception.class)
    public BaseResponse exceptionHandler(Exception e) {
        BaseResponse response = new BaseResponse();
        response.fail(e.getLocalizedMessage());
        return response;
    }
}
