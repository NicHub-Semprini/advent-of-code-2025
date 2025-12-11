package day_05;

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
        List<long[]> ranges = new ArrayList<>();
        List<Long> products = new ArrayList<>();

        // Read input file
        String input = getFilePath("input.txt", SolutionBase.class);
        try (BufferedReader bf = new BufferedReader(new InputStreamReader(new FileInputStream(input)))) {
            List<String> lines = bf.lines().toList();
            for (String line : lines) {
                String[] parts = line.split("-");
                if (parts.length == 2) {
                    ranges.add(new long[]{Long.parseLong(parts[0]), Long.parseLong(parts[1])});
                } else if (parts.length == 1 && !parts[0].isEmpty()) {
                    products.add(Long.parseLong(parts[0]));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Solution
        long total = 0;

        for (long product : products) {
            for (long[] range : ranges) {
                if (product >= range[0] && product <= range[1]) {
                    total += 1;
                    break;
                }
            }
        }

        System.out.println("total = " + total);
    }
}
