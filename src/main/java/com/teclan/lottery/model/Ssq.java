package com.teclan.lottery.model;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.fastjson.JSON;
import com.teclan.easyexcel.model.ExcelModel;

public class Ssq implements ExcelModel {

    @ExcelProperty("期数")
    private String issue;
    @ExcelProperty("开奖日期")
    private String openTime;
    @ExcelProperty("红球1")
    private int r1 ;
    @ExcelProperty("红球2")
    private int r2 ;
    @ExcelProperty("红球3")
    private int r3 ;
    @ExcelProperty("红球4")
    private int r4 ;
    @ExcelProperty("红球5")
    private int r5 ;
    @ExcelProperty("红球6")
    private int r6 ;
    @ExcelProperty("蓝球")
    private int blue ;

    public Ssq() {
    }

    public Ssq(String issue, String openTime, int r1, int r2, int r3, int r4, int r5, int r6, int blue) {
        this.issue = issue;
        this.openTime = openTime;
        this.r1 = r1;
        this.r2 = r2;
        this.r3 = r3;
        this.r4 = r4;
        this.r5 = r5;
        this.r6 = r6;
        this.blue = blue;
    }

    public String getIssue() {
        return issue;
    }

    public void setIssue(String issue) {
        this.issue = issue;
    }

    public String getOpenTime() {
        return openTime;
    }

    public void setOpenTime(String openTime) {
        this.openTime = openTime;
    }

    public int getR1() {
        return r1;
    }

    public void setR1(int r1) {
        this.r1 = r1;
    }

    public int getR2() {
        return r2;
    }

    public void setR2(int r2) {
        this.r2 = r2;
    }

    public int getR3() {
        return r3;
    }

    public void setR3(int r3) {
        this.r3 = r3;
    }

    public int getR4() {
        return r4;
    }

    public void setR4(int r4) {
        this.r4 = r4;
    }

    public int getR5() {
        return r5;
    }

    public void setR5(int r5) {
        this.r5 = r5;
    }

    public int getR6() {
        return r6;
    }

    public void setR6(int r6) {
        this.r6 = r6;
    }

    public int getBlue() {
        return blue;
    }

    public void setBlue(int blue) {
        this.blue = blue;
    }

    public String toString(){
        return JSON.toJSONString(this);
    }
}
