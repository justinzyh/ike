package com.justinzyh.film.mvp.bean;

/**
 * @创建者 justinzyh
 * @创建时间 2016 2016/11/4 13:19
 * @描述 ${TODO}
 * @qq 1476156127
 */

public class VerticalRollingBean {
    private String mFront; //前面的文字
    private String mBack; //后面的文字
    private String mUrl;//包含的链接

    public VerticalRollingBean() {
        super();
    }
    public VerticalRollingBean(String mFront, String mBack, String mUrl) {
        this.mFront = mFront;
        this.mBack = mBack;
        this.mUrl = mUrl;
    }

    public String getmUrl() {
        return mUrl;
    }

    public void setmUrl(String mUrl) {
        this.mUrl = mUrl;
    }

    public String getmFront() {
        return mFront;
    }

    public void setmFront(String mFront) {
        this.mFront = mFront;
    }

    public String getmBack() {
        return mBack;
    }

    public void setmBack(String mBack) {
        this.mBack = mBack;
    }
}

