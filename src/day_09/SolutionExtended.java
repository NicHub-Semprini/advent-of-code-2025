package day_09;

import template.AbstractSolution;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class SolutionExtended extends AbstractSolution {

    public static void main(String... args) {

        // Inputs
        List<Point> tiles = new ArrayList<>();
        Map<Integer, SortedSet<Point>> mapX = new HashMap<>();
        Map<Integer, SortedSet<Point>> mapY = new HashMap<>();

        // Read input file
        String input = getFilePath("input.txt", SolutionExtended.class);
        try (BufferedReader bf = new BufferedReader(new InputStreamReader(new FileInputStream(input)))) {
            List<String> lines = bf.lines().toList();
            Point previous = null;
            for (String line : lines) {
                StringTokenizer st = new StringTokenizer(line, ",");
                Point tile = new Point(st.nextToken(), st.nextToken());
                tiles.add(tile);
                // Add borders
                if (previous != null) {
                    if (tile.x == previous.x) {
                        if (tile.y > previous.y) {
                            for (int i = previous.y + 1; i < tile.y; i++) {
                                Point border = new Point(tile.x, i);
                                if (!mapX.containsKey(border.x)) {
                                    mapX.put(border.x, new TreeSet<>(new Point.PointComparatorY()));
                                }
                                mapX.get(border.x).add(border);
                                if (!mapY.containsKey(border.y)) {
                                    mapY.put(border.y, new TreeSet<>(new Point.PointComparatorX()));
                                }
                                mapY.get(border.y).add(border);
                            }
                        } else {
                            for (int i = tile.y + 1; i < previous.y; i++) {
                                Point border = new Point(tile.x, i);
                                if (!mapX.containsKey(border.x)) {
                                    mapX.put(border.x, new TreeSet<>(new Point.PointComparatorY()));
                                }
                                mapX.get(border.x).add(border);
                                if (!mapY.containsKey(border.y)) {
                                    mapY.put(border.y, new TreeSet<>(new Point.PointComparatorX()));
                                }
                                mapY.get(border.y).add(border);
                            }
                        }
                    } else if (tile.y == previous.y) {
                        if (tile.x > previous.x) {
                            for (int i = previous.x + 1; i < tile.x; i++) {
                                Point border = new Point(i, tile.y);
                                if (!mapX.containsKey(border.x)) {
                                    mapX.put(border.x, new TreeSet<>(new Point.PointComparatorY()));
                                }
                                mapX.get(border.x).add(border);
                                if (!mapY.containsKey(border.y)) {
                                    mapY.put(border.y, new TreeSet<>(new Point.PointComparatorX()));
                                }
                                mapY.get(border.y).add(border);
                            }
                        } else {
                            for (int i = tile.x + 1; i < previous.x; i++) {
                                Point border = new Point(i, tile.y);
                                if (!mapX.containsKey(border.x)) {
                                    mapX.put(border.x, new TreeSet<>(new Point.PointComparatorY()));
                                }
                                mapX.get(border.x).add(border);
                                if (!mapY.containsKey(border.y)) {
                                    mapY.put(border.y, new TreeSet<>(new Point.PointComparatorX()));
                                }
                                mapY.get(border.y).add(border);
                            }
                        }
                    }
                }
                if (!mapX.containsKey(tile.x)) {
                    mapX.put(tile.x, new TreeSet<>(new Point.PointComparatorY()));
                }
                mapX.get(tile.x).add(tile);
                if (!mapY.containsKey(tile.y)) {
                    mapY.put(tile.y, new TreeSet<>(new Point.PointComparatorX()));
                }
                mapY.get(tile.y).add(tile);
                previous = tile;
            }
            // Add border between first tile and last
            Point tile = tiles.getFirst();
            if (tile.x == previous.x) {
                if (tile.y > previous.y) {
                    for (int i = previous.y + 1; i < tile.y; i++) {
                        Point border = new Point(tile.x, i);
                        if (!mapX.containsKey(border.x)) {
                            mapX.put(border.x, new TreeSet<>(new Point.PointComparatorY()));
                        }
                        mapX.get(border.x).add(border);
                        if (!mapY.containsKey(border.y)) {
                            mapY.put(border.y, new TreeSet<>(new Point.PointComparatorX()));
                        }
                        mapY.get(border.y).add(border);
                    }
                } else {
                    for (int i = tile.y + 1; i < previous.y; i++) {
                        Point border = new Point(tile.x, i);
                        if (!mapX.containsKey(border.x)) {
                            mapX.put(border.x, new TreeSet<>(new Point.PointComparatorY()));
                        }
                        mapX.get(border.x).add(border);
                        if (!mapY.containsKey(border.y)) {
                            mapY.put(border.y, new TreeSet<>(new Point.PointComparatorX()));
                        }
                        mapY.get(border.y).add(border);
                    }
                }
            } else if (tile.y == previous.y) {
                if (tile.x > previous.x) {
                    for (int i = previous.x + 1; i < tile.x; i++) {
                        Point border = new Point(i, tile.y);
                        if (!mapX.containsKey(border.x)) {
                            mapX.put(border.x, new TreeSet<>(new Point.PointComparatorY()));
                        }
                        mapX.get(border.x).add(border);
                        if (!mapY.containsKey(border.y)) {
                            mapY.put(border.y, new TreeSet<>(new Point.PointComparatorX()));
                        }
                        mapY.get(border.y).add(border);
                    }
                } else {
                    for (int i = tile.x + 1; i < previous.x; i++) {
                        Point border = new Point(i, tile.y);
                        if (!mapX.containsKey(border.x)) {
                            mapX.put(border.x, new TreeSet<>(new Point.PointComparatorY()));
                        }
                        mapX.get(border.x).add(border);
                        if (!mapY.containsKey(border.y)) {
                            mapY.put(border.y, new TreeSet<>(new Point.PointComparatorX()));
                        }
                        mapY.get(border.y).add(border);
                    }
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        // Solution
        long total = 0;

        // Calculate all point-to-point distances
        List<Distance> distances = new ArrayList<>();
        for (int i = 0; i < tiles.size(); i++) {
            for (int j = i + 1; j < tiles.size(); j++) {
                distances.add(new Distance(tiles.get(i), tiles.get(j), distance(tiles.get(i), tiles.get(j))));
            }
        }
        // Order distances by ascending order
        distances.sort(Comparator.comparing((Distance d) -> d.distance).reversed());

        for (Distance distance : distances) {
            Point target;
            Point next;
            // Try to draw a rectangle from P2 to P1
            target = distance.p1;
            // 1. X coordinates
            next = distance.p2;
            if (target.x < next.x) {
                do {
                    next = nextLeft(next, mapY.get(next.y));
                } while (next != null && target.x < next.x);

            } else if (target.x > next.x) {
                do {
                    next = nextRight(next, mapY.get(next.y));
                } while (next != null && target.x > next.x);
            }
            // P1 and P2 cannot be connected on X coordinate
            if (next == null) {
                continue;
            }

            // 2. Y coordinate
            next = distance.p2;
            if (target.y < next.y) {
                do {
                    next = nextUp(next, mapX.get(next.x));
                } while (next != null && target.y < next.y);

            } else if (target.y > next.y) {
                do {
                    next = nextDown(next, mapX.get(next.x));
                } while (next != null && target.y > next.y);
            }
            // P1 and P2 cannot be connected on Y coordinate
            if (next == null) {
                continue;
            }

            // Try to draw a rectangle from P1 to P2
            target = distance.p2;
            // 1. X coordinates
            next = distance.p1;
            if (target.x < next.x) {
                do {
                    next = nextLeft(next, mapY.get(next.y));
                } while (next != null && target.x < next.x);

            } else if (target.x > next.x) {
                do {
                    next = nextRight(next, mapY.get(next.y));
                } while (next != null && target.x > next.x);
            }
            // P1 and P2 cannot be connected on X coordinate
            if (next == null) {
                continue;
            }

            // 2. Y coordinate
            next = distance.p1;
            if (target.y < next.y) {
                do {
                    next = nextUp(next, mapX.get(next.x));
                } while (next != null && target.y < next.y);

            } else if (target.y > next.y) {
                do {
                    next = nextDown(next, mapX.get(next.x));
                } while (next != null && target.y > next.y);
            }
            // P1 and P2 cannot be connected on Y coordinate
            if (next == null) {
                continue;
            }

            // Rectangle is valid
            total = area(distance.p1, distance.p2);
            break;
        }
        System.out.println("total = " + total);
        // TODO 45631422 too low
        // TODO 1787070717 too high
        // TODO 4638152834 too high
        // TODO top 10 aree
        //  [4638152834, 3930943764, 3572799186, 2727242700, 2622452400, 1909876878, 1903109208, 1796808234, 1787070717, 45631422]
    }

    private static double distance(Point p1, Point p2) {
        return Math.sqrt(Math.pow(p1.x - p2.x, 2) + Math.pow(p1.y - p2.y, 2));
    }

    private static Point nextLeft(Point ref, SortedSet<Point> pointsByX) {
        SortedSet<Point> leftOnes = pointsByX.headSet(ref);

        // No more points on left
        if (leftOnes.isEmpty()) {
            return null;
        }

        return leftOnes.getLast();
    }

//    private static Point nextLeft(Point ref, List<Point> pointsByX) {
//        int index = pointsByX.indexOf(ref);
//        for (int i = index - 1; i >= 0; i--) {
//            if (pointsByX.get(i).y == ref.y) {
//                return pointsByX.get(i);
//            }
//        }
//
//        return null;
//    }

    private static Point nextRight(Point ref, SortedSet<Point> pointsByX) {
        SortedSet<Point> rightOnes = new TreeSet<>(pointsByX.tailSet(ref));
        rightOnes.removeFirst();

        // No more points on left
        if (rightOnes.isEmpty()) {
            return null;
        }

        return rightOnes.getFirst();
    }

//    private static Point nextRight(Point ref, List<Point> pointsByX) {
//        int index = pointsByX.indexOf(ref);
//        for (int i = index + 1; i < pointsByX.size(); i++) {
//            if (pointsByX.get(i).y == ref.y) {
//                return pointsByX.get(i);
//            }
//        }
//
//        return null;
//    }

    private static Point nextUp(Point ref, SortedSet<Point> pointsByY) {
        SortedSet<Point> upOnes = pointsByY.headSet(ref);

        // No more points on left
        if (upOnes.isEmpty()) {
            return null;
        }

        return upOnes.getLast();
    }

//    private static Point nextUp(Point ref, List<Point> pointsByY) {
//        int index = pointsByY.indexOf(ref);
//        for (int i = index - 1; i >= 0; i--) {
//            if (pointsByY.get(i).x == ref.x) {
//                return pointsByY.get(i);
//            }
//        }
//
//        return null;
//    }

    private static Point nextDown(Point ref, SortedSet<Point> pointsByY) {
        SortedSet<Point> downOnes = new TreeSet<>(pointsByY.tailSet(ref));
        downOnes.removeFirst();

        // No more points on left
        if (downOnes.isEmpty()) {
            return null;
        }

        return downOnes.getFirst();
    }

//    private static Point nextDown(Point ref, List<Point> pointsByY) {
//        int index = pointsByY.indexOf(ref);
//        for (int i = index + 1; i < pointsByY.size(); i++) {
//            if (pointsByY.get(i).x == ref.x) {
//                return pointsByY.get(i);
//            }
//        }
//
//        return null;
//    }

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

        public static class PointComparatorX implements Comparator<Point> {

            @Override
            public int compare(Point o1, Point o2) {
                return Integer.compare(o1.x, o2.x);
            }
        }

        public static class PointComparatorY implements Comparator<Point> {

            @Override
            public int compare(Point o1, Point o2) {
                return Integer.compare(o1.y, o2.y);
            }
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
