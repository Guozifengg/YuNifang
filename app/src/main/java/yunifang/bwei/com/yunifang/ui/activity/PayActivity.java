package yunifang.bwei.com.yunifang.ui.activity;

import android.annotation.SuppressLint;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.alipay.sdk.app.PayTask;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Random;

import yunifang.bwei.com.yunifang.R;
import yunifang.bwei.com.yunifang.pay.PayResult;
import yunifang.bwei.com.yunifang.pay.SignUtils;

public class PayActivity extends AppCompatActivity {

    public static final String PARTNER = "2088901305856832";

    public static final String SELLER = "8@qdbaiu.com";

    public static final String RSA_PRIVATE = "MIICdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBAM" +
            "/KCxg/OIj6er2GEig0DOkHqBSzEPHGigMbTXP1k2nrxEHeE59xOOuy" +
            "ovQH/A1LgbuVKyOac3uAN4GXIBEoozRVzDhu5dobeNm48BPcpYSAfvN3K" +
            "/5GLacvJeENqsiBx8KufM/9inlHaDRQV7WhX1Oe2ckat1EkdHwxxQgc" +
            "36NhAgMBAAECgYEAwn3sWpq6cUR65LD8h9MIjopTImTlpFjgz72bhsHD" +
            "ZK6A+eJDXcddrwh7DI34t/0IBqu+QEoOc/f0fIEXS9hMwTvFY59XG7M8" +
            "M6SdeaAbemrGmZ1IdD6YDmpbQFHn2ishaYF0YDZIkBS3WLDFrtk/efaar" +
            "BCpGAVXeEiVQE4LewECQQD5W1rpkq+dHDRzzdtdi1bJ479wun5CfmVDV" +
            "b2CJH7Iz0t8zyp/iEVV2QELnxZMphmoSzKaLXutTTj2OImpzCtRAkEA1" +
            "VMxG6nQq9NkU51H1+I3mlUXRZ0XeFA1GFJ7xWpNRAVhEWlDz2zy9v/g" +
            "X+RFpNC3f5uznycas70Xp78ew43TEQJAZFFqi9mlqTF1sLk67bFnIyX" +
            "rGPEOZrXvC13tNfR0xVkQZ4/46wHp0xXQo9pG4GNaoyhNnVV7EkelCPn" +
            "J+HPZYQJAQh6T9QgQZoGR8hyovPAf3dUL7oa/VIo/urcuJ8VIB5JHQNdI" +
            "rk0NjaNHj1E4iNosVgATj3vWWel9IIArb99QkQJAKvfm78lwnImtg5IM6" +
            "04hdn/Wu1XF8tpxsKLWcnfchMr0bM9rCmKmhAY+wdmqSyPZRiNb1QaaaD" +
            "TqJxLy6AnQ+Q==";
    public static final String RSA_PUBLIC = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCd6rV3vOE578e6V" +
            "lGEakZpPdsX2QmGdIfi/yHe cg1CIEWzX9wn2LNFGtu1EzYQyKACG/RKeog0pUJEVGfBG30zFdNY2YocYJNdPtA" +
            "DqhJbS0GJm7f8 1vRiLKtOwKjdiz9oMEwxhc/5fysfMbercidRmlCDPU9BNL1UPb9bAx25JwIDAQAB";
    private static final int SDK_PAY_FLAG = 1;

    private static final String URL_JSON = "http://101.200.142.201:8080/alipayServer/AlipayDemo";

