package org.bidrbidi.hangman;

public interface Renderer {

    void render(char[] currentWord, int failedAttempts, int totalAttempts, boolean finish);
}
