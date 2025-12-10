package day_03;

import template.AbstractSolution;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class SolutionExtended extends AbstractSolution {

    public static void main(String... args) {

        // Inputs
        List<int[]> banks = new ArrayList<>();

        // Read input file
        String input = getFilePath("input.txt", SolutionExtended.class);
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
            int[] batteries = new int[12];
            int index = 0;
            for (int i = 0; i < 12; i++) {
                for (int j = index; j <= bank.length - 12 + i; j++) {
                    if (bank[j] > bank[index]) {
                        index = j;
                        // break the loop at the first 9 encounter
                        if (bank[j] == 9) {
                            break;
                        }
                    }
                }
                batteries[i] = bank[index];
                index++;
            }
            long voltage = arrayToLong(batteries);
            total += voltage;
        }

        System.out.println("total = " + total);
    }

    private static long arrayToLong(int[] input) {
        StringBuilder sb = new StringBuilder(input.length);
        for (int i = 0; i < input.length; i++) {
            sb.append(input[i]);
        }

        return Long.parseLong(sb.toString());
    }
}
