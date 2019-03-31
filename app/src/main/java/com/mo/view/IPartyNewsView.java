package com.mo.view;

import com.mo.bean.PartyNewsBean;
import com.mo.bean.PartyNewsListBean;

import java.util.List;
/**
 * Created by 风雨诺 on 2019/3/30.
 * 党务要闻视图层接口
 */

public interface IPartyNewsView {
    //显示所有党务要闻
    void showAllPartyNews(List<PartyNewsListBean.PartyAffairsNewsListBean> list);
    //显示党务要闻具体
    void showPartyNewsInfo(List<PartyNewsBean.PartyAffairsNewsBean> list);
}
