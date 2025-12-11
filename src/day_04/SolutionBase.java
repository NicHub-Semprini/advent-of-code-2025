package day_04;

import template.AbstractSolution;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

public class SolutionBase extends AbstractSolution {

    public static void main(String... args) {

        // Inputs
        char[][] rolls = null;

        // Read input file
        String input = getFilePath("input.txt", SolutionBase.class);
        try (BufferedReader bf = new BufferedReader(new InputStreamReader(new FileInputStream(input)))) {
            List<String> lines = bf.lines().toList();
            rolls = new char[lines.size()][lines.getFirst().length()];
            int i = 0;
            for (String line : lines) {
                rolls[i] = line.toCharArray();
                i++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Solution
        long total = 0;
        for (int y = 0; y < rolls.length; y++) {
            for (int x = 0; x < rolls[y].length; x++) {

                // Consider only rolls
                if (rolls[y][x] != '@') {
                    continue;
                }

                int count = 0;

                // N
                if (isRoll(rolls, x, y, 0, -1, rolls[y].length, rolls.length)) {
                    count += 1;
                }
                // NE
                if (isRoll(rolls, x, y, 1, -1, rolls[y].length, rolls.length)) {
                    count += 1;
                }
                // E
                if (isRoll(rolls, x, y, 1, 0, rolls[y].length, rolls.length)) {
                    count += 1;
                }
                // SE
                if (isRoll(rolls, x, y, 1, 1, rolls[y].length, rolls.length)) {
                    count += 1;
                }
                // S
                if (isRoll(rolls, x, y, 0, 1, rolls[y].length, rolls.length)) {
                    count += 1;
                }
                // SW
                if (isRoll(rolls, x, y, -1, 1, rolls[y].length, rolls.length)) {
                    count += 1;
                }
                // W
                if (isRoll(rolls, x, y, -1, 0, rolls[y].length, rolls.length)) {
                    count += 1;
                }
                // NW
                if (isRoll(rolls, x, y, -1, -1, rolls[y].length, rolls.length)) {
                    count += 1;
                }

                if (count < 4) {
                    total += 1;
                }
            }
        }

        System.out.println("total = " + total);
    }

    private static int pruneCoordinate(int coord, int min, int max) {
        return Math.min(Math.max(coord, min), max - 1);
    }

    private static boolean isRoll(char[][] matrix, int x, int y, int deltaX, int deltaY, int maxX, int maxY) {
        int targetX = pruneCoordinate(x + deltaX, 0, maxX);
        int targetY = pruneCoordinate(y + deltaY, 0, maxY);

        // check corner case (same coordinates)
        if ((deltaX != 0 && x == targetX) || (deltaY != 0 && y == targetY)) {
            return false;
        }

        return matrix[targetY][targetX] == '@';
    }
}
