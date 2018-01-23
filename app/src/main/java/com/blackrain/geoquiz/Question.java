package com.blackrain.geoquiz;

/**
 * Created by Administrator on 2018/1/22.
 */

public class Question
{

    public int getTextResId()
    {
        return mTextResId;
    }

    public void setTextResId(int textResId)
    {
        mTextResId = textResId;
    }

    public boolean isAnswerTrue()
    {
        return mAnswerTrue;
    }

    public void setAnswerTrue(boolean answerTrue)
    {
        mAnswerTrue = answerTrue;
    }

    private int mTextResId;
    private boolean mAnswerTrue;

    public Question(int textResId, boolean answerTrue)
    {
        mTextResId = textResId;
        mAnswerTrue = answerTrue;
    }
}
