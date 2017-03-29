package yunifang.bwei.com.yunifang.holder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import yunifang.bwei.com.yunifang.R;

/**
 * 姓名：郭子锋
 * Created by Administrator on 2017/3/23.
 * 作用：
 */
public class HGridHolder extends RecyclerView.ViewHolder {

    public final ImageView gridImg;
    public final TextView hGridTv1;
    public final TextView hGridTv2;
    public final TextView hGridTv3;
    public final TextView hGridTv4;

    public HGridHolder(View itemView) {
        super(itemView);
        gridImg = (ImageView) itemView.findViewById(R.id.gridImg);
        hGridTv1 = (TextView) itemView.findViewById(R.id.hGridTv1);
        hGridTv2 = (TextView) itemView.findViewById(R.id.hGridTv2);
        hGridTv3 = (TextView) itemView.findViewById(R.id.hGridTv3);
        hGridTv4 = (TextView) itemView.findViewById(R.id.hGridTv4);

    }

}
