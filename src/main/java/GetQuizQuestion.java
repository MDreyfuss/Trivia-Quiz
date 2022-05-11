import io.reactivex.Observable;
import json.NewQuestion;
import json.OpenTriviaDatabase;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class GetQuizQuestion {

    private final OpenTriviaDatabase service;

    public GetQuizQuestion() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://opentdb.com")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();

        service = retrofit.create(OpenTriviaDatabase.class);
    }

    public Observable<NewQuestion> getQuizQuestion() {
        Observable<NewQuestion> question = service.getQuestion();

        return question;
    }
}
