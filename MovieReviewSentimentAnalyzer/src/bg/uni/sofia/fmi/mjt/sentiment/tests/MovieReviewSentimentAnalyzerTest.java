package bg.uni.sofia.fmi.mjt.sentiment.tests;

import bg.uni.sofia.fmi.mjt.sentiment.MovieReviewSentimentAnalyzer;
import org.junit.Test;

import static org.junit.Assert.*;

public class MovieReviewSentimentAnalyzerTest {

    private String testsFolder = System.getProperty("user.dir") + "\\src\\bg\\uni\\sofia\\fmi\\mjt\\sentiment\\tests\\";

    private MovieReviewSentimentAnalyzer analyzer = new MovieReviewSentimentAnalyzer
            (testsFolder + "MovieReviews.txt",
                    testsFolder + "stopwords.txt");

    private double delta = 0.00001;

    @Test
    public void constructorShouldReadAndLoadReviewsAndStopwordsProperly() {
        MovieReviewSentimentAnalyzer analyzer = new MovieReviewSentimentAnalyzer
                (testsFolder + "constructorTest.txt",
                        testsFolder + "stopwords.txt");

        assertTrue("Reads the first stopword properly", analyzer.isStopWord("a"));
        assertTrue("Reads the last stopword properly", analyzer.isStopWord("yourselves"));
        assertTrue("Has recorded a( capitalized) word properly", analyzer.getWordSentiment("kung") >= 0);
        assertTrue("Hasn't recorded stopword \"the\"", analyzer.getWordSentiment("the") == -1);
        assertTrue("Hasn't recorded stopword \"an\"", analyzer.getWordSentiment("an") == -1);
        assertTrue("Hasn't recorded an invalid word", analyzer.getWordSentiment("abso@lute") == -1);
        assertTrue("Uses non- standard delimeters properly", analyzer.getWordSentiment("abso") >= 0 && analyzer.getWordSentiment("lute") >= 0);
        assertTrue("Doesnt think delimeters are words", analyzer.getWordSentiment(" ") == -1);
        assertTrue("Hasn't recorded \"nothing\"", analyzer.getWordSentiment(null) == -1 && analyzer.getWordSentiment("") == -1);
    }

    @Test
    public void shouldGetReviewSentiment() {
        assertEquals("Reads \"Dire disappointment: dull and unamusing freakshow\"",
                0.21857, analyzer.getReviewSentiment("Dire disappointment: dull and unamusing freakshow"), delta);
        assertEquals("Reads \"Immersive ecstasy: energizing artwork!\"",
                4.0, analyzer.getReviewSentiment("Immersive ecstasy: energizing artwork!"), delta);
        assertEquals("Reads \"A weak script that ends with a quick and boring finale.",
                1.4637421952077123, analyzer.getReviewSentiment("A weak script that ends with a quick and boring finale."), delta);
        // assertEquals("Reads \"The funniest comedy of the year, good work! Don't miss it!",
        //         2.2778941186748383, analyzer.getReviewSentiment("The funniest comedy of the year, good work! Don't miss it!"), delta);
    }

    @Test
    public void shouldGetReviewSentimentAsName() {
        assertEquals("Reads \"Dire disappointment: dull and unamusing freakshow\"",
                "negative", analyzer.getReviewSentimentAsName("Dire disappointment: dull and unamusing freakshow"));
        assertEquals("Reads \"Immersive ecstasy: energizing artwork!\"",
                "positive", analyzer.getReviewSentimentAsName("Immersive ecstasy: energizing artwork!"));
        assertEquals("Reads \"A weak script that ends with a quick and boring finale.",
                "somewhat negative", analyzer.getReviewSentimentAsName("A weak script that ends with a quick and boring finale."));
        assertEquals("Knows \"spurdo- sparde krastavica :DDD\" is not recorded",
                "unknown", analyzer.getReviewSentimentAsName("spurdo- sparde krastavica :DDD"));
    }

