import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class Solution {
    public List<List<String>> solveNQueens(int n) {
        List<List<String>> solutions = new ArrayList<>();
        if (n <= 0) {
            return solutions;
        }

        boolean[] cols = new boolean[n];
        boolean[] mainDiag = new boolean[2 * n - 1];
        boolean[] antiDiag = new boolean[2 * n - 1];
        int[] queensCol = new int[n];

        backtrack(0, n, cols, mainDiag, antiDiag, queensCol, solutions);
        return solutions;
    }

    private void backtrack(
            int row,
            int n,
            boolean[] cols,
            boolean[] mainDiag,
            boolean[] antiDiag,
            int[] queensCol,
            List<List<String>> solutions) {
        if (row == n) {
            solutions.add(buildBoard(queensCol, n));
            return;
        }

        for (int col = 0; col < n; col++) {
            int mainIdx = row - col + n - 1;
            int antiIdx = row + col;

            if (cols[col] || mainDiag[mainIdx] || antiDiag[antiIdx]) {
                continue;
            }

            cols[col] = true;
            mainDiag[mainIdx] = true;
            antiDiag[antiIdx] = true;
            queensCol[row] = col;

            backtrack(row + 1, n, cols, mainDiag, antiDiag, queensCol, solutions);

            cols[col] = false;
            mainDiag[mainIdx] = false;
            antiDiag[antiIdx] = false;
        }
    }

    private List<String> buildBoard(int[] queensCol, int n) {
        List<String> board = new ArrayList<>(n);
        char[] rowChars = new char[n];

        for (int r = 0; r < n; r++) {
            Arrays.fill(rowChars, '.');
            rowChars[queensCol[r]] = 'Q';
            board.add(new String(rowChars));
        }
        return board;
    }
}