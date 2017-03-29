package yunifang.bwei.com.yunifang.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.List;

import yunifang.bwei.com.yunifang.R;
import yunifang.bwei.com.yunifang.bean.DefaultGoods;
import yunifang.bwei.com.yunifang.holder.HGridHolder;
import yunifang.bwei.com.yunifang.utils.OnRecyclerViewItemClickLisenter;

/**
 * 姓名：郭子锋
 * Created by Administrator on 2017/3/23.
 * 作用：
 */
public class HGridAdapter extends RecyclerView.Adapter<HGridHolder> {
    private Context context;
    private List<DefaultGoods> list;
    private OnRecyclerViewItemClickLisenter onRecyclerViewItemClickLisenter;

    public HGridAdapter(Context context, List<DefaultGoods> list) {
        this.context = context;
        this.list = list;
    }

    public void setOnItemClickLisenter(OnRecyclerViewItemClickLisenter onRecyclerViewItemClickLisenter){
        this.onRecyclerViewItemClickLisenter=onRecyclerViewItemClickLisenter;
    }
    @Override
    public HGridHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.h_grid_layout, parent, false);
        HGridHolder hGridHolder = new HGridHolder(view);
        return hGridHolder;
    }

    @Override
    public void onBindViewHolder(HGridHolder holder, final int position) {

        holder.gridImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(onRecyclerViewItemClickLisenter!=null){
                    onRecyclerViewItemClickLisenter.onItemClickLisenter(position);
                }
            }
        });
        ImageLoader.getInstance().displayImage(list.get(position).goods_img,holder.gridImg);
        holder.hGridTv1.setText(list.get(position).efficacy);
        holder.hGridTv2.setText(list.get(position).goods_name);
        holder.hGridTv3.setText(list.get(position).shop_price);
        holder.hGridTv4.setText(list.get(position).market_price);
    }

    @Override
    public int getItemCount() {
        return 6;
    }
}
