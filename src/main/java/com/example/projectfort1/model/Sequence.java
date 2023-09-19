package com.example.projectfort1.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import java.util.Objects;

@Entity
@Table(name = "sequences")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Slf4j
public class Sequence {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        Sequence sequence = (Sequence) object;
        return Objects.equals(line, sequence.line) && Objects.equals(data, sequence.data);
    }

    @Override
    public int hashCode() {
        return Objects.hash(line, data);
    }

    @OneToOne
    @JoinColumn(name = "line_id", referencedColumnName = "id")
    private Line line;

    private String data;
}
