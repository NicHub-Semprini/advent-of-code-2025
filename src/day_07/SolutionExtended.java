package day_07;

import template.AbstractSolution;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

public class SolutionExtended extends AbstractSolution {

    public static void main(String... args) {

        // Inputs
        String[][] map = null;

        // Read input file
        String input = getFilePath("input.txt", SolutionExtended.class);
        try (BufferedReader bf = new BufferedReader(new InputStreamReader(new FileInputStream(input)))) {
            List<String> lines = bf.lines().toList();
            map = new String[lines.size()][lines.get(0).length()];
            for (int i = 0; i < lines.size(); i++) {
                for (int j = 0; j < lines.get(i).length(); j++) {
                    map[i][j] = String.valueOf(lines.get(i).charAt(j));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Solution
        long total = 0;

        for (int y = 1; y < map.length; y++) {
            for (int x = 0; x < map[y].length; x++) {
                String previous = map[y - 1][x];
                if (previous.equals(".")) { // Skip empty spaces
                    continue;
                } else if (previous.equals("S")) { // Start timeline
                    map[y][x] = String.valueOf(1);
                    continue;
                } else if (!previous.equals("^")) { // Propagate the beam
                    if (map[y][x].equals("^")) {
                        if (x - 1 >= 0) { // Split left
                            if (map[y][x - 1].equals(".")) {
                                map[y][x - 1] = previous;
                            } else {
                                map[y][x - 1] = String.valueOf(Long.parseLong(map[y][x - 1]) + Long.parseLong(previous));
                            }
                        }
                        if (x + 1 < map[y].length) { // Split right
                            if (map[y][x + 1].equals(".")) {
                                map[y][x + 1] = previous;
                            } else {
                                map[y][x + 1] = String.valueOf(Long.parseLong(map[y][x + 1]) + Long.parseLong(previous));
                            }
                        }
                    } else if (map[y][x].equals(".")) { // Propagate the beam
                        map[y][x] = previous;
                    } else { // Update keep all timelines
                        map[y][x] = String.valueOf(Long.parseLong(map[y][x]) + Long.parseLong(previous));
                    }
                    continue;
                }
            }
        }

        for (String s : map[map.length - 1]) {
            if (!s.equals(".")) {
                total += Long.parseLong(s);
            }
        }

        System.out.println("total = " + total);
    }
}
