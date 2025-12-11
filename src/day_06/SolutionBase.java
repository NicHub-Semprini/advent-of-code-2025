package day_06;

import template.AbstractSolution;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class SolutionBase extends AbstractSolution {

    public static void main(String... args) {

        // Inputs
        List<long[]> numbers = new ArrayList<>();
        List<Character> operands = new ArrayList<>();

        // Read input file
        String input = getFilePath("input.txt", SolutionBase.class);
        try (BufferedReader bf = new BufferedReader(new InputStreamReader(new FileInputStream(input)))) {
            List<String> lines = bf.lines().toList();
            for (String line : lines) {
                StringTokenizer st = new StringTokenizer(line);
                if (!line.startsWith("*") && !line.startsWith("+")) {
                    long[] nums = new long[st.countTokens()];
                    for (int i = 0; i < nums.length; i++) {
                        nums[i] = Long.parseLong(st.nextToken().trim());
                    }
                    numbers.add(nums);
                } else {
                    while (st.hasMoreTokens()) {
                        operands.add(st.nextToken().trim().charAt(0));
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Solution
        long total = 0;

        for (int i = 0; i < numbers.get(0).length; i++) {
            long[] factors = new long[numbers.size()];
            for (int j = 0; j < numbers.size(); j++) {
                factors[j] = numbers.get(j)[i];
            }
            total += apply(factors, operands.get(i));
        }

        System.out.println("total = " + total);
    }

    private static long apply(long[] numbers, char operand) {
        return Arrays.stream(numbers).reduce((n1, n2) ->
                switch (operand) {
                    case '+' -> n1 + n2;
                    case '*' -> n1 * n2;
                    default -> 0;
                }
        ).getAsLong();
    }
}
