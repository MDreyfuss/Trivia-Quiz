import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;

public class QuizQuestionFrame extends JFrame {

    private final QuizQuestionPresenter presenter;
    private JLabel questionLabel;
    private ArrayList<JButton> buttons;
    private JLabel scoreLabel;

    public QuizQuestionFrame()
    {
        setTitle("Trivia Quiz");
        setSize(400, 450);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setLayout(new FlowLayout());

        questionLabel = new JLabel("Question");
        add(questionLabel);

        buttons = new ArrayList<>();
        for (int i = 0; i < 4; i++)
        {
            buttons.add(new JButton());
        }

        for (int i = 0; i < buttons.size(); i++) {
            add(buttons.get(i));
            int index = i;
            buttons.get(i).addActionListener(actionEvent -> onSubmitClicked(actionEvent, index));
        }

        scoreLabel = new JLabel("Score: 0/0");
        add(scoreLabel);

        GetQuizQuestion model = new GetQuizQuestion();
        presenter = new QuizQuestionPresenter(this, model);
        presenter.nextQuestion();
    }

    public void setQuestion(String question)
    {
        questionLabel.setText(question);
    }


    private void onSubmitClicked(ActionEvent actionEvent, int whichOne) {
        presenter.checkAnswer(whichOne);
        //pause after recolor
        resetColors();
        presenter.nextQuestion();
    }

    public void displayScore(int score, int total) {
        scoreLabel.setText("Score: " + score + "/" + total);
    }

    public void resetColors() {

        for (JButton button: buttons) {
            button.setBackground(null);
        }
    }

    public void setAnswers(ArrayList<String> abcOrder)
    {
        for (int i = 0; i < abcOrder.size(); i++) {
            buttons.get(i).setText(abcOrder.get(i));
        }
    }

    public void showError() {
    }

    public static void main(String[] args) {
        QuizQuestionFrame frame = new QuizQuestionFrame();
        frame.setVisible(true);
    }

    public void setColorToRed(int index) {
        buttons.get(index).setBackground(Color.RED);
    }

    public void setColorToGreen(int index) {
        buttons.get(index).setBackground(Color.GREEN);
    }
}