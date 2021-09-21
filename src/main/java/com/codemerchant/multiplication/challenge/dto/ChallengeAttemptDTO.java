package com.codemerchant.multiplication.challenge.dto;

import lombok.Value;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;

@Value
public class ChallengeAttemptDTO {
    @Min(1) @Max(99)    // range of values allowed
    int factorA, factorB;
    @NotBlank           // self explanatory
    String userAlias;
    @Positive(message = "Result cannot be negative value.Try again")           // Only positive guesses allowed since we know that we'll be using positive factors only. See factorA and B annotations
    int guess;
}
