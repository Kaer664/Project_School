package com.mo.view;

import com.mo.bean.ScoreRankBean;
import com.mo.bean.UserScoreBean;

import java.util.List;

/**
 * Created by 风雨诺 on 2019/3/29.
 * 与积分相关的视图层接口
 */

public interface IScoreView {
    //显示个人积分具体信息
    void showUserScoreInfo(UserScoreBean bean);
    //显示积分排行榜
    void showScoreRank(List<ScoreRankBean.AllUserScoreListBean> list);
}
