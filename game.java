/**
 * This class represents a game board for a Four int the row game.
 */
public class game {
    private int NumberOfRow;// Number of rows in the game board
    private int NumberOfColumn;// Number of columns in the game board
    private int[][] matrix;
    private int NUMBERTOWIN = 4;// Number of consecutive circles needed to win
    int palyerOne = 1;// Identifier for player one
    int playerTwo = 2;// Identifier for player two


    /**
     * Constructor to initialize the game board with the given number of rows and columns.
    * @param row Number of rows in the game board
    * @param column Number of columns in the game board
    */
    public game(int row, int column){
        this.NumberOfRow = row;
        this.NumberOfColumn = column;
        this.matrix = new int[NumberOfRow][NumberOfColumn];
    }
   
    /**
     * Method to get the value at the specified row and column in the game board
     * @param r Row index
     * @param c Column index
     * @return Value at the specified position
     */
    public int getValueAt(int r,int c){
        return matrix[r][c];
    }
    /**
     * Method to clean the game board by setting all positions to empty.
     */
    public void cleanbord(){
        for(int i = 0; i < NumberOfRow;i++){
            for(int j = 0;j<NumberOfColumn;j++){
                matrix[i][j] = 0;// 0 represents an empty position
            }
        }
    }

    /**
     * Method to check if the game board is full.
     * @return True if the board is full, false otherwise
     */
    public boolean isBoardFull() {
        for (int col = 0; col < NumberOfColumn; col++) {
            for (int row = 0; row < NumberOfRow; row++) {
                if (getValueAt(row, col) == 0) {
                    return false;// If any position is empty, the board is not full
                }
            }
        }
        // All positions are filled, the board is full
        return true;
    }


    /**
     * Method to find the empty row in a specified column.
     * @param column Column index
     * @return Index of the empty row, or -1 if the column is full
     */
    public int findEmptyRow(int column) {
        for(int row = NumberOfRow - 1; row >= 0 ;row--){
            if(getValueAt(row, column) == 0){
                return row;
            }
        }
        return -1;
    }
    /**
     * Method to add a player's circle to the specified column.
     * @param column Column index
     * @param player Player identifier (1 or 2)
     */
    public void addPlayerCercle(int column,int player){
        int row = findEmptyRow(column);
        matrix[row][column] = player;
    }

    /**
     * Method to check if any player has won horizontally.
     * @return 1 to player 1 win, 2 for player 2 win ,0 for no player has won and game continue
     */
    public int CheckRowWin(){
        int res = 0;
        for (int row = 0; row < NumberOfRow; row++) {
            for (int column = 0; column <= NumberOfColumn - NUMBERTOWIN; column++) {
                boolean player1Win = true;
                boolean player2Win = true;
                //move the number that need to win and check if one of this not the player number it make the flag false
                for (int i = 0; i < NUMBERTOWIN; i++) {
                    if (matrix[row][column + i] != palyerOne) {
                        player1Win = false;
                    }
                    if (matrix[row][column + i] != playerTwo) {
                        player2Win = false;
                    }
                }
                //check if the all of them in the row the player win 
                if (player1Win) {
                    res = palyerOne;
                    return res;
                }
                if (player2Win) {
                    res = playerTwo;
                    return res;
                }
            }
        }
        return res;
    }
    /**
     * Method to check if any player has won vertically.
     * @return 1 to player 1 win, 2 for player 2 win ,0 for no player has won and game continue
     */
    public int CheckColumWin(){
        int res = 0;
        for (int column = 0; column < NumberOfColumn; column++) {
            for (int row = 0; row <= NumberOfRow - NUMBERTOWIN; row++) {
                boolean player1Win = true;
                boolean player2Win = true;
                //move the number that need to win and check if one of this not the player number it make the flag false
                for (int i = 0; i < NUMBERTOWIN; i++) {
                    if (matrix[row+i][column] != palyerOne) {
                        player1Win = false;
                    }
                    if (matrix[row+i][column] != playerTwo) {
                        player2Win = false;
                    }
                }
                if (player1Win) {
                    res = palyerOne;
                    return res;
                }
                if (player2Win) {
                    res = playerTwo;
                    return res;
                }
            }
        }
        return res;
    }

    /**
     * Method to check if any player has won diagonally.
     * @return 1 to player 1 win, 2 for player 2 win ,0 for no player has won and game continue
     */
    public int CheckDiagonalWin() {
        int res = 0;
    
        // Check diagonals from top-left to bottom-right
        for (int row = 0; row <= NumberOfRow - NUMBERTOWIN; row++) {
            for (int column = 0; column <= NumberOfColumn - NUMBERTOWIN; column++) {
                boolean player1Win = true;
                boolean player2Win = true;
                for (int i = 0; i < NUMBERTOWIN; i++) {
                    if (matrix[row + i][column + i] != palyerOne) {
                        player1Win = false;
                    }
                    if (matrix[row + i][column + i] != playerTwo) {
                        player2Win = false;
                    }
                }
                if (player1Win) {
                    res = palyerOne;
                    return res;
                }
                if (player2Win) {
                    res = playerTwo;
                    return res;
                }
            }
        }
    
        // Check diagonals from top-right to bottom-left
        for (int row = 0; row <= NumberOfRow - NUMBERTOWIN; row++) {
            for (int column = NUMBERTOWIN - 1; column < NumberOfColumn; column++) {
                boolean player1Win = true;
                boolean player2Win = true;
                for (int i = 0; i < NUMBERTOWIN; i++) {
                    if (matrix[row + i][column - i] != palyerOne) {
                        player1Win = false;
                    }
                    if (matrix[row + i][column - i] != playerTwo) {
                        player2Win = false;
                    }
                }
                if (player1Win) {
                    res = palyerOne;
                    return res;
                }
                if (player2Win) {
                    res = playerTwo;
                    return res;
                }
            }
        }
    
        return res;
    }
    /**
     * Method to check if any player has won the game.
     * @return Identifier of the winning player, or 0 if no player has won
     */
    public int checkWin(){
        if(this.CheckColumWin() == palyerOne || this.CheckRowWin() == palyerOne|| this.CheckDiagonalWin() == palyerOne){
            return palyerOne;
        }
        if(this.CheckColumWin() == playerTwo || this.CheckRowWin() == playerTwo|| this.CheckDiagonalWin() == playerTwo){
            return playerTwo;
        }
        else{
            return 0;
        }
    }


    /**
     * Method to represent the game board as a string.
     * @return String representation of the game board
     */
    @Override
    public String toString() {
        String result = "";
        for (int row = 0; row < NumberOfRow; row++) {
            for (int column = 0; column < NumberOfColumn; column++) {
                result += matrix[row][column] + " ";
            }
            result += "\n";
        }
        return result;
    }
}