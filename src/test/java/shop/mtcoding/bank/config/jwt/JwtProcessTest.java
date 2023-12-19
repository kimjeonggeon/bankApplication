package shop.mtcoding.bank.config.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.security.oauth2.resource.OAuth2ResourceServerProperties;
import shop.mtcoding.bank.config.auth.LoginUser;
import shop.mtcoding.bank.domain.user.User;
import shop.mtcoding.bank.domain.user.UserEnum;

import java.util.Date;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class JwtProcessTest {
    //토큰 만들어주는 테스트
    @Test
    public void create_test() throws Exception{
    //given
    //user객체를 만들어준다.
      User user =User.builder().id(1L).role(UserEnum.ADMIN).build();
      LoginUser loginUser = new LoginUser(user);

    //when
    String jwtToken = JwtProcess.create(loginUser);
        System.out.println("테스트 "+jwtToken);


    //then
        //token prefix 있는지 테스트
        assertTrue(jwtToken.startsWith(JwtVo.TOKEN_PREFIX));
    }
    @Test
    public void verify_test() throws Exception{
     //given
        String jwtToken ="eyJhbGciOiJIUzUxMiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiJiYW5rIiwiZXhwIjoxNzAzNTUyNjczLCJpZCI6MSwicm9sZSI6IkFETUlOIn0.9ivfWpfAinksi7uK4xW6bwA-QoxseAtzWodzQiwzILXi-Ur6C5-dXnapzgOxfb6nyQJO2cLFaK0PT4hNdxEqNA";

    //when
        LoginUser loginUser = JwtProcess.verify(jwtToken);
        System.out.println("테스트:" + loginUser.getUser().getId());
        System.out.println("테스트:" + loginUser.getUser().getRole().name());


     //then
        assertThat(loginUser.getUser().getId()).isEqualTo(1L);
        assertThat(loginUser.getUser().getRole()).isEqualTo(UserEnum.ADMIN);
    }
}
