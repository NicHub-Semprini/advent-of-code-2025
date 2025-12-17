package day_07;

import template.AbstractSolution;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

public class SolutionBase extends AbstractSolution {

    public static void main(String... args) {

        // Inputs
        char[][] map = null;

        // Read input file
        String input = getFilePath("input.txt", SolutionBase.class);
        try (BufferedReader bf = new BufferedReader(new InputStreamReader(new FileInputStream(input)))) {
            List<String> lines = bf.lines().toList();
            map = new char[lines.size()][lines.get(0).length()];
            for (int i = 0; i < lines.size(); i++) {
                map[i] = lines.get(i).toCharArray();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Solution
        long total = 0;

        for (int y = 1; y < map.length; y++) {
            for (int x = 0; x < map[y].length; x++) {
                switch (map[y - 1][x]) {
                    case '.':
                        continue;
                    case 'S':
                        map[y][x] = '|';
                        break;
                    case '|':
                        if (map[y][x] == '^') {
                            total += 1;
                            if (x - 1 >= 0) {
                                map[y][x - 1] = '|';
                            }
                            if (x + 1 < map[y].length) {
                                map[y][x + 1] = '|';
                            }
                        } else if (map[y][x] == '.') {
                            map[y][x] = '|';
                        }
                        break;
                }
            }
        }

        System.out.println("total = " + total);
    }
}
