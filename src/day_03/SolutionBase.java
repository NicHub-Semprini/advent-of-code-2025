package day_03;

import template.AbstractSolution;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class SolutionBase extends AbstractSolution {

    public static void main(String... args) {

        // Inputs
        List<int[]> banks = new ArrayList<>();

        // Read input file
        String input = getFilePath("input.txt", SolutionBase.class);
        try (BufferedReader bf = new BufferedReader(new InputStreamReader(new FileInputStream(input)))) {
            List<String> lines = bf.lines().toList();
            for (String line : lines) {
                int[] digits = new int[line.length()];
                for (int i = 0; i < line.length(); i++) {
                    digits[i] = Integer.parseInt(line.substring(i, i + 1));
                }
                banks.add(digits);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Solution
        long total = 0;
        for (int[] bank : banks) {
            // scan all batteries but last one
            int first = 1;
            int second = 1;
            for (int i = 0; i < bank.length - 1; i++) {
                if (bank[i] > first) {
                    first = bank[i];
                    second = 1;
                } else if (bank[i] > second) {
                    second = bank[i];
                }
            }
            if (bank[bank.length - 1] > second) {
                second = bank[bank.length - 1];
            }
            int voltage = Integer.parseInt(first + "" + second);
            total += voltage;
        }

        System.out.println("total = " + total);
    }
}
