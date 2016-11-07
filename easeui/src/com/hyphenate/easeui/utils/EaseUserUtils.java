package com.hyphenate.easeui.utils;

import android.content.Context;
import android.widget.ImageView;
import android.widget.TextView;
import com.hyphenate.chat.EMClient;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.hyphenate.easeui.R;
import com.hyphenate.easeui.controller.EaseUI;
import com.hyphenate.easeui.controller.EaseUI.EaseUserProfileProvider;
import com.hyphenate.easeui.domain.EaseUser;
import com.hyphenate.easeui.domain.User;


public class EaseUserUtils {
    
    static EaseUserProfileProvider userProvider;
    
    static {
        userProvider = EaseUI.getInstance().getUserProfileProvider();
    }
    
    /**
     * get EaseUser according username
     * @param username
     * @return
     */
    public static EaseUser getUserInfo(String username){
        if(userProvider != null)
            return userProvider.getUser(username);
        
        return null;
    }

    public static User getCurrentUserInfo(){
        String username = EMClient.getInstance().getCurrentUser();
        if(userProvider != null)
            return userProvider.getAppUser(username);
        return null;
    }
    
    /**
     * set user avatar
     * @param username
     */
    public static void setUserAvatar(Context context, String username, ImageView imageView){
    	EaseUser user = getUserInfo(username);
        if(user != null && user.getAvatar() != null){
            try {
                int avatarResId = Integer.parseInt(user.getAvatar());
                Glide.with(context).load(avatarResId).into(imageView);
            } catch (Exception e) {
                //use default avatar
                Glide.with(context).load(user.getAvatar()).diskCacheStrategy(DiskCacheStrategy.ALL).placeholder(R.drawable.ease_default_avatar).into(imageView);
            }
        }else{
            Glide.with(context).load(R.drawable.ease_default_avatar).into(imageView);
        }
    }
    
    /**
     * set user's nickname
     */
    public static void setUserNick(String username,TextView textView){
        if(textView != null){
        	EaseUser user = getUserInfo(username);
        	if(user != null && user.getNick() != null){
        		textView.setText(user.getNick());
        	}else{
        		textView.setText(username);
        	}
        }
    }

    public static void setAppUserNick(String username,TextView textView){
        if(textView != null){
            User user = getAppUserInfo(username);
            if(user != null && user.getMUserNick() != null){
                textView.setText(user.getMUserNick());
            }else{
                textView.setText(username);
            }
        }
    }

    public static void setAppUserAvatar(Context context, String username, ImageView imageView){
        User user = getAppUserInfo(username);
        if(user != null && user.getAvatar() != null){
            try {
                int avatarResId = Integer.parseInt(user.getAvatar());
                Glide.with(context).load(avatarResId).into(imageView);
            } catch (Exception e) {
                //use default avatar
                Glide.with(context).load(user.getAvatar()).diskCacheStrategy(DiskCacheStrategy.ALL).placeholder(R.drawable.ease_default_avatar).into(imageView);
            }
        }else{
            Glide.with(context).load(R.drawable.ease_default_avatar).into(imageView);
        }
    }

    public static User getAppUserInfo(String username){
        if(userProvider != null)
            return userProvider.getAppUser(username);
        return null;
    }

    public static void setCurrentAppUserAvatar(Context activity, ImageView imageView) {
        String username = EMClient.getInstance().getCurrentUser();
        setAppUserAvatar(activity,username,imageView);
    }

    public static void setCurrentAppUserNick(TextView textView) {
        String username = EMClient.getInstance().getCurrentUser();
        setAppUserNick(username,textView);
    }

    public static void setCurrentAppUserName(TextView textView) {
        String username = EMClient.getInstance().getCurrentUser();
        setAppUserName("微信号：",username,textView);
    }

    public static void setCurrentAppUserNameWithNo(TextView textView) {
        String username = EMClient.getInstance().getCurrentUser();
        setAppUserName("",username,textView);
    }

    public static void setAppUserName(String suffix,String username, TextView textView) {
        textView.setText(suffix + username);
    }

    public static void setAppUserNick(String userNick,TextView textView){
        textView.setText(userNick);
    }
}
