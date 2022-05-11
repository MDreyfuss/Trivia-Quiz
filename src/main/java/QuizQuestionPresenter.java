import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import json.NewQuestion;

import java.util.ArrayList;
import java.util.Collections;

public class QuizQuestionPresenter {

    private final QuizQuestionFrame view;
    private final GetQuizQuestion model;
    private Disposable disposable;
    private int placementOfCorrectAnswer;

    public QuizQuestionPresenter(QuizQuestionFrame view, GetQuizQuestion model)
    {
        this.view = view;
        this.model = model;
    }

    public void nextQuestion()
    {
        Observable<NewQuestion> question = model.getQuizQuestion();

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
        view.setQuestion(question.getQuestion1());

        String[] answers = question.getIncorrectAnswers1();
        String correct = question.getCorrectAnswer1();

        ArrayList<String> abcOrder = new ArrayList<>();
        abcOrder.add(answers[0]);
        abcOrder.add(answers[1]);
        abcOrder.add(answers[2]);
        abcOrder.add(correct);

        Collections.sort(abcOrder);

        placementOfCorrectAnswer = abcOrder.indexOf(correct);

        view.setAnswers(abcOrder);

    }

    private void onError(Throwable throwable) {
        throwable.printStackTrace();
        view.showError();
    }


    public boolean checkAnswer(int whichOne) {
        boolean result = true;
        if (whichOne != placementOfCorrectAnswer)
        {
            result = false;
            view.setColorToRed(whichOne);
        }
        view.setColorToGreen(placementOfCorrectAnswer);
        return result;
    }
}
