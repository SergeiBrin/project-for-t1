package com.example.projectfort1.service;

import com.example.projectfort1.mapper.LineMapper;
import com.example.projectfort1.mapper.SequenceMapper;
import com.example.projectfort1.model.CharacterCount;
import com.example.projectfort1.model.Line;
import com.example.projectfort1.model.Sequence;
import com.example.projectfort1.repository.LineRepository;
import com.example.projectfort1.repository.SequenceRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class LineServiceImpl implements LineService {
    private final LineRepository lineRepository;
    private final SequenceRepository sequenceRepository;

    @Transactional
    @Override
    public String getLetterSequences(String line) {
        // Ищем линию символов в базе данных.
        // Если линия есть, то последовательность уже вычислена.
        // Возвращаем последовательность.
        Line findLine = findLine(line);
        if (findLine != null) {
            return findLine.getSequence().getData();
        }

        // Если линии символов нет, то вычисляем последовательность
        String result = getCharacterCount(line);

        // Добавляю line и sequence в базу данных, чтобы потом не высчитывать вхождения букв в строке,
        // если оно уже есть.
        Line createLine = postLine(line);
        postSequence(result, createLine);

        return result;
    }

    private String getCharacterCount(String line) {
        List<CharacterCount> characterCounts = new ArrayList<>();

        // Убираем пробелы, их подсчитывать не будем
        String checkLine = line.replace(" ", "");
        while (!checkLine.isBlank()) {
            Character ch = checkLine.charAt(0);
            int initialLength = checkLine.length();

            checkLine = checkLine.replace(String.valueOf(ch), "");
            int lengthWithoutChar = checkLine.length();

            int charCount = initialLength - lengthWithoutChar;
            characterCounts.add(new CharacterCount(ch, charCount));
        }

        // Сортируем список от большего к меньшему.
        // Тут бы я его и вернул уже клиенту, но тогда на выходе будут названия полей класса CharacterCount,
        // что не соответствует условию задачи.
        characterCounts.sort(((o1, o2) -> Integer.compare(o2.getCount(), o1.getCount())));

        // По условию задачи вывод у клиента должен быть по шаблону - "a" : 2, "b" : 3, "c" : 1 - и тд,
        // поэтому шаманю со строками.
        String stringResult = characterCounts
                .stream()
                .map(characterCount ->
                        String.format("\"%c\" : %d", characterCount.getCh(), characterCount.getCount()))
                .collect(Collectors.joining(", "));

        log.info("");

        return stringResult;
    }

    // Метод ищет линию символов в базе данных
    private Line findLine(String line) {
        return lineRepository.findByLineContaining(line);
    }

    @Transactional
    private Line postLine(String line) {
        Line buildLine = LineMapper.buildLine(line);
        Line createLine = lineRepository.save(buildLine);
        log.info("");
        return createLine;
    }

    @Transactional
    private Sequence postSequence(String data, Line line) {
        Sequence buildSequence = SequenceMapper.buildSequence(data, line);
        Sequence createSequence = sequenceRepository.save(buildSequence);
        log.info("");
        return createSequence;
    }
}
