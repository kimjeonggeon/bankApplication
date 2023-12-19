package shop.mtcoding.bank.web;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import shop.mtcoding.bank.dto.ResponseDto;
import shop.mtcoding.bank.dto.user.UserReqDto;
import shop.mtcoding.bank.dto.user.UserRespDto;
import shop.mtcoding.bank.service.UserService;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping("/join")
    public ResponseEntity<?> join(@RequestBody @Valid UserReqDto.JoinReqDto joinReqDto, BindingResult bindingResult) {

        //null값 처리

        //회원가입 시에는 인증이 필요가없어서 주소에 /s/가 들어갈 필요가 없음
        //스프링에서는 username =ssar&~~이런 형식이라 제이슨으로 받으려면 requestbody로
        //회우너가입 req으로 요청을하면 respdto에 받아서 응답 성공은 201
        UserRespDto.JoinRespDto joinRespDto = userService.회원가입(joinReqDto);
        return new ResponseEntity<>(new ResponseDto<>(1, "회원가입성공", joinRespDto), HttpStatus.CREATED);
        }
    }

