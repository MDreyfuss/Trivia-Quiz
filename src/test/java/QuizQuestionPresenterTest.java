import io.reactivex.Single;
import json.NewQuestion;
import json.OpenTriviaDatabase;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import io.reactivex.plugins.RxJavaPlugins;
import io.reactivex.schedulers.Schedulers;
import org.junit.jupiter.api.BeforeEach;

import java.util.ArrayList;

class QuizQuestionPresenterTest {

    QuizQuestionFrame view = mock(QuizQuestionFrame.class);
    OpenTriviaDatabase model = mock(OpenTriviaDatabase.class);
    QuizQuestionPresenter presenter = new QuizQuestionPresenter(view, model);
    NewQuestion question = mock(NewQuestion.class);
    ArrayList<String> abc = new ArrayList<>();

    @BeforeEach
    public void beforeEachTest()
    {
        // this will get run before each test
        RxJavaPlugins.setIoSchedulerHandler((scheduler) -> Schedulers.trampoline());
        RxJavaPlugins.setNewThreadSchedulerHandler((scheduler) -> Schedulers.trampoline());

        doReturn("What color was George Washington's white horse?").when(question).getQuestion1();
        doReturn("black").when(question).getCorrectAnswer1();
        doReturn(new String[]{"white", "green", "blue"}).when(question).getIncorrectAnswers1();
        doReturn(Single.just(question))
                .when(model).getQuizQuestion();
        abc.add("black");
        abc.add("blue");
        abc.add("green");
        abc.add("white");
    }

    @Test
    void nextQuestion()
    {
        //given

        // when
        presenter.nextQuestion();

        // then
        verify(view).setQuestion("What color was George Washington's white horse?");
        verify(view).setAnswers(abc);

    }

    @Test
    void checkAnswerIncorrect()
    {
        //given

        //when
        presenter.checkAnswer(2);

        //then
        verify(view).setColorToRed(2);
        verify(view).setColorToGreen(0);
        verify(view).displayScore(0, 1);
    }

    @Test
    void checkAnswerCorrect()
    {
        //given

        //when
        presenter.checkAnswer(0);

        //then
        verify(view).displayScore(1, 1);
    }
}