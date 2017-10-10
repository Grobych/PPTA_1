package sample;

import java.text.ParseException;

/**
 * Created by Alex on 05.10.2017.
 */
public class StringParserException extends ParseException {
    public StringParserException(String s, int i) {
        super(s, i);
    }
}
