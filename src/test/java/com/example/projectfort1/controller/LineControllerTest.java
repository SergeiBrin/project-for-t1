package com.example.projectfort1.controller;

import com.example.projectfort1.service.LineService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.nio.charset.StandardCharsets;
import java.util.Random;

import static org.mockito.ArgumentMatchers.anyString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;



@WebMvcTest(controllers = LineController.class)
class LineControllerTest {
    @Autowired
    private ObjectMapper objectMapper;
    @MockBean
    private LineService lineService;
    @Autowired
    private MockMvc mvc;
    private String data = "\"l\" : 2, \"H\" : 1, \"e\" : 1, \"o\" : 1";

    @Test
    void shouldGetSequence() throws Exception {
        String line = "Hello";

        Mockito.when(lineService.getLetterSequences(anyString())).thenReturn(data);

        mvc.perform(get("/lines")
                        .param("line", line)
                        .characterEncoding(StandardCharsets.UTF_8)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string("\"l\" : 2, \"H\" : 1, \"e\" : 1, \"o\" : 1"));
    }

    @Test
    void shouldReturnBadRequestExceptionIfLineIsLessThan1Characters() throws Exception {
        String emptyLine = "";

        mvc.perform(get("/lines")
                        .param("line", emptyLine)
                        .characterEncoding(StandardCharsets.UTF_8)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    void shouldReturnBadRequestExceptionIfLineIsMoreThan1000Characters() throws Exception {
        Random random = new Random();
        StringBuilder stringBuilder = new StringBuilder(10001);
        for (int i = 0; i < 10001; i++) {
            // Генерирую случайный символ из набора a-z
            char randomChar = (char) ('a' + random.nextInt(26));
            stringBuilder.append(randomChar);
        }
        String lineOf1001Char = stringBuilder.toString();

        mvc.perform(get("/lines")
                        .param("line", lineOf1001Char)
                        .characterEncoding(StandardCharsets.UTF_8)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }
}