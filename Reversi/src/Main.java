import java.util.Scanner;
public class Main {
    static int gameStart() {
        int modeId;
        Scanner input = new Scanner( System.in );
        System.out.println("Please choose the mode");
        System.out.println("For computer simple mode press 1, for advanced mode press 2, for two players press 3 :");
        modeId = input.nextInt();
        return modeId;
    }
    public static void main(String[] args) {
        String word = "";
        System.out.println("Welcome to Reversi!");
        Scanner input = new Scanner(System.in);
        while (true) {
            int modeId = gameStart();
            Game newGame;
            if (modeId == 1) {
                newGame = new SimpleGame();
            } else if (modeId == 2) {
                newGame = new AdvancedGame();
            } else {
                newGame = new TwoPlayers();
            }
            newGame.game();
            while (!word.contains("yes") && !word.contains("no")) {
                System.out.println("Would you like to continue? Please enter yes/no");
                word = input.nextLine();
            }
            if (word.contains("no")) {
                break;
            }
        }
    }
}