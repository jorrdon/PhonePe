package com.phonepe.restapi.controller;

import com.phonepe.restapi.models.Solution;
import com.phonepe.restapi.service.SolutionsService;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/solutions")
@AllArgsConstructor
public class SolutionController {

    private final SolutionsService solutionsService;

    @RequestMapping(path = "/solve",
            method = {RequestMethod.POST},
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Solution> create(@RequestBody Solution solution) {
        Solution response = solutionsService.solve(solution);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{solutionId}")
    public ResponseEntity<Solution> getSolution(@PathVariable("solutionId") final String solutionId) {
        Solution response = solutionsService.getSolution(solutionId);
        return ResponseEntity.ok(response);
    }

    // Fetched problems solved for a user
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Solution>> get(@PathVariable("userId") final String userId,
                                              @RequestParam(value = "status", defaultValue = "") final String status // status of the solutions
    ) {
        List<Solution> response = solutionsService.getUserSolutions(userId, status);
        return ResponseEntity.ok(response);
    }

}
