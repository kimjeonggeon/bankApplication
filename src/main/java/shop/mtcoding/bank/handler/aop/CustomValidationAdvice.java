package shop.mtcoding.bank.handler.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import shop.mtcoding.bank.dto.ResponseDto;
import shop.mtcoding.bank.handler.ex.CustomValidationException;

import java.util.HashMap;
import java.util.Map;

@Component
@Aspect
public class CustomValidationAdvice {
    //메모리에 등록은 시켜야하기때문에 컨트롤러도 아니고 다른것도 아니기때문에 component로
    //get delete 말고  put post에는 body데이터가 있음
    @Pointcut("@annotation(org.springframework.web.bind.annotation.PostMapping)")
    public void postMapping(){
    }
    @Pointcut("@annotation(org.springframework.web.bind.annotation.PutMapping)")
    public void putMapping(){
    }
    @Around("postMapping()||putMapping()") // joinpoint 전후 제어
    public Object validationAdvice(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        Object[] args = proceedingJoinPoint.getArgs(); // joinpoint 매개변수
        for(Object arg : args){
            if(arg instanceof BindingResult){
                ///arg에 bindingresul가 있다면 바인딩 리절트로 바꿔준다
                BindingResult bindingResult = (BindingResult) arg;
                if(bindingResult.hasErrors()){
                    Map<String, String> errorMap = new HashMap<>();
                    for(FieldError error : bindingResult.getFieldErrors()){
                        errorMap.put(error.getField() , error.getDefaultMessage());
                    }
                    throw new CustomValidationException("유효성 검사 실패", errorMap);
                }
            }
        }
        return proceedingJoinPoint.proceed();
    }



}
