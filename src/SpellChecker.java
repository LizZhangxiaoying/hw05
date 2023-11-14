import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class SpellChecker {

    public SpellChecker() {
      // TODO: You can modify the body of this constructor,
      // or you can leave it blank. You must keep the signature, however.
    }
  
    public static void start() {
      // TODO: You can modify the body of this method,
      // or you can leave it blank. You must keep the signature, however.
        Scanner inputScanner = new Scanner(System.in);
        ArrayList<String> dicWords = new ArrayList<>();
        FileInputStream fis;
        FileOutputStream fos;
        String outputFileName;
        WordRecommender wr;
        ArrayList<String> suggestions = new ArrayList<>();
        System.out.printf(Util.DICTIONARY_PROMPT);
        System.out.print(">>");


       //get the name of the dictionary, and populate the words from the dictionary into an ArrayList
        while (true) {

            try {
                String dicName = inputScanner.nextLine();
                File dicFile = new File(dicName);
                wr = new WordRecommender(dicName);
                Scanner fileScanner = new Scanner(dicFile);
                while (fileScanner.hasNext()) {
                    String dictionaryWord = fileScanner.next();
                    dicWords.add(dictionaryWord);
                }
                System.out.printf(Util.DICTIONARY_SUCCESS_NOTIFICATION, dicName);
                fileScanner.close();
                break;
            } catch (IOException e){
                System.out.printf(Util.FILE_OPENING_ERROR);
                System.out.printf(Util.DICTIONARY_PROMPT);
                System.out.print(">>");
            }

        }

        System.out.printf(Util.FILENAME_PROMPT);
        System.out.print(">>");
        while (true) {

            try {
                String fileName = inputScanner.nextLine();
                outputFileName = fileName.replace(".txt", "_chk.txt");
                fos = new FileOutputStream(outputFileName);
                PrintWriter outwriter = new PrintWriter(fos);
                fis  = new FileInputStream(fileName);
                Scanner fileScanner = new Scanner(fis);
                //the next while loop is complicated: it contains the calling of WordRecommender, and writing to the output file
                while (fileScanner.hasNext()) {
                    String fileWord = fileScanner.next();
                    if (!dicWords.contains(fileWord)) {
                        System.out.printf(Util.MISSPELL_NOTIFICATION, fileWord);
                        ArrayList<String> wordSuggestions = wr.getWordSuggestions(fileWord, 2, 50, 4);

                        if (wordSuggestions.isEmpty()) {
                            System.out.printf(Util.NO_SUGGESTIONS);
                            System.out.printf(Util.TWO_OPTION_PROMPT);
                            System.out.print("\n");
                            System.out.print(">>");

                            while (true) {
                                String userChoice2 = inputScanner.next();
                                if (userChoice2.equals("a")) {
                                    outwriter.print(fileWord);
                                    outwriter.print(" ");
                                    break;
                                } else if (userChoice2.equals("t")) {
                                    System.out.printf(Util.MANUAL_REPLACEMENT_PROMPT);
                                    System.out.print(">>");
                                    String newWord2 = inputScanner.next();
                                    outwriter.print(newWord2);
                                    outwriter.print(" ");
                                    break;
                                } else {
                                    System.out.printf(Util.INVALID_RESPONSE);
                                    System.out.print(">>");
                                }
                            }

                        }

            else{
                        System.out.printf(Util.FOLLOWING_SUGGESTIONS);
                        for (int i = 0; i < wordSuggestions.size(); i++) {
                            System.out.printf(Util.SUGGESTION_ENTRY, i + 1, wordSuggestions.get(i));
                        }
                        System.out.printf(Util.THREE_OPTION_PROMPT);
                            System.out.print(">>");

                            while (true) {
                                int wordIndex;
                            String userDecision = inputScanner.next();
                            if (userDecision.equals("r")) {
                                System.out.printf(Util.AUTOMATIC_REPLACEMENT_PROMPT);
                                System.out.print(">>");

                                 wordIndex = inputScanner.nextInt() - 1;
                              while(wordIndex>(wordSuggestions.size()-1) || !inputScanner.hasNextInt()){
                                  System.out.printf(Util.INVALID_RESPONSE);
                                  System.out.print(">>");
                                  wordIndex = inputScanner.nextInt() - 1;
                              }
                                outwriter.print(wordSuggestions.get(wordIndex));
                                outwriter.print(" ");
                                break;
                            } else if (userDecision.equals("a")) {

                                outwriter.print(fileWord);
                                outwriter.print(" ");
                                break;

                            } else if (userDecision.equals("t")) {
                                System.out.printf(Util.MANUAL_REPLACEMENT_PROMPT);
                                System.out.print(">>");
                                String newWord = inputScanner.next();
                                outwriter.print(newWord);
                                outwriter.print(" ");
                                break;

                            } else {
                                System.out.printf(Util.INVALID_RESPONSE);
                                System.out.print(">>");
                            }
                        }
                    }
                }
                    else{
                        outwriter.print(fileWord);
                        outwriter.print(" ");

                    }

                }
                System.out.printf(Util.FILE_SUCCESS_NOTIFICATION, fileName, outputFileName);
                fileScanner.close();
                inputScanner.close();
                outwriter.close();
                break;
            } catch (IOException e){
                System.out.printf(Util.FILE_OPENING_ERROR);
                System.out.printf(Util.FILENAME_PROMPT);
                System.out.print(">>");
            }

        }
            }






        //get the name of the file, and populate the words from the dictionary into an ArrayList
        /*while (true) {
            try {
                String fileName = inputScanner.nextLine();
                fis = new FileInputStream(dicName);
                Scanner fileScanner = new Scanner(fis);
                while (fileScanner.hasNext()) {
                    String dictionaryWord = fileScanner.next();
                    dicWords.add(dictionaryWord);
                }
                System.out.printf(Util.DICTIONARY_SUCCESS_NOTIFICATION, dicName);
                fileScanner.close();
                break;

            } catch (IOException e) {
                System.out.printf(Util.FILE_OPENING_ERROR);
                System.out.printf(Util.DICTIONARY_PROMPT);
                System.out.print(">>");
            }
        }
*/




    }
  
    // You can of course write other methods as well.
