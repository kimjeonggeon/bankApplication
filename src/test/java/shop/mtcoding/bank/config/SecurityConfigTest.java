package shop.mtcoding.bank.config;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@AutoConfigureMockMvc // 가짜환경에 mockmvc가 등록
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
public class SecurityConfigTest {
    //가짜환경에 di함
    @Autowired
    private MockMvc mvc;


    @Test
    public void authentication_test() throws  Exception{
        //given


        //when
        /*ResultActions resultActions = mvc.perform((RequestBuilder) mvc.perform( MockMvcRequestBuilders
                        .get("/api/s/hello", 1)));*/
        ResultActions resultActions = mvc.perform(MockMvcRequestBuilders
                .get("/api/s/hello"));
        String responseBody = resultActions.andReturn().getResponse().getContentAsString();
        int httpStatusCode = resultActions.andReturn().getResponse().getStatus();
        System.out.println("테스트 : "+responseBody);
        assertThat(httpStatusCode).isEqualTo(401);
        //then
    }

    @Test
    public void authorization_test() throws  Exception{
        ResultActions resultActions = mvc.perform(MockMvcRequestBuilders
                .get("/api/admin/hello"));
        String responseBody = resultActions.andReturn().getResponse().getContentAsString();
        int httpStatusCode = resultActions.andReturn().getResponse().getStatus();
        System.out.println("테스트 : "+responseBody);
    }
}
