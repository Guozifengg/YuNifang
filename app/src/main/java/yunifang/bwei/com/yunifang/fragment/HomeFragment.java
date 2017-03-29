package yunifang.bwei.com.yunifang.fragment;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.lidroid.xutils.DbUtils;
import com.lidroid.xutils.exception.DbException;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.squareup.okhttp.Call;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import yunifang.bwei.com.yunifang.R;
import yunifang.bwei.com.yunifang.adapter.HGridAdapter;
import yunifang.bwei.com.yunifang.adapter.HotAdapter;
import yunifang.bwei.com.yunifang.bean.DefaultGoods;
import yunifang.bwei.com.yunifang.bean.UsersBean;
import yunifang.bwei.com.yunifang.bean.ViewPagerBean;
import yunifang.bwei.com.yunifang.ui.activity.DetailsActivity;
import yunifang.bwei.com.yunifang.utils.FullyGridLayoutManager;
import yunifang.bwei.com.yunifang.utils.FullyLinearLayoutManager;
import yunifang.bwei.com.yunifang.utils.GlideImageLoader;
import yunifang.bwei.com.yunifang.utils.OnRecyclerViewItemClickLisenter;

import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshBase.Mode;
import com.handmark.pulltorefresh.library.PullToRefreshScrollView;
import com.handmark.pulltorefresh.library.PullToRefreshBase.OnRefreshListener;
/**
 * 姓名：郭子锋
 * Created by Administrator on 2017/3/16.
 * 作用：
 */
public class HomeFragment extends Fragment{
    private String result;
    private List<String> listLun;
    private View view1;
    private List<String> listCenter;
    private List<String> listDefault;
    private List<String> smallLun;
    private List<String> hotList;

    private Handler handler;
    private DbUtils utils;
    private List<UsersBean> userList;

