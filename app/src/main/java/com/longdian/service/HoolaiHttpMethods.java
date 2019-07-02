package com.longdian.service;

import android.content.Context;

import com.longdian.DynamicAppUtil;
import com.longdian.bean.OprInfo;
import com.longdian.bean.OprInfoDto;
import com.longdian.bean.RunningConclusion;
import com.longdian.fragment.base.model.StationList;
import com.longdian.fragment.dataanalysis.model.CollectExtendData;
import com.longdian.fragment.weather.model.PieDataAll;
import com.longdian.fragment.weather.model.PredictDataAll;
import com.longdian.fragment.weather.model.WeatherData;
import com.longdian.fragment.weather.model.WeatherDataAll;
import com.longdian.service.base.ObserverOnNextAndErrorListener;
import com.longdian.service.base.ObserverOnNextListener;
import com.longdian.service.base.ProgressObserver;
import com.longdian.service.interceptor.HeaderInterceptor;
import com.longdian.service.interceptor.LoggingInterceptor;

import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Administrator on 2017/3/27.
 * 网络请求方法封装
 */

public class HoolaiHttpMethods {

//    private static final String BASE_URL = "http://192.168.1.8:8080/energy/";
//    private static final String BASE_URL = "http://10.1.4.77:8080/energy/";
    private static final String BASE_URL = "http://47.93.184.40:8088/energy/";
    //    private static final String BASE_URL = "http://47.93.184.40:" + DynamicAppUtil.getUrlPort() + "/energy/";
    private static final int DEFAULT_TIMEOUT = 5;

    private static final HoolaiHttpMethods mInstance = new HoolaiHttpMethods();//单例
    private HoolaiService service;

