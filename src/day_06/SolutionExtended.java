package day_06;

import template.AbstractSolution;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SolutionExtended extends AbstractSolution {

    public static void main(String... args) {

        // Inputs
        List<String[]> numbers = new ArrayList<>();
        List<Character> operands = new ArrayList<>();
        List<Integer> paddings = new ArrayList<>();

        // Read input file
        String input = getFilePath("input.txt", SolutionExtended.class);
        try (BufferedReader bf = new BufferedReader(new InputStreamReader(new FileInputStream(input)))) {
            List<String> lines = bf.lines().toList();
            for (String line : lines) {
                if (!line.startsWith("*") && !line.startsWith("+")) {
                    continue;
                }

                // Read operands keeping track of padding
                int count = 1;
                char operand = line.charAt(0);
                for (int i = 1; i < line.length(); i++) {
                    if (line.charAt(i) == ' ') {
                        count += 1;
                    } else if (line.charAt(i) == '+' || line.charAt(i) == '*') {
                        count -= 1;
                        paddings.add(count);
                        count = 1;
                        operands.add(operand);
                        operand = line.charAt(i);
                    }
                }
                paddings.add(count);
                operands.add(operand);
            }
            for (String line : lines) {
                if (!line.startsWith("*") && !line.startsWith("+")) {
                    List<String> nums = new ArrayList<>();
                    int index = 0;
                    for (int padding : paddings) {
                        nums.add(line.substring(index, Math.min(index + padding, line.length())));
                        index = index + padding + 1;
                    }
                    String[] array = new String[nums.size()];
                    for (int i = 0; i < array.length; i++) {
                        array[i] = nums.get(i);
                    }
                    numbers.add(array);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        for (String[] n : numbers) {
            System.out.println(Arrays.stream(n).toList());
        }
        System.out.println(operands);
        System.out.println(paddings);

        // Solution
        long total = 0;

        for (int i = 0; i < numbers.get(0).length; i++) {
            String[] nums = new String[numbers.size()];
            for (int j = 0; j < numbers.size(); j++) {
                nums[j] = numbers.get(j)[i];
            }
            long[] factors = calculateFactors(nums);
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

    private static long[] calculateFactors(String[] numbers) {
        // Calculate the longest number (in terms of digits)
        int maxL = 0;
        for (String n : numbers) {
            if (n.length() > maxL)
                maxL = n.length();
        }

        // Assemble the N-th digits
        long[] factors = new long[maxL];
        for (int i = 0; i < maxL; i++) {
            StringBuilder sb = new StringBuilder(factors.length);
            for (int j = 0; j < numbers.length; j++) {
                char[] digits = numbers[j].toCharArray();
                if (digits.length > i) {
                    sb.append(digits[i]);
                }
            }
            factors[i] = Long.parseLong(sb.toString().trim());
        }

        return factors;
    }
}
