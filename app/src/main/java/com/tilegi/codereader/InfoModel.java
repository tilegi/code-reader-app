package com.tilegi.codereader;

public class InfoModel {
    private String saveName;
    private String registerName;
    private String rimType;
    private String tireType;
    private String sizeName;
    private String rimCodeInside;
    private String rimCodeOutside;
    private String tireCode;
    //hangi yazıları kaydedeceğimizi tanımladım

    public InfoModel(){};

    public InfoModel(String saveName, String sizeName, String rimCodeInside, String rimCodeOutside, String tireCode) {
        this.saveName = saveName;
        this.sizeName = sizeName;
        this.rimCodeInside = rimCodeInside;
        this.rimCodeOutside = rimCodeOutside;
        this.tireCode = tireCode;
    }

    public InfoModel(String saveName, String registerName, String rimType, String tireType, String sizeName, String rimCodeInside, String rimCodeOutside, String tireCode) {
        this.saveName = saveName;
        this.registerName = registerName;
        this.rimType = rimType;
        this.tireType = tireType;
        this.sizeName = sizeName;
        this.rimCodeInside = rimCodeInside;
        this.rimCodeOutside = rimCodeOutside;
        this.tireCode = tireCode;
    }

    public String getRegisterName() {
        return registerName;
    }

    public void setRegisterName(String registerName) {
        this.registerName = registerName;
    }

    public String getRimType() {
        return rimType;
    }

    public void setRimType(String rimType) {
        this.rimType = rimType;
    }

    public String getTireType() {
        return tireType;
    }

    public void setTireType(String tireType) {
        this.tireType = tireType;
    }

    public String getSaveName() {
        return saveName;
    }

    public void setSaveName(String saveName) {
        this.saveName = saveName;
    }

    public String getSizeName() {
        return sizeName;
    }

    public void setSizeName(String sizeName) {
        this.sizeName = sizeName;
    }

    public String getRimCodeInside() {
        return rimCodeInside;
    }

    public void setRimCodeInside(String rimCodeInside) {
        this.rimCodeInside = rimCodeInside;
    }

    public String getRimCodeOutside() {
        return rimCodeOutside;
    }

    public void setRimCodeOutside(String rimCodeOutside) {
        this.rimCodeOutside = rimCodeOutside;
    }

    public String getTireCode() {
        return tireCode;
    }

    public void setTireCode(String tireCode) {
        this.tireCode = tireCode;
    }
}
