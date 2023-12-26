package com.phonepe.restapi.service;

import com.phonepe.restapi.Dto.Response.ProblemDto;
import com.phonepe.restapi.models.Problem;
import com.phonepe.restapi.models.Solution;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

@Service
@AllArgsConstructor
public class ProblemService {

    private final SolutionsService solutionsService;

    public HashMap<String, Problem> problemsData = new HashMap<>();

    public Problem addProblem(Problem problem) {
        problemsData.put(problem.getProblemId(), problem);
        return problem;
    }

    public ProblemDto getProblem(String problemId) {
        Problem problem = problemsData.get(problemId);
        Solution.ProblemsData problemsData = solutionsService.getProblemsData(problemId);
        return ProblemDto.builder()
                .problemId(problem.getProblemId())
                .description(problem.getDescription())
                .score(problem.getScore())
                .tags(problem.getTags())
                .difficultyLevel(problem.getDifficultyLevel())
                .problemsData(problemsData)
                .build();
    }

    public Set<Problem> getProblemByTag(Set<String> tags) {
        // TODO this
        Problem problem = problemsData.get(tags);

        Set<Problem> problemsSet = new HashSet<>();
        problemsSet.add(problem);
        return problemsSet;
    }
}
