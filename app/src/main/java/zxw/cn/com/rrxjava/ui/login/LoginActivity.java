package zxw.cn.com.rrxjava.ui.login;

import android.support.design.widget.TextInputLayout;
import android.view.View;
import android.widget.Button;

import java.util.HashMap;
import java.util.Map;

import devin.cn.com.rxjavaretrofit.mvp.BaseActivity;
import devin.cn.com.rxjavaretrofit.mvp.BasePresenter;
import zxw.cn.com.rrxjava.R;
import zxw.cn.com.rrxjava.utils.ToolBar;
import zxw.cn.com.rrxjava.utils.ToolHttpUitl;
import zxw.cn.com.rrxjava.utils.ToolString;

/**
 * Created by zengxiaowen on 2018/2/1.
 */

public class LoginActivity extends BaseActivity<LoginPresenter> {

    private TextInputLayout til_account;
    private TextInputLayout til_password;

    @Override
    public void refresh() {

    }

    @Override
    protected LoginPresenter initPresent() {
        return new LoginPresenter(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_login;
    }

    @Override
    protected void findView() {
//        ToolHttpUitl.getSessionConf(this);
        ToolBar.setToolBar(this,"登录");
        til_account = findViewById(R.id.til_account);
        til_password = findViewById(R.id.til_password);
        findViewById(R.id.sign_in_button).setOnClickListener(view -> {
            String account = til_account.getEditText().getText().toString();
            String password = til_password.getEditText().getText().toString();

            til_account.setErrorEnabled(false);
            til_password.setErrorEnabled(false);

            //验证用户名和密码
            if(validateAccount(account)&&validatePassword(password)){
                Map<String,Object> map = new HashMap<>();
                map.put("login", account);
                map.put("encryptedPassword", ToolString.YLPWDEncrypt(password));
//                map.put("encryptedPassword", password);
                basePresenter.initData(map);
            }

        });
    }
    /**
     * 显示错误提示，并获取焦点
     * @param textInputLayout
     * @param error
     */
    private void showError(TextInputLayout textInputLayout, String error){
        textInputLayout.setError(error);
        textInputLayout.getEditText().setFocusable(true);
        textInputLayout.getEditText().setFocusableInTouchMode(true);
        textInputLayout.getEditText().requestFocus();
    }

    /**
     * 验证用户名
     * @param account
     * @return
     */
    private boolean validateAccount(String account){
        if(ToolString.isEmpty(account)){
            showError(til_account,"用户名不能为空");
            return false;
        }
        return true;
    }

    /**
     * 验证密码
     * @param password
     * @return
     */
    private boolean validatePassword(String password) {
        if (ToolString.isEmpty(password)) {
            showError(til_password,"密码不能为空");
            return false;
        }

        if (password.length() < 6 || password.length() > 18) {
            showError(til_password,"密码长度为6-18位");
            return false;
        }

        return true;
    }
}
