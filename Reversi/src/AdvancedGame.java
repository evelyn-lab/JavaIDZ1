/*
 * класс AdvancedGame, тут должна была быть реализация продвинутого режима
 * */
public class AdvancedGame extends Game{
    private int currentComputerScore;
    AdvancedGame () {
        super();
        currentComputerScore = 0;
    }
    public void game() {

    }
    public void makeMoves() {

    }
    public void printScore() {
        System.out.println("Your score : " + currentPlayerScore);
        System.out.println("Computer score : " + currentComputerScore);
    }
}
