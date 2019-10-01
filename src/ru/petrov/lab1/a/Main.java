package ru.petrov.lab1.a;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        try (Scanner sc = new Scanner(System.in)) {
            int rowsCount = getIntegerFromUser("Enter number of rows", sc);
            int columnsCount = getIntegerFromUser("Enter number of columns", sc);

            if (rowsCount == 0 || columnsCount == 0) {
                System.err.println("RowCount or column count cannot be zero");
            }

            int[][] matrix = new int[rowsCount][columnsCount];
            for (int i = 0; i < rowsCount; i++) {
                matrix[i / columnsCount][i % columnsCount] = 1;
            }

            for (int i = 0; i < rowsCount; i++) {
                for (int j = 0; j < columnsCount; j++) {
                    System.out.print(matrix[i][j]);
                }
                System.out.println();
            }
        }
    }

    private static int getIntegerFromUser(String message, Scanner sc) {
        System.out.println(message);
        while (true) {
            String s = sc.nextLine();
            try {
                return Integer.parseInt(s);
            } catch (NumberFormatException e) {
                System.out.println("Enter number in correct format");
            }
        }
    }
}
