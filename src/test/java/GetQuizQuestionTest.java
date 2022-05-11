import json.NewQuestion;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GetQuizQuestionTest {

    @Test
    void getQuizQuestion() {
        //given
        GetQuizQuestion getQuizQuestion = new GetQuizQuestion();

        //when
        NewQuestion newQuestion = getQuizQuestion.getQuizQuestion().blockingFirst();

        //then
        assertTrue(newQuestion.getQuestion1() != null);
        assertTrue(newQuestion.getCorrectAnswer1() != null);
        assertTrue(newQuestion.getIncorrectAnswers1().length == 3);


    }
}