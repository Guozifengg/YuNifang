package yunifang.bwei.com.yunifang.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.List;

import yunifang.bwei.com.yunifang.R;
import yunifang.bwei.com.yunifang.bean.GoodsList;
import yunifang.bwei.com.yunifang.holder.HotHolder;

/**
 * 姓名：郭子锋
 * Created by Administrator on 2017/3/24.
 * 作用：
 */
public class HotAdapter extends RecyclerView.Adapter <HotHolder>{
    private Context context;
    private List<String> list;
    private List<String> list1;
    private List<String> list2;
    private List<String> list3;
    private List<String> list4;
    private List<String> list5;
    private List<String> list6;

    public HotAdapter(Context context, List<String> list, List<String> list1, List<String> list2, List<String> list3, List<String> list4, List<String> list5, List<String> list6) {
        this.context = context;
        this.list = list;
        this.list1 = list1;
        this.list2 = list2;
        this.list3 = list3;
        this.list4 = list4;
        this.list5 = list5;
        this.list6 = list6;
    }

    @Override
    public HotHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.hot_layout, parent, false);
        HotHolder hotHolder = new HotHolder(view);
        return hotHolder;
    }

    @Override
    public void onBindViewHolder(HotHolder holder, int position) {
        ImageLoader.getInstance().displayImage(list.get(position),holder.hotImage);

                ImageLoader.getInstance().displayImage(list1.get(position), holder.hotImage1);
                ImageLoader.getInstance().displayImage(list2.get(position), holder.hotImage2);
                ImageLoader.getInstance().displayImage(list3.get(position), holder.hotImage3);
                ImageLoader.getInstance().displayImage(list4.get(position), holder.hotImage4);
                ImageLoader.getInstance().displayImage(list5.get(position), holder.hotImage5);
                ImageLoader.getInstance().displayImage(list6.get(position), holder.hotImage6);

    }

    @Override
    public int getItemCount() {
        return 5;
    }
}