    {
        handler = new Handler() {

            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);

                Gson gson = new Gson();
                final ViewPagerBean viewPagerBean = gson.fromJson(result, ViewPagerBean.class);

                listLun = new ArrayList<>();
                for (int i = 0; i < viewPagerBean.data.ad1.size(); i++) {
                    String lunboPic = viewPagerBean.data.ad1.get(i).getImage();
                    listLun.add(lunboPic);
                }
                //无限轮播
                Banner banner = (Banner) view1.findViewById(R.id.banner);
                //设置图片加载器
                banner.setImageLoader(new GlideImageLoader());
                //设置图片集合
                banner.setImages(listLun);//可以是String类型
                //banner设置方法全部调用完毕时最后调用
                banner.start();

                listCenter = new ArrayList<>();
                for (int i = 0; i < viewPagerBean.data.ad5.size(); i++) {
                    String cen = viewPagerBean.data.ad5.get(i).getImage();
                    listCenter.add(cen);
                }
                com.nostra13.universalimageloader.core.ImageLoader.getInstance().displayImage(listCenter.get(0), qdImg, options);
                com.nostra13.universalimageloader.core.ImageLoader.getInstance().displayImage(listCenter.get(1), jfImg, options);
                com.nostra13.universalimageloader.core.ImageLoader.getInstance().displayImage(listCenter.get(2), dhImg, options);
                com.nostra13.universalimageloader.core.ImageLoader.getInstance().displayImage(listCenter.get(3), zwImg, options);

                listDefault = new ArrayList<>();
                for (int i = 0; i < viewPagerBean.data.defaultGoodsList.size(); i++) {
                    String defa = viewPagerBean.data.defaultGoodsList.get(i).getGoods_img();
                    listDefault.add(defa);
                }
                com.nostra13.universalimageloader.core.ImageLoader.getInstance().displayImage(listDefault.get(0), goodsImg1, options);
                com.nostra13.universalimageloader.core.ImageLoader.getInstance().displayImage(listDefault.get(1), goodsImg2, options);
                com.nostra13.universalimageloader.core.ImageLoader.getInstance().displayImage(listDefault.get(2), goodsImg3, options);
                com.nostra13.universalimageloader.core.ImageLoader.getInstance().displayImage(listDefault.get(3), goodsImg4, options);
                com.nostra13.universalimageloader.core.ImageLoader.getInstance().displayImage(listDefault.get(4), goodsImg5, options);
                com.nostra13.universalimageloader.core.ImageLoader.getInstance().displayImage(listDefault.get(5), goodsImg6, options);

                smallLun = new ArrayList<>();
                for (int i = 0; i < viewPagerBean.data.activityInfo.activityInfoList.size(); i++) {
                    String lu = viewPagerBean.data.activityInfo.activityInfoList.get(i).getActivityImg();
                    smallLun.add(lu);
                }

                //无限轮播
                Banner banner2 = (Banner) view1.findViewById(R.id.banner2);
                //设置图片加载器
                banner2.setImageLoader(new GlideImageLoader());
                //设置图片集合
                banner2.setImages(smallLun);//可以是String类型
                banner2.isAutoPlay(false);
                banner2.setBannerStyle(BannerConfig.NOT_INDICATOR);
                //banner设置方法全部调用完毕时最后调用
                banner2.start();

                hotList = new ArrayList<>();
                for (int i = 0; i < viewPagerBean.data.subjects.size(); i++) {
                    String hot = viewPagerBean.data.subjects.get(i).getImage();
                    hotList.add(hot);
                }

//            List<List<String>> hotImageList = new ArrayList<>();
                List<String> hotList1 = new ArrayList<>();
                for (int j = 0; j < viewPagerBean.data.subjects.size(); j++) {
//            for(int i=0;i<6;i++){
                    String hotImg = viewPagerBean.data.subjects.get(j).goodsList.get(0).getGoods_img();
                    hotList1.add(hotImg);
//            }
//                hotImageList.add(hotList2);
                }

                List<String> hotList2 = new ArrayList<>();
                for (int j = 0; j < viewPagerBean.data.subjects.size(); j++) {
                    String hotImg = viewPagerBean.data.subjects.get(j).goodsList.get(1).getGoods_img();
                    hotList2.add(hotImg);
                }

                List<String> hotList3 = new ArrayList<>();
                for (int j = 0; j < viewPagerBean.data.subjects.size(); j++) {
                    String hotImg = viewPagerBean.data.subjects.get(j).goodsList.get(2).getGoods_img();
                    hotList3.add(hotImg);
                }
                List<String> hotList4 = new ArrayList<>();
                for (int j = 0; j < viewPagerBean.data.subjects.size(); j++) {
                    String hotImg = viewPagerBean.data.subjects.get(j).goodsList.get(3).getGoods_img();
                    hotList4.add(hotImg);
                }
                List<String> hotList5 = new ArrayList<>();
                for (int j = 0; j < viewPagerBean.data.subjects.size(); j++) {
                    String hotImg = viewPagerBean.data.subjects.get(j).goodsList.get(4).getGoods_img();
                    hotList5.add(hotImg);
                }
                List<String> hotList6 = new ArrayList<>();
                for (int j = 0; j < viewPagerBean.data.subjects.size(); j++) {
                    String hotImg = viewPagerBean.data.subjects.get(j).goodsList.get(5).getGoods_img();
                    hotList6.add(hotImg);
                }

                //首页GridView类型的数据
                List<DefaultGoods> gridListGoods=new ArrayList<>();
                for(int i=0;i<viewPagerBean.data.defaultGoodsList.size();i++){
                    DefaultGoods defaultGoods=new DefaultGoods(viewPagerBean.data.defaultGoodsList.get(i).goods_img,viewPagerBean.data.defaultGoodsList.get(i).efficacy,
                            viewPagerBean.data.defaultGoodsList.get(i).goods_name,viewPagerBean.data.defaultGoodsList.get(i).shop_price,viewPagerBean.data.defaultGoodsList.get(i).market_price);
                    gridListGoods.add(defaultGoods);
                }
                homeLinear = (LinearLayout) view1.findViewById(R.id.homeLinear);

                View hotview = View.inflate(getActivity(), R.layout.home_hot, null);
                recyclerView = (RecyclerView) hotview.findViewById(R.id.recyclerView);
                recyclerView.setLayoutManager(new FullyLinearLayoutManager(getActivity()));
                RecyclerView gridRecyclerView = (RecyclerView) hotview.findViewById(R.id.gridRecyclerView);
                gridRecyclerView.setLayoutManager(new FullyGridLayoutManager(getActivity(),2));
            /*HotTopicAdapter hotTopicAdapter = new HotTopicAdapter(getActivity(),hotList);
            recyclerView.setAdapter(hotTopicAdapter);*/
                HotAdapter hotAdapter = new HotAdapter(getActivity(), hotList, hotList1, hotList2, hotList3, hotList4, hotList5, hotList6);
                recyclerView.setAdapter(hotAdapter);
                HGridAdapter hGridAdapter = new HGridAdapter(getActivity(), gridListGoods);
                //条目监听
                hGridAdapter.setOnItemClickLisenter(new OnRecyclerViewItemClickLisenter() {
                    @Override
                    public void onItemClickLisenter(int position) {

                        Intent intent=new Intent(getActivity(), DetailsActivity.class);
                        intent.putExtra("id",viewPagerBean.data.defaultGoodsList.get(position).id);
                        startActivity(intent);
                    }
                });
                gridRecyclerView.setAdapter(hGridAdapter);
                homeLinear.addView(hotview);
            }
        };
    }

    private ImageView qdImg;
    private ImageView jfImg;
    private ImageView dhImg;
    private ImageView zwImg;
    private DisplayImageOptions options;
    private ImageView goodsImg1;
    private ImageView goodsImg2;
    private ImageView goodsImg3;
    private ImageView goodsImg4;
    private ImageView goodsImg5;
    private ImageView goodsImg6;

    PullToRefreshScrollView mPullRefreshScrollView;
    ScrollView mScrollView;
    private RecyclerView recyclerView;
    private LinearLayout homeLinear;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view1 = View.inflate(getActivity(), R.layout.home_layout,null);
        options = new DisplayImageOptions.Builder().build();

        initView();

        //这几个刷新Label的设置
        mPullRefreshScrollView = (PullToRefreshScrollView) view1.findViewById(R.id.pull_refresh_scrollview);
        mPullRefreshScrollView.getLoadingLayoutProxy().setLastUpdatedLabel("lastUpdateLabel");
        mPullRefreshScrollView.getLoadingLayoutProxy().setPullLabel("PULLLABLE");
        mPullRefreshScrollView.getLoadingLayoutProxy().setRefreshingLabel("refreshingLabel");
        mPullRefreshScrollView.getLoadingLayoutProxy().setReleaseLabel("releaseLabel");
        //上拉、下拉设定
        mPullRefreshScrollView.setMode(Mode.PULL_FROM_START);
        //上拉监听函数
        mPullRefreshScrollView.setOnRefreshListener(new OnRefreshListener<ScrollView>() {
            @Override
            public void onRefresh(PullToRefreshBase<ScrollView> refreshView) {
                //执行刷新函数
                new GetDataTask().execute();
            }
        });
        //获取ScrollView布局，此文中用不到
        mScrollView = mPullRefreshScrollView.getRefreshableView();

        return view1;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        //okHttp请求数据
        OkHttpClient mOkHttpClient = new OkHttpClient();
        //创建一个Request
        final Request request = new Request.Builder()
                .url("http://m.yunifang.com/yunifang/mobile/home?random=84831&encode=9dd34239798e8cb22bf99a75d1882447")
                .build();
        //new call
        Call call = mOkHttpClient.newCall(request);
        //请求加入调度
        call.enqueue(new Callback()
        {
            @Override
            public void onFailure(Request request, IOException e)
            {
            }
            @Override
            public void onResponse(final Response response) throws IOException
            {
                result =  response.body().string();
                //利用handler更新UI
                handler.sendEmptyMessage(0);
            }
        });

    }


    public void initView(){
        qdImg = (ImageView) view1.findViewById(R.id.qdImg);
        jfImg = (ImageView) view1.findViewById(R.id.jfImg);
        dhImg = (ImageView) view1.findViewById(R.id.dhImg);
        zwImg = (ImageView) view1.findViewById(R.id.zwImg);

        goodsImg1 = (ImageView) view1.findViewById(R.id.goodsImg1);
        goodsImg2 = (ImageView) view1.findViewById(R.id.goodsImg2);
        goodsImg3 = (ImageView) view1.findViewById(R.id.goodsImg3);
        goodsImg4 = (ImageView) view1.findViewById(R.id.goodsImg4);
        goodsImg5 = (ImageView) view1.findViewById(R.id.goodsImg5);
        goodsImg6 = (ImageView) view1.findViewById(R.id.goodsImg6);
    }

    private class GetDataTask extends AsyncTask<Void, Void, LinearLayout> {
        @Override
        protected LinearLayout doInBackground(Void... params) {

            try {
                Thread.sleep(1000);

            } catch (InterruptedException e) {
            }
            return null;
        }
        @Override
        protected void onPostExecute(LinearLayout result) {

            mPullRefreshScrollView.setMode(Mode.PULL_FROM_START);

            mPullRefreshScrollView.onRefreshComplete();

            super.onPostExecute(result);
        }
    }

}
