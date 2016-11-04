package cn.ucai.superwechat.utils;

import android.app.Activity;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by ACherish on 2016/11/4.
 */
public class CommonActivityUtils {
    List<Activity> mList = new LinkedList<>();
    private static CommonActivityUtils instance = new CommonActivityUtils();
    private CommonActivityUtils(){}
    public static CommonActivityUtils getInstance(){
        return instance;
    }

    public void addActivity(Activity activity){
        mList.add(activity);
    }
    public void delActivity(Activity activity){
        mList.remove(activity);
    }

    public void eixt() {
        for (Activity activity:mList){
            activity.finish();
        }
    }
}
