class Solution {
    public int totalNQueens(int n) {
        if (n <= 0) {
            return 0;
        }

        boolean[] cols = new boolean[n];
        boolean[] mainDiag = new boolean[2 * n - 1];
        boolean[] antiDiag = new boolean[2 * n - 1];

        return backtrack(0, n, cols, mainDiag, antiDiag);
    }

    private int backtrack(
            int row,
            int n,
            boolean[] cols,
            boolean[] mainDiag,
            boolean[] antiDiag) {
        if (row == n) {
            return 1;
        }

        int count = 0;
        for (int col = 0; col < n; col++) {
            int mainIdx = row - col + n - 1;
            int antiIdx = row + col;

            if (cols[col] || mainDiag[mainIdx] || antiDiag[antiIdx]) {
                continue;
            }

            cols[col] = true;
            mainDiag[mainIdx] = true;
            antiDiag[antiIdx] = true;

            count += backtrack(row + 1, n, cols, mainDiag, antiDiag);

            cols[col] = false;
            mainDiag[mainIdx] = false;
            antiDiag[antiIdx] = false;
        }

        return count;
    }
}