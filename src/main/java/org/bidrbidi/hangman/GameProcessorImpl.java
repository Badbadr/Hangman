package org.bidrbidi.hangman;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Arrays;
import java.util.StringJoiner;

@NoArgsConstructor
public class GameProcessorImpl implements GameProcessor {

    @Getter
    @Setter(AccessLevel.PRIVATE)
    private boolean gameOn;
    private final WordGenerator wordGenerator = WordGeneratorImpl.getInstance();
    private final Renderer renderer = new RendererImpl();
    private final InputProcessor inputProcessor = new InputProcessorImpl();
    private char[] answer;
    private char[] currentWord;
    private int totalAttempts = 6;
    private int failedAttempts;
    private char[] wrongLetters;

    @Override
    public void start() {
        System.out.println("Игра началась!");
        this.setGameOn(true);
        failedAttempts = 0;
        wrongLetters = new char[0];
        answer = wordGenerator.getRandomWord();
        currentWord = new char[answer.length];
        for (int i = 0; i < answer.length; i++) {
            currentWord[i] = '_';
        }
        renderer.render(currentWord, failedAttempts, totalAttempts, false, String.valueOf(wrongLetters));
        while (isGameOn()) {
            handleAttempt();
        }
    }

    @Override
    public void stop() {
        this.setGameOn(false);
        System.out.println("Игра окончена. Желаете попробовать еще раз? (+/-)");
        if (inputProcessor.restartInput()) {
            start();
        }
    }

    @Override
    public void restart() {

    }

    @Override
    public void handleAttempt() {
        // TODO: do not append duplicated letter into missing letters
        if (!isGameOn()) {
            System.out.println("Игра окончена");
            return;
        }
        if (failedAttempts >= totalAttempts) {
            stop();
        }
        char suggestedLetter = inputProcessor.getUserInput();
        int[] lettersInAnswer = contains(answer, suggestedLetter);
        int[] currentLetters = contains(currentWord, suggestedLetter);

        if (lettersInAnswer.length > 0 && currentLetters.length < 1) {
            for(int i: lettersInAnswer) {
                currentWord[i] = suggestedLetter;
            }
        } else {
            failedAttempts++;
            wrongLetters = increaseArraySize(wrongLetters);
            wrongLetters[wrongLetters.length - 1] = suggestedLetter;
            Arrays.sort(wrongLetters);
        }

        boolean finish = Arrays.equals(currentWord, answer);

        if (finish) {
            renderer.render(currentWord, failedAttempts, totalAttempts, finish, String.valueOf(wrongLetters));
            stop();
            return;
        }

        renderer.render(currentWord, failedAttempts, totalAttempts, finish, String.valueOf(wrongLetters));
        if (failedAttempts >= totalAttempts) {
            stop();
        }
    }

    private int[] contains(char[] word, char character) {
        int[] res = new int[0];
        int[] buffRes;
        for (int i = 0; i < word.length; i++) {
            if (word[i] == character) {
                buffRes = new int[res.length + 1];
                System.arraycopy(res, 0, buffRes, 0, res.length);
                buffRes[buffRes.length - 1] = i;
                res = new int[buffRes.length];
                System.arraycopy(buffRes, 0, res, 0, buffRes.length);
            }
        }
        return res;
    }

    private char[] increaseArraySize(char[] array) {
        char[] buffRes = new char[array.length + 1];
        System.arraycopy(array, 0, buffRes, 0, array.length);
        array = new char[buffRes.length];
        System.arraycopy(buffRes, 0, array, 0, buffRes.length);
        return array;
    }
}
