package com.phonepe.restapi.service;

import java.util.Set;

public interface Recommendations<T> {
    Set<T> getRecommendations(String id);
}
