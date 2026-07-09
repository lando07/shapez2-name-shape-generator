package com.shapez2.shapeData;

import com.shapez2.shapeData.enums.Color;
import com.shapez2.shapeData.enums.QuadrantPosition;
import com.shapez2.shapeData.enums.ShapeType;

public interface Quadrant {
    String toCanonical();

    ShapeType type();

    static Quadrant fromCanonical(final String canonical, final QuadrantPosition position) {
        if (canonical == null || canonical.length() != 2) {
            throw new IllegalArgumentException("Canonical quadrant format must be exactly 2 characters, got: " + canonical);
        }
        ShapeType type = ShapeType.fromCode(canonical.charAt(0));
        if (type == ShapeType.PIN) {
            return new PinQuadrant(type, position);
        }
        Color color = Color.fromCode(canonical.charAt(1));
        return new ShapeQuadrant(type, color, position);
    }

    @Override
    int hashCode();

    @Override
    String toString();
}
