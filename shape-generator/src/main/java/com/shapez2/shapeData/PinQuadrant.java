package com.shapez2.shapeData;

import com.shapez2.shapeData.enums.QuadrantPosition;
import com.shapez2.shapeData.enums.ShapeType;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public record PinQuadrant(ShapeType type, QuadrantPosition position) implements Quadrant {
    public PinQuadrant {
        Objects.requireNonNull(type, "Type cannot be null");
        Objects.requireNonNull(position, "Position cannot be null");
    }

    @Override
    public String toCanonical() {
        return String.valueOf(type.getCode()) +
                '-';
    }

    @Override
    @NotNull
    public String toString() {
        return String.format("%s (%s)", type, position);
    }
}
