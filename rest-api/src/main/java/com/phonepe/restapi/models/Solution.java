package com.phonepe.restapi.models;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@javax.persistence.Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class Solution extends BaseEntity {
    private String solutionId;
    private String userId;
    private String problemId;
    private String status; // solved, attempted , todo

    private Object solution;
    private ProblemsData ProblemsData;
    private String language;
    private Long timeTakenToSolve;


    @Builder(toBuilder = true)
    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class ProblemsData {
        private String problemId;
        private Set<Problem> recommendedProblems;
        private String similarProblems;
        private Long numberOfPeopleAttempted;
        private Long averageTimeTakenToSolve;

    }
}
