package com.mo.model;

import android.content.Context;

import java.util.List;

/**
 * Created by 风雨诺 on 2019/3/28.
 */

public interface AnswerActivityDao {
    void getActivtyList(Context context, String userId, AnswerListener listener);

    void getQuestionInfo(Context context, String id, AnswerListener listener);

    void updateScore(Context context, String userid,String username,String answerId, String answerTitle, String score, UpdateScoreListener listener);

    interface AnswerListener {
        void result(List list);
    }
    interface UpdateScoreListener{
        void result(Boolean b);
    }
}
