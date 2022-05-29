package quiz.dagger;

import dagger.Component;
import quiz.QuizQuestionFrame;

import javax.inject.Singleton;

@Singleton
@Component(modules = { QuizQuestionModule.class })
public interface QuizQuestionComponent {

    QuizQuestionFrame getQuizQuestionFrame();

}