package ru.petrov.lab2.animals;

public class Carnivore extends Animal {

    public Carnivore(String name, int foodAmount) {
        super(name, foodAmount);
    }

    @Override
    public FoodType getFoodType() {
        return FoodType.ANIMALS;
    }
}
