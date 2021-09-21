package com.codemerchant.multiplication.challenge.service;

import com.codemerchant.multiplication.challenge.ChallengeAttempt;
import com.codemerchant.multiplication.challenge.dto.ChallengeAttemptDTO;
import com.codemerchant.multiplication.user.User;
import org.springframework.stereotype.Service;

@Service
public class ChallengeServiceImpl implements ChallengeService {


    @Override
    public ChallengeAttempt verifyAttempt(ChallengeAttemptDTO attemptDTO) {
        // Checking if the attempt is correct
        boolean isCorrect = attemptDTO.getGuess() == attemptDTO.getFactorA() * attemptDTO.getFactorB();

        User user = new User(null, attemptDTO.getUserAlias());

        ChallengeAttempt challengeAttempt = new ChallengeAttempt(null,
                user, attemptDTO.getFactorA(), attemptDTO.getFactorB(), attemptDTO.getGuess(), isCorrect);

        return challengeAttempt;
    }
}
