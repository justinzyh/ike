package com.justinzyh.film.mvp.bean;

/**
 * @创建者 justinzyh
 * @创建时间 2016 2016/11/3 12:33
 * @描述 ${TODO}
 * @qq 1476156127
 */

public class RightCardInfo {

    private String name;
    private int imageId;

    public RightCardInfo(int imageId, String name) {
        this.imageId = imageId;
        this.name = name;
    }

    public int getImageId() {
        return imageId;
    }

    public void setImageId(int imageId) {
        this.imageId = imageId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
