package com.example.projectfort1.mapper;

import com.example.projectfort1.model.Line;
import com.example.projectfort1.model.Sequence;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.assertNull;

class SequenceMapperTest {

    @Test
    void shouldCreateSequence() {
        Line line = new Line(1L, "He", null);
        Sequence sequence = SequenceMapper.buildSequence("\"H\" : 1, \"e\" : 1", line);

        assertNull(sequence.getId());
        assertThat(sequence.getData(), equalTo("\"H\" : 1, \"e\" : 1"));
        assertThat(sequence.getLine(), equalTo(line));
    }
}