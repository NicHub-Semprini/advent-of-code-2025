package day_08;

import template.AbstractSolution;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class SolutionExtended extends AbstractSolution {

    public static void main(String... args) {

        // Inputs
        List<Point> boxes = new ArrayList<>();

        // Read input file
        String input = getFilePath("input.txt", SolutionBase.class);
        try (BufferedReader bf = new BufferedReader(new InputStreamReader(new FileInputStream(input)))) {
            List<String> lines = bf.lines().toList();
            for (String line : lines) {
                StringTokenizer st = new StringTokenizer(line, ",");
                boxes.add(new Point(st.nextToken(), st.nextToken(), st.nextToken()));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Solution
        long total = 1;

        // Calculate all point-to-point distances
        List<Distance> distances = new ArrayList<>();
        for (int i = 0; i < boxes.size(); i++) {
            for (int j = i + 1; j < boxes.size(); j++) {
                distances.add(new Distance(boxes.get(i), boxes.get(j), distance(boxes.get(i), boxes.get(j))));
            }
        }
        // Order distances by ascending order
        distances.sort(Comparator.comparing(d -> d.distance));

        List<Set<Point>> circuits = new ArrayList<>();
        int max = distances.size();
        for (int i = 0; i < max; i++) {
            Distance distance = distances.get(i);
            Set<Point> circuit1 = findCircuit(distance.p1, circuits);
            Set<Point> circuit2 = findCircuit(distance.p2, circuits);

            // No circuits
            if (circuit1.isEmpty() && circuit2.isEmpty()) {
                Set<Point> circuit = new HashSet<>();
                circuit.add(distance.p1);
                circuit.add(distance.p2);
                circuits.add(circuit);
            }

            // P1 in circuit
            else if (!circuit1.isEmpty()) {

                // P2 in same circuit
                if (isInCircuit(distance.p2, circuit1)) {
                    ;
                }

                // P2 not in circuit
                else if (circuit2.isEmpty()) {
                    circuit1.add(distance.p2);
                }

                // P2 in other circuit
                else if (!circuit2.isEmpty()) {
                    circuits.remove(circuit1);
                    circuits.remove(circuit2);
                    Set<Point> circuit = new HashSet<>();
                    circuit.addAll(circuit1);
                    circuit.addAll(circuit2);
                    circuits.add(circuit);
                }
            }

            // P2 in circuit
            else if (!circuit2.isEmpty()) {
                circuit2.add(distance.p1);
            }

            // Check if all boxes are connected
            if (circuits.size() == 1 && circuits.getFirst().size() == boxes.size()) {
                total = ((long) distance.p1.x) * ((long) distance.p2.x);
                break;
            }
        }

        System.out.println("total = " + total);
    }

    private static double distance(Point p1, Point p2) {
        return Math.sqrt(Math.pow(p1.x - p2.x, 2) + Math.pow(p1.y - p2.y, 2) + Math.pow(p1.z - p2.z, 2));
    }

    private static Set<Point> findCircuit(Point box, List<Set<Point>> circuits) {
        for (Set<Point> circuit : circuits) {
            for (Point p : circuit) {
                if (box.equals(p)) {
                    return circuit;
                }
            }
        }

        return new HashSet<>();
    }

    private static boolean isInCircuit(Point box, Set<Point> circuit) {
        for (Point p : circuit) {
            if (box.equals(p)) {
                return true;
            }
        }

        return false;
    }

    private static class Point {
        final int x;
        final int y;
        final int z;

        public Point(String x, String y, String z) {
            this(Integer.parseInt(x), Integer.parseInt(y), Integer.parseInt(z));
        }

        public Point(int x, int y, int z) {
            this.x = x;
            this.y = y;
            this.z = z;
        }

        @Override
        public boolean equals(Object o) {
            if (o == null || getClass() != o.getClass()) return false;
            Point point = (Point) o;
            return x == point.x && y == point.y && z == point.z;
        }

        @Override
        public int hashCode() {
            return Objects.hash(x, y, z);
        }

        @Override
        public String toString() {
            return "Point{" + "x=" + x + ", y=" + y + ", z=" + z + '}';
        }
    }

    private static class Distance {
        final Point p1;
        final Point p2;
        final double distance;

        public Distance(Point p1, Point p2, double distance) {
            this.p1 = p1;
            this.p2 = p2;
            this.distance = distance;
        }

        @Override
        public String toString() {
            return "Distance{" + "p1=" + p1 + ", p2=" + p2 + ", distance=" + distance + '}';
        }
    }
}
