package com.blackrain.geoquiz;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class QuizActivity extends AppCompatActivity
{
    private static final String TAG = "QuizActivity";
    private static final String KEY_INDEX = "index";
    private static final String QUESTION_LIST = "question_list";

    private Button mTrueButton;
    private Button mFalseButton;
    private ImageButton mNextButton;
    private ImageButton mPrevButton;

    private TextView mQuestionTextView;
    private Question[] mQuestionBank = new Question[]
            {
                    new Question(R.string.question_australia, true),
                    new Question(R.string.question_oceans, true),
                    new Question(R.string.question_mideast, true),
                    new Question(R.string.question_africa, true),
                    new Question(R.string.question_americas, true),
                    new Question(R.string.question_africa, true),
                    new Question(R.string.question_asia, true),
            };
    private int mCurrentIndex = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate(Bundle) called");
        setContentView(R.layout.activity_quiz);
        if (savedInstanceState != null)
        {
            mCurrentIndex = savedInstanceState.getInt(KEY_INDEX, 0);
            int[] mQuestionAnswerArray = savedInstanceState.getIntArray(QUESTION_LIST);
            for (int i = 0; i < mQuestionBank.length; ++i)
            {
                mQuestionBank[i].setAnswered(mQuestionAnswerArray[i]);
            }
        }

        mQuestionTextView = (TextView) findViewById(R.id.question_text_view);

        mQuestionTextView.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                mCurrentIndex = (mCurrentIndex + 1) % mQuestionBank.length;
                updateQuestion();
            }
        });

        mTrueButton = (Button)findViewById(R.id.true_button);
        mTrueButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                checkAnswer(true);
            }
        });

        mFalseButton = (Button)findViewById(R.id.false_button);
        mFalseButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                checkAnswer(false);
            }
        });

        mNextButton = (ImageButton) findViewById(R.id.next_button);
        mNextButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                mCurrentIndex = (mCurrentIndex + 1) % mQuestionBank.length;
                updateQuestion();
            }
        });

        mPrevButton = (ImageButton) findViewById(R.id.pre_button);
        mPrevButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                mCurrentIndex = Math.max(0, mCurrentIndex - 1);
                updateQuestion();
            }
        });
        updateQuestion();
    }

    private void updateQuestion()
    {
        //Log.d(TAG, "Updateing question text ", new Exception());
        Log.d(TAG, "Current question index: " + mCurrentIndex);
        Question question = null;
        try
        {
            question = mQuestionBank[mCurrentIndex];
        }
        catch (ArrayIndexOutOfBoundsException ex)
        {
            Log.e(TAG, "Index was out of bounds", ex);
        }
        if (question == null)
            return;

        int questionId = question.getTextResId();
        mQuestionTextView.setText(questionId);
        updateButtons();
    }

    void updateButtons()
    {
        if (mQuestionBank[mCurrentIndex].getAnswered() > 0)
        {
            mTrueButton.setEnabled(false);
            mFalseButton.setEnabled(false);
        }
        else
        {
            mTrueButton.setEnabled(true);
            mFalseButton.setEnabled(true);
        }

    }

    private void checkAnswer(boolean userPressedTrue)
    {
        boolean answerIsTrue = mQuestionBank[mCurrentIndex].isAnswerTrue();
        int messageResId = 0;
        if (userPressedTrue == answerIsTrue)
        {
            mQuestionBank[mCurrentIndex].setAnswered(1);
            messageResId = R.string.correct_toast;
        }
        else
        {
            mQuestionBank[mCurrentIndex].setAnswered(2);
            messageResId = R.string.incorrect_toast;
        }
        updateButtons();
        boolean isFinished = true;
        float correct = 0f;
        for (int i = 0; i < mQuestionBank.length; ++i)
        {
            int answeredRes = mQuestionBank[i].getAnswered();
            if (answeredRes == 0)
            {
                isFinished = false;
                break;
            }
            else if (answeredRes == 1)
                correct += 1f;
        }

        Toast.makeText(this, messageResId, Toast.LENGTH_SHORT).show();
        if (isFinished)
        {
            float all = mQuestionBank.length;
            Toast.makeText(this, String.format("correct result: %.2f %%",  (correct / all ) * 100f), Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG, "onStart() called");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "onResume() called");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG, "onPause() called");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG, "onStop() called");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy() called");
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        Log.i(TAG, "onSaveInstanceState");
        outState.putInt(KEY_INDEX, mCurrentIndex);

        int[] mQuestionAnswerArray = new int[mQuestionBank.length];
        for (int i = 0; i < mQuestionBank.length; ++i)
        {
            mQuestionAnswerArray[i] = mQuestionBank[i].getAnswered();
        }
        outState.putIntArray(QUESTION_LIST, mQuestionAnswerArray);
    }
}
