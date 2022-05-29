package dagger;

import dagger.Component;
import dagger.QuizQuestionModule;

import javax.inject.Singleton;

@Singleton
@Component(modules = { QuizQuestionModule.class })
public interface QuizQuestionComponent {

    QuizQuestionFrame getQuizQuestionFrame();

}