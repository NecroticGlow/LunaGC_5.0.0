package com.xiaoer.watermark.bean;

import java.io.Serializable;
import java.util.ArrayList;

public class WaterMarkConfig implements Serializable {
    public int textColor = 1376819886;
    public String content = "watermark";
    public int textSize = 12;
    public float rotation = -25;
    public String configId = System.currentTimeMillis() + "";

    public boolean isOpen;

    public boolean isOpen() {
        return isOpen;
    }

    public void setOpen(boolean open) {
        isOpen = open;
    }

    public ArrayList<String> packageList = new ArrayList<>();
    public int getTextColor() {
        return textColor;
    }

    public void setTextColor(int textColor) {
        this.textColor = textColor;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getTextSize() {
        return textSize;
    }

    public void setTextSize(int textSize) {
        this.textSize = textSize;
    }

    public float getRotation() {
        return rotation;
    }

    public void setRotation(float rotation) {
        this.rotation = rotation;
    }

    public String getConfigId() {
        return configId;
    }

    public void setConfigId(String configId) {
        this.configId = configId;
    }

    public void setAppList(ArrayList<String> newList) {
        if(newList == null){
            newList = new ArrayList<>();
        }
        packageList = newList;
    }

    public ArrayList<String> getAppList(){
        return packageList;
    }

    public boolean useImage = false;
    public int imageAlpha = 128;

    public boolean isUseImage() {
        return useImage;
    }

    public void setUseImage(boolean useImage) {
        this.useImage = useImage;
    }

    public int getImageAlpha() {
        return imageAlpha;
    }

    public void setImageAlpha(int imageAlpha) {
        this.imageAlpha = imageAlpha;
    }

    @Override
    public String toString() {
        return "WaterMarkConfig{" + "content='" + content + '\'' + ", configId='" + configId + '\'' + ", packageList=" + packageList + '}';
    }
}

