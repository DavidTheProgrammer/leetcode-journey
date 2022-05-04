package com.learning.me.arrays;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

// https://leetcode.com/problems/min-cost-to-connect-all-points/
public class MinCostToConnectPoints {
    public static void main(String[] args) {
        var result = minCostConnectPoints(new int[][]{
            new int[]{2, -3},
            new int[]{-17, -8},
            new int[]{13, 8},
            new int[]{-17, -15},
        });

        System.out.println(result);
    }

    private static class Point {
        int x;
        int y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public boolean equals(Point p) {
            return (p.x == this.x) && (p.y == this.y);
        }
    }

    private static class Line {
        Point p1;
        Point p2;

        public Line(Point p1, Point p2) {
            this.p1 = p1;
            this.p2 = p2;
        }

        public int distance() {
            int xDistance = Math.abs(p1.x - p2.x);
            int yDistance = Math.abs(p1.y - p2.y);

            return xDistance + yDistance;
        }

        public boolean equals(Line l) {
            return (l.p1 == this.p1 && l.p2 == this.p2)
                || (l.p2 == this.p1 && l.p1 == this.p2);
        }
    }

    private static Line getShortestLine(List<Point> pointList, Point point) {
        Line shortestLine = null;
        for(Point p: pointList) {
            if(point.equals(p)) {
                continue;
            }

            Line line = new Line(point, p);
            if(shortestLine == null) {
                shortestLine = line;
                continue;
            }

            if(shortestLine.distance() > line.distance()) {
                shortestLine = line;
            }
        }

        return shortestLine;
    }

    public static int minCostConnectPoints(int[][] points) {
        List<Point> pointList = new ArrayList<Point>(points.length);
        for(int[] p: points) {
            pointList.add(new Point(p[0], p[1]));
        }

        int finalDistance = 0;
        HashSet<Point> connectedPoints = new HashSet<>();
        for(Point point: pointList) {
            if(connectedPoints.contains(point)) {
                continue;
            }

            Line line = getShortestLine(pointList, point);
            if(line != null) {
                connectedPoints.add(line.p1);
                connectedPoints.add(line.p2);
                finalDistance += line.distance();
            }
        }

        return finalDistance;
    }
}
