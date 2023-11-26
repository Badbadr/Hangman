package org.bidrbidi.hangman;

public interface GameProcessor {

    void start();
    void stop();
    void restart();
    void handleAttempt();
}
