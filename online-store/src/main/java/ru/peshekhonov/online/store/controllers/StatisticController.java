package ru.peshekhonov.online.store.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.peshekhonov.online.store.aop.Statistic;

import java.util.Map;

@RestController
@RequestMapping("/statistic")
@RequiredArgsConstructor
public class StatisticController {

    private final Statistic statistic;

    @GetMapping
    public Map<String, Long> getStatistic() {
        return statistic.get();
    }
}
