package bg.uni.sofia.fmi.mjt.sentiment.tests;

import bg.uni.sofia.fmi.mjt.sentiment.MovieReviewSentimentAnalyzer;

import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import java.io.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class MovieReviewSentimentAnalyzerTest {
    @Rule
    public TemporaryFolder folder = new TemporaryFolder();

    public MovieReviewSentimentAnalyzer setup(String text){
            // Creates a temporary reviews file //
            try(Writer fileWriter = new FileWriter(folder.newFile("MovieReviews.txt"))) {
                fileWriter.write(text);
                fileWriter.close();
            }catch(IOException a){
                System.out.println("Error with creating temp reviews file");
            }

            return new MovieReviewSentimentAnalyzer(folder.getRoot().getAbsolutePath() + "\\MovieReviews.txt",
                    System.getProperty("user.dir") + "\\src\\stopwords.txt");
    }

    //@
    //public MovieReviewSentimentAnalyzer analyzer = setup();

    @Test
    public void constructorReadsAndLoadsProperly(){
        String text =
                "1 You could hate it for the same reason .\n" +
                        "1 There 's little to recommend Snow Dogs , unless one considers cliched dialogue and perverse escapism a source of high hilarity .\n" +
                        "1 Kung Pow is Oedekerk 's realization of his childhood dream to be in a martial-arts flick , and proves that sometimes the dreams of youth should remain just that .\n" +
                        "4 The performances are an abso@lute joy .";;

        MovieReviewSentimentAnalyzer analyzer = setup(text);

        assertTrue("Reads the first stopword properly", analyzer.isStopWord("a"));
        assertTrue("Reads the last stopword properly", analyzer.isStopWord("yourselves"));
        assertTrue("Has recorded a( capitalized) word properly", analyzer.getWordSentiment("kung") >= 0);
        assertTrue("Hasn't recorded stopword \"the\"", analyzer.getWordSentiment("the") == -1);
        assertTrue("Hasn't recorded stopword \"an\"", analyzer.getWordSentiment("an") == -1);
        assertTrue("Hasn't recorded an invalid word",  analyzer.getWordSentiment("abso@lute") == -1);
        assertTrue("Uses non- standard delimeters properly", analyzer.getWordSentiment("abso") >= 0 && analyzer.getWordSentiment("lute") >= 0);
        assertTrue("Doesnt think delimeters are words", analyzer.getWordSentiment(" ") == -1);
        assertTrue("Hasn't recorded \"nothing\"", analyzer.getWordSentiment(null) == -1 && analyzer.getWordSentiment("") == -1);
    }

    @Test
    public void calculatesReviewSentimentProperly() {
      // assertEquals("You orange hate performances", );
    }

    @Test
    public void getReviewSentimentAsName(){
        // MovieReviewSentimentAnalyzer analyzer = setup();
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