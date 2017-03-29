package yunifang.bwei.com.yunifang.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.List;

import yunifang.bwei.com.yunifang.R;
import yunifang.bwei.com.yunifang.holder.HFirstHolder;
import yunifang.bwei.com.yunifang.holder.HSecondHolder;

/**
 * 姓名：郭子锋
 * Created by Administrator on 2017/3/22.
 * 作用：
 */
public class HotTopicAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

    private Context context;
    private List<String> list;
    private static final int FIRST_TYPE=0;
    private static final int SECOND_TYPE=1;
    private int type=FIRST_TYPE;
    private DisplayImageOptions options;

    public HotTopicAdapter(Context context) {
        this.context = context;
    }

    public HotTopicAdapter(Context context, List<String> list) {
        this.context = context;
        this.list=list;
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder viewHolder=null;
        switch (viewType){
            case 0:
                View view = LayoutInflater.from(context).inflate(R.layout.h_first_layout, parent, false);
                viewHolder=new HFirstHolder(view);
                break;
            case 1:
                View view1 = LayoutInflater.from(context).inflate(R.layout.h_second_layout, parent, false);
                viewHolder=new HSecondHolder(view1);
                break;
        }
        return viewHolder;
    }

    @Override
    public int getItemViewType(int position) {
        /*switch (position){
            case 0:
                type= FIRST_TYPE;
            break;
            case 1:
                type= SECOND_TYPE;
            break;
            case 2:
                type= FIRST_TYPE;
                break;
            case 3:
                type= SECOND_TYPE;
                break;
            case 4:
                type= FIRST_TYPE;
                break;
            case 5:
                type= SECOND_TYPE;
                break;
            case 6:
                type= FIRST_TYPE;
                break;
            case 7:
                type= SECOND_TYPE;
                break;
        }*/
        if(position%2==0){
            type=FIRST_TYPE;
        }else{
            type=SECOND_TYPE;
        }
        return type;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        /*int itemViewType=getItemViewType(position);
        switch (itemViewType){
            case 0:
                break;
            case 1:
                break;
        }*/
        options = new DisplayImageOptions.Builder().build();
        /*int itemViewType=getItemViewType(position);
        HFirstHolder hFirstHolder= (HFirstHolder) holder;*/

        /*switch (itemViewType){
            case 0:
                HFirstHolder hFirstHolder= (HFirstHolder) holder;
                ImageLoader.getInstance().displayImage(list.get(1),hFirstHolder.hotImg,options);
                break;

        }*/
    }

    @Override
    public int getItemCount() {
        return 9;
    }
}
