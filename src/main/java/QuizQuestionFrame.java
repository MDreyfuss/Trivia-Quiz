import json.OpenTriviaDatabaseFactory;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;

public class QuizQuestionFrame extends JFrame {

    private final QuizQuestionPresenter presenter;
    private JLabel questionLabel;
    private ArrayList<JButton> buttons;
    private JLabel scoreLabel;
    private JButton nextQuestionButton;

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

        nextQuestionButton = new JButton("Next Question");
        add(nextQuestionButton);
        nextQuestionButton.addActionListener(this::onNextQuestionClicked);

        OpenTriviaDatabaseFactory factory = new OpenTriviaDatabaseFactory();
        presenter = new QuizQuestionPresenter(this, factory.getInstance());

        presenter.nextQuestion();

    }

    private void onNextQuestionClicked(ActionEvent actionEvent) {
        resetColors();
        presenter.nextQuestion();
    }

    public void setQuestion(String question)
    {
        questionLabel.setText("<html>" + question + "</html>");
    }


    private void onSubmitClicked(ActionEvent actionEvent, int whichOne) {
        presenter.checkAnswer(whichOne);
    }

    public void displayScore(int score, int total) {
        scoreLabel.setText("Score: " + score + "/" + total);
    }

    public void resetColors() {

        for (JButton button : buttons) {
            button.setBackground(null);
        }
    }

    public void setAnswers(ArrayList<String> abcOrder)
    {
        for (int i = 0; i < abcOrder.size(); i++) {
            buttons.get(i).setText("<html>" + abcOrder.get(i) + "</html>");
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
