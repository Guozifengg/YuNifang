package yunifang.bwei.com.yunifang.holder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import yunifang.bwei.com.yunifang.R;

/**
 * 姓名：郭子锋
 * Created by Administrator on 2017/3/24.
 * 作用：
 */
public class HotHolder extends RecyclerView.ViewHolder {

    public final ImageView hotImage;
    public final ImageView hotImage1;
    public final ImageView hotImage2;
    public final ImageView hotImage3;
    public final ImageView hotImage4;
    public final ImageView hotImage5;
    public final ImageView hotImage6;

    public HotHolder(View itemView) {
        super(itemView);
        hotImage = (ImageView) itemView.findViewById(R.id.hotImage);
        hotImage1 = (ImageView) itemView.findViewById(R.id.hotImage1);
        hotImage2 = (ImageView) itemView.findViewById(R.id.hotImage2);
        hotImage3 = (ImageView) itemView.findViewById(R.id.hotImage3);
        hotImage4 = (ImageView) itemView.findViewById(R.id.hotImage4);
        hotImage5 = (ImageView) itemView.findViewById(R.id.hotImage5);
        hotImage6 = (ImageView) itemView.findViewById(R.id.hotImage6);
    }
}
