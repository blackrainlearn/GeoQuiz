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

    public int getAnswered()
    {
        return mAnswered;
    }

    public void setAnswered(int answered)
    {
        mAnswered = answered;
    }

    private int mTextResId;
    private boolean mAnswerTrue;
    private int mAnswered;

    public Question(int textResId, boolean answerTrue)
    {
        mTextResId = textResId;
        mAnswerTrue = answerTrue;
    }
}
