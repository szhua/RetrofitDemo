package szhua.demo.retrofitdemo.Http;

import android.content.Context;

import java.util.List;

import okhttp3.Cookie;
import okhttp3.CookieJar;
import okhttp3.HttpUrl;

/**
 * Created by szhua on 2016/7/25.
 */
public class CookiesManager implements CookieJar{
   Context context ;
    public CookiesManager(Context context){
        this.context =context ;
        cookieStore = new PersistentCookieStore(context);
    }

    private  PersistentCookieStore cookieStore ;

    @Override
    public void saveFromResponse(HttpUrl url, List<Cookie> cookies) {
        if (cookies != null && cookies.size() > 0) {
            for (Cookie item : cookies) {
                cookieStore.add(url, item);
            }
        }
    }

    @Override
    public List<Cookie> loadForRequest(HttpUrl url) {
        List<Cookie> cookies = cookieStore.get(url);
        return cookies;
    }
}
