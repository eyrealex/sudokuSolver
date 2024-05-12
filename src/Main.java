import constants.Constants;

public class Main {

    public static void main(String[] args) {

        // 2-dimensional array, first [] represents rows, second [] represents columns
        int[][] board = {
                {7, 0, 2, 0, 5, 0, 6, 0, 0},
                {0, 0, 0, 0, 0, 3, 0, 0, 0},
                {1, 0, 0, 0, 0, 9, 5, 0, 0},
                {8, 0, 0, 0, 0, 0, 0, 9, 0},
                {0, 4, 3, 0, 0, 0, 7, 5, 0},
                {0, 9, 0, 0, 0, 0, 0, 0, 8},
                {0, 0, 9, 7, 0, 0, 0, 0, 5},
                {0, 0, 0, 2, 0, 0, 0, 0, 0},
                {0, 0, 7, 0, 4, 0, 2, 0, 3}
        };

        System.out.println("\nPre-solved board\n");
        printBoard(board);

        if(solveBoard(board)){
            System.out.println("\nSolved Board\n");
        }
        else{
            System.out.println("Cannot solve board\n");
        }

        printBoard(board);
    }

    private static void printBoard(int[][] board) {
        for(int row = 0; row < Constants.GRID_SIZE; row++){
            if(row % 3 == 0 && row != 0){
                System.out.println("-----------");
            }
            for(int column = 0; column < Constants.GRID_SIZE; column++){
                if(column % 3 == 0 && column != 0){
                    System.out.print("|");
                }
                System.out.print(board[row][column]);
            }
            System.out.println();
        }
    }


    private static boolean isNumInRow(int[][] board, int number, int row){
        for(int i = 0; i < Constants.GRID_SIZE; i++){
            if(board[row][i] == number){
                return true;
            }
        }
        return false;
    }

    private static boolean isNumInColumn(int[][] board, int number, int column){
        for(int i = 0; i < Constants.GRID_SIZE; i++){
            if(board[i][column] == number){
                return true;
            }
        }
        return false;
    }

    private static boolean isNumInBox(int[][] board, int number, int row, int column){
        //formula to find top left box in a 3x3 grid
        int localBoxRow = row - row % 3; // pass in the row provided and subtract by the same row - % 3
        int localBoxColumn = column - column % 3;

        for(int i = localBoxRow; i < localBoxRow + 3; i++){
            for(int j = localBoxColumn; j < localBoxColumn + 3; j++){
                if(board[i][j] == number){
                    return true;
                }
            }
        }
        return false;
    }

    private static boolean isValidPlacement(int[][]board, int number, int row, int column){
        return !isNumInRow(board, number, row) &&
                !isNumInColumn(board, number, column) &&
                !isNumInBox(board, number, row, column);
    }

    private static boolean solveBoard(int[][] board){
        for(int row = 0; row < Constants.GRID_SIZE; row++){
            for(int column = 0; column < Constants.GRID_SIZE; column++){
                if(board[row][column] == 0){
                    for(int numToTry = 1; numToTry <= Constants.GRID_SIZE; numToTry++){
                        if(isValidPlacement(board, numToTry, row, column)){
                            board[row][column] = numToTry;

                            if(solveBoard(board)){
                                return true;
                            }
                            else{
                                board[row][column] = 0;
                            }
                        }
                    }
                    return false;
                }
            }
        }
        return true;
    }
}