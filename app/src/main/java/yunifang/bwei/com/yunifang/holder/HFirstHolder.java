package yunifang.bwei.com.yunifang.holder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import yunifang.bwei.com.yunifang.R;

/**
 * 姓名：郭子锋
 * Created by Administrator on 2017/3/22.
 * 作用：
 */
public class HFirstHolder extends RecyclerView.ViewHolder{

    public final ImageView hotImg;

    public HFirstHolder(View itemView) {
        super(itemView);
        hotImg = (ImageView) itemView.findViewById(R.id.hotImg);
    }
}
