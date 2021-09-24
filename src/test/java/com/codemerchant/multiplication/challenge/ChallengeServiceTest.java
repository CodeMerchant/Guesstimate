package com.codemerchant.multiplication.challenge;

import com.codemerchant.multiplication.attempt.ChallengeAttempt;
import com.codemerchant.multiplication.challenge.dto.ChallengeAttemptDTO;
import com.codemerchant.multiplication.challenge.repository.ChallengeAttemptRepository;
import com.codemerchant.multiplication.challenge.service.ChallengeService;
import com.codemerchant.multiplication.challenge.service.ChallengeServiceImpl;
import com.codemerchant.multiplication.user.entity.User;
import com.codemerchant.multiplication.user.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.BDDAssertions.then;
import static org.mockito.AdditionalAnswers.returnsFirstArg;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class ChallengeServiceTest {
    private ChallengeService challengeService;

    @Mock
    private UserRepository userRepository;
    @Mock
    private ChallengeAttemptRepository challengeAttemptRepository;

    @BeforeEach
    public void setUp() {
        challengeService = new ChallengeServiceImpl(
                userRepository, challengeAttemptRepository
        );

    }

    @Test
    public void checkCorrectAttemptTest() {
        //given
        given(challengeAttemptRepository
                .save(any()))
                .will(returnsFirstArg());
        ChallengeAttemptDTO attemptDTO = new ChallengeAttemptDTO(50, 60, "bruno", 3000);

        //when
        ChallengeAttempt resultAttempt = challengeService.verifyAttempt(attemptDTO);

        //then
        then(resultAttempt.isCorrect()).isTrue();
        verify(userRepository).save(new User("bruno"));
        verify(challengeAttemptRepository).save(resultAttempt);
    }

    @Test
    public void checkWrongAttemptTest() {
        //given
        given(challengeAttemptRepository
                .save(any()))
                .will(returnsFirstArg());
        ChallengeAttemptDTO attemptDTO = new ChallengeAttemptDTO(50, 60, "bruno", 5000);

        //when
        ChallengeAttempt resultAttempt = challengeService.verifyAttempt(attemptDTO);

        //then
        then(resultAttempt.isCorrect()).isFalse();
        verify(userRepository).save(new User("bruno"));
        verify(challengeAttemptRepository).save(resultAttempt);
    }

    @Test
    public void checkExistingUserTest() {
        // given
        given(challengeAttemptRepository
                .save(any()))
                .will(returnsFirstArg());
        User existingUser = new User(1L, "bruno");
        given(userRepository.findByAlias("bruno"))
                .willReturn(Optional.of(existingUser));
        ChallengeAttemptDTO attemptDTO = new ChallengeAttemptDTO(50, 60, "bruno", 5000);

        // when
        ChallengeAttempt resultAttempt = challengeService.verifyAttempt(attemptDTO);

        // then
        then(resultAttempt.isCorrect()).isFalse();
        then(resultAttempt.getUser()).isEqualTo(existingUser);
        verify(userRepository, never()).save(any());
        verify(challengeAttemptRepository).save(resultAttempt);
    }

    @Test
    public void getUserAttemptsTest() {
        // given
        User user = new User("bruno");
        ChallengeAttempt challengeAttempt = new ChallengeAttempt(1L, user, 20, 20, 800, false);
        ChallengeAttempt challengeAttempt1 = new ChallengeAttempt(2L, user, 20, 20, 500, false);

        List<ChallengeAttempt> brunoAttempts = new ArrayList<>();
        brunoAttempts.add(challengeAttempt);
        brunoAttempts.add(challengeAttempt1);

        given(challengeAttemptRepository.findTop10ByUserAliasOrderByIdDesc("bruno"))
                .willReturn(brunoAttempts);

        // when

        List<ChallengeAttempt> latestAttemptsResult = challengeService.getStatsForUser("bruno");

        // then
        then(latestAttemptsResult).isEqualTo(brunoAttempts);


    }

}
