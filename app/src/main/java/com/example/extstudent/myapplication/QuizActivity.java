package com.example.extstudent.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Queue;

public class QuizActivity extends AppCompatActivity {
    private ArrayList<QuizQuestion> quizQuestionList = null;
    QuizQuestion currentQuestion = null;
    int currentQuestionNumber = 1;

    private int currentScore = 0;
    private int maxScore = 0;

    TextView textViewQuestionTitle = null;
    TextView textViewQuestion = null;
    TextView textViewScore = null;

    RadioGroup radioGroupQuestion = null;
    RadioButton radioButtonA = null;
    RadioButton radioButtonB = null;
    RadioButton radioButtonC = null;
    RadioButton radioButtonD = null;

    Button buttonSubmit = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        // Initialize widgets.
        this.textViewQuestionTitle = (TextView) findViewById(R.id.textViewQuestionTitle);    // The question title
        this.textViewQuestion = (TextView) findViewById(R.id.textViewQuestion);              // The question asked.
        this.textViewScore = (TextView) findViewById(R.id.textViewScore);                    // The current score.

        // Intialize the radio buttons for question multiple choice answers.
        radioGroupQuestion = (RadioGroup) findViewById(R.id.radioGroupQuestion);             // Create a group for radio buttons.
        radioButtonA = (RadioButton) findViewById(R.id.radioButtonA);
        radioButtonB = (RadioButton) findViewById(R.id.radioButtonB);
        radioButtonC = (RadioButton) findViewById(R.id.radioButtonC);
        radioButtonD = (RadioButton) findViewById(R.id.radioButtonD);
    maxScore = 10;
        // Initialize the submit question answer along with the callback function.
        buttonSubmit = (Button) findViewById(R.id.buttonSubmit);
        this.buttonSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setQuestionView(quizQuestionList.get(1));
                // Step 1: Call validateAnswer() to check if the current answer (provided by user) is correct (valid).
                // Step 2: Compare the current question number with the max score. If the case where the current question number
                //         is less than the max score (we have more questions to go.
                // Step 3: If we still have questions to ask: (1) Set the currentQuestion variable, (2) prompt the user with setQuestionView.
                //         and (3) remember to increment the currentQuestionNumber.
                // Step 4: If there are now questions left in the array, transition the user to the results activity.
                if (validateAnswer()) {
                    if (currentQuestionNumber < maxScore) {
                        currentQuestion = quizQuestionList.get(1);

                        setQuestionView(currentQuestion);

                        ///////////////////////////////////////////////////////////////////////////////////////////////////////
                        // TO-DO: Set currentQuestion to the next question (because we want to process what is next).
                        // You can set the reference from the quizQuestionList.
                        // Use currentQuestionNumber as the index (remember to increment this at the end so that we can fetch the next question index).
                        ///////////////////////////////////////////////////////////////////////////////////////////////////////
                    } else {
                        // No questions left to ask. Transition the user to the results page.
                        Intent intent = new Intent(QuizActivity.this, com.example.extstudent.myapplication.ResultsActivity.class);
                        intent.putExtra("Score: " ,currentScore);
                        startActivity(intent);
                        ///////////////////////////////////////////////////////////////////////////////////////////////////////
                        // TO-DO: Create a new intent that will transition to user to next ResultsActivity.
                        // HINT: We want to pass in some extra values for the results class to use. So use something like:
                        //       intent.putExtra(...);
                        ///////////////////////////////////////////////////////////////////////////////////////////////////////
                    }
                }
            }
        });

        // Initiate all questions first.
        this.initQuestions();

        // Ask use the first question when this activity loads.
        this.setQuestionView(this.currentQuestion);
        currentQuestion = quizQuestionList.get(currentQuestionNumber ++);
    }

    private void initQuestions() {
        // Create some questions to ask the questions.

        this.quizQuestionList = new ArrayList<QuizQuestion>();  // Initialize our question array.

        ///////////////////////////////////////////////////////////////////////////////////////////////////////
        // TO-DO: Create instances (using the new QuizQuestion()) of your questions.
        QuizQuestion one = new QuizQuestion();
        QuizQuestion two = new QuizQuestion();
        QuizQuestion three = new QuizQuestion();
        QuizQuestion four = new QuizQuestion();
        QuizQuestion five = new QuizQuestion();
        one.setQuestion("How many bones does the average human have?");
        one.setChoiceA("10");
        one.setChoiceB("100");
        one.setChoiceC("206");
        one.setChoiceD("199");
        one.setCorrectAnswer("206");
        quizQuestionList.add(one);
        two.setQuestion("How many continents are there in the world?");
        two.setChoiceA("24");
        two.setChoiceB("7");
        two.setChoiceC("4");
        two.setChoiceD("2");
        two.setCorrectAnswer("7");
            // - The question to ask.
        // - Set choice options for A, B, C, and D.
        // - Set the correct answer (so that class knows which one is correct or not).
        // - Remember to add the object to our quizQuestionList array. Hint: Use .add(...) function here.
        // NOTE: No widgets should be set in this method.
        ///////////////////////////////////////////////////////////////////////////////////////////////////////
currentQuestion = one;

        ///////////////////////////////////////////////////////////////////////////////////////////////////////
        // TO-DO: Set your currentQuestion to point to your first question here (uncomment out the code below).
        ///////////////////////////////////////////////////////////////////////////////////////////////////////

    this.currentQuestion = quizQuestionList.get(0);
        // Set the current, score, and total question size.
        this.currentQuestionNumber = 1;
        this.maxScore = this.quizQuestionList.size();
        this.currentScore = 0;
    }

    private void setQuestionView(QuizQuestion quizQuestion) {
        if (quizQuestion == null) {
            Log.d("[DEBUG]", "quizQuestion is null in setQuestionView.");
            return;
        }

        // Clear the radio button checks just encase it was been set previously.
        radioGroupQuestion.clearCheck();
        textViewQuestionTitle.setText(currentQuestionNumber);
        // Loads the current question view.
        ///////////////////////////////////////////////////////////////////////////////////////////////////////
        // TO-DO: Set the UI view (all your widgets) with the current QuizQuestion passed in.
        // Hint: Use your getters from the QuizQuestion class to get the values stored there.
        // Set the following widget text:
        quizQuestion.getQuestion();
        radioButtonA.setText(quizQuestion.getChoiceA());
        radioButtonB.setText(quizQuestion.getChoiceB());
        radioButtonC.setText(quizQuestion.getChoiceC());
        radioButtonD.setText(quizQuestion.getChoiceD());
        textViewScore.setText(("Score: " + currentScore));
        // - The question text (i.e. Question #1).
        // - Set the question to ask.
        // - Set all for radio button text.
        // - Set the score view with the current score (remeber to convert integer to string).
        //   Example: Score: 2
        ///////////////////////////////////////////////////////////////////////////////////////////////////////
    }

    private boolean validateAnswer() {

        int selectedButtonId = this.radioGroupQuestion.getCheckedRadioButtonId();
        if (selectedButtonId != -1) {
            String answerSelectedStr = ((RadioButton) findViewById(selectedButtonId)).getText().toString();

            if (currentQuestion.isCorrectAnswer(answerSelectedStr)) {

                Log.d("ANSWER: ", "Correct");
                currentScore++;
            } else {
                Log.d("ANSWER: ", "Incorrect");
            }
            return true;
        } else {

            Toast.makeText(getApplicationContext(), "Please Select An Answer",
                    Toast.LENGTH_SHORT).show();
            return false;
        }
    }
}