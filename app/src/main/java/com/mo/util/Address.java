package com.mo.util;

/**
 * Created by 风雨诺 on 2019/3/25.
 */

public class Address {

	public static final String Common="http://172.18.1.168:8080";
    //用户登录
    public static final String USER_LOGIN=Common+"/redplat/servlet/UserLoginServletApp";
    //及时滚动通知
    public static final String ROLLING_NOTIFY=Common+"/redplat/servlet/RollingNotifyServletApp";
    //所有党务要闻列表
    public static final String GET_ALL_NEWS=Common+"/redplat/servlet/PartyAffairsNewsServletApp";
    //根据id获取党务要闻详细信息
    public static final String GET_NEWS_BY_ID=Common+"/redplat/servlet/PartyAffairsNewsServletAppByID";
    //获取所有党务活动列表
    public static final String GET_ALL_PARTY_ACTIVITY=Common+"/redplat/servlet/PartyActivitiesListServletApp";
    //根据ID获取党务活动详细信息及所有针对这个党务活动发布的观点留言
    public static final String GET_PARTY_ACTIVITY_BY_ID=Common+"/redplat/servlet/PartyActivitiesByIDServletApp";
    //发表评论（同时用于学习园地评论、党务活动以及党员生日活动评论）
    public static final String ADD_REPLY=Common+"/redplat/servlet/AddReplyServletApp";
    //通过id获取先进人物详细信息，如果是id=all就是获取所有先进人物信息
    public static final String GET_ADVANCED_PERSRON_BY_ID=Common+"/redplat/servlet/AdvancedPersonServletApp";
    //根据ID获取学习园地详细信息及所有针对这个学习活动发布的心得体会
    //如果id=all表示获取学习园地列表
    public static final String GET_LEARNING_GARDEN_BY_ID=Common+"/redplat/servlet/LearningGardenServletApp";
    ///问题反馈
    public static final String ADD_FEEDBACK=Common+"/redplat/servlet/AddFeedBackServletApp";
    //获取所有答题活动列表
    public static final String GET_ALL_ANSWER_ACTIVITY=Common+"/redplat/servlet/AnswerActivityServletApp";
    //根据id获取答题活动的题目明细
    public static final String GET_ANSWER_ACTIVITY_INFO_BY_ID=Common+"/redplat/servlet/AnswerActivityMXServletApp";
    //保存用户答题成绩
    public static final String SAVE_SCORE=Common+"/redplat/servlet/AddAnswerActivityScoreServletApp";
    //获取当前月份过生日的党员列表
    public static final String GET_BIRTHDAY=Common+"/redplat/servlet/BirthdayMonthServletApp";
    //根据党员生日活动信息ID，获取某一具体的活动详情
    // 如果id=all表示获取所有党员生日活动列表
    public static final String GET_BRITH_ACTIVITY_BY_ID=Common+"/redplat/servlet/BirthActivitiesServletApp";
    //获取当前登录用户积分列表
    public static final String GET_USER_SCORE=Common+"/redplat/servlet/getCurrentUserScoreListServeltApp";
    //获取积分排行榜
    public static final String GET_SCORE_RANKING=Common+"/redplat/servlet/getRankingListServletApp";
    //修改密码
    public static final String CHANGE_PWD=Common+"/redplat/servlet/ChangePassServletApp";
    //获取软件更新
    public static final String UPDATE_APP=Common+"/redplat/servlet/UploadNewAppVersionServletApp";
    //获取服务器时间
    public static final String GET_TIME=Common+"/redplat/servlet/GetServerSysCurrentDateServletApp";
    //图片url
    public static final String PIC_URL=Common+"/redplat//UpLoad/workPic/";
    //安装包文件地址
    public static final String APP_FILE_URL=Common+"/redplat/UpLoad/newAppVersion/";
    //学习园地文件地址
    public static final String FILE_URL=Common+"/redplat//UpLoad/learningFile/";
    //视频url
    public static final String VIDAO_URL=Common+"/redplat//UpLoad/learningVideo/";
}
