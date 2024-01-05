package shop.mtcoding.bank.config.jwt;

public interface JwtVo {
    //secret은 노출되면 안됨 클라우드 aws - 환경변수 파일에 있는것을 읽을수도 있고 숨기기
    //리플래시 토큰(자동으로 갱신해주는 것도 있음)

    public static final String SECRET = "메타코딩";//hs256 대칭키 사용
    public static final int EXPIRATION_TIME = 1000* 60 * 60 * 24 * 7 ; //일주일
    public static  final String TOKEN_PREFIX ="Bearer ";
    //프로토콜로 앞에 붙임
    public static final String HEADER = "Authorization";
}
