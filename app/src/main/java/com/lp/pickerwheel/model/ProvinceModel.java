package com.lp.pickerwheel.model;

import java.util.List;

/**
 * Created by Lipeng on 2017/2/8.
 */

public class ProvinceModel {
    private String name;
    private List<CityModel> cityList;

    public provinceModel(){
        super();
    }

    public ProvinceModel(String name, List<CityModel> cityList){
        super();
        this.name = name;
        this.cityList = cityList;
    }

    public List<CityModel> getCityList() {
        return cityList;
    }

    public void setCityList(List<CityModel> cityList) {
        this.cityList = cityList;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "ProvinceModel [name=" + name + "cityList = " + cityList + "]";
    }
}
