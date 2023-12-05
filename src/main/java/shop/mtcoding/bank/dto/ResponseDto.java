package shop.mtcoding.bank.dto;


import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class ResponseDto<T> {
    //final 이유
    //제너릭인이유 바디로 들어올수도 있고 json으로 들어올수도있음
    private final Integer code;
    private final String msg;
    private final T data;
}
