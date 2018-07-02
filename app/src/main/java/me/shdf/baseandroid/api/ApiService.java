package me.shdf.baseandroid.api;

import java.util.List;

import io.reactivex.Observable;
import me.shdf.baseandroid.base.basebean.BaseResponse;
import me.shdf.baseandroid.bean.CityBean;
import me.shdf.baseandroid.bean.DouBean;
import me.shdf.baseandroid.bean.LoginBean;
import me.shdf.baseandroid.bean.TestBean;
import me.shdf.baseandroid.bean.TyjUserBean;
import me.shdf.baseandroid.bean.UpdateAppBean;
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
    @GET("/login/zcm")
    Observable<BaseResponse<UserBean>> getUserMessage();
    //todo 版本更新
    @GET("/login/version")
    Observable<BaseResponse<UpdateAppBean>> getAppVersion();

    //todo 用户登录   entry!login.action
    @FormUrlEncoded
    @POST("entry!login.action")
    Observable<LoginBean> loginUser(@Field("user.uname") String uname,
                                    @Field("user.upassword") String upassword
    );

    //todo 添加用户
    @FormUrlEncoded
    @POST("entry!addUser.action")
    Observable<TestBean> addUser(@Field("user.uname") String uname,
                                 @Field("user.upassword") String upassword
    );

    //todo 删除用户  entry!deleteUser.action
    @FormUrlEncoded
    @POST("entry!deleteUser.action")
    Observable<TestBean> deleteUser(@Field("user.uid") String uid);


    //todo 修改用户  entry!updateUser.action
    @FormUrlEncoded
    @POST("entry!updateUser.action")
    Observable<TestBean> updateUser(@Field("user.uid") String uid,
                                    @Field("user.upassword") String upassword);

    //todo 查询用户  entry!queryAll.action
    @POST("entry!queryAll.action")
    Observable<List<TyjUserBean>> queryUser();
    //todo   ---book---

    //todo 添加课程
    @FormUrlEncoded
    @POST("sub!addSub.action")
    Observable<TestBean> addSub(@Field("subject.sname") String sname,
                                 @Field("subject.uid") String uid);


    //todo 课程  entry!deleteUser.action
    @FormUrlEncoded
    @POST("sub!deleteSub.action")
    Observable<TestBean> deleteSub(@Field("subject.sid") String sid);


    //todo 修改课程  entry!updateUser.action
    @FormUrlEncoded
    @POST("sub!updateSub.action")
    Observable<TestBean> updateSub(@Field("subject.sname") String sname,
                                   @Field("subject.sid") String sid);

    //todo ---book---

    //todo 添加书籍
    @FormUrlEncoded
    @POST("book!addBook.action")
    Observable<TestBean> addBook(@Field("book.bname") String bname,
                                @Field("book.sid") String sid);

    //todo 书籍  entry!deleteUser.action
    @FormUrlEncoded
    @POST("book!deleteSub.action")
    Observable<TestBean> deleteBook(@Field("book.bid") String bid);


    //todo 修改书籍  entry!updateUser.action
    @FormUrlEncoded
    @POST("book!updateBook.action")
    Observable<TestBean> updateBook(@Field("book.bname") String bname,
                                    @Field("book.sid") String bid);

}
