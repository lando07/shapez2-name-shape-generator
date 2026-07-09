package com.shapez2.shapeData.enums;

public enum QuadrantPosition {

    TOP_LEFT(0, "Top Left"),
    TOP_RIGHT(1, "Top Right"),
    BOTTOM_LEFT(2, "Bottom Left"),
    BOTTOM_RIGHT(3, "Bottom Right");
    private final int index;
    private final String name;

    QuadrantPosition(final int index, final String name) {
        this.index = index;
        this.name = name;
    }

    public int getIndex() {
        return this.index;
    }

    public String getName() {
        return this.name;
    }

    public static QuadrantPosition fromIndex(final int index) {
        for (final QuadrantPosition quadrantPosition : values()) {
            if (quadrantPosition.getIndex() == index) {
                return quadrantPosition;
            }
        }
        throw new IllegalArgumentException("Invalid quadrant position index " + index + ". Must be between 0 and 3 inclusive.");
    }
}
