package main;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Main {
    static Scanner scanner = new Scanner(System.in);
    static Random random = new Random();
    static List<String> vocabulary = getVocabulary();

    public static void main(String[] args) {
        while (true) {
            System.out.println("[N]ew game or [E]xit");
            String action = scanner.nextLine();

            if(action.equalsIgnoreCase("n")) {
                String word = randomWord();
                char[] hiddenWordAsChars = hiddenWord(word);
                game(word, hiddenWordAsChars);
            } else if (action.equalsIgnoreCase("e")){
                return;
            } else {
                System.out.println();
                System.out.println("!!!!! Print N for start or E for exit !!!!!");
                System.out.println();
            }
        }
    }

    /**
     * Starts the game "Hangman"
     * @param word              word from vocabulary
     * @param hiddenWordAsChars encrypted word
     */
    private static void game(String word, char[] hiddenWordAsChars) {
        int mistakesLeft = 6;
        int correctLetters = 0;

        Set<Character> usedLetters = new LinkedHashSet<>();

        //цикл будет работать до тех пор, пока остались шансы на ошибки или когда выявим полностью загаданное слово
        while (mistakesLeft != 0 && correctLetters != word.length()){

            //если Set пустой, то не пишем эту строчку
            if(usedLetters.size() > 0){
                System.out.println("Использованные буквы: "+usedLetters);
            }
            System.out.print("Загаданное слово: ");
            System.out.println(hiddenWordAsChars);

            System.out.print("Введите букву: ");
            String c = scanner.nextLine().toLowerCase();

            char character = c.charAt(0);

            boolean charInWord = false;

            for(int i = 0; i < word.length(); i++) {
                //если символ находится в слове И этот символ ещё не был выявлен в загаданном слове
                if(character == word.charAt(i) && hiddenWordAsChars[i] != character ) {
                    hiddenWordAsChars[i] = character;
                    correctLetters++;
                    charInWord=true;
                }
            }
            //если символа нет в массиве использованных символов, то заходим в if и выполняем логику
            //если символ уже есть в массиве, то проверка для него уже была сделана
            if(!usedLetters.contains(character)) {
                //добавляем символ в массив использованных символов
                usedLetters.add(character);
                //если символа в слове нет - уменьшается количество шансов на ошибку
                if(charInWord != true){
                    mistakesLeft--;
                    Gallows.printGallows(mistakesLeft);
                    System.out.println("Такой буквы нет");
                    System.out.println("Осталось попыток: "+mistakesLeft);
                }
                System.out.println();

            } else {
                System.out.println();
                System.out.println("!!!!!!!! Вы уже вводили этот символ !!!!!!!!");
                System.out.println();

            }
        }
        System.out.println();
        //если шансы на ошибки закончились - проигрыш
        if(mistakesLeft==0){
            System.out.println("!!!!! Вы проиграли !!!!!!");
            System.out.println("Загаданное слово было: "+word);
            System.out.println();
        } else {
            System.out.println("!!!!! Вы выиграли  !!!!!");
            System.out.println("Загаданное слово: "+word);
            System.out.println();
        }
    }

    /**
     * Returns random word from the vocabulary
     * @return      random word
     */
    private static String randomWord(){
        int randomNumber = random.nextInt(vocabulary.size());
        return vocabulary.get(randomNumber);
    }

    /**
     * Creates an encrypted word from a word from a parameter
     * @return  hidden word as char[] array
     */
    private static char[] hiddenWord(String word){
        char[] hiddenWord = new char[word.length()];
        for(int i = 0; i < word.length(); i++) {
            hiddenWord[i] = '_';
        }
        return hiddenWord;
    }

    /**
     * Creates vocabulary from .txt file
     * @return the vocabulary
     */
    private static List<String> getVocabulary() {
        File file = new File("vocabulary.txt");
        Scanner scanner;
        try {
            scanner = new Scanner(file);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        List<String> vocabulary = new ArrayList<>();

        while(scanner.hasNextLine()){
            String word = scanner.nextLine();

            vocabulary.add(word);
        }
        return vocabulary;
    }
}
