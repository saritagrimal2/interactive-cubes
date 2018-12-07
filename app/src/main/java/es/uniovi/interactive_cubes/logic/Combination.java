package es.uniovi.interactive_cubes.logic;

import java.util.ArrayList;
import java.util.List;

public class Combination {

    private List<CubeFace> cubeFaces;
    private String songTitle;

    public Combination(String songTitle) {
        cubeFaces = new ArrayList<CubeFace>();
        this.songTitle = songTitle;
    }

    public boolean addCube(CubeFace cube) {

        if(!cubeFaces.contains(cube)) {
            cubeFaces.add(cube);
            return true;
        }
        return false;
    }

    public boolean is(String combination) {

        return this.toString().equals(combination);
    }

    public String getSongTitle() { return this.songTitle; }

    @Override
    public String toString() {
        StringBuilder toReturn = new StringBuilder();

        for(CubeFace face : cubeFaces) {
            toReturn.append(face.getId());
        }

        return toReturn.toString();
    }
}
