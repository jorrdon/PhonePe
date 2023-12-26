package com.phonepe.restapi.controller;

import com.phonepe.restapi.Dto.Response.ProblemDto;
import com.phonepe.restapi.models.Problem;
import com.phonepe.restapi.service.ProblemService;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/problems")
@AllArgsConstructor
public class ProblemController {

    private final ProblemService problemService;

    @RequestMapping(path = "",
            method = {RequestMethod.POST},
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Problem> create(@RequestBody Problem problem) {
        Problem response = problemService.addProblem(problem);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProblemDto> get(@PathVariable("id") final String id) {
        ProblemDto response = problemService.getProblem(id);
        return ResponseEntity.ok(response);
    }

}
