package ru.petrov.lab2.animals;

public class Omnivorous extends Animal {

    public Omnivorous(String name, int foodAmount) {
        super(name, foodAmount);
    }

    @Override
    public FoodType getFoodType() {
        return FoodType.EVERYTHING;
    }
}
