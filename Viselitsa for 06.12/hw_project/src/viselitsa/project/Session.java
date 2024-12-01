package viselitsa.project;
public class Session {
    private final String answer;
    private final char[] userAnswer;
    private final int maxAttempts;
    private int attempts;
    public int getAttempts() {
        return attempts;
    }

    public int getMaxAttempts() {
        return maxAttempts;
    }

    public String getAnswer() {
        return answer;
    }

    public char[] getUserAnswer() {
        return userAnswer;
    }

    public Session(Dictionary dict, int maxAttempts) {
        this.answer = dict.randomWord();
        this.userAnswer = new char[answer.length()];
        for (int i = 0; i < userAnswer.length; i++) {
            userAnswer[i] = '_';
        }
        this.maxAttempts = maxAttempts;
        this.attempts = 0;
    }
    boolean flag = false;
    public GuessResult guess(char guess) {
        boolean flag = false;
        for (int i = 0; i < answer.length(); i++) {
            if (answer.charAt(i) == guess) {
                userAnswer[i] = guess;
                flag = true;
            }
        }
        if (String.valueOf(userAnswer).equals(answer)) {
            return new GuessResult.Win(getUserAnswer(), getAttempts(), getMaxAttempts(), "WOW! You won!");
        }
        else if(flag)
            return new GuessResult.SuccessfulGuess(getUserAnswer(), getAttempts(), getMaxAttempts(), "One more guessed letter!");
        else{
            attempts++;
            if (getAttempts() >= getMaxAttempts()) {
                GuessResult.Defeat defeat = new GuessResult.Defeat(getUserAnswer(), getAttempts(), getMaxAttempts(), "You lost. The word: " + getAnswer());
                return defeat;
            } else {
                GuessResult.FailedGuess failedGuess = new GuessResult.FailedGuess(getUserAnswer(), getAttempts(), getMaxAttempts(), "You are wrong, try one more time!");
                return failedGuess;
            }
        }
    }
    public GuessResult giveUp(){
        return new GuessResult.Defeat(getUserAnswer(), getAttempts(), getMaxAttempts(), "You gave up! The word was: " + getAnswer());
    }
}
