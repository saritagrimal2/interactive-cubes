package es.uniovi.uo252406.interactive_cubes.logic;

import java.util.ArrayList;
import java.util.List;

public class Game {

    private List<Combination> validCombinations;
    private int currentLevel = 1;

    private static Game instance;


    public static Game getInstance(){
        if(instance == null)
            instance = new Game();
        return instance;
    }

    private Game() {
        validCombinations = new ArrayList<Combination>();

        // I know this is shit but for this project will serve.

        // First valid combination
        Combination c1 = new Combination("primaveravivaldi", "Sinfonía nº 5 (Beethoven)", "Una canción mu bonita");
        c1.addCube(new CubeFace("11"));
        c1.addCube(new CubeFace("21"));

        validCombinations.add(c1);


        // Second valid combination
        Combination c2 = new Combination("estaciones", "Sinfonía nº 5 (Beethoven)", "Una canción mu bonita");
        c2.addCube(new CubeFace("12"));
        c2.addCube(new CubeFace("22"));
        c2.addCube(new CubeFace("32"));

        validCombinations.add(c2);


        // Third valid combination
        Combination c3 = new Combination("juegodetronos", "Sinfonía nº 5 (Beethoven)", "Una canción mu bonita");
        c3.addCube(new CubeFace("13"));
        c3.addCube(new CubeFace("23"));
        c3.addCube(new CubeFace("33"));
        c3.addCube(new CubeFace("43"));

        validCombinations.add(c3);


        // Fourth valid combination
        Combination c4 = new Combination("doctorwho", "Sinfonía nº 5 (Beethoven)", "Una canción mu bonita");
        c4.addCube(new CubeFace("14"));
        c4.addCube(new CubeFace("24"));
        c4.addCube(new CubeFace("34"));
        c4.addCube(new CubeFace("44"));
        c4.addCube(new CubeFace("54"));

        validCombinations.add(c4);


        // Fifth valid combination
        Combination c5 = new Combination("allice", "Sinfonía nº 5 (Beethoven)", "Una canción mu bonita");
        c5.addCube(new CubeFace("15"));
        c5.addCube(new CubeFace("25"));
        c5.addCube(new CubeFace("35"));
        c5.addCube(new CubeFace("45"));
        c5.addCube(new CubeFace("55"));

        validCombinations.add(c5);

    }

    public int getLevel() {
        return this.currentLevel;
    }

    public int getNumberOfCubesOfCurrentLevel() {
        switch(this.currentLevel) {
            case 1: return 2;
            case 2: return 3;
            case 3: return 4;
            case 4: return 5;
        }

        return -1;
    }


    public Combination checkCombination(String combination) {

        for(Combination comb : validCombinations) {
            if( comb.is(combination) && ( combination.length() == ( getNumberOfCubesOfCurrentLevel() * 2 ) ) ) {
                return comb;
            }
        }

        return null;
    }

    public boolean checkCube(String cubeId) {

        if(cubeId == null) {
            return false;
        }

        if(cubeId.length() != 2) {
            return false;
        }

        return true;
    }

    public int incrementLevel(){

        if(currentLevel <4 )
            currentLevel++;

        return currentLevel;
    }

    public int decrementLevel() {

        if(currentLevel > 1 )
            currentLevel--;

        return currentLevel;

    }
}