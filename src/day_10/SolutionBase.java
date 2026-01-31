package day_10;

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
        List<int[]> indicators = new ArrayList<>();
        List<List<Button>> buttons = new ArrayList<>();

        // Read input file
        String input = getFilePath("input_short.txt", SolutionBase.class);
        try (BufferedReader bf = new BufferedReader(new InputStreamReader(new FileInputStream(input)))) {
            List<String> lines = bf.lines().toList();
            for (int i = 0; i < lines.size(); i++) {
                StringTokenizer st = new StringTokenizer(lines.get(i));
                // Indicator
                String indicator = st.nextToken();
                indicator = indicator.substring(1, indicator.length() - 1); // Remove [ ]
                int count = 0;
                int[] array = new int[indicator.toCharArray().length];
                for (char c : indicator.toCharArray()) {
                    if (c == '.') {
                        array[count] = 0;
                    } else {
                        array[count] = 1;
                    }
                    count++;
                }
                indicators.add(array);

                List<Button> button = new ArrayList<>();
                while (st.hasMoreTokens()) {
                    String token = st.nextToken();
                    // Voltage
                    if (token.charAt(0) == '{') {
                        break;
                    } else {
                        token = token.substring(1, token.length() - 1); // Remove ( )
                        button.add(new Button(Arrays.stream(token.split(",")).map(Integer::parseInt).toList()));
                    }
                }
                buttons.add(button);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Solution
        long total = 0;

        for (int i = 0; i < indicators.size(); i++) {
            int[] indRef = indicators.get(i);
            List<Button> buttonList = buttons.get(i);

            // Target status is all off
            if (areOff(indRef)) {
                continue;
            }

            long min = Long.MAX_VALUE;
            for (int j = 0; j < buttonList.size(); j++) {
                List<Button> clicked = new ArrayList<>();
                int[] lightsRef = Arrays.copyOf(indRef, indRef.length);
                long current = 0;
                for (int k = j; k < buttonList.size(); k++) {
                    // Try each button with all combinations of subsequent ones
                    Button ref = buttonList.get(k);
                    lightsRef = click(ref, lightsRef);
                    current += 1;

                    // TODO via!
                    clicked.add(ref);

                    // Early exit if is not a record
                    if (current >= min) {
                        break;
                    }

                    // Check if everything is off
                    if (areOff(lightsRef)) {
                        if (current < min) {
                            min = current;
                        }
                        break;
                    }

                    for (int index = k + 1; index < buttonList.size(); index++) {
                        long localCurrent = clickAllNext(index, buttonList, lightsRef, current, min, clicked);
                        if (localCurrent < min) {
                            min = localCurrent;
                        }
                    }
                }
            }
            System.out.println(min);

            total += min;
        }

        System.out.println("total = " + total);
    }

    private static int[] click(Button button, int[] lights) {
        for (int zwitch : button.switches) {
            lights[zwitch] = (lights[zwitch] + 1) % 2;
        }

        return lights;
    }

    private static boolean areOff(int[] lights) {
        for (int i = 0; i < lights.length; i++) {
            if (lights[i] != 0) {
                return false;
            }
        }

        return true;
    }

    private static long clickAllNext(int index, List<Button> buttonList, int[] lightsRef, long current, long min, List<Button> clicked/* TODO via! */) {
        long localMin = min;
        List<Button> subList = buttonList.subList(index, buttonList.size());

        // TODO via!
        List<Button> temp = new ArrayList<>();
        temp.addAll(clicked);
        temp.addAll(subList);
        System.out.println(temp);

        int[] lights = Arrays.copyOf(lightsRef, lightsRef.length);
        for (Button button : subList) {
            lights = click(button, lights);
            current += 1;

            // Early exit if is not a record
            if (current >= min) {
                break;
            }

            // Check if everything is off
            if (areOff(lights)) {
                if (current < localMin) {
                    localMin = current;
                }
                break;
            }
        }

        return localMin;
    }

    private static void print(int[] lights, List<Button> clicked, long clicks) {
        System.out.println(Arrays.stream(lights).boxed().toList() + " | " + clicks + " | " + clicked);
    }

    private static class Button {
        final List<Integer> switches;

        public Button(List<Integer> switches) {
            this.switches = switches;
        }

        @Override
        public String toString() {
            return "Button{" + "switches=" + switches + '}';
        }
    }
}
