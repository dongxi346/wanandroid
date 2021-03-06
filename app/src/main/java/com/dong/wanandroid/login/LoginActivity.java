package com.dong.wanandroid.login;

import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.dong.wanandroid.R;
import com.dong.wanandroid.base.BaseActivity;
import com.dong.wanandroid.data.db.DBHelper;
import com.dong.wanandroid.data.event_bus_model.Event;
import com.dong.wanandroid.data.event_bus_model.EventConfig;
import com.dong.wanandroid.data.user.UserModel;
import com.dong.wanandroid.register.RegisterActivity;
import com.dong.wanandroid.util.third_lib.EventBusUtil;

import butterknife.BindView;
import butterknife.OnClick;

public class LoginActivity extends BaseActivity implements ILoginView {
    private static final String TAG = "LoginActivity";
    @BindView(R.id.et_username)
    EditText etUsername;
    @BindView(R.id.et_password)
    EditText etPassword;
    @BindView(R.id.register_btn)
    Button btnRegister;
    @BindView(R.id.login_btn)
    Button btnLogin;
    @BindView(R.id.loading_view)
    ProgressBar loadingView;

    private IPresenter iPresenter;
    private String use;
    private String pwdstr;



    @OnClick({R.id.login_btn,R.id.register_btn})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.register_btn:
                startActivity(new Intent(this, RegisterActivity.class));
                break;
            case R.id.login_btn:
                use = etUsername.getText().toString().trim();
                pwdstr = etPassword.getText().toString().trim();
                //判断输入内容不能为空
                if (!TextUtils.isEmpty(use)&&!TextUtils.isEmpty(pwdstr)){
                    //调用逻辑层的登录
                    iPresenter.login(use,pwdstr);
                }else{
                    Toast.makeText(this,"还有信息未填写哟~",Toast.LENGTH_SHORT).show();
                }
                break;
        }

    }

    @Override
    public void showLoadingView() {
        loadingView.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoadingView() {
        loadingView.setVisibility(View.GONE);
    }

    @Override
    public void loginResult(int resultCode, String msg, UserModel userModel) {
        if (resultCode == 0) {
            Toast.makeText(this, "欢迎你"+userModel.getUsername(), Toast.LENGTH_SHORT).show();
            // 存储到数据库中
            DBHelper.setUserToDb(userModel);
            EventBusUtil.sendStickyEvent(new Event<UserModel>(EventConfig.EVENT_LOGIN,userModel));
            finish();
        }else {
            Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public int intiLayout() {
        return R.layout.activity_login;
    }

    @Override
    public void initView() {

    }

    @Override
    public void initData() {
        iPresenter = new IpresenterCompl(this,this);

    }
}
