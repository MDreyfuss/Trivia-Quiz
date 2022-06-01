package quiz;

import io.reactivex.Single;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import json.NewQuestion;
import json.OpenTriviaDatabase;

import javax.inject.Inject;
import javax.inject.Provider;
import javax.inject.Singleton;
import java.util.ArrayList;
import java.util.Collections;

@Singleton
public class QuizQuestionPresenter {

    private final Provider<QuizQuestionFrame> viewProvider;
    private final OpenTriviaDatabase model;
    private Disposable disposable;
    private int placementOfCorrectAnswer;
    private int total;
    private int score;

    @Inject
    public QuizQuestionPresenter(Provider<QuizQuestionFrame> viewProvider, OpenTriviaDatabase model)
    {
        this.viewProvider = viewProvider;
        this.model = model;
    }

    public void nextQuestion()
    {
        Single<NewQuestion> question = model.getQuizQuestion();

        disposable = question
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.newThread())
                .subscribe(this::onNext, this::onError);
    }

    public void cancel() {
        if (disposable != null) {
            disposable.dispose();
        }
    }

    private void onNext(NewQuestion question) {
        viewProvider.get().setQuestion(question.getQuestion1());

        String[] answers = question.getIncorrectAnswers1();
        String correct = question.getCorrectAnswer1();

        ArrayList<String> abcOrder = new ArrayList<>();
        abcOrder.add(answers[0]);
        abcOrder.add(answers[1]);
        abcOrder.add(answers[2]);
        abcOrder.add(correct);

        Collections.sort(abcOrder);

        placementOfCorrectAnswer = abcOrder.indexOf(correct);

        viewProvider.get().setAnswers(abcOrder);

    }

    private void onError(Throwable throwable) {
        throwable.printStackTrace();
        viewProvider.get().showError();
    }


    public void checkAnswer(int whichOne) {
        total++;
        if (whichOne != placementOfCorrectAnswer)
        {
            viewProvider.get().setColorToRed(whichOne);
        } else
        {
            score++;
        }
        viewProvider.get().setColorToGreen(placementOfCorrectAnswer);
        viewProvider.get().displayScore(score, total);
    }
}
