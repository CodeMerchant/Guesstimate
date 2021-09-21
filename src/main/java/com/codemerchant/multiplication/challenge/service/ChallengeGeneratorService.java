package com.codemerchant.multiplication.challenge.service;

import com.codemerchant.multiplication.challenge.entity.Challenge;

public interface ChallengeGeneratorService {
    /**
     * @return a randomly-generated challenge with factors between 11 and 99
     */

    Challenge randomChallenge();
}
