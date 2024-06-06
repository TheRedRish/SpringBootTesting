package com.dolphin.repositoryTesting.service;

import com.dolphin.repositoryTesting.model.User;
import com.dolphin.repositoryTesting.repository.ResultRepository;
import com.dolphin.repositoryTesting.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.dolphin.repositoryTesting.model.Result;

import java.util.Optional;

@Service
public class ResultService {

    @Autowired
    private ResultRepository resultRepository;

    @Autowired
    private UserRepository userRepository;

    public Result createResult(String userId, int time, int distance) {
        Optional<User> userOptional = userRepository.findById(userId);
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            Result result = new Result(user, time, distance);
            return resultRepository.save(result);
        } else {
            throw new RuntimeException("User not found");
        }
    }

    public Iterable<Result> getAllResults() {
        return resultRepository.findAll();
    }

    public Optional<Result> getResultById(Long id) {
        return resultRepository.findById(id);
    }

    public void updateResult(Result result) {
        Optional<Result> resultOptional = resultRepository.findById(result.getId());
        if (resultOptional.isPresent()) {
            Result resultToEdit = resultOptional.get();
            resultToEdit.setTime(result.getTime());
            resultToEdit.setDistance(result.getDistance());
            resultRepository.save(resultToEdit);
        } else {
            throw new RuntimeException("Result not found");
        }
    }

    public void deleteResult(Long id) {
        resultRepository.deleteById(id);
    }
}
