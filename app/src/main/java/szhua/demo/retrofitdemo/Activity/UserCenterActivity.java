package szhua.demo.retrofitdemo.Activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;

import com.squareup.picasso.Picasso;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import de.hdodenhof.circleimageview.CircleImageView;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import rx.Observable;
import szhua.demo.retrofitdemo.AppHolder;
import szhua.demo.retrofitdemo.Constant;
import szhua.demo.retrofitdemo.R;
import szhua.demo.retrofitdemo.Service.DemoService;
import szhua.demo.retrofitdemo.util.ImageTools;
import szhua.demo.retrofitdemo.util.RetrofitUtil;
import szhua.demo.retrofitdemo.util.UiUtil;

public class UserCenterActivity extends AppCompatActivity {

    private CircleImageView imageView ;
    //此路径为缓存头像图片的路径；
    private String tempPicPath = Environment.getExternalStorageDirectory() + "/temp/";
    //头像图片的名字；
    private String tempPicName = "head_temp.jpg";
    private Bitmap bitmap;
    private static final String BASE_URL ="http://139.129.51.233/group1/mm_mall/index.php/Webservice/v100/" ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_center);

        imageView = (CircleImageView) findViewById(R.id.touxiang);
        if(AppHolder.getInstance().getUser()!=null){
            if(!TextUtils.isEmpty(AppHolder.getInstance().getUser().getAvatar())){
                Picasso.with(this).load(AppHolder.getInstance().getUser().getAvatar()).placeholder(R.drawable.touxiang).into(imageView);
            }
        }
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Retrofit retrofit = RetrofitUtil.getInstance(UserCenterActivity.this);
                DemoService demoService =retrofit.create(DemoService.class) ;
//                Call<Object> objectCall =  demoService.getAddressList(AppHolder.getInstance().getUser().getToken());
//                objectCall.enqueue(new  Callback<Object>() {
//                    @Override
//                    public void onResponse(Call<Object> call, Response<Object> response) {
//                        Log.i("MMMM",response.body().toString());
//                    }
//
//                    @Override
//                    public void onFailure(Call<Object> call, Throwable t) {
//
//                    }
//                });

                showSelectPic();
            }
        });

    }


    private void showSelectPic() {
        AlertDialog dialog = new AlertDialog.Builder(this).create();
        dialog.setTitle("提示");
        dialog.setButton(AlertDialog.BUTTON_NEGATIVE, "去图库", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent i = new Intent(
                        Intent.ACTION_PICK,
                        MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(i, Constant.FILECODE);
            }
        });
        dialog.setButton(AlertDialog.BUTTON_POSITIVE, "去拍照", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String state = Environment.getExternalStorageState();
                if (state.equals(Environment.MEDIA_MOUNTED)) {
                    Intent intentcamera = new Intent(
                            MediaStore.ACTION_IMAGE_CAPTURE);
                    File dir = new File(tempPicPath);
                    if (!dir.exists()) {
                        dir.mkdirs();
                    }
                    File imgFile = new File(dir, tempPicName);
                    if (!imgFile.exists()) {
                        try {
                            imgFile.createNewFile();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                    Uri imageUri = Uri.fromFile(imgFile);
                    intentcamera.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
                    startActivityForResult(intentcamera, Constant.CAMERACODE);
                } else {
                    UiUtil.showLongToast(getApplicationContext(), "无内存卡");
                }
            }
        });
        dialog.show();
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case Constant.CAMERACODE:
                    int degree = ImageTools.readPictureDegree(tempPicPath + tempPicName); //图片被旋转的角度
                    try {
                        //获得取得的图片对象；
                        bitmap = ImageTools.readBitmapAutoSize(tempPicPath + tempPicName, 500, 600);
                        //压缩图片。必须进行处理，要不然图片太大
                        bitmap = ImageTools.compressImage(bitmap);
                        bitmap = ImageTools.rotaingImageView(degree, bitmap); //把图片转到正的角度
                        // picturePath = ImageTools.savaPhotoToLocal(bitmap);
                        try {
                            imageView.setImageBitmap(bitmap);
                        } catch (Exception e) {
//                          MobclickAgent.reportError(this_context, e);
                        }
                    } catch (Exception e) {
//                        MobclickAgent.reportError(this_context, e);
                    }
                    //  updateHeaderDao.updateHeader(ImageTools.Bitmap2InputStream(bitmap, 100));
                  //  showProgressWithText(true, "正在上传头像……");
                    break;
                case Constant.FILECODE:
                    Uri selectedImage = data.getData();
                    String[] filePathColumn = {MediaStore.Images.Media.DATA};
                    Cursor cursor = getContentResolver().query(selectedImage,
                            filePathColumn, null, null, null);
                    cursor.moveToFirst();
                    int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
                    String picturePath = cursor.getString(columnIndex);
                    cursor.close();
                    bitmap = ImageTools.readBitmapAutoSize(picturePath, 500,
                            600);
                    bitmap = ImageTools.compressImage(bitmap);
                    picturePath = ImageTools.savaPhotoToLocal(bitmap);

                  //  Log.i("szhua", picturePath);
                    // 显示选择的图片
                    try {
                        imageView.setImageBitmap(bitmap);
                    } catch (Exception e) {
//                        MobclickAgent.reportError(this_context, e);
                    }
                    File dir = new File(tempPicPath);
                    if (!dir.exists()) {
                        dir.mkdirs();
                    }
                    File file =new File(picturePath) ;
                    //// TODO: 2016/6/25
