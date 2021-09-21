package com.codemerchant.multiplication.challenge.service;

import com.codemerchant.multiplication.challenge.ChallengeAttempt;
import com.codemerchant.multiplication.challenge.dto.ChallengeAttemptDTO;

public interface ChallengeService {
    /**
     * Verifies if an attempt coming from the presentation layer is correct or not.
     * *
     @return the resulting ChallengeAttempt object
     */

    ChallengeAttempt verifyAttempt(ChallengeAttemptDTO resultAttempt);
}
