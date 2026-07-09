package com.shapez2.shapeData.enums;

public enum Color {
    UNCOLORED('u', "Uncolored"),
    RED('r', "Red"),
    GREEN('g', "Green"),
    BLUE('b', "Blue"),
    CYAN('c', "Cyan"),
    MAGENTA('m', "Magenta"),
    YELLOW('y', "Yellow"),
    WHITE('w', "White"),
//    BLACK('k', "Black"),
    NONE('-', "None"); // For pins that don't have colors

    private final char code;
    private final String name;

    Color(final char code, final String name) {
        this.code = code;
        this.name = name;
    }

    public char getCode() {
        return code;
    }

    public String getName() {
        return name;
    }
    public static Color fromCode(final char code){
        for (final Color color : Color.values()) {
            if (color.getCode() == code) {
                return color;
            }
        }
        throw new IllegalArgumentException("No color with code " + code);
    }

    @Override
    public String toString() {
        return this.name;
    }
}
