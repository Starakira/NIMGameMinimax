import java.util.Scanner;

public class Game {
    private State currentState;
    private String currentMove;

    public Game(State state, int move) {
        this.currentState = state;

        if (move == 1) this.currentMove = "Human";
        else this.currentMove = "Computer";
    }

    public State getCurrentState() {
        return currentState;
    }

    public void setCurrentState(State currentState) {
        this.currentState = currentState;
    }

    public void start() {
        if (currentMove.equals("Human"))
            this.humanMove();
        else
            this.computerMove();
    }

    public void humanMove() {
        
        Scanner scan = new Scanner(System.in);
        
        int choice;
        State chosen_state;
        System.out.println("\n\n---------- Human Move ----------");
        this.showCurrentState();

        if (!this.isCurrentStateHasChild())
            this.showWin("Computer");
        else {
            try {
                System.out.print("Next State : ");
                choice = scan.nextInt();
                chosen_state = this.getCurrentState().getChildList().get(choice - 1);

                this.setCurrentState(chosen_state);
                System.out.println("Chosen state" + chosen_state);
                this.computerMove();
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public void computerMove() {
        State chosen_state = null;
        System.out.println("\n\n---------- Computer Move ----------");
        this.showCurrentState();

        if (!this.isCurrentStateHasChild())
            this.showWin("Human");
        else {
            try {
                chosen_state = this.getCurrentState().getChildList().get(0);
                if (this.getCurrentState().getMinimax() > 0) {
                    for (State childState : this.getCurrentState().getChildList()) {
                        if (childState.getValue() == 1)
                            chosen_state = childState;
                    }
                } else {
                    for (State childState : this.getCurrentState().getChildList()) {
                        if (childState.getValue() == 0)
                            chosen_state = childState;
                    }
                }

                this.setCurrentState(chosen_state);
                System.out.println("Chosen state" + chosen_state);
                this.humanMove();
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public void showCurrentState() {
        int i = 1;

        System.out.println("------------ State -------------");
        for (State state : this.getCurrentState().getChildList()) {
            System.out.println( (i++) + ". " + state);
        }
        System.out.println("--------------------------------");
    }

    public boolean isCurrentStateHasChild() {
        if (this.currentState.getChildList().size() > 0)
            return true;
        return false;
    }

    public void showWin(String playerWin) {
        if (playerWin.equals("Human"))
            System.out.println("\n------ You win! ------");
        else
            System.out.println("\n------ You lose! -------");
    }
}
