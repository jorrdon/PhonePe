package com.phonepe.restapi.service;

import com.phonepe.restapi.Utils.util;
import com.phonepe.restapi.exception.ValidationException;
import com.phonepe.restapi.models.Problem;
import com.phonepe.restapi.models.Solution;
import com.phonepe.restapi.models.User;
import io.micrometer.core.instrument.util.StringUtils;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class SolutionsService {

    private final RecommendationService recommendationService;

    public HashMap<String, Solution> solutionsData = new HashMap<>(); // key = solution_id
    public HashMap<String, List<Solution>> userProblemSolvedData = new HashMap<>();


    public Solution solve(Solution solution) {
        String sId = util.generateRandomUUID();
        solution.setSolutionId(sId);
        validateSolutionRequest(solution);
        solutionsData.put(solution.getSolutionId(), solution);
        addUsersToSolvedList(solution);
        addProblemsToSolvedList(solution);
        return solution;
    }

    private void validateSolutionRequest(Solution solution) {
        if (StringUtils.isBlank(solution.getUserId())) {
            throw new ValidationException("user_id is required.");
        }
        if (StringUtils.isBlank(solution.getProblemId())) {
            throw new ValidationException("problem_id is required.");
        }
        if (StringUtils.isBlank(solution.getStatus())) {
            throw new ValidationException("status is required.");
        }
    }

    public Solution getSolution(String solutionId) {
        return solutionsData.get(solutionId);
    }


    public List<Solution> getUserSolutions(String userId, String status) {
        List<Solution> solutionList = userProblemSolvedData.get(util.getUsersKey(userId));
        if (!Objects.equals(status, "")) {
            return solutionList.stream().filter(x -> x.getStatus().equalsIgnoreCase(status)).collect(Collectors.toList());
        }
        return solutionList;
    }

    private void addProblemsToSolvedList(Solution solution) {
        List<Solution> solutionList = userProblemSolvedData.get(util.getUsersKey(solution.getUserId()));
        if (solutionList == null) {
            solutionList = new ArrayList<>();
        }
        Solution.ProblemsData problemsData = solution.getProblemsData();
        problemsData.setProblemId(solution.getProblemId());
        problemsData.setRecommendedProblems(generateRecommendedProblemsList(solution.getProblemId()));
        problemsData.setNumberOfUsersAttempted(getNumberOfUsersAttempted(solution.getProblemId()));
        solution.setProblemsData(problemsData);
        solutionList.add(solution);
        userProblemSolvedData.put(util.getProblemKey(solution.getProblemId()), solutionList);
    }

    private Long getNumberOfUsersAttempted(String problemId) {
        return 0L;
    }

    private Set<Problem> generateRecommendedProblemsList(String problemId) {
        Set<Problem> res = new HashSet<>();
        List<Solution> solutionList = userProblemSolvedData.get(util.getProblemKey(problemId));
//        solutionList.stream()
//                .filter(x -> x.getProblemId().equalsIgnoreCase(problemId))
//                .collect(Collectors.groupingBy(Solution::getUserId, Collectors.counting()))
//                .size();
        return (Set<Problem>) recommendationService.getRecommendations(problemId);
    }

    private void addUsersToSolvedList(Solution solution) {
        List<Solution> solutionList = userProblemSolvedData.get(util.getUsersKey(solution.getUserId()));
        if (solutionList == null) {
            solutionList = new ArrayList<>();
        }
        solutionList.add(solution);
        userProblemSolvedData.put(util.getUsersKey(solution.getUserId()), solutionList);
    }


    public Solution.ProblemsData getProblemsData(String problemId) {
        Solution.ProblemsData.ProblemsDataBuilder builder = Solution.ProblemsData.builder();
        List<Solution> solutionList = userProblemSolvedData.get(util.getProblemKey(problemId));
        builder.numberOfUsersAttempted(numberOfPeopleAttempted(solutionList, problemId));
        builder.averageTimeTakenToSolve(getAverageTimeTaken(problemId, solutionList));
        return builder.build();
    }

    private Long numberOfPeopleAttempted(List<Solution> solutionList, String problemId) {
        return (long) solutionList.stream()
                .collect(Collectors.groupingBy(Solution::getUserId, Collectors.counting()))
                .size();
    }

    private Long getAverageTimeTaken(String problemId, List<Solution> solutionList) {
        if (solutionList.isEmpty()) {
            return 0L; // handle the case where the list is empty to avoid division by zero
        }
        long sum = solutionList.stream()
                .mapToLong(Solution::getTimeTakenToSolve)
                .sum();
        return sum / solutionList.size();
    }
}
