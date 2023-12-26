package com.phonepe.restapi.service;

import com.phonepe.restapi.models.Problem;
import com.phonepe.restapi.models.Solution;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
@AllArgsConstructor
public class ProblemService {

    private final SolutionsService solutionsService;

    public HashMap<String, Problem> problemsData = new HashMap<>();

    public Problem addProblem(Problem problem) {
        problemsData.put(problem.getProblemId(), problem);
        return problem;
    }

    public Problem getProblem(String problemId) {
        Problem problem = problemsData.get(problemId);
        Solution.ProblemsData problemsData = solutionsService.getProblemsData(problemId);
        problem.setProblemsData(problemsData);
        return problem;
    }
}
