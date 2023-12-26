package com.phonepe.restapi.service;

import com.phonepe.restapi.Dto.Response.ProblemDto;
import com.phonepe.restapi.models.Problem;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Component
@AllArgsConstructor
public class TagBasedRecommendation<T> implements Recommendations<T> {

    private final ProblemService problemService;

    @Override
    public Set<T> getRecommendations(String id) {
        ProblemDto problem1 = problemService.getProblem(id);
        Set<Problem> problemByTag = problemService.getProblemByTag(problem1.getTags());
        return (Set<T>) problemByTag;
    }
}
