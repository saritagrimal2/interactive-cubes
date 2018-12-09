package es.uniovi.interactive_cubes.logic.Entities;

public class User {

    private String email;
    private Long goodCombinations;
    private Long aux;


    public User() {
        // Default constructor required for calls to DataSnapshot.getValue(User.class)
    }

    public User(String email,Long goodCombinations) {
        this.email = email;
        this.goodCombinations = goodCombinations;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Long getGoodCombinations() {
        return goodCombinations;
    }

    public void setGoodCombinations(Long goodCombinations) {
        this.goodCombinations = goodCombinations;
    }

    public Long getAux() {
        return aux;
    }

    public void setAux(Long aux) {
        this.aux = aux;
    }

    @Override
    public String toString() {
        return "User{" +
                "username='"  + '\'' +
                ", email='" + email + '\'' +
                ", goodCombinations='" + goodCombinations + '\'' +
                '}';
    }
}
