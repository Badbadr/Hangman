package org.bidrbidi.hangman;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.concurrent.ThreadLocalRandom;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class WordGeneratorImpl implements WordGenerator {

    private static WordGenerator singletone;
    public static String ANSWER;
    public static final String[] LOCAL_WORDS = {"MACHINE", "GANGER", "STEERS"};
    @Override
    public final char[] getRandomWord() {
        try {
            URL url = new URL("https://random-wfford-api.herokuapp.com/word?length=6");
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");
            BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
            String inputLine;
            StringBuilder content = new StringBuilder();
            while ((inputLine = in.readLine()) != null) {
                content.append(inputLine);
            }
            in.close();
            con.disconnect();
            ANSWER = content.subSequence(2, content.length() - 2).toString().toUpperCase();
        } catch (IOException e) {
            ANSWER = LOCAL_WORDS[ThreadLocalRandom.current().nextInt(0, LOCAL_WORDS.length)];
        }
        return ANSWER.toCharArray();
    }

    public static WordGenerator getInstance() {
        if (singletone == null) {
            singletone = new WordGeneratorImpl();
        }
        return singletone;
    }
}
