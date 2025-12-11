package day_05;

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
        List<long[]> ranges = new ArrayList<>();

        // Read input file
        String input = getFilePath("input.txt", SolutionExtended.class);
        try (BufferedReader bf = new BufferedReader(new InputStreamReader(new FileInputStream(input)))) {
            List<String> lines = bf.lines().toList();
            for (String line : lines) {
                String[] parts = line.split("-");
                if (parts.length == 2) {
                    ranges.add(new long[]{Long.parseLong(parts[0]), Long.parseLong(parts[1])});
                } else if (parts.length == 1) {
                    break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Solution
        long total = 0;

        List<long[]> merged;
        long size;
        do {
            merged = new ArrayList<>();
            size = ranges.size();
            for (long[] range : ranges) {

                if (merged.isEmpty()) {
                    merged.add(range);
                    continue;
                }

                // check if ranges overlap
                boolean hasMerged = false;
                for (int i = 0; i < merged.size(); i++) {
                    if (!(range[1] < merged.get(i)[0] || range[0] > merged.get(i)[1])) {
                        long[] newRange = new long[2];
                        newRange[0] = Math.min(range[0], merged.get(i)[0]);
                        newRange[1] = Math.max(range[1], merged.get(i)[1]);
                        merged.remove(i);
                        merged.add(i, newRange);
                        hasMerged = true;
                        break;
                    }
                }

                // the range was not merged, add it
                if (!hasMerged) {
                    merged.add(range);
                }
            }
            ranges = new ArrayList<>(merged);
        } while (size != ranges.size());

        for (long[] range : ranges) {
            total += range[1] - range[0] + 1;
        }

        System.out.println("total = " + total);
    }
}
