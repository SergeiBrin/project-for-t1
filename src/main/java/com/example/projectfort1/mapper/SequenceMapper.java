package com.example.projectfort1.mapper;

import com.example.projectfort1.model.Line;
import com.example.projectfort1.model.Sequence;

public class SequenceMapper {
    public static Sequence buildSequence(String data, Line line) {
        Sequence buildSequence = new Sequence();
        buildSequence.setData(data);
        buildSequence.setLine(line);
        return buildSequence;
    }
}
