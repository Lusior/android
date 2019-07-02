package com.longdian.bean;

import java.util.List;

public class RunningConclusion {

    private TecolCurveData tecolCurveData;
    private List<TecolCurveCulData> tecolCurveCulData;
    private Float curTem;

    public TecolCurveData getTecolCurveData() {
        return tecolCurveData;
    }

    public void setTecolCurveData(TecolCurveData tecolCurveData) {
        this.tecolCurveData = tecolCurveData;
    }

    public List<TecolCurveCulData> getTecolCurveCulData() {
        return tecolCurveCulData;
    }

    public void setTecolCurveCulData(List<TecolCurveCulData> tecolCurveCulData) {
        this.tecolCurveCulData = tecolCurveCulData;
    }

    public Float getCurTem() {
        return curTem;
    }

    public void setCurTem(Float curTem) {
        this.curTem = curTem;
    }
}
