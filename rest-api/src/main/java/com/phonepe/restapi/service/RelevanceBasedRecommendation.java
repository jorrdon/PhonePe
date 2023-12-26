package com.phonepe.restapi.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Component
@AllArgsConstructor
public class RelevanceBasedRecommendation<T> implements Recommendations<T> {
    @Override
    public Set<T> getRecommendations(String id) {
        return new HashSet<>();
    }
}
