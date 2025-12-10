package p2.a2x1.application;

import java.util.ArrayList;
import java.util.List;

import p2.a2x1.supportStuff.applicationSupport.Coordinate;

public class PathFinder implements PathFinder_I {
    List<Coordinate> path;
    int[][] mazeMap;
    Coordinate start;
    int sx;
    int sy;
    int dx;
    int dy;
    int s;
    
    public PathFinder( final Maze maze ) {
        mazeMap = maze.getMazeField();
        start = maze.getStart();
        sx = start.getX();
        sy = start.getY();
        dx = maze.getDestination().getX();
        dy = maze.getDestination().getY();
        s = 1;
        path = new ArrayList<Coordinate>();
    }
    
    @Override
    public List<Coordinate> getShortestPath() {
        
        boolean isFind = findWay(sx, sy, s);
        if (isFind) {
            return path;
        }
        return null;
    }
    
    private boolean findWay(int sx, int sy, int s) {
      
        if (sx < 0 || sy < 0 || sx > this.mazeMap.length-1 || sy > this.mazeMap[sx].length-1 ) {
            return false;
        }
        if (mazeMap[sx][sy] < s || mazeMap[sx][sy] == Integer.MIN_VALUE) {
            return false;
        }
        
        mazeMap[sx][sy] = s;
        
        if ((this.dx==sx && this.dy==sy) 
                || (findWay(sx - 1, sy, s+1)) 
                || (findWay(sx, sy - 1, s+1)) 
                || (findWay(sx + 1, sy, s+1)) 
                || (findWay(sx, sy + 1,  s+1)) 
    
        ) 
        {
            path.add(0,new Coordinate(sx, sy));
            
            return true;
        }
        return false;
    }

}
