package shop.mtcoding.bank.config.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import shop.mtcoding.bank.config.auth.LoginUser;
import shop.mtcoding.bank.domain.user.User;
import shop.mtcoding.bank.domain.user.UserEnum;

import java.util.Date;
import java.util.Locale;

public class JwtProcess {
    private final Logger log = LoggerFactory.getLogger(getClass());
    //토큰생성에 책임
    //토큰안에는 암호화 된게 아니기때문에 아이디랑 롤만 넣는다 이메일 같은거 안넣는다.
    public static String create(LoginUser loginUser){
    String jwtToken = JWT.create()
            .withSubject("bank")
            .withExpiresAt(new Date(System.currentTimeMillis() + JwtVo.EXPIRATION_TIME)) // 토큰 만들어지고 7일ㄷ ㅟ까지 유효합니다.
            .withClaim("id", loginUser.getUser().getId())
            .withClaim("role",loginUser.getUser().getRole()+"")//어드민이면 어드민 커스터머면 커스터머 리턴 문자열로 캐스팅 해줘야함
            .sign(Algorithm.HMAC512(JwtVo.SECRET)); // jwt 암호화
        return JwtVo.TOKEN_PREFIX+jwtToken;
    }

    //토큰 검증에 책임(return 되느 로그인 유저를 객체를 강제로 시큐리티 세션에 직접 주입할 예정)
    public static LoginUser verify(String token){
        DecodedJWT decodedJWT = JWT.require(Algorithm.HMAC512(JwtVo.SECRET)).build().verify(token);
        //생성과 검증을 한군데서 받으니까 대칭키로 받아도됨 token을 넣어준다
        Long id = decodedJWT.getClaim("id").asLong();
        String role = decodedJWT.getClaim("role").asString();
        User user = User.builder().id(id).role(UserEnum.valueOf(role)).build();
        LoginUser loginUser = new LoginUser(user);
        return loginUser;

    }

}
