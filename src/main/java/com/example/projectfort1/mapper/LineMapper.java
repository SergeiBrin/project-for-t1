package com.example.projectfort1.mapper;

import com.example.projectfort1.model.Line;

public class LineMapper {
    public static Line buildLine(String line) {
        Line buildLine = new Line();
        buildLine.setLine(line);
        return buildLine;
    }
}
