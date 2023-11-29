package org.bidrbidi.hangman;

public class RendererImpl implements Renderer {
    private final static String INITIAL_STATE = """
            \n
            \n
            \n
            \n
            \n
            ========     || Слово:           || Отсутствующие буквы:
            ||     |     || %s           || %s
            ||     |     ||------------------
            ||           || Осталось попыток:
            ||           || %d
            ||           ||------------------
            ||           ||
            =========    ||
            """;

    private final static String FIRST_WRONG_ATTEMPT = """
            \n
            \n
            \n
            \n
            \n
            ========     || Слово:           || Отсутствующие буквы:
            ||     |     || %s           || %s
            ||     |     ||------------------
            ||     O     || Осталось попыток:
            ||           || %d
            ||           ||------------------
            ||           ||
            =========    ||
            """;

    private final static String SECOND_WRONG_ATTEMPT = """
            \n
            \n
            \n
            \n
            \n
            ========     || Слово:           || Отсутствующие буквы: 
            ||     |     || %s           || %s                  
            ||     |     ||------------------                  
            ||     O     || Осталось попыток:                   
            ||    /      || %d                    
            ||           ||------------------                     
            ||           ||                  
            =========    ||                  
            """;

    private final static String THIRD_WRONG_ATTEMPT = """
            \n
            \n
            \n
            \n
            \n
            ========     || Слово:           || Отсутствующие буквы: 
            ||     |     || %s           || %s                  
            ||     |     ||------------------                  
            ||     O     || Осталось попыток:                   
            ||    /|     || %d                    
            ||           ||------------------                     
            ||           ||                  
            =========    ||                  
            """;

    private final static String FOURTH_WRONG_ATTEMPT = """
            \n
            \n
            \n
            \n
            \n
            ========     || Слово:           || Отсутствующие буквы: 
            ||     |     || %s           || %s                  
            ||     |     ||------------------                  
            ||     O     || Осталось попыток:                   
            ||    /|\\    || %d                    
            ||           ||------------------                     
            ||           ||                  
            =========    ||                  
            """;

    private final static String FIFTH_WRONG_ATTEMPT = """
            \n
            \n
            \n
            \n
            \n
            ========     || Слово:           || Отсутствующие буквы: 
            ||     |     || %s           || %s                  
            ||     |     ||------------------                  
            ||     O     || Осталось попыток:                   
            ||    /|\\    || %d                    
            ||     Г     ||------------------                     
            ||           ||                  
            =========    ||          
            """;

    private final static String GAME_OVER = """
            \n
            \n
            \n
            \n
            \n
            ========     || Слово:           || Отсутствующие буквы: 
            ||     |     || %s           || %s                  
            ||     |     ||------------------                  
            ||     O     || Осталось попыток:                   
            ||    /|\\    || %d                    
            ||     П     ||------------------                     
            ||           || Ответ:                  
            =========    || %s                   
            """;

    private final static String[] states = {INITIAL_STATE, FIRST_WRONG_ATTEMPT, SECOND_WRONG_ATTEMPT,
            THIRD_WRONG_ATTEMPT, FOURTH_WRONG_ATTEMPT, FIFTH_WRONG_ATTEMPT, GAME_OVER};

    private final WordGenerator wordGenerator = WordGeneratorImpl.getInstance();

    @Override
    public void render(char[] currentWord, int failedAttempts, int totalAttempts, boolean finish, String wrongLetters) {
        if (finish) {
            System.out.printf((states[failedAttempts]) + "%n",
                    String.valueOf(currentWord), wrongLetters, totalAttempts - failedAttempts, WordGeneratorImpl.ANSWER);
            System.out.println("Ага, все так!");
        } else {
            System.out.printf((states[failedAttempts]) + "%n",
                    String.valueOf(currentWord), wrongLetters, totalAttempts - failedAttempts, WordGeneratorImpl.ANSWER);
        }
    }
}
