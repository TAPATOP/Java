package bg.uni.sofia.fmi.mjt.sentiment.tests;

import bg.uni.sofia.fmi.mjt.sentiment.MovieReviewSentimentAnalyzer;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import java.io.*;

import static org.junit.Assert.assertEquals;

public class MovieReviewSentimentAnalyzerTest {
    @Rule
    public TemporaryFolder folder = new TemporaryFolder();

    @Test
    public void constructorReadsAndLoadsProperly() throws IOException{
        // Creates a temporary stopwords file //
        File stopwordsTempFile= folder.newFile("stopwords.txt");
        Writer fileWriter = new FileWriter(stopwordsTempFile);
        String tempTextToWrite =
                "fruit\n" +
                        "banana\n" +
                        "orange\n" +
                        "third reich\n" +
                        "allo' ha bar\n" +
                        "the\n" +
                        "my grandson Nathan\n";

        fileWriter.write(tempTextToWrite);
        fileWriter.close();

        // Creates a temporary reviews file //
        File reviewsTempFile = folder.newFile("MovieReviews.txt");
        fileWriter = new FileWriter(reviewsTempFile);
        tempTextToWrite =
                        "1 You could hate it for the same reason .\n" +
                        "1 There 's little to recommend Snow Dogs , unless one considers cliched dialogue and perverse escapism a source of high hilarity .\n" +
                        "1 Kung Pow is Oedekerk 's realization of his childhood dream to be in a martial-arts flick , and proves that sometimes the dreams of youth should remain just that .\n" +
                        "4 The performances are an abso@lute joy .";
        fileWriter.write(tempTextToWrite);
        fileWriter.close();

        // Creates and prepares the analyzer //
        MovieReviewSentimentAnalyzer object = new MovieReviewSentimentAnalyzer(
                reviewsTempFile.getAbsolutePath(),
                stopwordsTempFile.getAbsolutePath());

        assertEquals("Reads the first stopword properly", true, object.isStopWord("fruit"));
        assertEquals("Reads the last stopword properly", true, object.isStopWord("my grandson Nathan"));
        assertEquals("Has recorded a( capitalized) word properly", true, object.getWordSentiment("kung") >= 0);
        assertEquals("Hasn't recorded a stopword", true, object.getWordSentiment("the") == -1);
        assertEquals("Hasn't recorded an invalid word", true, object.getWordSentiment("abso@lute") == -1);
        assertEquals("Uses non- standard delimeters properly", true, object.getWordSentiment("abso") >= 0 && object.getWordSentiment("lute") >= 0);
        assertEquals("Doesnt think delimeters are words", true, object.getWordSentiment(" ") == -1);
        assertEquals("Doesnt think nothing is a word", false, object.getWordSentiment(null) >= 0|| object.getWordSentiment("") >= 0);
    }

    @Test
    public void getReviewSentimentAsName(){
    }

    @Test
    public void getWordSentiment(){
    }

    @Test
    public void getMostFrequentWords(){
    }

    @Test
    public void getMostPositiveWords(){
    }

    @Test
    public void getMostNegativeWords(){
    }

    @Test
    public void getSentimentDictionarySize(){
    }

    @Test
    public void isStopWord(){
    }

}