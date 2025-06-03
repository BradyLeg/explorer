import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

public class ExplorerSearchTest {
    @Test
    public void testReachableArea_someUnreachable() {
        int[][] island = {
                { 1, 1, 1, 3, 1, 1 },
                { 3, 2, 3, 1, 3, 1 },
                { 1, 1, 1, 1, 3, 3 },
                { 3, 1, 2, 1, 0, 1 },
                { 1, 1, 1, 2, 1, 1 },
        };
        int actual = ExplorerSearch.reachableArea(island);
        assertEquals(14, actual);
    }

    // Add more tests here!
    // Come up with varied cases
    @Test
    public void testStart_originalStart() {
        int[][] island = {
                { 1, 1, 1, 3, 1, 1 },
                { 3, 2, 3, 1, 3, 1 },
                { 1, 1, 1, 1, 3, 3 },
                { 3, 1, 2, 1, 0, 1 },
                { 1, 1, 1, 2, 1, 1 },
        };
        int[] expected = { 3, 4 };

        assertArrayEquals(expected, ExplorerSearch.start(island));
    }

    @Test
    public void testStart_topLeft() {
        int[][] island = {
                { 0, 1, 1, 3, 1, 1 },
                { 3, 2, 3, 1, 3, 1 },
                { 1, 1, 1, 1, 3, 3 },
                { 3, 1, 2, 1, 3, 1 },
                { 1, 1, 1, 2, 1, 1 },
        };
        int[] expected = { 0, 0 };

        assertArrayEquals(expected, ExplorerSearch.start(island));
    }

    @Test
    public void testStart_none() {
        int[][] island = {
                { 1, 1, 1, 3, 1, 1 },
                { 3, 2, 3, 1, 3, 1 },
                { 1, 1, 1, 1, 3, 3 },
                { 3, 1, 2, 1, 1, 1 },
                { 1, 1, 1, 2, 1, 1 },
        };

        assertThrows(IllegalArgumentException.class, () -> ExplorerSearch.start(island));
    }

    @Test
    public void testMoves_allPossible() {
        int[][] island = {
                { 1, 1, 1, 3, 1, 1 },
                { 3, 2, 3, 1, 3, 1 },
                { 1, 1, 1, 3, 1, 2 },
                { 3, 1, 2, 1, 0, 1 },
                { 1, 1, 1, 2, 1, 2 },
        };

        int[] location = { 3, 4 };
        List<int[]> move = ExplorerSearch.moves(island, location);

        assertEquals(4, move.size());
        assertTrue(move.get(0)[0] == 2 && move.get(0)[1] == 4);
        assertTrue(move.get(1)[0] == 4 && move.get(1)[1] == 4);
        assertTrue(move.get(2)[0] == 3 && move.get(2)[1] == 3);
        assertTrue(move.get(3)[0] == 3 && move.get(3)[1] == 5);
    }

    @Test
    public void testMoves_bottomRightCorner() {
        int[][] island = {
                { 1, 1, 1, 3, 1, 1 },
                { 3, 2, 3, 1, 3, 1 },
                { 1, 1, 1, 3, 1, 2 },
                { 3, 1, 2, 1, 1, 1 },
                { 1, 1, 1, 2, 1, 0 },
        };

        int[] location = { 4, 5 };
        List<int[]> move = ExplorerSearch.moves(island, location);

        assertEquals(2, move.size());
        assertTrue(move.get(0)[0] == 3 && move.get(0)[1] == 5);
        assertTrue(move.get(1)[0] == 4 && move.get(1)[1] == 4);
    }

    @Test
    public void testMoves_mixedBlocks() {
        int[][] island = {
                { 1, 1, 0, 3, 1, 1 },
                { 3, 2, 2, 1, 3, 1 },
                { 1, 1, 1, 3, 1, 2 },
                { 3, 1, 2, 1, 1, 1 },
                { 1, 3, 1, 1, 1, 1 },
        };

        int[] location = { 0, 2 };
        List<int[]> move = ExplorerSearch.moves(island, location);

        assertEquals(1, move.size());
        assertTrue(move.get(0)[0] == 0 && move.get(0)[1] == 1);
    }

}
