package pr.games;

public class TicTacToe {

    private int[][] grid = new int[3][3];

    public TicTacToe() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                grid[i][j] = 0;
            }
        }
    }

    public void newTurn(int i, int j, int player) {
        this.grid[i][j] = player;
        if (checkWin()) {

        }
    }

    private boolean checkWin() {

        for (int i = 0; i < 3; i++) {
            if (grid[i][0] != 0 && grid[i][0] == grid[i][1] && grid[i][0] == grid[i][2]) {
                return true;
            }
            if (grid[0][i] != 0 && grid[0][i] == grid[1][i] && grid[0][i] == grid[2][i]) {
                return true;
            }
        }
        return grid[1][1] != 0 && (grid[1][1] == grid[0][0] && grid[1][1] == grid[2][2] || grid[1][1] == grid[0][2] && grid[1][1] == grid[2][0]);
    }


}
