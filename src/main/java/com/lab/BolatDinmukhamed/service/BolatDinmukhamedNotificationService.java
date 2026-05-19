package com.lab.BolatDinmukhamed.service;

import com.lab.BolatDinmukhamed.repository.BolatDinmukhamedGradeRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@Slf4j
@Service
@RequiredArgsConstructor
public class BolatDinmukhamedNotificationService {

    private final BolatDinmukhamedGradeRepository gradeRepository;

    // ===== Async 1: send welcome email =====
    @Async("taskExecutor")
    public void sendWelcomeEmail(String email, String username) {
        log.info("[ASYNC] Sending welcome email to {} on thread {}", email, Thread.currentThread().getName());
        try {
            Thread.sleep(3000);
            log.info("[ASYNC] Welcome email sent to {} for user {}", email, username);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    // ===== Async 2: enrollment notification =====
    @Async("taskExecutor")
    public void sendEnrollmentNotification(String email, String courseTitle) {
        log.info("[ASYNC] Sending enrollment notification to {} on thread {}", email, Thread.currentThread().getName());
        try {
            Thread.sleep(2000);
            log.info("[ASYNC] Enrollment notification sent: user={}, course={}", email, courseTitle);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    // ===== Async 3: GPA calculation with CompletableFuture =====
    @Async("taskExecutor")
    public CompletableFuture<Double> calculateGPA(Long userId) {
        log.info("[ASYNC] Calculating GPA for user {} on thread {}", userId, Thread.currentThread().getName());
        try {
            Thread.sleep(3000);

            List<Double> scores = gradeRepository.findByUserId(userId).stream()
                    .map(g -> g.getScore())
                    .toList();

            if (scores.isEmpty()) {
                log.info("[ASYNC] No grades found for user {}", userId);
                return CompletableFuture.completedFuture(0.0);
            }

            double average = scores.stream().mapToDouble(Double::doubleValue).average().orElse(0.0);
            double gpa = (average / 100.0) * 4.0;

            log.info("[ASYNC] GPA calculated for user {}: {}", userId, gpa);
            return CompletableFuture.completedFuture(gpa);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            return CompletableFuture.failedFuture(e);
        }
    }
}