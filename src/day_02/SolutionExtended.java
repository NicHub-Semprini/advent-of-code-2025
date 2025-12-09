package day_02;

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
        long[][] ranges = null;

        // Read input file
        String input = getFilePath("input.txt", SolutionExtended.class);
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

                // split digits evenly, starting with 1 and up to half their length
                for (int radix = 1; radix <= length / 2; radix++) {

                    // check that the string can be split evenly
                    if (length % radix != 0) {
                        continue;
                    }

                    List<String> parts = splitEvenly(digits, radix);

                    // check that all parts are the same sequence of digit
                    boolean found = true;
                    for (int j = 0; j < parts.size() - 1; j++) {
                        if (!parts.get(j).equals(parts.get(j + 1))) {
                            // different sequence of digits, try splitting with next radix
                            found = false;
                            break;
                        }
                    }

                    if (found) {
                        // same sequence of digits, increase the total and skip to next number
                        total += n;
                        break;
                    }
                }
            }
        }

        System.out.println("total = " + total);
    }

    private static List<String> splitEvenly(String string, int radix) {
        List<String> parts = new ArrayList<>();
        int length = string.length();
        for (int i = 0; i < length; i += radix) {
            parts.add(string.substring(i, i + radix));
        }
        return parts;
    }
}
