package com.phonepe.restapi.Dto.Response;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.phonepe.restapi.enums.DifficultyLevel;
import com.phonepe.restapi.models.BaseEntity;
import com.phonepe.restapi.models.Solution;
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
public class ProblemDto extends BaseEntity {
    private String problemId;
    private String description;
    private Set<String> tags;
    private int score;
    private DifficultyLevel difficultyLevel;
    private Solution.ProblemsData problemsData;

}
