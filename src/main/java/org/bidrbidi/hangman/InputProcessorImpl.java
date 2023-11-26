package org.bidrbidi.hangman;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class InputProcessorImpl implements InputProcessor {

    private final static String restartRegex = "^[\\+\\-]$";
    private final static String regexRu = "^[а-яА-Я]$";
    private final static String regexEn = "^[a-zA-Z]$";
    private final static String regex = regexEn;
    private final static Scanner scanner = new Scanner(System.in);

    @Override
    public char getUserInput() {
        System.out.println("Введите букву:");
        String input = scanner.next();
        while (!validateInput(input)) {
            input = scanner.next();
        }
        return input.toUpperCase().toCharArray()[0];
    }

    @Override
    public boolean restartInput() {
        String input = scanner.next();
        Pattern pattern = Pattern.compile(restartRegex);
        Matcher matcher = pattern.matcher(input);
        while (!matcher.matches()) {
            System.out.println("Введите + или -");
            input = scanner.next();
            matcher = pattern.matcher(input);
        }

        return input.equals("+");
    }

    @Override
    public boolean validateInput(String input) {
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(input);
        if (matcher.matches()) {
            return true;
        } else {
            System.out.println("Неверный формат ввода. Введите одну букву английского алфавита");
            return false;
        }
    }
}
