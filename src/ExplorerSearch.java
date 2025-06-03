import java.util.ArrayList;
import java.util.List;

public class ExplorerSearch {

    /**
     * Returns how much land area an explorer can reach on a rectangular island.
     * 
     * The island is represented by a rectangular int[][] that contains
     * ONLY the following nunbers:
     * 
     * '0': represents the starting location of the explorer
     * '1': represents a field the explorer can walk through
     * '2': represents a body of water the explorer cannot cross
     * '3': represents a mountain the explorer cannot cross
     * 
     * The explorer can move one square at a time: up, down, left, or right.
     * They CANNOT move diagonally.
     * They CANNOT move off the edge of the island.
     * They CANNOT move onto a a body of water or mountain.
     * 
     * This method should return the total number of spaces the explorer is able
     * to reach from their starting location. It should include the starting
     * location of the explorer.
     * 
     * For example
     * 
     * @param island the locations on the island
     * @return the number of spaces the explorer can reach
     */
    public static int reachableArea(int[][] island) {
        int[] starting = start(island);
        boolean[][] visited = new boolean[island.length][island[0].length];

        return reachableArea(island, starting, visited);
    }

    public static int reachableArea(int[][] island, int[] location, boolean[][] visited) {
        int r = location[0];
        int c = location[1];
        if (visited[r][c]) {
            return 0;
        }

        visited[r][c] = true;
        int count = 1;
        List<int[]> paths = moves(island, location);
        for (int[] path : paths) {
            count += reachableArea(island, path, visited);
        }

        return count;
    }

    public static List<int[]> moves(int[][] island, int[] location) {
        int r = location[0];
        int c = location[1];
        List<int[]> next = new ArrayList<>();

        int newR = r - 1;
        int newC = c;
        if (newR >= 0 && island[newR][newC] == 1) {
            next.add(new int[] { newR, newC });
        }

        newR = r + 1;
        newC = c;
        if (newR < island.length && island[newR][newC] == 1) {
            next.add(new int[] { newR, newC });
        }

        newR = r;
        newC = c - 1;
        if (newC >= 0 && island[newR][newC] == 1) {
            next.add(new int[] { newR, newC });
        }

        newR = r;
        newC = c + 1;
        if (newC < island[newR].length && island[newR][newC] == 1) {
            next.add(new int[] { newR, newC });
        }

        return next;
    }

    public static int[] start(int[][] island) {

        for (int r = 0; r < island.length; r++) {
            for (int c = 0; c < island[r].length; c++) {
                if (island[r][c] == 0) {
                    return new int[] { r, c };
                }
            }
        }
        throw new IllegalArgumentException();
    }
}