    @SuppressLint("HandlerLeak")
    private Handler mHandler = new Handler() {
        @SuppressWarnings("unused")
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case SDK_PAY_FLAG: {
                    PayResult payResult = new PayResult((String) msg.obj);

                    String resultInfo = payResult.getResult();// 鍚屾杩斿洖闇€瑕侀獙璇佺殑淇℃伅

                    String resultStatus = payResult.getResultStatus();
                    if (TextUtils.equals(resultStatus, "9000")) {

                    } else {

                        if (TextUtils.equals(resultStatus, "8000")) {

                        } else {

                        }
                    }
                    break;
                }
                default:
                    break;
            }
        }

    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pay);

    }

    public void pay(View view) {

        paySignFromClient();
    }

    private void paySignFromClient() {

        String orderInfo = getOrderInfo("我么", "sadas?" ,"0.1");

        String sign = sign(orderInfo);
        //閫氳繃URLEncoder杩涜缂栫爜
        try {
            sign = URLEncoder.encode(sign, "utf-8");
        } catch (UnsupportedEncodingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        //鎷艰鏈€缁堢殑鏀粯淇℃伅
        StringBuffer sb = new StringBuffer(orderInfo);
        sb.append("&sign=\"");
        sb.append(sign);
        sb.append("\"&");
        sb.append(getSignType());
        final String payInfo = sb.toString();//鑾峰緱鏈€缁堢殑鏀粯淇℃伅
        Runnable payRunnable = new Runnable() {
            @Override
            public void run() {
                // 鏋勯€燩ayTask 瀵硅薄
                PayTask alipay = new PayTask(PayActivity.this);
                String result = alipay.pay(payInfo, true);
                Log.i("TAG", "璧颁簡pay鏀粯鏂规硶.............");

                Message msg = new Message();
                msg.what = SDK_PAY_FLAG;
                msg.obj = result;
                mHandler.sendMessage(msg);
            }
        };

        Thread payThread = new Thread(payRunnable);
        payThread.start();
    }

    private void paySignFromServer() {
        StringBuffer sb = new StringBuffer("?");
        sb.append("subject=");
        sb.append("娴嬭瘯鐨勫晢鍝?");
        sb.append("&");
        sb.append("body=");
        sb.append("璇ユ祴璇曞晢鍝佺殑璇︾粏鎻忚堪");
        sb.append("&");
        sb.append("total_fee=");
        sb.append("0.01");
        //鍒版湇鍔″櫒杩涜璁㈠崟鍔犲瘑
        HttpUtils http = new HttpUtils();
        http.send(HttpRequest.HttpMethod.POST, URL_JSON + sb.toString(), new RequestCallBack<String>() {
            @Override
            public void onLoading(long total, long current, boolean isUploading)
            {
                // super.onLoading(total, current, isUploading);
                Log.i("TAG", "鍔犺浇杩囩▼...............");
            }


            public void onFailure(HttpException arg0, String arg1) {
                // TODO Auto-generated method stub
                Log.i("TAG", "澶辫触............");
            }

            public void onSuccess(ResponseInfo<String> arg0) {
                // TODO Auto-generated method stub
                final String signResult =arg0.result;
                Log.i("TAG", signResult);

                Runnable payRunnable = new Runnable() {
                    @Override
                    public void run() {

                        PayTask alipay = new PayTask(PayActivity.this);
                        String result = alipay.pay(signResult, true);
                        Log.i("TAG", "璧颁簡pay鏀粯鏂规硶.............");

                        Message msg = new Message();
                        msg.what = SDK_PAY_FLAG;
                        msg.obj = result;
                        mHandler.sendMessage(msg);
                    }
                };

                Thread payThread = new Thread(payRunnable);
                payThread.start();
            }
        });
    }

    /**
     * get the sdk version. 鑾峰彇SDK鐗堟湰鍙?	 *
     */
    public void getSDKVersion() {
        PayTask payTask = new PayTask(this);
        String version = payTask.getVersion();
        Toast.makeText(this, version, Toast.LENGTH_SHORT).show();
    }

    /**
     * 鍘熺敓鐨凥5锛堟墜鏈虹綉椤电増鏀粯鍒噉atvie鏀粯锛?銆愬搴旈〉闈㈢綉椤垫敮浠樻寜閽€?	 *
     * @param v
     */

    /**
     * create the order info. 鍒涘缓璁㈠崟淇℃伅
     *
     */
    private String getOrderInfo(String subject, String body, String price) {

        // 绛剧害鍚堜綔鑰呰韩浠絀D
        String orderInfo = "partner=" + "\"" + PARTNER + "\"";

        orderInfo += "&seller_id=" + "\"" + SELLER + "\"";

        orderInfo += "&out_trade_no=" + "\"" + getOutTradeNo() + "\"";

        orderInfo += "&subject=" + "\"" + subject + "\"";

        // 鍟嗗搧璇︽儏
        orderInfo += "&body=" + "\"" + body + "\"";

        orderInfo += "&total_fee=" + "\"" + price + "\"";

        orderInfo += "&notify_url=" + "\"" + "http://notify.msp.hk/notify.htm" + "\"";


        return orderInfo;
    }

    private String getOutTradeNo() {
        SimpleDateFormat format = new SimpleDateFormat("MMddHHmmss", Locale.getDefault());
        Date date = new Date();
        String key = format.format(date);

        Random r = new Random();
        key = key + r.nextInt();
        key = key.substring(0, 15);
        return key;
    }

    private String sign(String content) {
        return SignUtils.sign(content, RSA_PRIVATE);
    }

    private String getSignType() {
        return "sign_type=\"RSA\"";
    }
}
