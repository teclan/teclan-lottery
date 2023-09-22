package com.teclan.lottery.model;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.fastjson.JSON;
import com.teclan.easyexcel.model.ExcelModel;

public class Happy8 implements ExcelModel {

    @ExcelProperty("期数")
    private String issue;
    @ExcelProperty("开奖日期")
    private String openTime;
    @ExcelProperty("号码1")
    private int v1 ;
    @ExcelProperty("号码2")
    private int v2 ;
    @ExcelProperty("号码3")
    private int v3 ;
    @ExcelProperty("号码4")
    private int v4 ;
    @ExcelProperty("号码5")
    private int v5 ;
    @ExcelProperty("号码6")
    private int v6 ;
    @ExcelProperty("号码7")
    private int v7 ;
    @ExcelProperty("号码8")
    private int v8 ;
    @ExcelProperty("号码9")
    private int v9 ;
    @ExcelProperty("号码10")
    private int v10 ;
    @ExcelProperty("号码11")
    private int v11 ;
    @ExcelProperty("号码12")
    private int v12 ;
    @ExcelProperty("号码13")
    private int v13 ;
    @ExcelProperty("号码14")
    private int v14 ;
    @ExcelProperty("号码15")
    private int v15 ;
    @ExcelProperty("号码16")
    private int v16 ;
    @ExcelProperty("号码17")
    private int v17 ;
    @ExcelProperty("号码18")
    private int v18 ;
    @ExcelProperty("号码19")
    private int v19 ;
    @ExcelProperty("号码20")
    private int v20 ;

    public Happy8() {
    }

    public Happy8(String issue, String openTime, int v1, int v2, int v3, int v4, int v5, int v6, int v7, int v8, int v9, int v10, int v11, int v12, int v13, int v14, int v15, int v16, int v17, int v18, int v19, int v20) {
        this.issue = issue;
        this.openTime = openTime;
        this.v1 = v1;
        this.v2 = v2;
        this.v3 = v3;
        this.v4 = v4;
        this.v5 = v5;
        this.v6 = v6;
        this.v7 = v7;
        this.v8 = v8;
        this.v9 = v9;
        this.v10 = v10;
        this.v11 = v11;
        this.v12 = v12;
        this.v13 = v13;
        this.v14 = v14;
        this.v15 = v15;
        this.v16 = v16;
        this.v17 = v17;
        this.v18 = v18;
        this.v19 = v19;
        this.v20 = v20;
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

    public int getV1() {
        return v1;
    }

    public void setV1(int v1) {
        this.v1 = v1;
    }

    public int getV2() {
        return v2;
    }

    public void setV2(int v2) {
        this.v2 = v2;
    }

    public int getV3() {
        return v3;
    }

    public void setV3(int v3) {
        this.v3 = v3;
    }

    public int getV4() {
        return v4;
    }

    public void setV4(int v4) {
        this.v4 = v4;
    }

    public int getV5() {
        return v5;
    }

    public void setV5(int v5) {
        this.v5 = v5;
    }

    public int getV6() {
        return v6;
    }

    public void setV6(int v6) {
        this.v6 = v6;
    }

    public int getV7() {
        return v7;
    }

    public void setV7(int v7) {
        this.v7 = v7;
    }

    public int getV8() {
        return v8;
    }

    public void setV8(int v8) {
        this.v8 = v8;
    }

    public int getV9() {
        return v9;
    }

    public void setV9(int v9) {
        this.v9 = v9;
    }

    public int getV10() {
        return v10;
    }

    public void setV10(int v10) {
        this.v10 = v10;
    }

    public int getV11() {
        return v11;
    }

    public void setV11(int v11) {
        this.v11 = v11;
    }

    public int getV12() {
        return v12;
    }

    public void setV12(int v12) {
        this.v12 = v12;
    }

    public int getV13() {
        return v13;
    }

    public void setV13(int v13) {
        this.v13 = v13;
    }

    public int getV14() {
        return v14;
    }

    public void setV14(int v14) {
        this.v14 = v14;
    }

    public int getV15() {
        return v15;
    }

    public void setV15(int v15) {
        this.v15 = v15;
    }

    public int getV16() {
        return v16;
    }

    public void setV16(int v16) {
        this.v16 = v16;
    }

    public int getV17() {
        return v17;
    }

    public void setV17(int v17) {
        this.v17 = v17;
    }

    public int getV18() {
        return v18;
    }

    public void setV18(int v18) {
        this.v18 = v18;
    }

    public int getV19() {
        return v19;
    }

    public void setV19(int v19) {
        this.v19 = v19;
    }

    public int getV20() {
        return v20;
    }

    public void setV20(int v20) {
        this.v20 = v20;
    }

    public String toString(){
        return JSON.toJSONString(this);
    }
}
