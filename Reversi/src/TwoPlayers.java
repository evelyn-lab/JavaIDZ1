import java.util.Scanner;
/*
 * класс TwoPlayers, реализация режима игры для двух игроков
 * */
public class TwoPlayers extends Game{
    private int currentPlayer2Score;
    boolean player1MadeAMove;
    boolean player2MadeAMove;
    TwoPlayers () {
        super();
        currentPlayer2Score = 0;
        player2MadeAMove = true;
        player1MadeAMove = true;
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
            player1MadeAMove = true;
        } else {
            if (!player2MadeAMove) {
                flag = false;
                return;
            }
            System.out.println("Ther're no possible moves. Player 'x', you're skipping a move ;(");
            player1MadeAMove = false;
        }
        gameField.calculatePossibleMoves(2, 1);
        gameField.fillPossibleMoves();
        if (gameField.flag) {
            gameField.printField();
            playerMoves(2);
            player2MadeAMove = true;
        } else {
            if (!player1MadeAMove) {
                flag = false;
                return;
            } else {
                System.out.println("Ther're no possible moves. Player 'o', you're skipping a move ;(");
                player2MadeAMove = false;
            }
        }
        gameField.calculatePossibleMoves(1, 2);
        gameField.fillPossibleMoves();
    }
    public void calculateScore() {
        currentPlayerScore = gameField.calculateTiles(1);
        currentPlayer2Score = gameField.calculateTiles(2);
    }
    public void printScore() {
        calculateScore();
        System.out.println("Player 'x' score : " + currentPlayerScore);
        System.out.println("Player 'o' score : " + currentPlayer2Score);
    }
    public void printTotal() {
        calculateScore();
        if (currentPlayerScore > currentPlayer2Score) {
            System.out.println("Player 'x' won!");
        } else if (currentPlayerScore < currentPlayer2Score) {
            System.out.println("Player 'o' won!");
        } else {
            System.out.println("It's a draw!");
        }
    }
}
