package json;

import io.reactivex.Observable;
import retrofit2.http.GET;

public interface OpenTriviaDatabase {

    @GET("/api.php?amount=1&category=9&difficulty=easy&type=multiple")
    Observable<NewQuestion> getQuizQuestion();

}
