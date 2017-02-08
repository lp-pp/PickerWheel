package com.lp.pickerwheel.model;


/**
 * Created by Lipeng on 2017/2/8.
 */

public class DistrictModel {
    private String name;
    private String zipCode;

    public DistrictModel(){
        super();
    }

    public DistrictModel(String name, String zipCode){
        super();
        this.name = name;
        this.zipCode = zipCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    @Override
    public String toString() {
        return "DistrictModel name[" + name + "zipCode = " + zipCode + "]";
    }
}

