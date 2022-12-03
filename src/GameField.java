public class GameField {
    private int [][] field;
    GameField() {
        initializeField();
    }
    private void initializeField() {
        field = new int[8][8];
        field[3][3] = 2;
        field[3][4] = 1;
        field[4][4] = 2;
        field[4][3] = 1;
        field[2][3] = 3;
        field[3][2] = 3;
        field[4][5] = 3;
        field[5][4] = 3;
    }
    public void makeMove(int posX, int posY, int value) {
        field[posX][posY] = value;
    }
    public void printField() {
        System.out.println("  1 2 3 4 5 6 7 8");
        for (int i = 0; i < 8; i++) {
            System.out.print((i + 1) + "|");
            for (int j = 0; j < 8; j++) {
                if (field[i][j] == 0) {
                    System.out.print(" |");
                } else if (field[i][j] == 1) {
                    System.out.print("x|");
                } else if (field[i][j] == 3) {
                    System.out.print("*|");
                } else {
                    System.out.print("o|");
                }
            }
            System.out.print("\n");
        }
    }
}
