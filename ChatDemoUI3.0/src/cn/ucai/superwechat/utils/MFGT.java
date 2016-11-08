package cn.ucai.superwechat.utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;


import com.hyphenate.easeui.domain.User;

import cn.ucai.superwechat.I;
import cn.ucai.superwechat.R;
import cn.ucai.superwechat.ui.AddContactActivity;
import cn.ucai.superwechat.ui.ApplyAddFriendActivity;
import cn.ucai.superwechat.ui.ChatActivity;
import cn.ucai.superwechat.ui.MainActivity;
import cn.ucai.superwechat.ui.SettingsActivity;
import cn.ucai.superwechat.ui.UserDetailActivity;
import cn.ucai.superwechat.ui.UserProfileActivity;


public class MFGT {
    public static void finish(Activity activity){
        activity.finish();
        activity.overridePendingTransition(R.anim.push_right_in,R.anim.push_right_out);
    }
    public static void gotoMainActivity(Activity context){
        startActivity(context, MainActivity.class);
    }
    public static void startActivity(Activity context,Class<?> cls){
        Intent intent = new Intent();
        intent.setClass(context,cls);
        startActivity(context,intent);
        /*context.startActivity(intent);
        context.overridePendingTransition(R.anim.push_left_in,R.anim.push_left_out);*/
    }

    public static void startActivity(Context context,Intent intent){
        context.startActivity(intent);
        ((Activity)context).overridePendingTransition(R.anim.push_left_in,R.anim.push_left_out);
    }

    public static void startActivityForResult(Activity context,Intent intent,int requestCode){
        context.startActivityForResult(intent,requestCode);
        context.overridePendingTransition(R.anim.push_left_in,R.anim.push_left_out);
    }

    public static void gotoSettings(Activity activity) {
        startActivity(activity, SettingsActivity.class);
    }

    public static void gotoUserProfile(Activity activity) {
        startActivity(activity, UserProfileActivity.class);
    }

    public static void gotoAddFriends(Activity activity) {
        startActivity(activity, AddContactActivity.class);
    }

    public static void gotoUserDetail(Activity activity, User user) {
        Intent intent = new Intent();
        intent.setClass(activity,UserDetailActivity.class);
        intent.putExtra(I.User.USER_NAME,user);
        startActivity(activity,intent);
    }

    public static void gotoApplyAddContact(Activity activity, String username) {
        Intent intent = new Intent();
        intent.setClass(activity,ApplyAddFriendActivity.class);
        intent.putExtra(I.User.NICK,username);
        startActivity(activity,intent);
    }

    public static void gotoChat(Activity activity, String userId) {
        Intent intent = new Intent();
        intent.setClass(activity,ChatActivity.class);
        intent.putExtra("userId",userId);
        startActivity(activity,intent);
    }
}
