package org.bidrbidi.hangman;

import java.io.IOException;

public interface GameProcessor {

    void start();
    void stop();
    void restart();
    void handleAttempt();
}
