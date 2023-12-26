package com.phonepe.restapi.models;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.phonepe.restapi.enums.DifficultyLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class Problem extends BaseEntity{
    private String problemId;
    private String description;
    private Set<String> tags;
    private int score;
    private DifficultyLevel difficultyLevel;
    private Solution.ProblemsData problemsData;

}
