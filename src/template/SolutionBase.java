package template;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

public class SolutionBase extends AbstractSolution {

    public static void main(String... args) {

        // Inputs
        // TODO

        // Read input file
        String input = getFilePath("TODO", SolutionBase.class);
        try (BufferedReader bf = new BufferedReader(new InputStreamReader(new FileInputStream(input)))) {
            List<String> lines = bf.lines().toList();
            // TODO
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Solution
        // TODO
    }
}
