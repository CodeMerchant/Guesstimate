package com.codemerchant.multiplication.challenge.repository;

import com.codemerchant.multiplication.attempt.ChallengeAttempt;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ChallengeAttemptRepository extends JpaRepository<ChallengeAttempt, Long> {
    /**
     * @return the last 10 attempts for a given user identified by their alias.
     */
    List<ChallengeAttempt> findTop10ByUserAliasOrderByIdDesc(String userAlias);
}
