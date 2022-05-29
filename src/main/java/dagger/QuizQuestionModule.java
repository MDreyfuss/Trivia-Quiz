package dagger;

import json.OpenTriviaDatabase;
import json.OpenTriviaDatabaseFactory;

import dagger.Module;
import dagger.Provides;
import javax.inject.Singleton;

@Module
public class QuizQuestionModule {

    @Singleton
    @Provides
    public OpenTriviaDatabase providesOpenTriviaDatabase(OpenTriviaDatabaseFactory factory)
    {
        return factory.getInstance();
    }
}
