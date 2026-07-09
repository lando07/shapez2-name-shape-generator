package com.shapez2.shapeData.enums;

public enum ShapeType {
    CIRCLE('C', "Circle"),
    SQUARE('R', "Square"),
    STAR('S', "Star"),
    DIAMOND('W', "Diamond"),
//    REFINED_X('X', "Refined X"),
//    REFINED_Y('Y', "Refined Y"),
    PIN('P', "Pin"),
    CRYSTAL('c', "Crystal"),
    EMPTY('-', "Empty");

    private final char code;
    private final String name;

    ShapeType(final char code, final String name) {
        this.code = code;
        this.name = name;
    }

    public char getCode() {
        return this.code;
    }

    public String getName() {
        return this.name;
    }

    public static ShapeType fromCode(final char code) {
        for (final ShapeType shapeType : ShapeType.values()) {
            if (shapeType.getCode() == code) {
                return shapeType;
            }
        }
        throw new IllegalArgumentException("No shape type with code " + code);
    }

    @Override
    public String toString() {
        return this.name;
    }
}
