package shop.mtcoding.bank.temp;

import org.junit.jupiter.api.Test;

import java.util.regex.Pattern;


public class RegexTest {
    @Test
    public void 한글만된다_test() throws Exception{
        String value ="가나";
        boolean result = Pattern.matches("^[가-핳]$", value);
        System.out.println("테스트" + result);
    }
    @Test
    public void user_username_test() throws Exception{
    //username , fullname 영문 숫자 20자
        String username ="ss ar";
        boolean result = Pattern.matches("^[a-zA-Z0-9]{2,20}$", username);
        System.out.println("테스트" +result);

    }
    @Test
    public void user_fullname_test() throws Exception{
        //username , fullname 영어 한글 한자리 -스무자
        String fullname ="쌀";
        boolean result = Pattern.matches("^[a-zA-Z가-힣]{1,20}$", fullname);
        System.out.println("테스트" +result);

    }
    @Test
    public void 이메일_test() throws Exception{
        String email = "ssar@nate.com";
        boolean result = Pattern.matches("^[a-zA-Z0-9]{2,6}@[a-zA-Z0-9]\\.{2,3}$",email);
        System.out.println("테스트" +result);
    }



}
