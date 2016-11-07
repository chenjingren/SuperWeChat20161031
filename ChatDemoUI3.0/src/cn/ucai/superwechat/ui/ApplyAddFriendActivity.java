package cn.ucai.superwechat.ui;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.hyphenate.chat.EMClient;
import com.hyphenate.easeui.utils.EaseUserUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.ucai.superwechat.I;
import cn.ucai.superwechat.R;
import cn.ucai.superwechat.utils.MFGT;

public class ApplyAddFriendActivity extends Activity {

    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.tv_add_friend)
    TextView tvAddFriend;
    @BindView(R.id.btn_send)
    Button btnSend;
    @BindView(R.id.et_msg)
    EditText etMsg;

    private ProgressDialog progressDialog;

    String username;

    String msg;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_apply_add_friend);
        ButterKnife.bind(this);
        initView();
        username = getIntent().getStringExtra(I.User.NICK);
        if (username == null){
            MFGT.finish(this);
        }
    }

    private void initView() {
        ivBack.setVisibility(View.VISIBLE);
        tvAddFriend.setVisibility(View.VISIBLE);
        btnSend.setVisibility(View.VISIBLE);
        String currentUserNick = EaseUserUtils.getCurrentUserInfo().getMUserNick();
        msg = getString(R.string.addcontact_send_msg_prefix)+currentUserNick;
        etMsg.setText(msg);
    }

    @OnClick({R.id.iv_back, R.id.btn_send})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                MFGT.finish(this);
                break;
            case R.id.btn_send:
                sendMsg();
                break;
        }
    }

    private void sendMsg() {
        progressDialog = new ProgressDialog(this);
        String stri = getResources().getString(R.string.addcontact_adding);
        progressDialog.setMessage(stri);
        progressDialog.setCanceledOnTouchOutside(false);
        progressDialog.show();

        new Thread(new Runnable() {
            public void run() {
                try {
                    //demo use a hardcode reason here, you need let user to input if you like
                    String s = getResources().getString(R.string.Add_a_friend);
                    EMClient.getInstance().contactManager().addContact(username, msg+","+s);
                    runOnUiThread(new Runnable() {
                        public void run() {
                            progressDialog.dismiss();
                            String s1 = getResources().getString(R.string.send_successful);
                            Toast.makeText(getApplicationContext(), s1, Toast.LENGTH_LONG).show();
                            MFGT.finish(ApplyAddFriendActivity.this);
                        }
                    });
                } catch (final Exception e) {
                    runOnUiThread(new Runnable() {
                        public void run() {
                            progressDialog.dismiss();
                            String s2 = getResources().getString(R.string.Request_add_buddy_failure);
                            Toast.makeText(getApplicationContext(), s2 + e.getMessage(), Toast.LENGTH_LONG).show();
                            MFGT.finish(ApplyAddFriendActivity.this);
                        }
                    });
                }
            }
        }).start();
    }
}
