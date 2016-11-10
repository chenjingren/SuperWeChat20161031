package cn.ucai.superwechat.ui;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.hyphenate.chat.EMClient;
import com.hyphenate.easeui.domain.User;
import com.hyphenate.easeui.utils.EaseUserUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.ucai.superwechat.I;
import cn.ucai.superwechat.R;
import cn.ucai.superwechat.SuperWeChatHelper;
import cn.ucai.superwechat.utils.L;
import cn.ucai.superwechat.utils.MFGT;

public class UserDetailActivity extends BaseActivity {

    public static final String TAG = UserDetailActivity.class.getName();

    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.profile_image)
    ImageView profileImage;
    @BindView(R.id.tv_userinfo_nick)
    TextView tvUserinfoNick;
    @BindView(R.id.tv_userinfo_name)
    TextView tvUserinfoName;

    User user;
    @BindView(R.id.btn_add_contact)
    Button btnAddContact;
    @BindView(R.id.btn_send_msg)
    Button btnSendMsg;
    @BindView(R.id.btn_send_video)
    Button btnSendVideo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_detail);
        ButterKnife.bind(this);
        user = (User) getIntent().getSerializableExtra(I.User.USER_NAME);
        L.e(TAG,"UserDetailActivity.user====="+user);
        if (user == null) {
            MFGT.finish(this);
        }

        ivBack.setVisibility(View.VISIBLE);
        tvTitle.setVisibility(View.VISIBLE);
        tvTitle.setText(R.string.userinfo_txt_profile);
        L.e(TAG,"00000000000000");
        EaseUserUtils.setAppUserPathAvatar(this,user.getAvatar(),profileImage);
        Log.e(TAG,"0000000000000011111111111111111");
        Log.e(TAG,"UserDetailActivity.user.getUserNick===="+user.getMUserNick());
        EaseUserUtils.setAppUserNick(user.getMUserNick(), tvUserinfoNick);
        L.e(TAG,"00000000000000222222222222222222222222");
        EaseUserUtils.setAppUserName("", user.getMUserName(), tvUserinfoName);

        isFriend();
    }

    private void isFriend() {
        if (SuperWeChatHelper.getInstance().getContactList().containsKey(user.getMUserName())) {
            btnSendMsg.setVisibility(View.VISIBLE);
            btnSendVideo.setVisibility(View.VISIBLE);
        }else {
            btnAddContact.setVisibility(View.VISIBLE);
        }
    }

    @OnClick({R.id.iv_back, R.id.btn_add_contact, R.id.btn_send_msg, R.id.btn_send_video})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                MFGT.finish(this);
                break;
            case R.id.btn_add_contact:
                MFGT.gotoApplyAddContact(this,user.getMUserName());
                break;
            case R.id.btn_send_msg:
                MFGT.gotoChat(this,user.getMUserName());
                //startActivity(new Intent(getActivity(), ChatActivity.class).putExtra("userId", username));
                break;
            case R.id.btn_send_video:
                if (!EMClient.getInstance().isConnected())
                    Toast.makeText(this, R.string.not_connect_to_server, Toast.LENGTH_SHORT).show();
                else {
                    startActivity(new Intent(this, VideoCallActivity.class).putExtra("username", user.getMUserName())
                            .putExtra("isComingCall", false));
                    // videoCallBtn.setEnabled(false);
                    //inputMenu.hideExtendMenuContainer();
                }
                break;
        }
    }
}
