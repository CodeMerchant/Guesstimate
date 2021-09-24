package com.codemerchant.multiplication.challenge.controller;

import com.codemerchant.multiplication.attempt.ChallengeAttempt;
import com.codemerchant.multiplication.challenge.dto.ChallengeAttemptDTO;
import com.codemerchant.multiplication.challenge.service.ChallengeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/attempts")
public class ChallengeAttemptController {
    private final ChallengeService challengeService;

    @PostMapping
    ResponseEntity<ChallengeAttempt> postResult(@RequestBody @Valid ChallengeAttemptDTO challengeAttemptDTO) {
        return ResponseEntity.ok(challengeService.verifyAttempt(challengeAttemptDTO));
    }

    @GetMapping
    ResponseEntity<List<ChallengeAttempt>> getStatistics(@RequestParam("alias") String alias) {
        return ResponseEntity.ok(challengeService.getStatsForUser(alias));
    }

}
