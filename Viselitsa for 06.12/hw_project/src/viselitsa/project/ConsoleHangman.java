package viselitsa.project;
import java.util.Scanner;
public class ConsoleHangman {
    private final Dictionary dictionary = new Dict();
    Scanner scanner = new Scanner(System.in);

    public void run() {
        System.out.println("Hello! Welcome to my game =)");
        Session session = new Session(dictionary, 7);
        boolean flag = true;
        while(flag){
            System.out.println("State: " + String.valueOf(session.getUserAnswer()));
            System.out.println((session.getMaxAttempts() - session.getAttempts()) + " left");
            System.out.println("Type letter or 'give up': ");
            String input = scanner.nextLine().trim();
            if (input.equals("give up")) {
                printState(session.giveUp());
                flag = false;
            } else {
                char guess = input.charAt(0);
                printState(session.guess(guess));
                if(String.valueOf(session.getUserAnswer()).equals(session.getAnswer()))
                    flag = false;
                if (session.getAttempts() >= session.getMaxAttempts())
                    flag = false;
            }

        }
    scanner.close();
    }

    private void printState(GuessResult guess) {
        System.out.println(guess.message());
    }

}


