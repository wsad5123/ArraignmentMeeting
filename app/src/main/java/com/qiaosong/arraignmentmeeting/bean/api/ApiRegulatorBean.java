package com.qiaosong.arraignmentmeeting.bean.api;

import com.qiaosong.arraignmentmeeting.bean.FunitBean;
import com.qiaosong.arraignmentmeeting.bean.PrisonBean;

import java.util.List;

public class ApiRegulatorBean {
    private List<PrisonBean> prisonData;
    private List<FunitBean> unitData;


    public List<PrisonBean> getPrisonData() {
        return prisonData;
    }

    public void setPrisonData(List<PrisonBean> prisonData) {
        this.prisonData = prisonData;
    }

    public List<FunitBean> getUnitData() {
        return unitData;
    }

    public void setUnitData(List<FunitBean> unitData) {
        this.unitData = unitData;
    }

    /**
     * 判断数据是否为空
     *
     * @return
     */
    public boolean isEmpty() {
        if (prisonData != null && !prisonData.isEmpty())
            return false;
        if (unitData != null && !unitData.isEmpty())
            return false;
        return true;
    }
}
