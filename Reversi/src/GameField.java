import java.util.List;
import java.util.ArrayList;
/*
 * класс GameField, класс игрового поля, содержит поля и методы для отрисовки игрового поля на консоли
 * и вспомогательные методы
 * */
public class GameField {
    private int [][] field;
    private List<Slot> possibleMoves;
    private List<List<Slot>> tilesToFlip;
    public boolean flag;
    GameField() {
        initializeField();
        tilesToFlip = new ArrayList();
        ArrayList<Slot> arr = new ArrayList<Slot>();
        arr.add(new Slot(3,3));
        tilesToFlip.add(arr);
        arr = new ArrayList<Slot>();
        arr.add(new Slot(3,3));
        tilesToFlip.add(arr);
        arr = new ArrayList<Slot>();
        arr.add(new Slot(4,4));
        tilesToFlip.add(arr);
        arr = new ArrayList<Slot>();
        arr.add(new Slot(4,4));
        tilesToFlip.add(arr);
        arr = new ArrayList<Slot>();
        possibleMoves = new ArrayList<Slot>();
        possibleMoves.add(new Slot(2,3));
        possibleMoves.add(new Slot(3,2));
        possibleMoves.add(new Slot(4,5));
        possibleMoves.add(new Slot(5,4));
        flag = true;
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
    public void makeMove(int posX, int posY, int player) {
        field[posX][posY] = player;
        int i = 0;
        while (possibleMoves.get(i).x != posX || possibleMoves.get(i).y != posY) {
            i++;
        }
        for (Slot sl : tilesToFlip.get(i)) {
            field[sl.x][sl.y] = player;
        }
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
    public void renewField() {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (field[i][j] == 3) {
                    field[i][j] = 0;
                }
            }
        }
        tilesToFlip.clear();
        possibleMoves.clear();
    }
    public void calculatePossibleMoves(int player, int otherPlayer) {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (isValidMove(player, otherPlayer, i, j)) {
                    possibleMoves.add(new Slot(i, j));
                }
            }
        }
        if (possibleMoves.isEmpty()) {
            flag = false;
        } else {
            flag = true;
        }
    }
    private boolean posExists(int x, int y) {
        return (x >= 0 && x < 8 && y >= 0 && y < 8);
    }
    public boolean isValidMove(int player, int otherPlayer, int xStart, int yStart) {
        ArrayList<Slot> arr = new ArrayList<Slot>();
        if (field[xStart][yStart] != 0 || !posExists(xStart, yStart)) {
            return false;
        }
        field[xStart][yStart] = player;
        int[][] directions = { {0, 1}, {1, 1}, {1, 0}, {1, -1}, {0, -1}, {-1, -1}, {-1, 0}, {-1, 1} };
        int x, y;
        for (int i = 0; i < directions.length; i++) {
            x = xStart + directions[i][0];
            y = yStart + directions[i][1];
            if (!posExists(x, y)) {
                continue;
            }
            while (field[x][y] == otherPlayer) {
                x += directions[i][0];
                y += directions[i][1];
                if (!posExists(x, y)) {
                    break;
                }
            }
            if (!posExists(x, y)) {
                continue;
            }
            if (field[x][y] == player) {
                while (true) {
                    x -= directions[i][0];
                    y -= directions[i][1];
                    if (x == xStart && y == yStart) {
                        break;
                    }
                    arr.add(new Slot(x, y));
                }
            }
        }
        field[xStart][yStart] = 0;
        if (arr.isEmpty()) {
            return false;
        }
        tilesToFlip.add(arr);
        return true;
    }
    public void fillPossibleMoves() {
        for (Slot sl : possibleMoves) {
            field[sl.x][sl.y] = 3;
        }
    }
    public boolean checkPosition(int x, int y) {
        if (!posExists(x, y) || field[x][y] != 3) {
            return false;
        } else {
            return true;
        }
    }
    public int calculateTiles(int player) {
        int counter = 0;
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (field[i][j] == player) {
                    counter++;
                }
            }
        }
        return counter;
    }
    private boolean isOnEdge(int x, int y) {
        if (x == 0 || y == 0 || x == 8 || y == 8) {
            return true;
        } else {
            return false;
        }
    }
    public void makeComputerMove() {
        int bestId = calculateBestMove();
        field[possibleMoves.get(bestId).x][possibleMoves.get(bestId).y] = 2;
        for (Slot sl : tilesToFlip.get(bestId)) {
            field[sl.x][sl.y] = 2;
        }
    }
    public int calculateBestMove() {
        int bestScore = 0;
        int bestId = 0;
        for (int i = 0; i < possibleMoves.size(); i++) {
            int currentScore = 0;
            for (int j = 0; j < tilesToFlip.get(i).size(); j++) {
                if (isOnEdge(tilesToFlip.get(i).get(j).x, tilesToFlip.get(i).get(j).y)) {
                    currentScore += 2;
                } else {
                    currentScore += 1;
                }
            }
            if (tilesToFlip.get(i).size() == 1) {
                if (tilesToFlip.get(i).get(0).x == possibleMoves.get(i).x ||
                        tilesToFlip.get(i).get(0).y == possibleMoves.get(i).y) {
                    currentScore += 0.4;
                } else {
                    currentScore += 0.8;
                }
            }
            if (currentScore > bestScore) {
                bestId = i;
                bestScore = currentScore;
            }
        }
        return bestId;
    }
}
