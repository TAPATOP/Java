import bg.uni.sofia.fmi.mjt.sentiment.MovieReviewSentimentAnalyzer;


public class something {
    public static void main(String args[]){
        MovieReviewSentimentAnalyzer analyzer =
                new MovieReviewSentimentAnalyzer(
                        "E:\\3-ти курс\\JAva\\MovieReviewSentimentAnalyzer\\src\\tests.txt",
                        "E:\\3-ти курс\\JAva\\MovieReviewSentimentAnalyzer\\src\\stopwords.txt");
        String text = "nice shit, fucker shitface hell shitty retarded craphead druggie";
        System.out.println(analyzer.getReviewSentiment(text));
        System.out.println(analyzer.getReviewSentimentAsName(text));
        System.out.println(analyzer.getSentimentDictionarySize());
    }
}
