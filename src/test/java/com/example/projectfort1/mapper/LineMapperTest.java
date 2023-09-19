package com.example.projectfort1.mapper;

import com.example.projectfort1.model.Line;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.assertNull;

class LineMapperTest {

    @Test
    void shouldCreateLine() {
        String line = "Hello";
        Line createLine = LineMapper.buildLine(line);

        assertNull(createLine.getId());
        assertNull(createLine.getSequence());
        assertThat(createLine.getLine(), equalTo(line));
    }
}