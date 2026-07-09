package com.shapez2;

import com.shapez2.shapeData.PinQuadrant;
import com.shapez2.shapeData.Shape;
import com.shapez2.shapeData.ShapeLayer;
import com.shapez2.shapeData.ShapeQuadrant;
import com.shapez2.shapeData.enums.Color;
import com.shapez2.shapeData.enums.QuadrantPosition;
import com.shapez2.shapeData.enums.ShapeType;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class NameEncoder {
    private static String normalize(String firstName, String lastName){
        if (firstName == null) firstName = "";
        if (lastName == null) lastName = "";

        String combined = (firstName.trim() + " " + lastName.trim()).trim();

        StringBuilder sb = new StringBuilder();
        for (char c : combined.toUpperCase().toCharArray()) {
            if (Character.isLetter(c)) {
                sb.append(c);
            }
        }
        return sb.toString();
    }

    public static long hashName(String firstName, String lastName){
        String normalized = normalize(firstName, lastName);
        if(normalized.isBlank()){
            return 0;
        }
        return ((long) normalized.hashCode()) + normalized.length();
    }

    public static Shape generateShape(String firstName, String lastName){
        long seed = hashName(firstName, lastName);
        Random random = new Random(seed);

        Shape shape = new Shape();
        int numLayers = 1 + random.nextInt(3);
        for (int i = 0; i < numLayers; i++) {
            ShapeLayer layer = new ShapeLayer();
            int numQuads = 2 + random.nextInt(3);
            fillLayerWithShapes(layer, numQuads, random);
            shape.addLayer(layer);
        }
        shape.fixShape();
        return shape;
    }

    private static void fillLayerWithShapes(ShapeLayer layer, int numQuads, Random random) {
        // Initialize all as empty first
        for (int i = 0; i < 4; i++) {
            QuadrantPosition pos = QuadrantPosition.fromIndex(i);
            layer.setQuadrant(pos, new ShapeQuadrant(ShapeType.EMPTY, Color.NONE, pos));
        }

        List<Integer> availableIndices = new ArrayList<>(List.of(0, 1, 2, 3));
        // Shuffle the available indices
        for (int i = availableIndices.size() - 1; i > 0; i--) {
            int j = random.nextInt(i + 1);
            int temp = availableIndices.get(i);
            availableIndices.set(i, availableIndices.get(j));
            availableIndices.set(j, temp);
        }
        int toFill = Math.min(numQuads, 4);
        for(int i = 0; i < toFill; i++){
            int quadIdx = availableIndices.get(i);
            QuadrantPosition pos = QuadrantPosition.fromIndex(quadIdx);
            
            // Turn the random integer into a ShapeType using its ordinal
            ShapeType type = ShapeType.values()[random.nextInt(ShapeType.values().length)];

            // If an empty quadrant is selected, it only has a 25% chance of staying empty
            if (type == ShapeType.EMPTY && random.nextInt(25) != 0) {
                type = ShapeType.values()[random.nextInt(ShapeType.values().length - 1)];
            }

            if (type == ShapeType.PIN) {
                layer.setQuadrant(pos, new PinQuadrant(type, pos));
            } else {
                // Select a random color
                Color color = Color.values()[random.nextInt(Color.values().length)];
                layer.setQuadrant(pos, new ShapeQuadrant(type, color, pos));
            }
        }
    }
}
