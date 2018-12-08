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

        //Level 1

        Combination c11 = new Combination("Sinfonía nº5 en Do menor");
        c11.addCube(new CubeFace("11"));
        c11.addCube(new CubeFace("21"));

        validCombinations.add(c11);

        Combination c12 = new Combination("Serenata nº 13");
        c12.addCube(new CubeFace("12"));
        c12.addCube(new CubeFace("22"));

        validCombinations.add(c12);

        Combination c13 = new Combination("Las cuatro estaciones");
        c13.addCube(new CubeFace("13"));
        c13.addCube(new CubeFace("23"));

        validCombinations.add(c13);

        Combination c14 = new Combination("Tocata y fuga");
        c14.addCube(new CubeFace("14"));
        c14.addCube(new CubeFace("24"));

        validCombinations.add(c14);

        Combination c15 = new Combination("El lago de los cisnes");
        c15.addCube(new CubeFace("15"));
        c15.addCube(new CubeFace("25"));

        validCombinations.add(c15);

        //Level 2

        Combination c21 = new Combination("Sinfonía nº5 en Do menor");
        c21.addCube(new CubeFace("11"));
        c21.addCube(new CubeFace("21"));
        c21.addCube(new CubeFace("31"));

        validCombinations.add(c21);

        Combination c22 = new Combination("Serenata nº 13");
        c22.addCube(new CubeFace("12"));
        c22.addCube(new CubeFace("22"));
        c22.addCube(new CubeFace("32"));

        validCombinations.add(c22);

        Combination c23 = new Combination("Las cuatro estaciones");
        c23.addCube(new CubeFace("13"));
        c23.addCube(new CubeFace("23"));
        c23.addCube(new CubeFace("33"));

        validCombinations.add(c23);

        Combination c24 = new Combination("Tocata y fuga");
        c24.addCube(new CubeFace("14"));
        c24.addCube(new CubeFace("24"));
        c24.addCube(new CubeFace("34"));

        validCombinations.add(c24);

        Combination c25 = new Combination("El lago de los cisnes");
        c25.addCube(new CubeFace("15"));
        c25.addCube(new CubeFace("25"));
        c25.addCube(new CubeFace("35"));

        validCombinations.add(c25);

        //Level 3

        Combination c31 = new Combination("Sinfonía nº5 en Do menor");
        c31.addCube(new CubeFace("11"));
        c31.addCube(new CubeFace("21"));
        c31.addCube(new CubeFace("31"));
        c31.addCube(new CubeFace("41"));

        validCombinations.add(c31);

        Combination c32 = new Combination("Serenata nº 13");
        c32.addCube(new CubeFace("12"));
        c32.addCube(new CubeFace("22"));
        c32.addCube(new CubeFace("32"));
        c32.addCube(new CubeFace("42"));

        validCombinations.add(c32);

        Combination c33 = new Combination("Las cuatro estaciones");
        c33.addCube(new CubeFace("13"));
        c33.addCube(new CubeFace("23"));
        c33.addCube(new CubeFace("33"));
        c33.addCube(new CubeFace("43"));

        validCombinations.add(c33);

        Combination c34 = new Combination("Tocata y fuga");
        c34.addCube(new CubeFace("14"));
        c34.addCube(new CubeFace("24"));
        c34.addCube(new CubeFace("34"));
        c34.addCube(new CubeFace("43"));

        validCombinations.add(c34);

        Combination c35 = new Combination("El lago de los cisnes");
        c35.addCube(new CubeFace("15"));
        c35.addCube(new CubeFace("25"));
        c35.addCube(new CubeFace("35"));
        c35.addCube(new CubeFace("41"));

        validCombinations.add(c35);

        //Level 4

        Combination c41 = new Combination("Sinfonía nº5 en Do menor");
        c41.addCube(new CubeFace("11"));
        c41.addCube(new CubeFace("21"));
        c41.addCube(new CubeFace("31"));
        c41.addCube(new CubeFace("41"));
        c41.addCube(new CubeFace("51"));

        validCombinations.add(c41);

        Combination c42 = new Combination("Serenata nº 13");
        c42.addCube(new CubeFace("12"));
        c42.addCube(new CubeFace("22"));
        c42.addCube(new CubeFace("32"));
        c42.addCube(new CubeFace("42"));
        c42.addCube(new CubeFace("51"));

        validCombinations.add(c42);

        Combination c43 = new Combination("Las cuatro estaciones");
        c43.addCube(new CubeFace("13"));
        c43.addCube(new CubeFace("23"));
        c43.addCube(new CubeFace("33"));
        c43.addCube(new CubeFace("43"));
        c43.addCube(new CubeFace("52"));

        validCombinations.add(c43);

        Combination c44 = new Combination("Tocata y fuga");
        c44.addCube(new CubeFace("14"));
        c44.addCube(new CubeFace("24"));
        c44.addCube(new CubeFace("34"));
        c44.addCube(new CubeFace("43"));
        c44.addCube(new CubeFace("53"));

        validCombinations.add(c44);

        Combination c45 = new Combination("El lago de los cisnes");
        c45.addCube(new CubeFace("15"));
        c45.addCube(new CubeFace("25"));
        c45.addCube(new CubeFace("35"));
        c45.addCube(new CubeFace("41"));
        c45.addCube(new CubeFace("53"));

        validCombinations.add(c45);

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

    public void playAgain() {
         actualCombs = new ArrayList<>();
    }
}