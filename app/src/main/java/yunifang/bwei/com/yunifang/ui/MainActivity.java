package yunifang.bwei.com.yunifang.ui;

import android.graphics.Color;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import yunifang.bwei.com.yunifang.R;
import yunifang.bwei.com.yunifang.fragment.CartFragment;
import yunifang.bwei.com.yunifang.fragment.ClassifyFragment;
import yunifang.bwei.com.yunifang.fragment.HomeFragment;
import yunifang.bwei.com.yunifang.fragment.MeilaFragment;
import yunifang.bwei.com.yunifang.fragment.MineFragment;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private LinearLayout syLl;
    private LinearLayout flLl;
    private LinearLayout mlLl;
    private LinearLayout gwcLl;
    private LinearLayout wdLl;
    private TextView[] textArray;
    private Fragment fragment;
    private HomeFragment homeFragment;
    private ClassifyFragment classifyFragment;
    private MeilaFragment meilaFragment;
    private CartFragment cartFragment;
    private MineFragment mineFragment;
    private int[] img1;
    private int[] img2;
    private ImageView[] imgArray;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //初始化控件
        initView();
        syLl.setOnClickListener(this);
        flLl.setOnClickListener(this);
        mlLl.setOnClickListener(this);
        gwcLl.setOnClickListener(this);
        wdLl.setOnClickListener(this);

        if(homeFragment==null){
            homeFragment=new HomeFragment();
        }
        addFragment(homeFragment);
        setColor(0);
        setBackground(0);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.syLl:
                if(homeFragment==null) {
                    homeFragment = new HomeFragment();
                }
                addFragment(homeFragment);
                setColor(0);
                setBackground(0);
                break;
            case R.id.flLl:
                if(classifyFragment==null) {
                    classifyFragment = new ClassifyFragment();
                }
                addFragment(classifyFragment);
                setColor(1);
                setBackground(1);
                break;
            case R.id.mlLl:
                if(meilaFragment==null) {
                    meilaFragment = new MeilaFragment();
                }
                addFragment(meilaFragment);
                setColor(2);
                setBackground(2);
                break;
            case R.id.gwcLl:
                //每次都new一下加载数据库里面的数据
                    cartFragment = new CartFragment();
                addFragment(cartFragment);
                setColor(3);
                setBackground(3);
                break;
            case R.id.wdLl:
                if(mineFragment==null) {
                    mineFragment = new MineFragment();
                }
                addFragment(mineFragment);
                setColor(4);
                setBackground(4);
                break;
        }
    }

    public void addFragment(Fragment f){
        FragmentManager manager=getSupportFragmentManager();
        FragmentTransaction transaction=manager.beginTransaction();
        if(fragment!=null){
            transaction.hide(fragment);
        }if(!f.isAdded()){
            transaction.add(R.id.frame,f);
        }
            transaction.show(f);
        transaction.commit();
        fragment=f;
    }

    public void setColor(int index){
        for(int i=0;i<textArray.length;i++){
            if(i==index){
                textArray[i].setTextColor(Color.RED);
            }else{
                textArray[i].setTextColor(Color.parseColor("#666666"));
            }
        }
    }
    public void setBackground(int index){
        for(int i=0;i<imgArray.length;i++){
            if(i==index){
                //要替换图片不是背景
                imgArray[i].setImageResource(img2[i]);
            }else{
                imgArray[i].setImageResource(img1[i]);
            }
        }
    }
    public void initView(){
        syLl = (LinearLayout) findViewById(R.id.syLl);
        flLl = (LinearLayout) findViewById(R.id.flLl);
        mlLl = (LinearLayout) findViewById(R.id.mlLl);
        gwcLl = (LinearLayout) findViewById(R.id.gwcLl);
        wdLl = (LinearLayout) findViewById(R.id.wdLl);

        TextView syTv = (TextView) findViewById(R.id.syTv);
        TextView flTv = (TextView) findViewById(R.id.flTv);
        TextView mlTv = (TextView) findViewById(R.id.mlTv);
        TextView gwcTv = (TextView) findViewById(R.id.gwcTv);
        TextView wdTv = (TextView) findViewById(R.id.wdTv);
        textArray = new TextView[]{syTv,flTv,mlTv,gwcTv,wdTv};
        ImageView syimg = (ImageView) findViewById(R.id.syimg);
        ImageView flimg = (ImageView) findViewById(R.id.flimg);
        ImageView mlimg = (ImageView) findViewById(R.id.mlimg);
        ImageView gwcimg = (ImageView) findViewById(R.id.gwcimg);
        ImageView wdimg = (ImageView) findViewById(R.id.wdimg);
        imgArray = new ImageView[]{syimg,flimg,mlimg,gwcimg,wdimg};
        img1 = new int[]{R.mipmap.shouye1,R.mipmap.fenlei1,R.mipmap.meila1,R.mipmap.gouwuche1,R.mipmap.wode1};
        img2 = new int[]{R.mipmap.shouye2,R.mipmap.fenlei2,R.mipmap.meila2,R.mipmap.gouwuche2,R.mipmap.wode2};
    }
}
