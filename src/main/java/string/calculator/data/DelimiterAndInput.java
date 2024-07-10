package string.calculator.data;

import java.util.List;

public class DelimiterAndInput {

    private List<Character> delimiters;
    private String input;

    public DelimiterAndInput(List<Character> delimiters, String input) {
        this.delimiters = delimiters;
        this.input = input;
    }

    public List<Character> getDelimiters() {
        return delimiters;
    }

    public String getInput() {
        return input;
    }
}
