package viselitsa.project;
public interface GuessResult {
    char[] state();

    int attempt();

    int maxAttempts();

    String message();

    record Defeat(char[] state, int attempt, int maxAttempts, String message) implements GuessResult {
    }

    record Win(char[] state, int attempt, int maxAttempts, String message) implements GuessResult {
    }

    record SuccessfulGuess(char[] state, int attempt, int maxAttempts, String message) implements GuessResult {
    }

    record FailedGuess(char[] state, int attempt, int maxAttempts, String message) implements GuessResult {
    }
}

