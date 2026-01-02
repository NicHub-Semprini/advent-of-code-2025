package day_09;

import template.AbstractSolution;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;

public class SolutionBase extends AbstractSolution {

    public static void main(String... args) {

        // Inputs
        List<Point> redPoints = new ArrayList<>();

        // Read input file
        String input = getFilePath("input.txt", SolutionBase.class);
        try (BufferedReader bf = new BufferedReader(new InputStreamReader(new FileInputStream(input)))) {
            List<String> lines = bf.lines().toList();
            for(String line : lines) {
                String[] coord = line.split(",");
                redPoints.add(new Point(coord[0], coord[1]));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Solution
        long area = 0;

        // Calculate distance between all couple of points
        List<Distance> distances = new ArrayList<>();
        for(int i = 0; i < redPoints.size(); i++) {
            Point pointA = redPoints.get(i);
            for (int j = i + 1; j < redPoints.size(); j++) {
                Point pointB = redPoints.get(j);
                distances.add(new Distance(pointA, pointB, distance(pointA, pointB)));
            }
        }

        // Sort distances and get the biggest
        distances.sort(Comparator.comparingDouble(d -> d.distance));
        Distance max = distances.getLast();
        area = area(max.p1, max.p2);

        System.out.println("area = " + area);
    }

    private static double distance(Point p1, Point p2) {
        return Math.sqrt(Math.pow(p1.x - p2.x, 2) + Math.pow(p1.y - p2.y, 2));
    }

    private static long area(Point p1, Point p2) {
        long b = Math.abs(p1.x - p2.x) + 1;
        long h = Math.abs(p1.y - p2.y) + 1;
        return b * h;
    }

    private static class Point {
        final int x;
        final int y;

        public Point(String x, String y) {
            this(Integer.parseInt(x), Integer.parseInt(y));
        }

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public boolean equals(Object o) {
            if (o == null || getClass() != o.getClass()) return false;
            Point point = (Point) o;
            return x == point.x && y == point.y;
        }

        @Override
        public int hashCode() {
            return Objects.hash(x, y);
        }

        @Override
        public String toString() {
            return "Point{" + "x=" + x + ", y=" + y + '}';
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
