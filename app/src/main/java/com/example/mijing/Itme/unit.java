package com.example.mijing.Itme;

/**
 * Created by 程延宏 on 2017/4/24.
 */

public class unit {
    public unit(String unit, int unit_iv) {
        this.unit = unit;
        this.unit_iv = unit_iv;
    }

    public String getUnit() {
        return unit;
    }

    public int getUnit_iv() {
        return unit_iv;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public void setUnit_iv(int unit_iv) {
        this.unit_iv = unit_iv;
    }

    private String unit;
    private int unit_iv;

}
