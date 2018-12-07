package es.uniovi.interactive_cubes.logic;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Game {

    private List<Combination> validCombinations;
    private int currentLevel = 1;
    
    private List<String> actualCombs = new ArrayList<>();

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
        c1.addCube(new CubeFace("13"));
        c1.addCube(new CubeFace("23"));

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

    public boolean checkCube(String cubeId,String index) {

        // Checks that the id is not null
        if(cubeId == null) {
            return false;
        }

        // Checks that the id has two elements.
        if(cubeId.length() != 2) {
            return false;
        }

        // That those two elements are digits.
        if(!cubeId.matches("-?\\d+(\\.\\d+)?")) {
            return false;
        }


        
        // Check the available ranges of cubes ids.
        if(cubeId.charAt(0) != index.charAt(0)){
            return false;
        }


        if( cubeId.compareTo(infLimit(cubeId))<0 || cubeId.compareTo(supLimit(cubeId))>0 ) {
            return false;
        }

        // If all checks passed, then...
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

    public void removeActualComn() {

        if(getActualCombsSize() > 0)
            actualCombs.remove(actualCombs.size()-1);

    }

    public void addCombination(String comb){
        actualCombs.add(comb);
    }

    
    public String getActualComb(int i) {
        return actualCombs.get(i);
    }

    public int getActualCombsSize(){
        return actualCombs.size();
    }


    public String getCubeName(String index) {

        String name = "";
        switch (index) {

            case "1":
                name= "Autor";
                break;

            case "2":
                name= "Obra";
                break;

            case "3":
                name= "Fecha";
                break;

            case "4":
                name= "Época";
                break;

            case "5":
                name="Lugar";
                break;

        }
        return name;
    }

    private String supLimit(String index) {

        String limit="";
        switch (index.charAt(0)) {

            case '1':
                limit = "15";
                break;

            case '2':
                limit = "25";
                break;

            case '3':
                limit = "35";
                break;

            case '4':
                limit = "43";
                break;

            case '5':
                limit = "53";
                break;

        }
        return limit;
    }

    private String infLimit(String index) {

        String limit="";
        switch (index.charAt(0)) {

            case '1':
                limit = "11";
                break;

            case '2':
                limit = "21";
                break;

            case '3':
                limit = "31";
                break;

            case '4':
                limit = "41";
                break;

            case '5':
                limit = "51";
                break;


        }
        return limit;
    }

    public List<String> getActualCombs() {
        return actualCombs;
    }


    public String getInfoName() {
        return actualCombs.get(0);
    }
}