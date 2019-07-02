package com.longdian.fragment.weather.model;

import java.util.List;

public class WeatherDataAll {

    private List<WeatherData> data7;
    private WeatherData weatherData;
    private WeatherDataStal weatherDataStal;
    private String dt;
    private String xiangsu;
    private String wendu;
    private String winLevl;
    private String shikuang;
    private String winName;

    public List<WeatherData> getData7() {
        return data7;
    }

    public void setData7(List<WeatherData> data7) {
        this.data7 = data7;
    }

    public WeatherData getWeatherData() {
        return weatherData;
    }

    public void setWeatherData(WeatherData weatherData) {
        this.weatherData = weatherData;
    }

    public WeatherDataStal getWeatherDataStal() {
        return weatherDataStal;
    }

    public void setWeatherDataStal(WeatherDataStal weatherDataStal) {
        this.weatherDataStal = weatherDataStal;
    }

    public String getDt() {
        return dt;
    }

    public void setDt(String dt) {
        this.dt = dt;
    }

    public String getXiangsu() {
        return xiangsu;
    }

    public void setXiangsu(String xiangsu) {
        this.xiangsu = xiangsu;
    }

    public String getWendu() {
        return wendu;
    }

    public void setWendu(String wendu) {
        this.wendu = wendu;
    }

    public String getWinLevl() {
        return winLevl;
    }

    public void setWinLevl(String winLevl) {
        this.winLevl = winLevl;
    }

    public String getShikuang() {
        return shikuang;
    }

    public void setShikuang(String shikuang) {
        this.shikuang = shikuang;
    }

    public String getWinName() {
        return winName;
    }

    public void setWinName(String winName) {
        this.winName = winName;
    }
}
