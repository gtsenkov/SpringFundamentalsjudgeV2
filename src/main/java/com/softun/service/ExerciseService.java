package com.softun.service;

import com.softun.model.entity.Exercise;
import com.softun.model.service.ExerciseServiceModel;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

public interface ExerciseService {
    void addEx(ExerciseServiceModel exerciseServiceModel);

    List<String> findAllExNames();

    boolean checkIsLate(String exercise);

    Exercise findByName(String name);
}
