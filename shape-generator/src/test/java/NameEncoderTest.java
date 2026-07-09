import com.shapez2.NameEncoder;
import com.shapez2.shapeData.Shape;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class NameEncoderTest {

    @Test
    public void testGenerateShape() {
        Shape shape = NameEncoder.generateShape("John", "Doe");
        assertNotNull(shape);
        assertFalse(shape.layers().isEmpty(), "Generated shape should have at least one layer");
        assertTrue(shape.isValid(), "Generated shape should be valid");
    }

    @Test
    public void testHashNameConsistency() {
        long hash1 = NameEncoder.hashName("John", "Doe");
        long hash2 = NameEncoder.hashName("John", "Doe");
        assertEquals(hash1, hash2, "Same name should produce same hash");
    }

    @Test
    public void testHashNameNormalization() {
        long hash1 = NameEncoder.hashName("John", "Doe");
        long hash2 = NameEncoder.hashName(" john ", " DOE ");
        assertEquals(hash1, hash2, "Normalization should make hash case-insensitive and trim spaces");
    }
}
