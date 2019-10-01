package ru.petrov.lab2;

import ru.petrov.lab2.animals.Animal;
import ru.petrov.lab2.animals.Carnivore;
import ru.petrov.lab2.animals.Herbivorous;
import ru.petrov.lab2.animals.Omnivorous;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) throws IOException {
        List<Animal> animals;
        try (FileReader fr = new FileReader("resources/plain/animals")) {
            animals = readFromFile(fr);
        } catch (IOException e) {
            System.err.println(e.getMessage());
            System.err.println("Terminating process");
            return;
        }

        List<Animal> sorted = animals
            .stream()
            .sorted(
                Comparator.comparingInt(Animal::getFoodAmount).reversed()
                    .thenComparing(Animal::getName)
            )
            .collect(Collectors.toList());

        StringBuilder resultA = new StringBuilder("Task a:\n");
        sorted.forEach(animal -> resultA.append(animal).append('\n'));
        writeTo("output/lab2/a", resultA.toString());

        StringBuilder resultB = new StringBuilder("Task b:\n");
        sorted
            .stream()
            .limit(5)
            .forEach(animal -> resultB.append(animal.getName()).append('\n'));
        writeTo("output/lab2/b", resultB.toString());

        StringBuilder resultC = new StringBuilder("Task c:\n");
        sorted
            .stream()
            .skip(sorted.size() - 3)
            .forEach(animal -> resultC.append(animal.getId()).append('\n'));
        writeTo("output/lab2/c", resultC.toString());
    }


    private static List<Animal> readFromFile(InputStreamReader reader) throws IOException {
        List<Animal> result = new ArrayList<>();
        Scanner sc = new Scanner(reader);
        while (sc.hasNext()) {
            String[] tokens = sc.nextLine().split(" ");
            try {
                switch (tokens[0]) {
                    case "Herbivorous": {
                        result.add(new Herbivorous(tokens[1], Integer.parseInt(tokens[2])));
                        break;
                    }
                    case "Carnivore": {
                        result.add(new Carnivore(tokens[1], Integer.parseInt(tokens[2])));
                        break;
                    }
                    case "Omnivorous": {
                        result.add(new Omnivorous(tokens[1], Integer.parseInt(tokens[2])));
                        break;
                    }
                    default: {
                        throw new IOException("Invalid data format: No such animal category: " + tokens[0]);
                    }
                }
            } catch (NumberFormatException e) {
                throw new IOException("Invalid data format: Numbers are written in incorrect form: " + e.getMessage(), e);
            }
        }
        return result;
    }

    private static void writeTo(String path, String content) throws IOException {
        try (FileWriter fw = new FileWriter(path)) {
            fw.write(content);
        }
    }

}
