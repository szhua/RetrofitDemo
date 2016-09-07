package szhua.demo.retrofitdemo.Service;


import java.io.File;
import java.util.Map;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Part;
import retrofit2.http.PartMap;
import retrofit2.http.Query;
import rx.Observable;
import szhua.demo.retrofitdemo.Model.PhoneResult;

/**
 * Created by szhua on 2016/6/24.
 * todo stepOne 创建访问的接口；
 * todo FormUrlEncoded
 */
public interface DemoService {

    /**
     * @param apikey
     * @param phone
     * @return
     * @Header用来添加Header
     * @Query用来添加查询关键字
     */
    @GET("/apistore/mobilenumber/mobilenumber")
    Call<PhoneResult> getResult(@Header("apikey") String apikey, @Query("phone") String phone);

    /**
     * 获得多说的一个评论详情；
     *
     * @param short_name
     * @param threads
     * @return
     */
    @GET("/threads/counts.json")
    Call<Object> getObject(@Query("short_name") String short_name, @Query("threads") String threads);


    /**
     * @param keytype
     * @return
     */
    @FormUrlEncoded
    @POST("/posts/create.json")
    Call<Object> getPostObject(@Field("keytype") String keytype);

    /**
     * 以map的形式进行传参
     *
     * @param map
     * @return
     */
    @FormUrlEncoded
    @POST("ad_list")
    Call<Object> getPostMap(@FieldMap Map<String, String> map);



    @FormUrlEncoded
    @POST("client_login")
    Call<Object> login(@FieldMap Map<String, String> map);


    /**
     *  params.put("token", AppHolder.getInstance().getUser().getToken());
     params.put("keytype", 1);
     params.put("keyid", 0);
     params.put("orderby", 0);
     params.put("content", "vc");
     params.put("duration",0);
     params.put("goods_id", 0);
     //pics.put("temp_file", filePath);// 临时需要上传的文件控件名称对应表单type="file"中的name值,相关文件请先在客户端压缩再上传（压缩尺寸宽度固定640）
     try {
     params.put("temp_file",inputStream);
     * @param file
     * @return
     */

    @Multipart
    @POST("file_upload")
    Call<Object>upLoadImag(@Part("temp_file") MultipartBody.Part file, @Part Map<String,String> map);

    @Multipart
    @POST("file_upload")
    Call<Object>upLoadImag(@PartMap Map<String, RequestBody> params);


    @Multipart
    @PUT("file_upload")
    Call<Object> postFileLiveDynamic(
            @Part MultipartBody.Part file,
            @Part("token") String token,
            @Part("keytype") String keytype,
            @Part("keyid") String keyid,
            @Part("orderby") String orderby,
            @Part("content") String content,
            @Part("duration") String duration,
            @Part("goods_id") String goods_id);


    @FormUrlEncoded
    @POST("address_list")
    Call<Object>getAddressList(@FieldMap Map<String,String> token);



}
