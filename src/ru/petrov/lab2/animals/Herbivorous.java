package ru.petrov.lab2.animals;

public class Herbivorous extends Animal{

    public Herbivorous(String name, int foodAmount) {
        super(name, foodAmount);
    }

    @Override
    public FoodType getFoodType() {
        return FoodType.PLANTS;
    }

    @Override
    public String toString() {
        return "Herbivorous " + super.toString();
    }
}
