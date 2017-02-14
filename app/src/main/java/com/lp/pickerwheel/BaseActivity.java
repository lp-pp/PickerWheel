package com.lp.pickerwheel;

import android.content.res.AssetManager;
import android.support.v7.app.AppCompatActivity;

import com.lp.pickerwheel.model.CityModel;
import com.lp.pickerwheel.model.DistrictModel;
import com.lp.pickerwheel.model.ProvinceModel;
import com.lp.pickerwheel.service.XmlparseHandler;

import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    protected Map<String, String[]> mDistrictDatasMap = new HashMap<String, String[]>();
    /*
    *key - 区 value - 邮编
     */
    protected Map<String, String[]> mZipcodeDatasMap = new HashMap<String, String[]>();
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
            for (int i = 0; i < provinceList.size(); i++) {
                // 遍历所有省的数据
                mProvinceDatas[i] = provinceList.get(i).getName();
                List<CityModel> cityList = provinceList.get(i).getCityList();
                String[] cityName = new String[cityList.size()];
                for (int j = 0; j < cityList.size(); j++) {
                    // 遍历省下面的所有市的数据
                    cityName[j] = cityList.get(j).getName();
                    List<DistrictModel> districtList = cityList.get(j).getDistrictList();
                    String[] districtNameArray = new String[districtList.size()];
                    DistrictModel[] districtArray = new DistrictModel[districtList.size()];
                    for (int k = 0; k < districtList.size(); k++) {
                        // 遍历市下面所有区/县的数据
                        DistrictModel districtModel = new DistrictModel(districtList.get(k).getName(), districtList.get(k).getZipCode());
                        // 区/县对于的邮编，保存到mZipcodeDatasMap
                        mDistrictDatasMap.put(districtList.get(k).getName(), districtList.get(k).getZipCode());
                        districtArray[k] = districtModel;
                        districtNameArray[k] = districtModel.getName();
                    }
                    // 市-区/县的数据，保存到mDistrictDatasMap
                    mDistrictDatasMap.put(cityName[j], districtNameArray);
                }
                // 省-市的数据，保存到mCitisDatasMap
                mCityDatasMap.put(mProvinceDatas[i], cityName);
            }

        } catch (Throwable e) {
            e.printStackTrace();
        } finally {
        }
    }

}
