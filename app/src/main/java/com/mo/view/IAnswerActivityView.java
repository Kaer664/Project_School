package com.mo.view;

import com.mo.bean.AnswerActivityListBean;
import com.mo.bean.QuestionInfoBean;

import java.util.List;

/**
 * Created by 风雨诺 on 2019/3/30.
 */

public interface IAnswerActivityView {
    //显示所有答题活动列表
    void showAnswerActivityList(List<AnswerActivityListBean.UserAnswerActivityListBean> list);
    //显示问题信息
    void showQuestionInfo(List<QuestionInfoBean.ProblemListBean> list);
    //显示保存成绩是否成功
    void isSave(boolean b);
}
