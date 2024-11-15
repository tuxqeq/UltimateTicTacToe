public class BigBoard {
    private SmallBoard[][] boards;
    private char winner;
    private boolean isComplete;

    public BigBoard() {
        boards = new SmallBoard[3][3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                boards[i][j] = new SmallBoard();
            }
        }
        winner = ' ';
        isComplete = false;
    }

    public SmallBoard getBoard(int row, int col) {
        return boards[row][col];
    }

    public boolean makeMove(int boardRow, int boardCol, int cellRow, int cellCol, char player) {
        if (!isComplete && boards[boardRow][boardCol].setCell(cellRow, cellCol, player)) {
            checkWinner();
            return true;
        }
        return false;
    }

    public void checkWinner() {
        // Check rows
        for (int i = 0; i < 3; i++) {
            if (boards[i][0].isComplete() && boards[i][0].getWinnerSymbol() == boards[i][1].getWinnerSymbol() && boards[i][1].getWinnerSymbol() == boards[i][2].getWinnerSymbol() && boards[i][0].getWinnerSymbol() != ' ') {
                winner = boards[i][0].getWinnerSymbol();
                isComplete = true;
                return;
            }
        }
        // Check columns
        for (int j = 0; j < 3; j++) {
            if (boards[0][j].isComplete() && boards[0][j].getWinnerSymbol() == boards[1][j].getWinnerSymbol() && boards[1][j].getWinnerSymbol() == boards[2][j].getWinnerSymbol() && boards[0][j].getWinnerSymbol() != ' ') {
                winner = boards[0][j].getWinnerSymbol();
                isComplete = true;
                return;
            }
        }
        // Check diagonals
        if (boards[0][0].isComplete() && boards[0][0].getWinnerSymbol() == boards[1][1].getWinnerSymbol() && boards[1][1].getWinnerSymbol() == boards[2][2].getWinnerSymbol() && boards[0][0].getWinnerSymbol() != ' ') {
            winner = boards[0][0].getWinnerSymbol();
            isComplete = true;
            return;
        }
        if (boards[0][2].isComplete() && boards[0][2].getWinnerSymbol() == boards[1][1].getWinnerSymbol() && boards[1][1].getWinnerSymbol() == boards[2][0].getWinnerSymbol() && boards[0][2].getWinnerSymbol() != ' ') {
            winner = boards[0][2].getWinnerSymbol();
            isComplete = true;
            return;
        }
        // Check for tie
        boolean tie = true;
        for (SmallBoard[] boardRow : boards) {
            for (SmallBoard board : boardRow) {
                if (!board.isComplete()) {
                    tie = false;
                    break;
                }
            }
        }
        if (tie) {
            isComplete = true;
        }
    }

    public char getWinner() {
        return winner;
    }

    public boolean isComplete() {
        return isComplete;
    }
}
