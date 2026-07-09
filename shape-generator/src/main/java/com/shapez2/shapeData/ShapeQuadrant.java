package com.shapez2.shapeData;

import com.shapez2.shapeData.enums.ShapeType;
import com.shapez2.shapeData.enums.Color;
import com.shapez2.shapeData.enums.QuadrantPosition;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public record ShapeQuadrant(ShapeType type, Color color, QuadrantPosition position) implements Quadrant {
    public ShapeQuadrant {
        Objects.requireNonNull(color, "Color cannot be null");
        Objects.requireNonNull(type, "Type cannot be null");
        Objects.requireNonNull(position, "Position cannot be null");
        if(type == ShapeType.EMPTY){
            color = Color.NONE;
        }
    }

    @Override
    public String toCanonical() {
        return String.valueOf(this.type.getCode()) +
                this.color.getCode();
    }

    @Override
    @NotNull
    public String toString() {
        return String.format("%s (%s) at %s", type, color, position);
    }
}
