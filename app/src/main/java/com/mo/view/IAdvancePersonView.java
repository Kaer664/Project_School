package com.mo.view;

import com.mo.bean.AdvancePersonBean;

import java.util.List;

/**
 * Created by 风雨诺 on 2019/3/30.
 */

public interface IAdvancePersonView {
    //显示先进党员列表
    void showAdvancePersonList(List<AdvancePersonBean.AdvancedPersonListBean> list) ;
    //显示先进党员信息
    void showAdvancePersonInfo(List<AdvancePersonBean.AdvancedPersonListBean> list);
}
