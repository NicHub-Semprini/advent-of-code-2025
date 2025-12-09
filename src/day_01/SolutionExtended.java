package day_01;

import template.AbstractSolution;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

public class SolutionExtended extends AbstractSolution {

    public static void main(String... args) {

        // Inputs
        List<Integer> numbers = null;

        // Read input file
        String input = getFilePath("input.txt", SolutionExtended.class);
        try (BufferedReader bf = new BufferedReader(new InputStreamReader(new FileInputStream(input)))) {
            numbers = bf.lines().map(l -> {
                if (l.charAt(0) == 'L') {
                    return Integer.parseInt(l.substring(1)) * -1;
                } else {
                    return Integer.parseInt(l.substring(1));
                }
            }).toList();

        } catch (IOException e) {
            e.printStackTrace();
        }

        // Solution
        int current = 50;
        int count = 0;
        for (Integer n : numbers) {
            int previous = current;
            current = normalize(previous + n);
            System.out.println("n = " + n + " | previous = " + previous + " | current = " + current);
            // full rotations
            int fullRotations = Math.abs(n) / 100;
            if (fullRotations > 0) { // full rotations
                System.out.println("full rotations = " + fullRotations);
                count += fullRotations;
            }
            if (previous != 0 && current == 0) { // perfect 0
                System.out.println("perfect 0");
                count += 1;
            } else if (previous != 0 && (previous - current) * n > 0) { // hovered 0
                System.out.println("hovered 0");
                count += 1;
            }
        }

        System.out.println("count = " + count);
    }

    private static int normalize(int number) {
        return number >= 0 ? number % 100 : (100 - Math.abs(number % 100)) % 100;
    }
}
