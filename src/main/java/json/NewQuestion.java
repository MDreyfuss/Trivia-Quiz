package json;

public class NewQuestion {

    Results results[];

    public String getQuestion1()
    {
        return results[0].question;
    }

    public String getCorrectAnswer1()
    {
        return results[0].correctAnswer;
    }

    public String[] getIncorrectAnswers1()
    {
        return results[0].incorrectAnswers;
    }

}
