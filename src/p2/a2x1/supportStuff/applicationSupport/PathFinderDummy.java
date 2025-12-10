package p2.a2x1.supportStuff.applicationSupport;

import java.util.ArrayList;
import java.util.List;

import p2.a2x1.application.Maze;
import p2.a2x1.application.PathFinder_I;

/**
 * Implementation of the pathfinding algorithm to find the shortest path in a
 * maze.
 * <p>
 * This class uses the Wave Algorithm (also known as Lee Algorithm or BFS based
 * approach) which consists of two steps: 1. Mapping the maze with distances
 * from the start point. 2. Backtracking from the destination to the start to
 * reconstruct the path.
 */
public class PathFinderDummy implements PathFinder_I {

    List<Coordinate> path;
    int[][] mazeMap;
    Coordinate start;
    int sx;
    int sy;
    int dx;
    int dy;
    int s;

    /**
     * Constructs the PathFinder with the given maze. Initializes the map and
     * coordinates.
     *
     * @param maze The maze object containing the grid, start, and destination
     *             points.
     */
    public PathFinderDummy(final Maze maze) {
        mazeMap = maze.getMazeField();
        start = maze.getStart();
        sx = start.getX();
        sy = start.getY();
        dx = maze.getDestination().getX();
        dy = maze.getDestination().getY();
        s = 1;
        path = new ArrayList<Coordinate>();
    }

    /**
     * Calculates and returns the shortest path from start to destination.
     *
     * @return A list of coordinates representing the path, or null if no path is
     *         found.
     */
    @Override
    public List<Coordinate> getShortestPath() {
        mapeMaze(sx, sy, s);
        int steps = mazeMap[dx][dy];
        boolean isFind = findWay(dx, dy, steps);

        if (isFind) {
            return path;
        }
        return null;
    }

    /**
     * Recursively maps the maze by assigning step counts to each reachable cell.
     * Stops if a wall is hit or if a shorter path to the current cell was already
     * found.
     *
     * @param sx Current X coordinate.
     * @param sy Current Y coordinate.
     * @param s  Current step count (distance from start).
     */
    private void mapeMaze(int sx, int sy, int s) {

        if (sx < 0 || sy < 0 || sx >= mazeMap.length || sy >= mazeMap[0].length) {
            return;
        }
        if (mazeMap[sx][sy] == Integer.MIN_VALUE) {
            return;
        }
        if (s >= mazeMap[sx][sy]) {
            return;
        }

        mazeMap[sx][sy] = s;

        mapeMaze(sx - 1, sy, s + 1);
        mapeMaze(sx, sy - 1, s + 1);
        mapeMaze(sx + 1, sy, s + 1);
        mapeMaze(sx, sy + 1, s + 1);

    }

    /**
     * Reconstructs the path by backtracking from the destination to the start. It
     * looks for a neighbor with a value of (currentStep - 1).
     *
     * @param sx Current X coordinate (starts at destination).
     * @param sy Current Y coordinate (starts at destination).
     * @param s  Current step value at the coordinate.
     * @return true if the start is reached, false otherwise.
     */
    private boolean findWay(int sx, int sy, int s) {

        path.add(0, new Coordinate(sx, sy));

        if (s == 1) { // basisfall
            return true;
        }

        int target = s - 1;

        if (sx - 1 >= 0 && mazeMap[sx - 1][sy] == target) {
            return findWay(sx - 1, sy, s - 1);
        } else if (sy - 1 >= 0 && mazeMap[sx][sy - 1] == target) {
            return findWay(sx, sy - 1, s - 1);
        } else if (sx + 1 < mazeMap.length && mazeMap[sx + 1][sy] == target) {
            return findWay(sx + 1, sy, s - 1);
        } else if (sy + 1 < mazeMap[0].length && mazeMap[sx][sy + 1] == target) {
            return findWay(sx, sy + 1, s - 1);
        }

        return false;

    }

}