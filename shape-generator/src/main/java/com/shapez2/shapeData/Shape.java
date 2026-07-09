package com.shapez2.shapeData;

import com.shapez2.shapeData.enums.QuadrantPosition;
import com.shapez2.shapeData.enums.ShapeType;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public record Shape(List<ShapeLayer> layers) {
    public static final int MAX_LAYERS = 4;

    public Shape() {
        this(new ArrayList<>());
    }

    public Shape(final List<ShapeLayer> layers) {
        if (layers == null) {
            this.layers = new ArrayList<>();
            return;
        }
        if (layers.size() > MAX_LAYERS) {
            throw new IllegalArgumentException("Too many layers, max is " + MAX_LAYERS + ", got: " + layers.size() + " layers");
        }
        this.layers = layers;
    }

    public void addLayer(final ShapeLayer layer) {
        if (this.layers.size() >= MAX_LAYERS) {
            throw new IllegalArgumentException("Cannot add layer, max is " + MAX_LAYERS);
        }
        layers.add(Objects.requireNonNull(layer, "Layer cannot be null"));
    }

    public int getLayerCount() {
        return this.layers.size();
    }

    public ShapeLayer getLayer(int index) {
        return this.layers.get(index);
    }

    public String toCanonical() {
        if (layers.isEmpty()) {
            return "";
        }
        List<String> canonicals = new ArrayList<>();
        for (ShapeLayer layer : layers) {
            canonicals.add(layer.toCanonical());
        }
        return String.join(":", canonicals);
    }

    public static Shape fromCanonical(final String canonical) {
        if (canonical == null || canonical.isEmpty()) {
            return new Shape();
        }
        String[] canonicals = canonical.split(":");
        List<ShapeLayer> layers = new ArrayList<>();
        //ignore excess layers
        for (int i = 0; i < Math.min(canonicals.length, MAX_LAYERS); i++) {
            layers.add(ShapeLayer.fromCanonical(canonicals[i]));
        }
        return new Shape(layers);
    }

    public boolean isValid() {
        if (layers.size() <= 1) {
            return true;
        }
        for (int layer = 1; layer < layers.size(); layer++) {
            ShapeLayer currentLayer = layers.get(layer);
            ShapeLayer prevLayer = layers.get(layer - 1);
            for (int quadrant = 0; quadrant < 4; quadrant++) {
                Quadrant current = currentLayer.getQuadrant(quadrant);
                Quadrant prev = prevLayer.getQuadrant(quadrant);

                if (current.type() != ShapeType.EMPTY && prev.type() == ShapeType.EMPTY) {
                    return false;
                }
            }
        }
        return true;
    }

    public void fixShape() {
        if (!isValid()) {
            for (int layer = 1; layer < layers.size(); layer++) {
                ShapeLayer currentLayer = layers.get(layer);
                ShapeLayer prevLayer = layers.get(layer - 1);

                for (int quadrant = 0; quadrant < 4; quadrant++) {
                    Quadrant current = currentLayer.getQuadrant(quadrant);
                    Quadrant prev = prevLayer.getQuadrant(quadrant);
                    if (current.type() != ShapeType.EMPTY && prev.type() == ShapeType.EMPTY) {
                        QuadrantPosition position = QuadrantPosition.fromIndex(quadrant);
                        prevLayer.setQuadrant(position, new PinQuadrant(ShapeType.PIN, position));
                    }
                }
            }
        }
    }

    @Override
    @NotNull
    public String toString() {
        if(layers.isEmpty()){
            return "Empty shape";
        }
        return "Shape: " + toCanonical();
    }
}
