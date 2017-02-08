package com.lp.pickerwheel;

import android.content.res.AssetManager;
import android.support.v7.app.AppCompatActivity;

import com.lp.pickerwheel.model.CityModel;
import com.lp.pickerwheel.model.DistrictModel;
import com.lp.pickerwheel.model.ProvinceModel;
import com.lp.pickerwheel.service.XmlparseHandler;

import org.xml.sax.SAXException;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

/**
 * Created by Lipeng on 2017/2/8.
 */

public class BaseActivity extends AppCompatActivity {

    /**
    *所有省
     */
    protected String[] mProvinceDatas;
    /**
    *key - 省 value - 市
     */
    protected Map<String, String[]> mCityDatasMap = new HashMap<String, String[]>();
    /*
    *key - 市 value - 区
    */
    protected Map<String, String[]> mCityDatasMap = new HashMap<String, String[]>();
    /*
    *key - 区 value - 邮编
     */
    protected Map<String, String[]> mDistrictDatasMap = new HashMap<String, String[]>();
    //当前省的名称
    protected String mCurrentProvinceName = "";
    //当前市的名称
    protected String mCurrentCityName = "";
    //当前区的名称
    protected String mCurrentDistrictName = "";
    //当前区的邮政编码
    protected String mCurrentZipCode = "";

    /*
    *解析省市区的XML文件数据
     */
    protected  void initProvinceDatas(){
        List<ProvinceModel> provinceList;
        AssetManager assetManager = getAssets();
        try {
            InputStream inputStream = assetManager.open("province_data.xml");
            //创建一个Xml解析的工厂对象
            SAXParserFactory saxParserFactory = SAXParserFactory.newInstance();
            //解析Xml
            SAXParser saxParser = saxParserFactory.newSAXParser();
            XmlparseHandler xmlparseHandler = new XmlparseHandler();
            saxParser.parse(inputStream, xmlparseHandler);
            inputStream.close();
            //获取解析出来的数据
            provinceList = xmlparseHandler.getDataList();
            //初始化默认选中的省、市、区
            if (provinceList != null && !provinceList.isEmpty()){
                mCurrentProvinceName = provinceList.get(0).getName();
                List<CityModel> cityList = provinceList.get(0).getCityList();
                if (cityList != null && !cityList.isEmpty()){
                    mCurrentCityName = cityList.get(0).getName();
                    List<DistrictModel> districtList = cityList.get(0).getDistrictList();
                    mCurrentDistrictName = districtList.get(0).getName();
                    mCurrentZipCode = districtList.get(0).getZipCode();
                }
            }
            mProvinceDatas = new String[provinceList.size()];

        } catch (Throwable e) {
            e.printStackTrace();
        } finally {
        }
    }

}
