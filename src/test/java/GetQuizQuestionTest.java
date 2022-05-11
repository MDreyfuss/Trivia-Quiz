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
        assertNotNull(newQuestion.getQuestion1());
        assertNotNull(newQuestion.getCorrectAnswer1());
        assertEquals(3, newQuestion.getIncorrectAnswers1().length);
    }
}