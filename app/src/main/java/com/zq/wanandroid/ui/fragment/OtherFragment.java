package com.zq.wanandroid.ui.fragment;

import android.support.design.widget.TextInputLayout;
import android.support.v7.widget.Toolbar;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.jakewharton.rxbinding2.view.RxView;
import com.jakewharton.rxbinding2.widget.RxTextView;
import com.mikepenz.iconics.IconicsDrawable;
import com.mikepenz.ionicons_typeface_library.Ionicons;
import com.zq.wanandroid.R;
import com.zq.wanandroid.common.Constants;
import com.zq.wanandroid.common.fonts.AppIcons;
import com.zq.wanandroid.common.util.ACache;
import com.zq.wanandroid.di.component.AppComponent;
import com.zq.wanandroid.di.component.DaggerLoginComponent;
import com.zq.wanandroid.di.module.LoginModule;
import com.zq.wanandroid.http.responsebean.LoginBean;
import com.zq.wanandroid.http.responsebean.User;
import com.zq.wanandroid.presenter.LoginPresenter;
import com.zq.wanandroid.presenter.contract.LoginContract;
import com.zq.wanandroid.ui.activity.BottomNavActivity;

import butterknife.BindView;
import io.reactivex.Observable;
import io.reactivex.functions.BiFunction;
import io.reactivex.functions.Consumer;
import jp.wasabeef.glide.transformations.CropCircleTransformation;

/**
 * Created by zhangqi on 2019/8/14
 */
public class OtherFragment extends BaseFragment<LoginPresenter> implements LoginContract.View {

    private static final String TAG = OtherFragment.class.getSimpleName();
    @BindView(R.id.tool_bar)
    Toolbar toolBar;
    @BindView(R.id.txt_mobi)
    EditText txtMobi;
    @BindView(R.id.view_mobi_wrapper)
    TextInputLayout viewMobiWrapper;
    @BindView(R.id.txt_password)
    EditText txtPassword;
    @BindView(R.id.view_password_wrapper)
    TextInputLayout viewPasswordWrapper;
    @BindView(R.id.btn_login)
    Button btnLogin;
    @BindView(R.id.btn_logout)
    Button btnLogout;
    @BindView(R.id.head_photo)
    ImageView headPhoto;
    @BindView(R.id.tv_username)
    TextView tvUsername;

    @Override
    protected void init() {
        initView();
    }

    private void initView() {
        toolBar.setNavigationIcon(new IconicsDrawable(activity)
                .icon(Ionicons.Icon.ion_ios_arrow_back)
                .sizeDp(16));
//        toolBar.setNavigationOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                finish();
//            }
//        });

        Observable<CharSequence> obMobi = RxTextView.textChanges(txtMobi).skipInitialValue();
        Observable<CharSequence> obPwd = RxTextView.textChanges(txtPassword).skipInitialValue();
        Observable.combineLatest(obMobi, obPwd, new BiFunction<CharSequence, CharSequence, Boolean>() {
            @Override
            public Boolean apply(CharSequence charSequence, CharSequence charSequence2) throws Exception {
                return isPhoneValid(charSequence.toString()) && isPasswordValid(charSequence2.toString());
            }
        }).subscribe(new Consumer<Boolean>() {
            @Override
            public void accept(Boolean aBoolean) throws Exception {
                if (aBoolean) {
                    RxView.enabled(btnLogin).accept(aBoolean);

                }
            }
        });

        RxView.clicks(btnLogin).subscribe(new Consumer<Object>() {
            @Override
            public void accept(Object o) throws Exception {
                presenter.login(txtMobi.getText().toString().trim(), txtPassword.getText().toString().trim());
            }
        });

        RxView.clicks(btnLogout).subscribe(new Consumer<Object>() {
            @Override
            public void accept(Object o) throws Exception {
                logout();
            }
        });

        initUser();
    }

    private boolean isPhoneValid(String phone) {
        return phone.length() == 11;
    }

    private boolean isPasswordValid(String password) {
        return password.length() >= 6;
    }

    @Override
    protected int setLayout() {
        return R.layout.fragment_login;
    }

    @Override
    protected void setAppcomponent(AppComponent appcomponent) {
        DaggerLoginComponent.builder()
                .appComponent(appcomponent)
                .loginModule(new LoginModule(this))
                .build()
                .inject(this);
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void showError(String errorMsg) {

    }

    @Override
    public void dismissLoading() {

    }

    @Override
    public void checkPhoneError() {
        viewMobiWrapper.setError("手机号格式不正确");
    }

    @Override
    public void checkPhoneSuccess() {
        viewMobiWrapper.setError("");
        viewMobiWrapper.setErrorEnabled(false);
    }

    @Override
    public void loginSuccess(LoginBean bean) {
        BottomNavActivity bottomNavActivity = (BottomNavActivity) this.activity;
        bottomNavActivity.selectBottomNavFirstItem();
    }

    private void initUserHeadPhoto(User user) {
        headPhoto.setEnabled(false);
        Glide.with(activity).load(user.getLogo_url()).bitmapTransform(new CropCircleTransformation(activity)).into(headPhoto);
        tvUsername.setText(user.getUsername());
    }

    private void initUser() {
        Object objUser = ACache.get(activity).getAsObject(Constants.USER);
        if (objUser != null) {
            User user = (User) objUser;
            initUserHeadPhoto(user);
        }
    }

    private void logout() {
        headPhoto.setEnabled(true);
        ACache aCache = ACache.get(activity);
        aCache.put(Constants.TOKEN, "");
        aCache.put(Constants.USER, "");

        headPhoto.setImageDrawable(new IconicsDrawable(activity, AppIcons.Icon.appicon_bottm_nav_setting));
        tvUsername.setText("未登陆");
        Toast.makeText(activity, "您已退出登陆", Toast.LENGTH_LONG).show();

    }

}
