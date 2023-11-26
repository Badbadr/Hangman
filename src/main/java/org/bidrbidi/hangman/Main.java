package org.bidrbidi.hangman;

import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        GameProcessor processor = new GameProcessorImpl();
        processor.start();
    }
}