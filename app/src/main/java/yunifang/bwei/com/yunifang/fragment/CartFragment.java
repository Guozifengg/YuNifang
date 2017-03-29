package yunifang.bwei.com.yunifang.fragment;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.lidroid.xutils.DbUtils;
import com.lidroid.xutils.db.sqlite.WhereBuilder;
import com.lidroid.xutils.exception.DbException;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.ArrayList;
import java.util.List;

import yunifang.bwei.com.yunifang.R;
import yunifang.bwei.com.yunifang.adapter.MyBase;
import yunifang.bwei.com.yunifang.bean.GouWuCarBean;
import yunifang.bwei.com.yunifang.bean.UsersBean;
import yunifang.bwei.com.yunifang.ui.activity.PayActivity;

/**
 * 姓名：郭子锋
 * Created by Administrator on 2017/3/16.
 * 作用：
 */
public class CartFragment extends Fragment {

    private List<GouWuCarBean> list;
    private View view1;
    private CheckBox checkAll;
    private MyBase2 myBase;
    private TextView tv_price;
    private DbUtils utils;
    private List<UsersBean> list2;
    private ListView carLv;
    private RelativeLayout emptyCar;
    private LinearLayout ll2;
    private TextView jieSuan;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view1 = View.inflate(getActivity(), R.layout.cart_layout,null);
        initView();
        //数据库
        initDataBase();
        carLv = (ListView) view1.findViewById(R.id.carLv);
        emptyCar = (RelativeLayout) view1.findViewById(R.id.emptyCar);
        ll2 = (LinearLayout) view1.findViewById(R.id.ll2);
        jieSuan = (TextView) view1.findViewById(R.id.jieSuan);
        //初始化数据
        initData();

            myBase = new MyBase2();
            carLv.setAdapter(myBase);
        checkAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(checkAll.isChecked()==true) {
                    for (int i = 0; i < list.size(); i++) {
                        list.get(i).setChecked(true);
                    }
                }else{
                    for (int i = 0; i < list.size(); i++) {
                        list.get(i).setChecked(false);
                    }
                }
                myBase.notifyDataSetChanged();
                setPrices();
            }
        });

        //条目监听
        carLv.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, final int position, long l) {
                Toast.makeText(getActivity(), "长按了"+position, Toast.LENGTH_SHORT).show();
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                builder.setTitle("提示");
                builder.setMessage("是否删除！");
                builder.setIcon(R.mipmap.ic_launcher);
                builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        list.remove(position);
                        try {
                            int dd=utils.findAll(UsersBean.class).get(position).getId();
                            utils.delete(UsersBean.class, WhereBuilder.b("id","=",dd));
                        } catch (DbException e) {
                            e.printStackTrace();
                        }
                        myBase.notifyDataSetChanged();

                    }
                });
                builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                    }
                });
                builder.show();

                return true;
            }
        });

        /*if(list2!=null&&!list2.isEmpty()){
            emptyCar.setVisibility(View.GONE);
            ll2.setVisibility(View.VISIBLE);
        }else {
            emptyCar.setVisibility(View.VISIBLE);
            ll2.setVisibility(View.GONE);
        }*/
        jieSuan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getActivity(), PayActivity.class);
                startActivity(intent);
            }
        });
        return view1;
    }

    private void initView() {
        checkAll = (CheckBox) view1.findViewById(R.id.checkAll);
        tv_price = (TextView) view1.findViewById(R.id.tv_price);
    }

    public void setPrices(){
        int price=0;
        for(int i=0;i<list.size();i++){
            boolean checked=list.get(i).isChecked();
            if(checked){
                price=price+list.get(i).getGoodsprice()*list.get(i).getGoodscount();
            }
        }
        tv_price.setText(price+"");
    }
    //数据库
    private void initDataBase() {
        utils = DbUtils.create(getActivity(), "users.db");
    }

    private void initData() {
        UsersBean usersBean=new UsersBean();
        try {
            list2 = utils.findAll(UsersBean.class);
        } catch (DbException e) {
            e.printStackTrace();
        }
        list=new ArrayList<>();
        if(list2!=null&&!list2.isEmpty()){
            for (UsersBean s : list2) {
                list.add(new GouWuCarBean(s.getDbImg(), s.getDbName(), s.getDbPrice(), s.getDbCount()));
            }
            emptyCar.setVisibility(View.GONE);
        }else{
            Toast.makeText(getActivity(), "数据库为空", Toast.LENGTH_SHORT).show();
            emptyCar.setVisibility(View.VISIBLE);
            ll2.setVisibility(View.GONE);
        }

    }

    //List适配器
    class MyBase2 extends BaseAdapter{

        @Override
        public int getCount() {
            return list.size();
        }

        @Override
        public Object getItem(int i) {
            return null;
        }

        @Override
        public long getItemId(int i) {
            return 0;
        }

        @Override
        public View getView(final int i, View view, ViewGroup viewGroup) {
            final ViewHolder viewHolder;
            if(view==null){
                viewHolder = new ViewHolder();
                view=View.inflate(getActivity(), R.layout.gwcar_layout,null);
                viewHolder.carImage = (ImageView) view.findViewById(R.id.carImage);
                viewHolder.carName = (TextView) view.findViewById(R.id.carName);
                viewHolder.carPrice = (TextView) view.findViewById(R.id.carPrice);
                viewHolder.carCount = (TextView) view.findViewById(R.id.carCount);
                viewHolder.carCheckBox = (CheckBox) view.findViewById(R.id.carCheckBox);
                view.setTag(viewHolder);
            }else{
                viewHolder= (ViewHolder) view.getTag();
            }
            ImageLoader.getInstance().displayImage(list.get(i).getGoodsimage(),viewHolder.carImage);
            viewHolder.carName.setText(list.get(i).getGoodsname());
            viewHolder.carPrice.setText(list.get(i).getGoodsprice()+"");
            viewHolder.carCount.setText(list.get(i).getGoodscount()+"");
            viewHolder.carCheckBox.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    boolean checked=viewHolder.carCheckBox.isChecked();
                    list.get(i).setChecked(checked);
                    setPrices();
                }
            });
            viewHolder.carCheckBox.setChecked(list.get(i).isChecked());
            return view;
        }

    }

    class ViewHolder{
        public TextView carName;
        public TextView carPrice;
        public TextView carCount;
        public ImageView carImage;
        public CheckBox carCheckBox;
    }
}
