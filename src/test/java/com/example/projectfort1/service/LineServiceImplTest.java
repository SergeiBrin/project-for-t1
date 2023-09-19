package com.example.projectfort1.service;

import com.example.projectfort1.mapper.LineMapper;
import com.example.projectfort1.model.Line;
import com.example.projectfort1.model.Sequence;
import com.example.projectfort1.repository.LineRepository;
import com.example.projectfort1.repository.SequenceRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.mockito.ArgumentMatchers.any;

@ExtendWith(MockitoExtension.class)
class LineServiceImplTest {
    @Mock
    private LineRepository mockLineRepository;
    @Mock
    private SequenceRepository mockSequenceRepository;
    @InjectMocks
    private LineServiceImpl lineService;

    @Test
    void shouldReturnCorrectSequence() {
        String line = "aaaaccbbbv";
        String result = lineService.getLetterSequences(line);
        assertThat(result, equalTo("\"a\" : 4, \"b\" : 3, \"c\" : 2, \"v\" : 1"));
    }

    @Test
    void shouldReturnCorrectSequenceWithoutSpaces() {
        String result = lineService.getLetterSequences("aa aa  cc b bb    v");
        assertThat(result, equalTo("\"a\" : 4, \"b\" : 3, \"c\" : 2, \"v\" : 1"));
    }

    @Test
    void shouldReturnEmptyString() {
        String result = lineService.getLetterSequences("  ");
        assertThat(result, equalTo(""));
    }

    @Test
    void shouldCallLineRepositorySaveWithCorrectArgument() {
        String line = "Hello";
        Line createLine = LineMapper.buildLine(line);
        lineService.getLetterSequences(line);

        Mockito.verify(mockLineRepository, Mockito.times(1)).save(createLine);
    }

    @Test
    void shouldCallSequenceRepositorySaveWithCorrectArgument() {
        String line = "aaaaccbbbv";
        String sequence = "\"a\" : 4, \"b\" : 3, \"c\" : 2, \"v\" : 1";

        Line createLine = new Line(1L, line, null);
        Sequence createSequence = new Sequence(null, createLine, sequence);

        Mockito
                .when(mockLineRepository.save(any(Line.class)))
                .thenReturn(createLine);

        lineService.getLetterSequences(line);

        Mockito.verify(mockSequenceRepository, Mockito.times(1)).save(createSequence);
    }
}