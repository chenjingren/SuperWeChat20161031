package cn.ucai.superwechat.ui;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.hyphenate.easeui.domain.User;
import com.hyphenate.easeui.utils.EaseUserUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.ucai.superwechat.I;
import cn.ucai.superwechat.R;
import cn.ucai.superwechat.utils.MFGT;

public class UserDetailActivity extends BaseActivity {

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
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_detail);
        ButterKnife.bind(this);
        user = (User) getIntent().getSerializableExtra(I.User.USER_NAME);
        if (user == null){
            MFGT.finish(this);
        }

        ivBack.setVisibility(View.VISIBLE);
        tvTitle.setVisibility(View.VISIBLE);
        tvTitle.setText(R.string.userinfo_txt_profile);

        EaseUserUtils.setAppUserAvatar(this,user.getMUserName(),profileImage);
        EaseUserUtils.setAppUserNick(user.getMUserName(),tvUserinfoNick);
        EaseUserUtils.setAppUserName("",user.getMUserName(),tvUserinfoName);
    }

    @OnClick(R.id.iv_back)
    public void onClick() {
        MFGT.finish(this);
    }
}
