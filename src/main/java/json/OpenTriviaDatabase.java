package json;

import io.reactivex.Single;
import retrofit2.http.GET;

public interface OpenTriviaDatabase {

    @GET("/api.php?amount=1&category=9&difficulty=easy&type=multiple")
    Single<NewQuestion> getQuizQuestion();

}