    //构造方法私有
    private HoolaiHttpMethods() {
        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(new HeaderInterceptor())
                .addInterceptor(new LoggingInterceptor())
                .connectTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS)
                .readTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS)
                .build();
        Retrofit.Builder builder = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(client)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create());
        service = builder.build().create(HoolaiService.class);
    }

    //获取单例
    public static HoolaiHttpMethods getInstance() {
        return mInstance;
    }

    /**
     * 业务方法
     */
    public void login(Context context, String account, String pwd, ObserverOnNextAndErrorListener<OprInfoDto> observerOnNextAndErrorListener) {
        toObserverHoolai(context, service.login(account, pwd), observerOnNextAndErrorListener);
    }

    public void changePwd(Context context, String account, String oldPwd, String pwd, ObserverOnNextAndErrorListener<String> observerOnNextAndErrorListener) {
        toObserverHoolai(context, service.changePwd(account, oldPwd, pwd), observerOnNextAndErrorListener);
    }

    public void pieChart(Context context, String start, String end, ObserverOnNextAndErrorListener<PieDataAll> observerOnNextAndErrorListener) {
        toObserverHoolai(context, service.pieChart(start, end), observerOnNextAndErrorListener);
    }

    public void realtimeData(Context context, ObserverOnNextAndErrorListener<List<CollectExtendData>> observerOnNextAndErrorListener) {
        toObserverHoolai(context, service.realtimeData(), observerOnNextAndErrorListener);
    }

    public void historyData(Context context, String eTime, ObserverOnNextAndErrorListener<List<CollectExtendData>> observerOnNextAndErrorListener) {
        toObserverHoolai(context, service.historydata(eTime + " 00:00"), observerOnNextAndErrorListener);
    }

    public void reportDay(Context context, String date, ObserverOnNextAndErrorListener<List<Map<String, String>>> observerOnNextAndErrorListener) {
        toObserverHoolai(context, service.reportDay(date), observerOnNextAndErrorListener);
    }

    public void reportMonth(Context context, String date, String name, ObserverOnNextAndErrorListener<List<Map<String, String>>> observerOnNextAndErrorListener) {
        toObserverHoolai(context, service.reportMonth(date, name), observerOnNextAndErrorListener);
    }

    public void reportYear(Context context, String year, String name, ObserverOnNextAndErrorListener<List<Map<String, String>>> observerOnNextAndErrorListener) {
        toObserverHoolai(context, service.reportYear(year, name), observerOnNextAndErrorListener);
    }

    public void reportEconomics(Context context, String start, String end, String name, ObserverOnNextAndErrorListener<List<Map<String, String>>> observerOnNextAndErrorListener) {
        toObserverHoolai(context, service.reportEconomics(start, end, name), observerOnNextAndErrorListener);
    }

    public void stationList(Context context, ObserverOnNextAndErrorListener<StationList> observerOnNextAndErrorListener) {
        toObserverHoolai(context, service.stationList(), observerOnNextAndErrorListener);
    }

    public void weatherList(Context context, String start, String end, ObserverOnNextAndErrorListener<List<WeatherData>> observerOnNextAndErrorListener) {
        toObserverHoolai(context, service.weatherList(start, end), observerOnNextAndErrorListener);
    }

    public void weatherDetail(Context context, ObserverOnNextAndErrorListener<List<WeatherData>> observerOnNextAndErrorListener) {
        toObserverHoolai(context, service.weatherDetail(), observerOnNextAndErrorListener);
    }

    public void weatherIndex(Context context, ObserverOnNextAndErrorListener<WeatherDataAll> observerOnNextAndErrorListener) {
        Observer<WeatherDataAll> observer = new ProgressObserver<>(context, observerOnNextAndErrorListener, false);
        toObserverHoolai(service.weatherIndex(), observer);
    }

    public void analysisWater(Context context, String start, String end, String name, ObserverOnNextAndErrorListener<List<Map<String, String>>> observerOnNextAndErrorListener) {
        toObserverHoolai(context, service.analysisWater(start, end, name), observerOnNextAndErrorListener);
    }

    public void analysisElectricity(Context context, String start, String end, String name, ObserverOnNextAndErrorListener<List<Map<String, String>>> observerOnNextAndErrorListener) {
        toObserverHoolai(context, service.analysisElectricity(start, end, name), observerOnNextAndErrorListener);
    }

    public void analysisHeat(Context context, String start, String end, String name, ObserverOnNextAndErrorListener<List<Map<String, String>>> observerOnNextAndErrorListener) {
        toObserverHoolai(context, service.analysisHeat(start, end, name), observerOnNextAndErrorListener);
    }

    public void predictData(Context context, String start, ObserverOnNextAndErrorListener<Map<String, String>> observerOnNextAndErrorListener) {
        toObserverHoolai(context, service.predictData(start), observerOnNextAndErrorListener);
    }

    public void predictData3(Context context, String start, ObserverOnNextAndErrorListener<PredictDataAll> observerOnNextAndErrorListener) {
        toObserverHoolai(context, service.predictData3(start), observerOnNextAndErrorListener);
    }

    public void runningConclusion(Context context, ObserverOnNextAndErrorListener<RunningConclusion> observerOnNextAndErrorListener) {
        toObserverHoolai(context, service.runningConclusion(), observerOnNextAndErrorListener);
    }

    /**
     * 下面两个是封装方法
     */
    private <T> void toObserver(Context context, Observable<T> observable, ObserverOnNextListener<T> observerOnNextListener) {
        toObserver(observable, new ProgressObserver<T>(context, observerOnNextListener));
    }

    private <T> void toObserver(Observable<T> observable, Observer<T> observer) {
        observable
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }

    //只关心onNext方法
    private <T> void toObserverHoolai(Context context, Observable<HoolaiResponse<T>> observable, ObserverOnNextListener<T> observerOnNextListener) {
        Observer<T> observer = new ProgressObserver<T>(context, observerOnNextListener);
        toObserverHoolai(observable, observer);
    }

    //只关心onNext和onError方法
    private <T> void toObserverHoolai(Context context, Observable<HoolaiResponse<T>> observable, ObserverOnNextAndErrorListener<T> observerOnNextAndErrorListener) {
        Observer<T> observer = new ProgressObserver<T>(context, observerOnNextAndErrorListener);
        toObserverHoolai(observable, observer);
    }

    //去掉最外层包装
    private <T> void toObserverHoolai(Observable<HoolaiResponse<T>> observable, Observer<T> observer) {
        observable
                .map(new HttpResultFunc<T>())
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }

    private static class HttpResultFunc<T> implements Function<HoolaiResponse<T>, T> {

        @Override
        public T apply(HoolaiResponse<T> tHoolaiResponse) throws Exception {
            if (!tHoolaiResponse.isSuccess()) {
                throw new HoolaiException(tHoolaiResponse.getCode(), new Throwable(tHoolaiResponse.getDesc()));
            }
            return tHoolaiResponse.getValue();
        }
    }
}
