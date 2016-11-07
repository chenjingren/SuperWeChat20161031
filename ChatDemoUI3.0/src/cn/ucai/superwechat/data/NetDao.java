package cn.ucai.superwechat.data;

import android.content.Context;

import java.io.File;

import cn.ucai.superwechat.I;
import cn.ucai.superwechat.bean.Result;
import cn.ucai.superwechat.db.OkHttpUtils;
import cn.ucai.superwechat.utils.MD5;

/**
 * Created by ACherish on 2016/10/17.
 */
public class NetDao {

    public static void reqRegister(Context context,String userName,String userNick,
                                   String password,OkHttpUtils.OnCompleteListener<Result> listener){
        OkHttpUtils<Result> utils = new OkHttpUtils<>(context);
        utils.setRequestUrl(I.REQUEST_REGISTER)
                .addParam(I.User.USER_NAME,userName)
                .addParam(I.User.NICK,userNick)
                .addParam(I.User.PASSWORD, MD5.getMessageDigest(password))
                .targetClass(Result.class)
                .post()
                .execute(listener);
    }
    public static void reqUnRegister(Context context,String userName,OkHttpUtils.OnCompleteListener<Result> listener){
        OkHttpUtils<Result> utils = new OkHttpUtils<>(context);
        utils.setRequestUrl(I.REQUEST_UNREGISTER)
                .addParam(I.User.USER_NAME,userName)
                .targetClass(Result.class)
                .execute(listener);
    }

    public static void reqLogin(Context context,String userName,String password,
                                OkHttpUtils.OnCompleteListener<String> listener){
         OkHttpUtils<String> utils = new OkHttpUtils<>(context);
         utils.setRequestUrl(I.REQUEST_LOGIN)
                 .addParam(I.User.USER_NAME,userName)
                 .addParam(I.User.PASSWORD, MD5.getMessageDigest(password))
                 .targetClass(String.class)
                 .execute(listener);
    }

    public static void reqUpdateUserNick(Context context,String userName,String nick,
                                           OkHttpUtils.OnCompleteListener<String> listener){
        OkHttpUtils<String> utils = new OkHttpUtils<>(context);
        utils.setRequestUrl(I.REQUEST_UPDATE_USER_NICK)
                .addParam(I.User.USER_NAME,userName)
                .addParam(I.User.NICK,nick)
                .targetClass(String.class)
                .execute(listener);
    }

    public static void reqUpdateUserAvatar(Context context,String userName,File file,
                                         OkHttpUtils.OnCompleteListener<String> listener){
        OkHttpUtils<String> utils = new OkHttpUtils<>(context);
        utils.setRequestUrl(I.REQUEST_UPDATE_AVATAR)
                .addParam(I.NAME_OR_HXID,userName)
                .addParam(I.AVATAR_TYPE,I.AVATAR_TYPE_USER_PATH)
                .addFile2(file)
                .targetClass(String.class)
                .post()
                .execute(listener);
    }

    public static void reqFindUserByUsername(Context context,String userName,
                                OkHttpUtils.OnCompleteListener<String> listener){
        OkHttpUtils<String> utils = new OkHttpUtils<>(context);
        utils.setRequestUrl(I.REQUEST_FIND_USER)
                .addParam(I.User.USER_NAME,userName)
                .targetClass(String.class)
                .execute(listener);
    }
}
