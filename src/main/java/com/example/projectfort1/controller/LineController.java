package com.example.projectfort1.controller;

import com.example.projectfort1.service.LineService;
import jakarta.validation.constraints.Size;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Validated
@RestController
@RequestMapping("/lines")
@RequiredArgsConstructor
@Slf4j
public class LineController {
    private final LineService service;

    @GetMapping
    public ResponseEntity<String> getSequence(@RequestParam @Size(min = 1, max = 10000) String line) {
        log.info("В LineController поступил запрос. Метод getSequence(), параметр={}", line);

        String sequence = service.getLetterSequences(line);
        log.info("Запрос в LineController выполнен успешно. Результат запроса={}", sequence);

        return new ResponseEntity<>(sequence, HttpStatus.OK);
    }
}
