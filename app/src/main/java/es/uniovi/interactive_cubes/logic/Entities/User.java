package es.uniovi.interactive_cubes.logic.Entities;

public class User {

    private String username;
    private String email;
    private String goodCombinations;


    public User() {
        // Default constructor required for calls to DataSnapshot.getValue(User.class)
    }

    public User(String username, String email,String goodCombinations) {
        this.username = username;
        this.email = email;
        this.goodCombinations = goodCombinations;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getGoodCombinations() {
        return goodCombinations;
    }

    public void setGoodCombinations(String goodCombinations) {
        this.goodCombinations = goodCombinations;
    }

    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", goodCombinations='" + goodCombinations + '\'' +
                '}';
    }
}
