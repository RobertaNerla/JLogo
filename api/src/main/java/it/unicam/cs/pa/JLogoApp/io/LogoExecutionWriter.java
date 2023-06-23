package it.unicam.cs.pa.JLogoApp.io;

import it.unicam.cs.pa.JLogoApp.model.Coordinate;
import it.unicam.cs.pa.JLogoApp.model.Figure;
import it.unicam.cs.pa.JLogoApp.model.Line;
import it.unicam.cs.pa.JLogoApp.model.Worksheet;

import java.util.stream.Collectors;

public class LogoExecutionWriter<C extends Coordinate<C>, L extends Line<C>> implements ExecutionWriter<C,L> {

    @Override
    public String stringOf(Worksheet<C,L> worksheet) {
        return worksheet.stringOf() +
                worksheet.getLines().stream().map(Line::stringOf).collect(Collectors.joining("\n")) +
                worksheet.getFigures().stream().map(Figure::stringOf).collect(Collectors.joining("\n"));


    }
}
