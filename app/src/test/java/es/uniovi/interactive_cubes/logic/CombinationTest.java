package es.uniovi.interactive_cubes.logic;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class CombinationTest {

    private Combination combination1;

    @Before
    public void setUp() {
        combination1 = new Combination("una","dos","tres");
    }

    @Test
    public void constructorTest() {
        combination1 = new Combination("una","dos","tres");

        assertNotNull(combination1);

        assertEquals("una", combination1.getSong());
        assertEquals("dos",combination1.getSongTitle());
        assertEquals("tres",combination1.getSongDescription());
    }

    @Test
    public void addCubeTest() {
        assertTrue(combination1.addCube(new CubeFace("11")));
        assertTrue(combination1.addCube(new CubeFace("12")));

        System.out.println(combination1.addCube(new CubeFace("11")));

        assertFalse(combination1.addCube(new CubeFace("12")));
    }

    @Test
    public void isTest() {
        combination1.addCube(new CubeFace("11"));

        assertTrue(combination1.is("11"));
        assertFalse(combination1.is("12"));

        combination1.addCube(new CubeFace("12"));

        assertFalse(combination1.is("11"));
        assertFalse(combination1.is("12"));
        assertTrue(combination1.is("1112"));
    }
}
