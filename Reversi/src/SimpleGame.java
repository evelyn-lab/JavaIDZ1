import java.util.Scanner;
/*
 * класс SimpleGame, реализация простого режима
 * */
public class SimpleGame extends Game{
    private int currentComputerScore;
    private boolean playerMadeAMove;
    private boolean computerMadeAMove;
    SimpleGame () {
        super();
        playerMadeAMove = true;
        computerMadeAMove = true;
        currentComputerScore = 0;
    }
    public void game() {
        while (flag) {
            makeMoves();
            printScore();
        }
        printTotal();
    }
    public void makeMoves() {
        if (gameField.flag) {
            gameField.printField();
            playerMoves(1);
            playerMadeAMove = true;
        } else {
            if (!playerMadeAMove) {
                flag = false;
                return;
            }
            System.out.println("Ther're no possible moves. You're skipping a move ;(");
            playerMadeAMove = false;
        }
        gameField.calculatePossibleMoves(2, 1);
        if (gameField.flag) {
            computerMoves();
            computerMadeAMove = true;
        } else {
            if (!playerMadeAMove) {
                flag = false;
                return;
            } else {
                computerMadeAMove = false;
            }
        }
        gameField.calculatePossibleMoves(1, 2);
        gameField.fillPossibleMoves();
    }
    public void calculateScore() {
        currentPlayerScore = gameField.calculateTiles(1);
        currentComputerScore = gameField.calculateTiles(2);
    }
    public void printScore() {
        calculateScore();
        System.out.println("Your score : " + currentPlayerScore);
        System.out.println("Computer score : " + currentComputerScore);
    }
    public void printTotal() {
        calculateScore();
        if (currentPlayerScore > currentComputerScore) {
            System.out.println("You won!");
        } else if (currentPlayerScore < currentComputerScore) {
            System.out.println("You've lost :(");
        } else {
            System.out.println("It's a draw!");
        }
    }
    public void computerMoves() {
        boolean current_flag = true;
        int x, y;
        int bestScore = 0;
        gameField.makeComputerMove();
        gameField.renewField();
    }


}
