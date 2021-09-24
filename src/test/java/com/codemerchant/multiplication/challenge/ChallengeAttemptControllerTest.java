package com.codemerchant.multiplication.challenge;

import com.codemerchant.multiplication.attempt.ChallengeAttempt;
import com.codemerchant.multiplication.challenge.controller.ChallengeAttemptController;
import com.codemerchant.multiplication.challenge.dto.ChallengeAttemptDTO;
import com.codemerchant.multiplication.challenge.service.ChallengeService;
import com.codemerchant.multiplication.user.entity.User;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.assertj.core.api.BDDAssertions.then;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@ExtendWith(SpringExtension.class)        // makes sure JUnit loads the extensions for Spring so we can use test context
@AutoConfigureJsonTesters                 // tells Spring to configure beans of type JacksonTester for some fields we declare.
@WebMvcTest(ChallengeAttemptController.class) //makes Spring treat this class as a presentation layer test aka controller
public class ChallengeAttemptControllerTest {
    @MockBean
    private ChallengeService challengeService;

    @Autowired
    private MockMvc mockMvc;  // allows us to simulate requests to the presentation layer when we make tests that don't load a real server

    @Autowired
    private JacksonTester<ChallengeAttemptDTO> jsonRequestAttempt;
    @Autowired
    private JacksonTester<ChallengeAttempt> jsonResultAttempt;

    @Test
    public void postValidResult() throws Exception {
        // given
        User user = new User(1L, "dave");
        long attemptId = 5L;
        ChallengeAttemptDTO attemptDTO = new ChallengeAttemptDTO(50, 70, "dave", 3500);
        ChallengeAttempt expectedResponse = new ChallengeAttempt(attemptId, user, 50, 70, 3500, true);

        given(challengeService
                .verifyAttempt(eq(attemptDTO)))
                .willReturn(expectedResponse);

        // when
        MockHttpServletResponse response = mockMvc.perform(
                post("/attempts")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonRequestAttempt.write(attemptDTO).getJson()))
                .andReturn()
                .getResponse();

        // then
        then(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        then(response.getContentAsString())
                .isEqualTo(jsonResultAttempt.write(expectedResponse)
                        .getJson());


    }

    @Test
    public void postInvalidResult() throws Exception {
        // given an input that has invalid input data
        ChallengeAttemptDTO challengeAttemptDTO = new ChallengeAttemptDTO(2000, -70, "dave", 1);

        // when
        MockHttpServletResponse response = mockMvc.perform(post("/attempts")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonRequestAttempt.write(challengeAttemptDTO).getJson()))
                .andReturn()
                .getResponse();

        // then
        then(response.getStatus())
                .isEqualTo(HttpStatus.BAD_REQUEST.value());
    }

}
