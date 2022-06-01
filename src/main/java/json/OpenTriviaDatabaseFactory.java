package json;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

import javax.inject.Inject;

public class OpenTriviaDatabaseFactory {

    @Inject
    public OpenTriviaDatabaseFactory()
    {
        //empty constructor for dagger
    }


    public OpenTriviaDatabase getInstance() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://opentdb.com")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();

        return  retrofit.create(OpenTriviaDatabase.class);
    }

}
