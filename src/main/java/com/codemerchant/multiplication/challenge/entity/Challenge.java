package com.codemerchant.multiplication.challenge.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * This class represents a Challenge to solve a Multiplication (a * b).
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Challenge {
    private int factorA;
    private int factorB;
}
