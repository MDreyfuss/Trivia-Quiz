package json;

import com.google.gson.annotations.SerializedName;

public class Results {

    String question;

    @SerializedName("correct_answer")
    String correctAnswer;

    @SerializedName("incorrect_answers")
    String[] incorrectAnswers;
}
