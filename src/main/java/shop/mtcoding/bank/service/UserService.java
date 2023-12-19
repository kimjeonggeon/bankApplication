package shop.mtcoding.bank.service;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import shop.mtcoding.bank.domain.user.User;
import shop.mtcoding.bank.domain.user.UserEnum;
import shop.mtcoding.bank.domain.user.UserRepository;
import shop.mtcoding.bank.dto.user.UserReqDto;
import shop.mtcoding.bank.dto.user.UserRespDto;
import shop.mtcoding.bank.handler.ex.CustomApiException;

import javax.transaction.Transactional;
import java.util.Optional;

@RequiredArgsConstructor
@Service
//서비스는 dto로 응답 받고  dto로 응답한다.
public class UserService {
    private final Logger log = LoggerFactory.getLogger(getClass());
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;
    @Transactional// 트랜젝션이 메서드 시작될 때 시작되고 종료될떄 함께 종료
    public UserRespDto.JoinRespDto 회원가입(UserReqDto.JoinReqDto joinReqDto){
        Optional<User> userOP= userRepository.findByUsername(joinReqDto.getUsername());
        //1. 동일 유저 네임 존재 검사
        if(userOP.isPresent()){
            throw new CustomApiException("동일한 username이 존재합니다.");
        }
        //2. 패스워드 인코딩 + 회원가입
        User userPS = userRepository.save(joinReqDto.toEntity(passwordEncoder));

        return new UserRespDto.JoinRespDto(userPS);
    }
    //3. responsedto응답




}
