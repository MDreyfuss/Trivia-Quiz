import json.NewQuestion;
import json.OpenTriviaDatabase;
import json.OpenTriviaDatabaseFactory;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class OpenTriviaDatabaseFactoryTest {

    @Test
    void getQuizQuestion() {
        //given
        OpenTriviaDatabaseFactory factory = new OpenTriviaDatabaseFactory();
        OpenTriviaDatabase service = factory.getInstance();

        //when
        NewQuestion newQuestion = service.getQuizQuestion().blockingFirst();


        //then
        assertNotNull(newQuestion.getQuestion1());
        assertNotNull(newQuestion.getCorrectAnswer1());
        assertEquals(3, newQuestion.getIncorrectAnswers1().length);
    }
}