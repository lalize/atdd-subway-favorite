package wooteco.subway.web.member;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static wooteco.subway.service.member.MemberServiceTest.TEST_USER_EMAIL;
import static wooteco.subway.service.member.MemberServiceTest.TEST_USER_NAME;
import static wooteco.subway.service.member.MemberServiceTest.TEST_USER_PASSWORD;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import wooteco.subway.domain.member.Member;
import wooteco.subway.service.member.MemberService;

@SpringBootTest
@AutoConfigureMockMvc
public class MemberControllerTest {
    @MockBean
    protected MemberService memberService;

    @Autowired
    protected MockMvc mockMvc;

    @Test
    public void createMember() throws Exception {
        Member member = new Member(1L, TEST_USER_EMAIL, TEST_USER_NAME, TEST_USER_PASSWORD);
        given(memberService.createMember(any())).willReturn(member);

        String inputJson =
                "{\"email\":\"" + TEST_USER_EMAIL + "\"," + "\"name\":\"" + TEST_USER_NAME + "\","
                        + "\"password\":\"" + TEST_USER_PASSWORD + "\"}";

        mockMvc.perform(post("/members")
                .content(inputJson)
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andDo(print());
    }
}
