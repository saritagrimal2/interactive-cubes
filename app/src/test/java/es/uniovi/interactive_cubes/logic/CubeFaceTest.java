package es.uniovi.interactive_cubes.logic;

import org.junit.Test;

import static org.junit.Assert.*;

public class CubeFaceTest {

    private CubeFace cube1;

    @Test
    public void getIdTest() {
        cube1 = new CubeFace("11");
        assertEquals("11", cube1.getId());
    }
}
