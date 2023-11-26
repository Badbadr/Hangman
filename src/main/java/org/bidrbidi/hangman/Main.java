package org.bidrbidi.hangman;

public class Main {
    public static void main(String[] args) {
        GameProcessor processor = new GameProcessorImpl();
        processor.start();
    }
}