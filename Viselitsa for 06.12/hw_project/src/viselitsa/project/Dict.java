package viselitsa.project;
import java.util.Random;
public class Dict implements Dictionary{
    private final String[] words = {"home", "apple", "love", "tinkoff", "informatics", "winter", "moon", "sunshine", "horse", "river"};
    private final Random random = new Random();

    public Dict() {}

    public String[] words() {
        return new String[0];
    }

    public String randomWord(){
        return words[random.nextInt(words.length)];
    }
}
