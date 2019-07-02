package com.longdian.service;

import com.longdian.bean.OprInfoDto;
import com.longdian.bean.RunningConclusion;
import com.longdian.fragment.base.model.StationList;
import com.longdian.fragment.dataanalysis.model.CollectExtendData;
import com.longdian.fragment.weather.model.PieDataAll;
import com.longdian.fragment.weather.model.PredictDataAll;
import com.longdian.fragment.weather.model.WeatherData;
import com.longdian.fragment.weather.model.WeatherDataAll;

import java.util.List;
import java.util.Map;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface HoolaiService {

    @GET("login")
    Observable<HoolaiResponse<OprInfoDto>> login(@Query("loginId") String account, @Query("loginPwd") String pwd);

    @GET("user/changePwd")
    Observable<HoolaiResponse<String>> changePwd(@Query("account") String account, @Query("oldPwd") String oldPwd, @Query("newPwd") String newPwd);

    @GET("basic/station/actualdataAjax")
    Observable<HoolaiResponse<List<CollectExtendData>>> realtimeData();

    @GET("basic/station/historydataAjax")
    Observable<HoolaiResponse<List<CollectExtendData>>> historydata(@Query("tTime") String eTime);

    @GET("infoOverview")
    Observable<HoolaiResponse<PieDataAll>> pieChart(@Query("beginTime") String beginTime, @Query("endTime") String endTime);

    @GET("report/day/list")
    Observable<HoolaiResponse<List<Map<String, String>>>> reportDay(@Query("searchDateTime") String searchDateTime);

    @GET("report/month/list")
    Observable<HoolaiResponse<List<Map<String, String>>>> reportMonth(@Query("searchDateTime") String searchDateTime, @Query("stationName") String stationName);

    @GET("report/year/list")
    Observable<HoolaiResponse<List<Map<String, String>>>> reportYear(@Query("year") String year, @Query("stationName") String stationName);

    @GET("report/econ/list")
    Observable<HoolaiResponse<List<Map<String, String>>>> reportEconomics(@Query("beginTime") String beginTime, @Query("endTime") String endTime, @Query("stationName") String stationName);

    @POST("basic/station/list")
    Observable<HoolaiResponse<StationList>> stationList();

    @POST("weather/situation/list")
    Observable<HoolaiResponse<List<WeatherData>>> weatherList(@Query("beginTime") String beginTime, @Query("endTime") String endTime);

    @POST("weather/weatherDetail")
    Observable<HoolaiResponse<List<WeatherData>>> weatherDetail();

    @POST("weather/pandect")
    Observable<HoolaiResponse<WeatherDataAll>> weatherIndex();

    @POST("basic/analysis/water/list")
    Observable<HoolaiResponse<List<Map<String, String>>>> analysisWater(
            @Query("beginTime") String beginTime,
            @Query("endTime") String endTime,
            @Query("stationName") String stationName
    );

    @POST("basic/analysis/electricity/list")
    Observable<HoolaiResponse<List<Map<String, String>>>> analysisElectricity(
            @Query("beginTime") String beginTime,
            @Query("endTime") String endTime,
            @Query("stationName") String stationName
    );

    @POST("basic/analysis/heat/list")
    Observable<HoolaiResponse<List<Map<String, String>>>> analysisHeat(
            @Query("beginTime") String beginTime,
            @Query("endTime") String endTime,
            @Query("stationName") String stationName
    );

    @GET("predict/history/history_data2")
    Observable<HoolaiResponse<Map<String, String>>> predictData(@Query("beginTime") String beginTime);

    @GET("predict/history/history_data3")
    Observable<HoolaiResponse<PredictDataAll>> predictData3(@Query("beginTime") String beginTime);

    @GET("tecolcurve/runningConclusion")
    Observable<HoolaiResponse<RunningConclusion>> runningConclusion();
}
