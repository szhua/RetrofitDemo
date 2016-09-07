package szhua.demo.retrofitdemo;

import szhua.demo.retrofitdemo.Model.User;

/**
 * Created by szhua on 2016/6/25.
 */
public class AppHolder {

    private String token ;
    private boolean hasMsg ;
    private User user ;
    private boolean isfirst;

    public boolean isfirst() {
        return isfirst;
    }

    public void setIsfirst(boolean isfirst) {
        this.isfirst = isfirst;
    }

    public static AppHolder getOurInstance() {
        return ourInstance;
    }

    public static void setOurInstance(AppHolder ourInstance) {
        AppHolder.ourInstance = ourInstance;
    }

    public boolean isHasMsg() {
        return hasMsg;
    }

    public void setHasMsg(boolean hasMsg) {
        this.hasMsg = hasMsg;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public User getUser() {
        return user;
    }

    private static AppHolder ourInstance = new AppHolder();

    public static AppHolder getInstance() {
        return ourInstance;
    }

    private AppHolder() {
    }
}
