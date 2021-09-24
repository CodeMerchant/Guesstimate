package com.codemerchant.multiplication.challenge;

import com.codemerchant.multiplication.user.User;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

/**
 * Identifies the attempt from a {@link User} to solve a challenge.
 */
@ToString
@EqualsAndHashCode
@Getter
@AllArgsConstructor
public class ChallengeAttempt {
    private Long id;
    private User user;
    private int factorA;
    private int factorB;
    private int resultAttempt;
    private boolean correct;

}
