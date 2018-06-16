package me.shdf.baseandroid.api;

import io.reactivex.Observable;
import me.shdf.baseandroid.base.basebean.BaseResponse;
import me.shdf.baseandroid.bean.CityBean;
import me.shdf.baseandroid.bean.DouBean;
import me.shdf.baseandroid.bean.UserBean;
import me.shdf.baseandroid.bean.WeatherBean;
import me.shdf.baseandroid.bean.ZhihuBean;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by zcm on 2016/3/31.
 * qq:656025633
 * 存放接口
 * 1.http://www.weather.com.cn/data/sk/101010100.html
 */
public interface ApiService {
    /* @GET("/data/sk/101010100.html")
     Observable<WeatherBean> getWeathe();*/
    //https://api.douban.com/v2/movie/top250
    @GET("/data/sk/101010100.html")
    Call<WeatherBean> getWeathe();

    @GET("api/4/news/latest")
    Observable<ZhihuBean> getLatestNews();

    @Headers("Cache-Control: public, max-age=640000, s-maxage=640000 , max-stale=2419200")
    @GET("v2/movie/top250")
    Observable<DouBean> getDouNews(@Query("start") int start, @Query("count") int count);

    @FormUrlEncoded
    @POST("EducationController/selectEducation")
    Observable<CityBean> getCity(
            @Field("educationType") int educationType);
    @GET("/login/zcma")
    Observable<BaseResponse<UserBean>> getUserMessage();

}
