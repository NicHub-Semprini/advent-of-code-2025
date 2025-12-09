package day_02;

import template.AbstractSolution;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

public class SolutionBase extends AbstractSolution {

    public static void main(String... args) {

        // Inputs
        long[][] ranges = null;

        // Read input file
        String input = getFilePath("input.txt", SolutionBase.class);
        try (BufferedReader bf = new BufferedReader(new InputStreamReader(new FileInputStream(input)))) {
            List<String> lines = bf.lines().toList();
            String[] rangesTxt = lines.get(0).split(",");
            ranges = new long[rangesTxt.length][2];
            for (int i = 0; i < rangesTxt.length; i++) {
                String[] range = rangesTxt[i].split("-");
                ranges[i][0] = Long.parseLong(range[0]);
                ranges[i][1] = Long.parseLong(range[1]);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Solution
        long total = 0;
        for (long[] range : ranges) {
            for (int i = 0; range[0] + i <= range[1]; i++) {
                long n = range[0] + i;
                String digits = Long.toString(n);
                int length = digits.length();

                // exclude a priori odd number of digits
                if (length % 2 != 0) {
                    continue;
                }

                String firstHalf = digits.substring(0, length / 2);
                String secondHalf = digits.substring(length / 2, length);
                if (firstHalf.equals(secondHalf)) {
                    total += n;
                }
            }
        }

        System.out.println("total = " + total);
    }
}
