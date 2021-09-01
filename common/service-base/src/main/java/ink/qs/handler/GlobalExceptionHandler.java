package ink.qs.handler;

import ink.qs.utils.Re;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ResponseBody
    @ExceptionHandler(Exception.class)
    public Re globalExceptionHandler(Exception e) {
        return Re.error().message(e.getMessage());
    }
}