//                    updateHeaderDao.update Header(new File(picturePath));
//                    showProgressWithText(true, "正在上传头像……");
                    uoLoad(file);
                    break;
            }
        }
    }


    public void uoLoad(File file){
        //以以下的方式进行表单传递参数；
Log.i("MMM","okoko");
        try {
            RequestBody token = RequestBody.create(MediaType.parse("text/plain"), AppHolder.getInstance().getUser().getToken());
            RequestBody keytype = RequestBody.create(MediaType.parse("text/plain"),"1");
            RequestBody keyid = RequestBody.create(MediaType.parse("text/plain"),"0");
            RequestBody orderby = RequestBody.create(MediaType.parse("text/plain"),"0");
            RequestBody content = RequestBody.create(MediaType.parse("text/plain"),"无");
            RequestBody duration = RequestBody.create(MediaType.parse("text/plain"),"0");
            RequestBody goods_id = RequestBody.create(MediaType.parse("text/plain"), "0");

            Map<String, RequestBody> map = new HashMap<>();
            map.put("token",token);
            map.put("keyid",keyid);
            map.put("keytype",keytype);
            map.put("orderby",orderby);
            map.put("content",content);
            map.put("duration",duration);
            map.put("goods_id",goods_id);


            if (file != null) {
                RequestBody fileBody = RequestBody.create(MediaType.parse("image/png"), file);
                map.put("temp_file\"; filename=\"image.png\"",fileBody);
            }

            Retrofit retrofit =RetrofitUtil.getInstance(UserCenterActivity.this);
            DemoService demoService =retrofit.create(DemoService.class) ;
            Call<Object> callObject= demoService.upLoadImag(map);
            callObject.enqueue(new  Callback<Object>() {
                @Override
                public void onResponse(Call<Object> call, Response<Object> response) {
                    Log.i("MMMMM","body"+response.body().toString()) ;
                }
                @Override
                public void onFailure(Call<Object> call, Throwable t) {
                    Log.i("MMMM","erro");
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void uploadFile(File file){

        // TODO: 2016/7/28

        Retrofit retrofit =RetrofitUtil.getInstance(UserCenterActivity.this);
        DemoService demoService =retrofit.create(DemoService.class) ;


  //  File file1 = new File(Environment.getExternalStorageDirectory(), "icon.png");
  RequestBody photoRequestBody = RequestBody.create(MediaType.parse("image/jpg"), file);
    MultipartBody.Part photo = MultipartBody.Part.createFormData("temp_file", "icon.jpg", photoRequestBody);

        Map<String,String> params =new HashMap<>() ;
        params.put("token",AppHolder.getInstance().getUser().getToken()) ;
        params.put("keytype","1");
        params.put("keyid", "0");
        params.put("orderby","0");
        params.put("content", "无");
        params.put("duration","0");
        params.put("goods_id", "0");
        RequestBody requestBody = RequestBody.create(MediaType.parse("multipart/form-data"),file);
        Call<Object> callObject =demoService.upLoadImag(photo,params);
        callObject.enqueue(new  Callback<Object>() {
            @Override
            public void onResponse(Call<Object> call, Response<Object> response) {
                Log.i("MMMMM","body"+response.body().toString()) ;
            }
            @Override
            public void onFailure(Call<Object> call, Throwable t) {
                Log.i("MMMM","erro");
            }
        });
    }


}
