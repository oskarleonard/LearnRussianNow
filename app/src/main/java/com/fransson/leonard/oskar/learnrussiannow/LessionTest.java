package com.fransson.leonard.oskar.learnrussiannow;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.fransson.leonard.oskar.learnrussiannow.MyClasses.Answer;
import com.fransson.leonard.oskar.learnrussiannow.MyClasses.Lession;
import com.fransson.leonard.oskar.learnrussiannow.MyClasses.Question;
import com.fransson.leonard.oskar.learnrussiannow.MyClasses.SPHelper;
import com.fransson.leonard.oskar.learnrussiannow.util.MyDebugger;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class LessionTest extends AppCompatActivity {

    private boolean testPassed = true;

    private List<Question>shfdQuestions;
    private Lession lession;
    private Question currentQuestion;
    private int questionNr = 0;
    private List<Answer> currentAnswers = new ArrayList<>();

    private TextView tvQuestions;
    private Button b1,b2,b3,b4,b5,b6;
    private Button[] answerButtons;
    private List<String> pressedButtons = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lession_test);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            if (MyDebugger.ON) {
                Log.i("LessionTest", "Trying to get Parcable");
            }
            lession = extras.getParcelable(LessionActivity.BUNDLE_LESSION);
            shfdQuestions = lession.getClassQuestions();
            Collections.shuffle(shfdQuestions);
        }


        addXmlViews();
        setBtnText();
    }

    private void addXmlViews() {
        tvQuestions = (TextView) findViewById(R.id.tvQuestion);
        b1 = (Button) findViewById(R.id.btnText1);
        b2 = (Button) findViewById(R.id.btnText2);
        b3 = (Button) findViewById(R.id.btnText3);
        b4 = (Button) findViewById(R.id.btnText4);
        b5 = (Button) findViewById(R.id.btnText5);
        answerButtons = new Button[]{b1, b2, b3, b4, b5};
        //Add a listener metod for all buttons
        for (Button b : answerButtons) {
            b.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Button bb = (Button) v;

                    //Check weather it was already pressed. It should be able to deactivate a selection.
                    String buttonText = bb.getText().toString();
                    if (pressedButtons.contains(buttonText)) {
                        //Show that it has been deactivated.
                        bb.setBackgroundResource(R.drawable.test_buttons);
                        //Remove it from pressedbuttons.
                        pressedButtons.remove(buttonText);
                    }else{
                        pressedButtons.add(bb.getText().toString());
                        //bb.getBackground().setColorFilter(Color.parseColor("#ffafafaf"), PorterDuff.Mode.SRC_ATOP);
                        bb.setBackgroundResource(R.drawable.test_buttons_pressed);
                    }

                }
            });
        }

        b6 = (Button) findViewById(R.id.btnText6);
        b6.setBackgroundResource(R.drawable.test_buttons_done);
        b6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkCorrectness();

                //Let the user see if he got it correct.
                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    public void run() {

                        resetButtonColors();
                        //Increment until all questions has been asked.
                        if (questionNr < (shfdQuestions.size() - 1)) {
                            questionNr++;
                            pressedButtons = new ArrayList<>();
                            currentAnswers = new ArrayList<>();

                            setBtnText();
                        } else { //End of test
                            testEnded();
                        }

                    }
                }, 1000);

            }
        });
    }

    private void testEnded() {
        if (testPassed) {
            Toast.makeText(this, "You pased the test", Toast.LENGTH_SHORT).show();

            //lession.setClassQuestions(shfdQuestions);
            //Mark this in the lession obj.
            lession.setCompleted(true);
            //Save the obj
            SPHelper.update_lession(this, lession, SPHelper.SPK_MODULE_INTRODUCTION);

            finish();
        }else{
            Toast.makeText(this, "Please try again", Toast.LENGTH_SHORT).show();
            SPHelper.update_lession(this, lession, SPHelper.SPK_MODULE_INTRODUCTION);
            finish();
        }
    }

    //If user aborts the test before the last question i need to save his statisticCorrectness
    // for each question he answered.
    @Override
    protected void onDestroy() {
        super.onDestroy();
        testEnded();
        if (MyDebugger.ON) {
            Log.i("LessionTest", "ondestroy= ");
        }
    }

    private void checkCorrectness() {
        boolean win = true;
        for (Answer a : currentAnswers) {
            //If an answer is correct it should be in pressedButtons
            if (a.isCorrect()) {
                if (!pressedButtons.contains(a.getAnswer())) {
                    win = false;
                }
            }else{ //is answer is wrong it shouldnt be in pressedButtons
                if (pressedButtons.contains(a.getAnswer())) {
                    win = false;
                }
            }
        }

        if (win) {
            currentQuestion.increaseCorrectness();
            //b6.getBackground().setColorFilter(Color.GREEN, PorterDuff.Mode.SRC_ATOP);
            b6.setBackgroundResource(R.drawable.test_buttons_green);
            b6.setEnabled(false);
        }else{
            testPassed = false;
            currentQuestion.decreaseCorrectness();
            //b6.getBackground().setColorFilter(Color.RED, PorterDuff.Mode.SRC_OVER);
            b6.setBackgroundResource(R.drawable.test_buttons_red);
            b6.setEnabled(false);
        }

        if (MyDebugger.ON) {
            Log.i("LessionTest", "The currentQuestion statistics = " + currentQuestion.getStatisticsCorrectness());
        }
    }

    private void setBtnText() {

        currentQuestion = shfdQuestions.get(questionNr);

        if (MyDebugger.ON) {
            Log.i("LessionTest", "Question Nr " + questionNr);
            Log.i("LessionTest", "shfdQuestions size  " + shfdQuestions.size());
        }

        tvQuestions.setText(currentQuestion.getQuestion());

        List<Answer> allAnswersForAQuestion = currentQuestion.getAnswers(); //Multiple (more than 5)
        Collections.shuffle(allAnswersForAQuestion);

        for (int x = 0; x < answerButtons.length; x++) {
            answerButtons[x].setText(allAnswersForAQuestion.get(x).getAnswer());
            currentAnswers.add(allAnswersForAQuestion.get(x)); //Max 5
        }

    }

    private void resetButtonColors(){
        //Somehow the button keeps the color for pressed state so i need to set it to original pressed state and than clear it.

        b6.setEnabled(true);

        b6.invalidate(); //Does this do anything?
        b6.setBackgroundResource(R.drawable.test_buttons_done);
        b6.invalidate();

//        Drawable d1 = b6.getBackground();
//        d1.clearColorFilter();
//        b6.invalidateDrawable(d1);
//        b6.getBackground().clearColorFilter();

        //b6.invalidate();


        for(Button button : answerButtons){
//            Drawable d = button.getBackground();
//            button.invalidateDrawable(d);
//            d.clearColorFilter();
            button.setBackgroundResource(R.drawable.test_buttons);
        }



    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_lession_test, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
