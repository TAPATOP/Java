package bg.uni.sofia.fmi.mjt.sentiment;

import java.io.*;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Scanner;

public class MovieReviewSentimentAnalyzer implements SentimentAnalyzer {

    // Constructors//
    public MovieReviewSentimentAnalyzer(String reviewsFileName, String stopwordsFileName) {
        records = new HashMap<>();
        stopwords = new HashSet<>();

        File reviewsFile = new File(reviewsFileName);
        File stopwordsFile = new File(stopwordsFileName);

        loadStopwords(stopwordsFile);
        loadReviews(reviewsFile);
    }

    // Interface implementations //
    @Override
    public double getReviewSentiment(String review) {
        return 0;
    }

    @Override
    public String getReviewSentimentAsName(String review) {
        return null;
    }

    @Override
    public double getWordSentiment(String word) {
        if(records.containsKey(word)) return records.get(word).getRating();
        return -1;
    }

    @Override
    public Collection<String> getMostFrequentWords(int n) {
        return null;
    }

    @Override
    public Collection<String> getMostPositiveWords(int n) {
        return null;
    }

    @Override
    public Collection<String> getMostNegativeWords(int n) {
        return null;
    }

    @Override
    public int getSentimentDictionarySize() {
        return 0;
    }

    @Override
    public boolean isStopWord(String word) {
        return stopwords.contains(word);
    }

    // Additional functions //


    // Private functions //

    private void loadStopwords(File stopwordsFile){
        try(Scanner reader = new Scanner(stopwordsFile)){
            while(reader.hasNextLine()) {
                stopwords.add(reader.nextLine());
            }
            reader.close();
        }catch(FileNotFoundException a){
            System.out.println("I couldn't find the stopwords file");
        }
    }

    private void loadReviews(File reviewsFile){
        try(Scanner reader = new Scanner(reviewsFile)){
            while(reader.hasNextLine()){
                int rating = reader.nextInt();

                String[] words = parseLine(reader);

                for ( String word : words) {
                    if(!wordIsValid(word)) continue;
                    recordWord(word, rating);
                }
            }
        }catch(FileNotFoundException a){
            System.out.println("Couldn't open the reviews file");
        }
    }

    private String[] parseLine(Scanner input){
        return input.nextLine().toLowerCase().split("[^a-z0-9]");
    }

    private boolean wordIsValid(String word){
        return word.length() > 0;
    }

    private void recordWord(String word, int rating){
        if(!stopwords.contains(word)){
            if(records.containsKey(word)){
                records.get(word).addOneRating(rating);
            }
            else{
                records.put(word, new Record(rating));
            }
        }
    }

    // Member variables //
    private HashMap<String, Record> records;
    private HashSet<String> stopwords;

    // Nested Classes //
    public class Record {

        // Constructors //
        Record(){
            totalSumOfRatings = 0;
            totalCount = 0;
        }

        Record(int rating){
            totalSumOfRatings = rating;
            totalCount = 1;
        }
        // Getters //
        public int getTotalSumOfRatings() {
            return totalSumOfRatings;
        }

        public int getTotalCount() {
            return totalCount;
        }

        public double getRating(){
            return totalSumOfRatings / totalCount;
        }

        // Functions //
        public void addValue(int add){
            totalSumOfRatings += add;
        }

        public void addCount(int add){
            totalCount += add;
        }

        public void addOneRating(int value){
            totalSumOfRatings += value;
            addCount(1);
        }

        // Member Variables //
        private int totalSumOfRatings;
        private int totalCount;
    }
}
