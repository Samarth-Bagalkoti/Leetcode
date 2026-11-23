class Solution {
    public boolean isValidSudoku(char[][] board) {
        if (board == null || board.length != 9 || board[0].length != 9) {
            return false;
        }

        int[] rows = new int[9];
        int[] cols = new int[9];
        int[] boxes = new int[9];

        for (int r = 0; r < 9; r++) {
            for (int c = 0; c < 9; c++) {
                char ch = board[r][c];
                if (ch == '.') {
                    continue;
                }
                if (ch < '1' || ch > '9') {
                    return false;
                }

                int bit = 1 << (ch - '1');
                int boxIdx = (r / 3) * 3 + (c / 3);

                if ((rows[r] & bit) != 0 || (cols[c] & bit) != 0 || (boxes[boxIdx] & bit) != 0) {
                    return false;
                }

                rows[r] |= bit;
                cols[c] |= bit;
                boxes[boxIdx] |= bit;
            }
        }

        return true;
    }
}