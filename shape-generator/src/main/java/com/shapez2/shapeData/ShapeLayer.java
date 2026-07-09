package com.shapez2.shapeData;

import com.shapez2.shapeData.enums.QuadrantPosition;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public record ShapeLayer(Quadrant[] quadrants) {
    public ShapeLayer() {
        this(new Quadrant[4]);
    }

    public ShapeLayer {
        if (quadrants == null || quadrants.length != 4) {
            throw new IllegalArgumentException("Quadrant array must be exactly 4 elements, for each quadrant");
        }
    }

    public Quadrant getQuadrant(final QuadrantPosition position) {
        return this.quadrants[position.getIndex()];
    }

    public Quadrant getQuadrant(int index) {
        return this.quadrants[index];
    }

    public void setQuadrant(final QuadrantPosition position, final Quadrant quadrant) {
        this.quadrants[position.getIndex()] = Objects.requireNonNull(quadrant, "Quadrant cannot be null");
    }

    public String toCanonical() {
        StringBuilder sb = new StringBuilder();
        for (Quadrant quadrant : this.quadrants) {
            sb.append(quadrant.toCanonical());
        }
        return sb.toString();
    }

    public static ShapeLayer fromCanonical(final String canonical) {
        if (canonical == null || canonical.length() != 8) {
            throw new IllegalArgumentException("Canonical shape layer format must be exactly 8 characters, got: " + canonical);
        }
        Quadrant[] quadrantPositions = new Quadrant[4];
        for (int i = 0; i < 4; i++) {
            String quad = canonical.substring(i * 2, i * 2 + 2);
            quadrantPositions[i] = Quadrant.fromCanonical(quad, QuadrantPosition.fromIndex(i));
        }
        return new ShapeLayer(quadrantPositions);
    }

    @Override
    @NotNull
    public String toString() {
        StringBuilder sb = new StringBuilder("Layer: ");
        for (int i = 0; i < 4; i++) {
            sb.append(quadrants[i]);
        }
        sb.append("]");
        return sb.toString();
    }
}
