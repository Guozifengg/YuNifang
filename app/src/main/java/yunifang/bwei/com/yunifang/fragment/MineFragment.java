package yunifang.bwei.com.yunifang.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.display.RoundedBitmapDisplayer;
import com.tencent.connect.UserInfo;
import com.tencent.connect.auth.QQToken;
import com.tencent.connect.common.Constants;
import com.tencent.tauth.IUiListener;
import com.tencent.tauth.Tencent;
import com.tencent.tauth.UiError;

import org.json.JSONException;
import org.json.JSONObject;

import yunifang.bwei.com.yunifang.R;

/**
 * 姓名：郭子锋
 * Created by Administrator on 2017/3/16.
 * 作用：
 */
public class MineFragment extends Fragment {

    private RecyclerView recyclerView2;
    private RecyclerView recyclerView;
    private TextView dengLu;
    private LinearLayout dengLuLl;
    private ImageView qqImg;
    private TextView qqName;

    private static final String TAG = "MainActivity";
    private static final String APP_ID = "1105602574";//官方获取的APPID
    private Tencent mTencent;
    private BaseUiListener mIUiListener;
    private UserInfo mUserInfo;
    private DisplayImageOptions options;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view1=View.inflate(getActivity(), R.layout.mine_layout,null);

        dengLu = (TextView) view1.findViewById(R.id.dengLu);
        dengLuLl = (LinearLayout) view1.findViewById(R.id.dengLuLl);
        qqImg = (ImageView) view1.findViewById(R.id.qqImg);
        qqName = (TextView) view1.findViewById(R.id.qqName);

        //传入参数APPID和全局Context上下文
        mTencent = Tencent.createInstance(APP_ID,getActivity());
        options = new DisplayImageOptions.Builder()
                .displayer(new RoundedBitmapDisplayer(180)).build();
        return view1;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        dengLu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mIUiListener = new BaseUiListener();
                //all表示获取所有权限 第一个参数写getActivity登录返回不成功
                mTencent.login(MineFragment.this,"all", mIUiListener);
            }
        });
    }

    /**
     * 自定义监听器实现IUiListener接口后，需要实现的3个方法
     * onComplete完成 onError错误 onCancel取消
     */
    private class BaseUiListener implements IUiListener {

        @Override
        public void onComplete(Object response) {
            Toast.makeText(getActivity(), "授权成功", Toast.LENGTH_SHORT).show();
            Log.e(TAG, "response:" + response);
            JSONObject obj = (JSONObject) response;
            try {
                String openID = obj.getString("openid");
                String accessToken = obj.getString("access_token");
                String expires = obj.getString("expires_in");
                mTencent.setOpenId(openID);
                mTencent.setAccessToken(accessToken,expires);
                QQToken qqToken = mTencent.getQQToken();
                mUserInfo = new UserInfo(getActivity(),qqToken);
                mUserInfo.getUserInfo(new IUiListener() {
                    @Override
                    public void onComplete(Object response) {
                        Log.e(TAG,"登录成功"+response.toString());
                        JSONObject object=(JSONObject) response;
                        String nickname = object.optString("nickname");
                        String figureurl_qq_2 = object.optString("figureurl_qq_2");

                        dengLuLl.setVisibility(View.GONE);
                        ImageLoader.getInstance().displayImage(figureurl_qq_2,qqImg,options);
                        qqName.setText(nickname);
                    }

                    @Override
                    public void onError(UiError uiError) {
                        Log.e(TAG,"登录失败"+uiError.toString());
                    }

                    @Override
                    public void onCancel() {
                        Log.e(TAG,"登录取消");

                    }
                });
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void onError(UiError uiError) {
            Toast.makeText(getActivity(), "授权失败", Toast.LENGTH_SHORT).show();

        }

        @Override
        public void onCancel() {
            Toast.makeText(getActivity(), "授权取消", Toast.LENGTH_SHORT).show();

        }

    }

    /**
     * 在调用Login的Activity或者Fragment中重写onActivityResult方法
     * @param requestCode
     * @param resultCode
     * @param data
     */
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == Constants.REQUEST_LOGIN){
            Tencent.onActivityResultData(requestCode,resultCode,data,mIUiListener);
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}
