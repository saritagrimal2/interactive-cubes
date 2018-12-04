package es.uniovi.uo252406.interactive_cubes.logic;

import java.util.ArrayList;
import java.util.List;

public class Combination {

    private List<CubeFace> cubeFaces;
    private String song;
    private String songTitle;
    private String songDescription;

    public Combination(String song, String songTitle, String songDescription) {
        cubeFaces = new ArrayList<CubeFace>();
        this.song = song;
        this.songTitle = songTitle;
        this.songDescription = songDescription;
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

    public String getSong() {
        return this.song;
    }

    public String getSongTitle() { return this.songTitle; }

    public String getSongDescription() { return this.songDescription; }

    @Override
    public String toString() {
        StringBuilder toReturn = new StringBuilder();

        for(CubeFace face : cubeFaces) {
            toReturn.append(face.getId());
        }

        return toReturn.toString();
    }
}
