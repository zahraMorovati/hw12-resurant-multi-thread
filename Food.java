package model;

public class Food {

    private String name;
    private int cookTime;

    public Food(String name, int cookTime) {
        this.name = name;
        this.cookTime = cookTime;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCookTime() {
        return cookTime;
    }

    public void setCookTime(int cookTime) {
        this.cookTime = cookTime;
    }

    @Override
    public String toString() {
        return name;
    }
}