    @Test
    public void shouldGetWordSentiment() {
        assertEquals("Checks if analyzer knows \"kudesiwe\"", -1, analyzer.getWordSentiment("kudesiwe"), delta);
        assertEquals("Checks if analyzer knows \"dire\"", 0.0, analyzer.getWordSentiment("dire"), delta);
        assertEquals("Checks if analyzer knows \"dull\"", 0.892857, analyzer.getWordSentiment("dull"), delta);
        assertEquals("Checks if analyzer knows \"disappointment\"", 0.2, analyzer.getWordSentiment("disappointment"), delta);
        assertEquals("Checks if analyzer knows \"freakshow\"", 0.0, analyzer.getWordSentiment("freakshow"), delta);
        assertEquals("Checks if analyzer knows \"ecstasy\"", 4.0, analyzer.getWordSentiment("ecstasy"), delta);
        assertEquals("Checks if analyzer knows \"kudesiwe\"", -1, analyzer.getWordSentiment("kudesiwe"), delta);
        assertEquals("Checks if analyzer knows \"skateboards\"", 4.0, analyzer.getWordSentiment("skateboards"), delta);
        assertEquals("Checks if analyzer knows \"staggering\"", 4.0, analyzer.getWordSentiment("staggering"), delta);
        assertEquals("Checks if analyzer knows \"achievements\"", 3.5, analyzer.getWordSentiment("achievements"), delta);
        assertEquals("Checks if analyzer knows \"popular\"", 3.25, analyzer.getWordSentiment("popular"), delta);
        assertEquals("Checks if analyzer knows \"spells\"", 3.0, analyzer.getWordSentiment("spells"), delta);
        assertEquals("Checks if analyzer knows \"international\"", 2.6, analyzer.getWordSentiment("international"), delta);
        assertEquals("Checks if analyzer knows \"snowball\"", 2.0, analyzer.getWordSentiment("snowball"), delta);
        assertEquals("Checks if analyzer knows \"dude\"", 1.75, analyzer.getWordSentiment("dude"), delta);
        assertEquals("Checks if analyzer knows \"mediterranean \"", 1.0, analyzer.getWordSentiment("mediterranean"), delta);
        assertEquals("Checks if analyzer knows \"cash\"", 0.0, analyzer.getWordSentiment("cash"), delta);
    }

    @Test
    public void shouldGetMostFrequentWords() {
        assertTrue("Knows \"film\" is a top 10 common word", analyzer.getMostFrequentWords(10).contains("film"));
        assertTrue("Knows \"movie\" is a top 10 common word", analyzer.getMostFrequentWords(10).contains("movie"));
        assertTrue("Knows \"film\" is a top 2 common word", analyzer.getMostFrequentWords(2).contains("film"));
        assertFalse("Knows \"like\" is NOT a top 3 common word", analyzer.getMostFrequentWords(3).contains("like"));
    }

    @Test
    public void shouldGetMostPositiveWords() {
        assertTrue("Knows \"depictions\" is a top 10 positive word", analyzer.getMostPositiveWords(10).contains("depictions"));
        assertTrue("Knows \"skeleton\" is a top 10 positive word", analyzer.getMostPositiveWords(10).contains("skeleton"));
        assertTrue("Knows \"kudos\" is a top 10 positive word", analyzer.getMostPositiveWords(10).contains("kudos"));
        assertFalse("Knows \"film\" is a NOT top 10 positive word", analyzer.getMostPositiveWords(10).contains("film"));
    }

    @Test
    public void shouldGetMostNegativeWords() {
        assertTrue("Knows \"claptrap\" is a top 10 negative word", analyzer.getMostNegativeWords(10).contains("claptrap"));
        assertTrue("Knows \"cancer\" is a top 10 negative word", analyzer.getMostNegativeWords(10).contains("cancer"));
        assertTrue("Knows \"turd\" is a top 10 negative word", analyzer.getMostNegativeWords(10).contains("turd"));
        assertFalse("Knows \"good\" is NOT a top 10 negative word", analyzer.getMostNegativeWords(10).contains("good"));
    }

    @Test
    public void shouldKnowDictionarySize() {
        assertEquals("Knows the number of unique words", 15079, analyzer.getSentimentDictionarySize());
    }

    @Test
    public void shouldKnowStopwords() {
        assertTrue("Knows \"aren't\" is a stopword", analyzer.isStopWord("aren't"));
        assertTrue("Knows \"any\" is a stopword", analyzer.isStopWord("any"));
        assertTrue("Knows \"themselves\" is a stopword", analyzer.isStopWord("themselves"));
        assertTrue("Knows \"so\" is a stopword", analyzer.isStopWord("so"));
        assertFalse("Knows \"benis\" is NOT a stopword", analyzer.isStopWord("benis"));
    }

}