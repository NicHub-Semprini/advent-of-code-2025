package day_01;

import template.AbstractSolution;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

public class SolutionBase extends AbstractSolution {

    public static void main(String... args) {

        // Inputs
        List<Integer> numbers = null;

        // Read input file
        String input = getFilePath("input.txt", SolutionBase.class);
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
            current = (current + n) % 100;
            if (current == 0) {
                count++;
            }
        }

        System.out.println("Count = " + count);
    }
}
