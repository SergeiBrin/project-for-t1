package com.example.projectfort1.service;

import com.example.projectfort1.model.Line;
import com.example.projectfort1.model.Sequence;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

@Transactional
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class LineServiceImplIT {
    private final EntityManager em;
    private final LineService lineService;

    @Test
    void lineMustBeSavedToTheDatabase() {
        String line = "Hello";
        lineService.getLetterSequences(line);

        TypedQuery<Line> query = em.createQuery("select l from Line as l where l.line = :line", Line.class);
        Line getLine = query.setParameter("line", line).getSingleResult();

        assertThat(getLine.getId(), equalTo(1L));
        assertThat(getLine.getLine(), equalTo(line));
        assertNull(getLine.getSequence());
    }

    @Test
    void sequenceMustBeSavedToTheDatabase() {
        String line = "Hello";
        String sequence = "\"l\" : 2, \"H\" : 1, \"e\" : 1, \"o\" : 1";
        lineService.getLetterSequences(line);

        TypedQuery<Sequence> query = em.createQuery("select s from Sequence as s where s.data = :data", Sequence.class);
        Sequence getSequence = query
                .setParameter("data", sequence)
                .getSingleResult();

        assertNotNull(getSequence.getId());
        assertThat(getSequence.getData(), equalTo(sequence));
        assertThat(getSequence.getLine().getLine(), equalTo(line));
    }
}
