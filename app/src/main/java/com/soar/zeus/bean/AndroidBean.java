package com.soar.zeus.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by jingbin on 2016/11/30.
 * 首页item bean
 */

public class AndroidBean implements Serializable {

    public int style_type;
    public int size_type;
    // 存储单独设置的值，用来显示title
    private String type_title;
    // 随机图URL
    private String image_url;

    private String _id;
    private String createdAt;
    private String desc;
    private String publishedAt;
    private String url;
    private boolean used;
    private String who;

    private String source;
    private List<String> images;

    public AndroidBean(int style_type, int size_type, String image_url) {
        this.style_type = style_type;
        this.size_type = size_type;
        this.image_url = image_url;
    }

    //    public AndroidBean(String type_title, String image_url, String desc) {
    //        this.type_title = type_title;
    //        this.image_url = image_url;
    //        this.desc = desc;
    //    }

    //    public AndroidBean(int style_type, int size_type, String type_title, String image_url, String desc) {
    //        this.type_title = type_title;
    //        this.image_url = image_url;
    //        this.desc = desc;
    //        this.size_type = size_type;
    //        this.style_type = style_type;
    //    }

    public String get_id() {
        return _id;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public String getDesc() {
        return desc;
    }

    public String getPublishedAt() {
        return publishedAt;
    }

    public int getType() {
        return size_type;
    }

    public String getUrl() {
        return url;
    }

    public boolean isUsed() {
        return used;
    }

    public String getWho() {
        return who;
    }

    public String getSource() {
        return source;
    }

    public List<String> getImages() {
        return images;
    }

    public String getType_title() {
        return type_title;
    }

    public void setType_title(String type_title) {
        this.type_title = type_title;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public void setPublishedAt(String publishedAt) {
        this.publishedAt = publishedAt;
    }

    public void setType(int type) {
        this.size_type = type;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setUsed(boolean used) {
        this.used = used;
    }

    public void setWho(String who) {
        this.who = who;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public void setImages(List<String> images) {
        this.images = images;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }

    public String getImage_url() {
        return image_url;
    }
}
