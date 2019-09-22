package ru.petrov.lab2.animals;

public abstract class Animal {

    private static int totalAnimals = 0;

    private int id;
    private String name;
    private int foodAmount;

    protected Animal(String name, int foodAmount) {
        this.id = totalAnimals++;
        this.name = name;
        this.foodAmount = foodAmount;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getFoodAmount() {
        return foodAmount;
    }

    public abstract FoodType getFoodType();

    @Override
    public String toString() {
        return "Animal{" +
            "id: " + id +
            ", name: '" + name + '\'' +
            ", food amount: " + foodAmount +
            ", food type " + getFoodType() +
            '}';
    }
}
