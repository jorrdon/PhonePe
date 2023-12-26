package com.phonepe.restapi.service;

import com.phonepe.restapi.models.Problem;
import com.phonepe.restapi.models.User;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ProblemRecommendationService implements RecommendationService {

    List<Recommendations<Problem>> recommendations;

    @Override
    public Set<Problem> getRecommendations(String problemId) {
        Set<Problem> res = new HashSet<>();
        recommendations.forEach(x -> res.addAll(x.getRecommendations(problemId)));
        return res;
    }
}
