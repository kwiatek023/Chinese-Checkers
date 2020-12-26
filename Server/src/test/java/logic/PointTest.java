package logic;

import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertTrue;

class PointTest {
    @Test
    void testContainsPoint() {
        Point p1 = new Point(3, 5);
        Point p2 = new Point(3, 5);
        Point p3 = new Point(2, 6);

        Set<Point> set = new HashSet<>();
        set.add(p2);
        set.add(p3);

        assertTrue(set.contains(p1));
    }
}