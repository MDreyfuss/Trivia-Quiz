import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;

public class QuizQuestionFrame extends JFrame {

    private final QuizQuestionPresenter presenter;
    private JLabel questionLabel;
    private ArrayList<JButton> buttons;

    public QuizQuestionFrame()
    {
        setTitle("Trivia Quiz");
        setSize(400, 450);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setLayout(new FlowLayout());

        JPanel questionsPanel = new JPanel();
        questionsPanel.setLayout(new BoxLayout(questionsPanel, BoxLayout.PAGE_AXIS));
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
        //pause
        continueAfterPause();
    }

    private void continueAfterPause() {
        resetColors();
        presenter.nextQuestion();
    }

    private void resetColors() {

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

    public void setColorToRed(int whichOne) {
        buttons.get(whichOne).setBackground(Color.RED);
    }

    public void setColorToGreen(int placementOfCorrectAnswer) {
        buttons.get(placementOfCorrectAnswer).setBackground(Color.GREEN);
    }
}
