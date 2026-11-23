class Solution {
    private final int[] rows = new int[9];
    private final int[] cols = new int[9];
    private final int[] boxes = new int[9];

    public void solveSudoku(char[][] board) {
        for (int r = 0; r < 9; r++) {
            for (int c = 0; c < 9; c++) {
                char ch = board[r][c];
                if (ch != '.') {
                    int bit = 1 << (ch - '1');
                    rows[r] |= bit;
                    cols[c] |= bit;
                    boxes[boxIndex(r, c)] |= bit;
                }
            }
        }
        backtrack(board);
    }

    private boolean backtrack(char[][] board) {
        int bestR = -1;
        int bestC = -1;
        int bestMask = 0;
        int minOptions = 10;

        for (int r = 0; r < 9; r++) {
            for (int c = 0; c < 9; c++) {
                if (board[r][c] == '.') {
                    int used = rows[r] | cols[c] | boxes[boxIndex(r, c)];
                    int availableMask = (~used) & 0x1FF;
                    int options = Integer.bitCount(availableMask);

                    if (options == 0) {
                        return false;
                    }
                    if (options < minOptions) {
                        minOptions = options;
                        bestR = r;
                        bestC = c;
                        bestMask = availableMask;
                        if (options == 1) {
                            break;
                        }
                    }
                }
            }
            if (minOptions == 1) {
                break;
            }
        }

        if (bestR == -1) {
            return true;
        }

        int bIdx = boxIndex(bestR, bestC);
        int mask = bestMask;

        while (mask != 0) {
            int bit = mask & -mask;
            int digitIndex = Integer.numberOfTrailingZeros(bit);
            board[bestR][bestC] = (char) ('1' + digitIndex);

            rows[bestR] |= bit;
            cols[bestC] |= bit;
            boxes[bIdx] |= bit;

            if (backtrack(board)) {
                return true;
            }

            rows[bestR] ^= bit;
            cols[bestC] ^= bit;
            boxes[bIdx] ^= bit;
            board[bestR][bestC] = '.';

            mask ^= bit;
        }

        return false;
    }

    private int boxIndex(int r, int c) {
        return (r / 3) * 3 + (c / 3);
    }
}