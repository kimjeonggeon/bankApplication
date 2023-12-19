package shop.mtcoding.bank.handler.ex;

import lombok.Getter;

import java.util.Map;
@Getter
public class CustomValidationException extends RuntimeException{
    public CustomValidationException(String message, Map<String, String> errorMap) {
        super(message);
        this.errorMap = errorMap;
    }

    private Map<String, String> errorMap;
}
