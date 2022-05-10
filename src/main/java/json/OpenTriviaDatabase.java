package json;

import retrofit2.http.GET;

public interface OpenTriviaDatabase {

    @GET("/api.php?amount=1&category=9&difficulty=easy&type=multiple")
    NewQuestion getQuestion();

}
