package org.bidrbidi.hangman;

public interface InputProcessor {

    char getUserInput();

    boolean restartInput();

    boolean validateInput(String input);

}
