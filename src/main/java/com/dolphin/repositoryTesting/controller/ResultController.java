package com.dolphin.repositoryTesting.controller;

import com.dolphin.repositoryTesting.model.Result;
import com.dolphin.repositoryTesting.service.ResultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping("/results")
public class ResultController {

    @Autowired
    private ResultService resultService;

    @GetMapping("/create")
    public String create() {
        return "result/createResult";
    }

    @PostMapping("/createNew")
    public String createResult(@RequestParam String userId, @RequestParam int time, @RequestParam int distance) {
        resultService.createResult(userId, time, distance);
        return "redirect:/";
    }

//    @PutMapping("/updateResult/{id}")
//    public String updateResult(@PathVariable("id") String id, @RequestParam int time, @RequestParam int distance, Model model) {
//        model.addAttribute("user", resultService.getResultById(Long.valueOf(id)));
//        if (resultOptional.isPresent()) {
//            Result result = resultOptional.get();
//            result.setTime(time);
//            result.setDistance(distance);
//            resultRepository.save(result);
//            return "redirect:/viewUser/" + result.getUser().getId();
//        } else {
//            throw new RuntimeException("Result not found");
//        }
//    }

    @GetMapping
    public Iterable<Result> getAllResults() {
        return resultService.getAllResults();
    }

    @GetMapping("/{id}")
    public Result getResultById(@PathVariable Long id) {
        return resultService.getResultById(id).orElseThrow(() -> new RuntimeException("Result not found"));
    }

    @DeleteMapping("/{id}")
    public void deleteResult(@PathVariable Long id) {
        resultService.deleteResult(id);
    }
}
