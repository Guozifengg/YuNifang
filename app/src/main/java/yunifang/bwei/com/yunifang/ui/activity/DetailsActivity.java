package yunifang.bwei.com.yunifang.ui.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.lidroid.xutils.DbUtils;
import com.lidroid.xutils.exception.DbException;
import com.youth.banner.Banner;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import yunifang.bwei.com.yunifang.R;
import yunifang.bwei.com.yunifang.bean.UsersBean;
import yunifang.bwei.com.yunifang.bean.XqDaaBean;
import yunifang.bwei.com.yunifang.bean.XqGoods;
import yunifang.bwei.com.yunifang.bean.XqImg;
import yunifang.bwei.com.yunifang.utils.GlideImageLoader;
import yunifang.bwei.com.yunifang.utils.StreamUtils;

public class DetailsActivity extends AppCompatActivity {

    private String result;
    private XqDaaBean xqDaaBean;
    private List<String> list;
    private Banner banner;
    private TextView xqName;
    private TextView xqPrice;
    private TextView xqPriceNone;
    private String xqid;
    private TextView addCart;
    private DbUtils utils;
    private ArrayList<UsersBean> userList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        Intent intent = getIntent();
        xqid = intent.getStringExtra("id");
        banner = (Banner) findViewById(R.id.banner3);
        xqName = (TextView) findViewById(R.id.xqName);
        xqPrice = (TextView) findViewById(R.id.xqPrice);
        xqPriceNone = (TextView) findViewById(R.id.xqPriceNone);
        addCart = (TextView) findViewById(R.id.addCart);
        //数据库
        utils = DbUtils.create(this, "users.db", 1, new DbUtils.DbUpgradeListener() {
            @Override
            public void onUpgrade(DbUtils dbUtils, int i, int i1) {

            }
        });
        try {
            utils.createTableIfNotExist(UsersBean.class);
        } catch (DbException e) {
            e.printStackTrace();
        }
        new Thread(){
            @Override
            public void run() {
                super.run();
                requestData();
                //更新UI
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {

                        //设置图片加载器
                        banner.setImageLoader(new GlideImageLoader());
                        //设置图片集合
                        banner.setImages(list);
                        banner.isAutoPlay(false);
                        //banner设置方法全部调用完毕时最后调用
                        banner.start();

                        xqName.setText(xqDaaBean.data.goods.goods_name);
                        xqPrice.setText(xqDaaBean.data.goods.shop_price);
                        xqPriceNone.setText(xqDaaBean.data.goods.market_price);
                        //加入购物车
                        addCart.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                Toast.makeText(DetailsActivity.this, "已经加入购物车", Toast.LENGTH_SHORT).show();
                                insert();
                            }
                        });
                    }
                });
            }
        }.start();

    }

    public void insert(){
        userList = new ArrayList<>();
        try {
            List<UsersBean> findList=utils.findAll(UsersBean.class);
            /*for(UsersBean s:findList){
                if(s.getDbName().equals(userList.get(0).getDbName()))
            }*/

//            Toast.makeText(DetailsActivity.this, "已经有数据了", Toast.LENGTH_SHORT).show();
            UsersBean usersBean = new UsersBean(1,xqDaaBean.data.goods.gallery.get(0).getNormal_url() , xqDaaBean.data.goods.goods_name , 100, 1);
            userList.add(usersBean);

            utils.saveAll(userList);

        } catch (DbException e) {
            e.printStackTrace();
        }
    }
    private void requestData() {
        String path="http://m.yunifang.com/yunifang/mobile/goods/detail?random=42187&encode=168d21c6d627072293fbbb0a44cc72e9&id="+xqid;
        try {
            URL url=new URL(path);
            HttpURLConnection connection= (HttpURLConnection) url.openConnection();
            int code=connection.getResponseCode();
            if(code==200){
                InputStream inputStream=connection.getInputStream();
                result = StreamUtils.parser(inputStream);
            }
            Gson gson=new Gson();
            xqDaaBean = gson.fromJson(result, XqDaaBean.class);
            list = new ArrayList<>();
            for(int i=0;i<xqDaaBean.data.goods.gallery.size();i++){
                String xqImg=xqDaaBean.data.goods.gallery.get(i).getNormal_url();
                list.add(xqImg);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }


}
