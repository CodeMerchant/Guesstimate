package com.codemerchant.multiplication.attempt;

import com.codemerchant.multiplication.user.entity.User;
import lombok.*;

import javax.persistence.*;

/**
 * Identifies the attempt from a {@link User} to solve a challenge.
 */
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ChallengeAttempt {

    @Id
    @GeneratedValue
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="USER_ID")
    private User user;
    private int factorA;
    private int factorB;
    private int resultAttempt;
    private boolean correct;

}
