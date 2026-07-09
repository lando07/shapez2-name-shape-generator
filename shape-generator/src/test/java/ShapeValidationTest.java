import com.shapez2.shapeData.Shape;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ShapeValidationTest {

    @Test
    public void testValidShape() {
        // Layer 0: all squares
        // Layer 1: all squares
        Shape shape = Shape.fromCanonical("RuRuRuRu:RuRuRuRu");
        assertTrue(shape.isValid(), "Shape with support should be valid");
    }

    @Test
    public void testInvalidShape() {
        // Layer 0: all empty
        // Layer 1: one square
        Shape shape = Shape.fromCanonical("--------:Ru------");
        assertFalse(shape.isValid(), "Shape with floating quadrant should be invalid");
    }

    @Test
    public void testFixShape() {
        // Layer 0: all empty
        // Layer 1: one square
        Shape shape = Shape.fromCanonical("--------:Ru------");
        assertFalse(shape.isValid());
        shape.fixShape();
        assertTrue(shape.isValid(), "Fixed shape should be valid");
        assertEquals("P-------:Ru------", shape.toCanonical());
    }
    
    @Test
    public void testMultipleLayersValid() {
        // L0: P-, L1: P-, L2: Ru
        Shape shape = Shape.fromCanonical("P-P-P-P-:P-P-P-P-:RuRuRuRu");
        assertTrue(shape.isValid());
    }

    @Test
    public void testMultipleLayersInvalid() {
        Shape shape = Shape.fromCanonical("P-P-P-P-:--------:R-R-R-R-");
        assertFalse(shape.isValid());
    }
    @Test
    public void testMultipleLayersFix() {
        // L0: P-, L1: --, L2: Ru
        Shape shape = Shape.fromCanonical("P-P-P-P-:--------:RuRuRuRu");
        assertFalse(shape.isValid());
        shape.fixShape();
        assertTrue(shape.isValid());
        assertEquals("P-P-P-P-:P-P-P-P-:RuRuRuRu", shape.toCanonical());
    }

    @Test
    public void testPinSupport() {
        // Pins can support other quadrants
        Shape shape = Shape.fromCanonical("P-P-P-P-:RuRuRuRu");
        assertTrue(shape.isValid(), "Pins should support other quadrants");
    }
}
