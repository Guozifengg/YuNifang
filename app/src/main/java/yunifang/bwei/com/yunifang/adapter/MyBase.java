package yunifang.bwei.com.yunifang.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.List;

import yunifang.bwei.com.yunifang.R;
import yunifang.bwei.com.yunifang.bean.GouWuCarBean;
import yunifang.bwei.com.yunifang.fragment.CartFragment;

/**
 * 姓名：郭子锋
 * Created by Administrator on 2017/3/26.
 * 作用：
 */
public class MyBase extends BaseAdapter {
    private Context context;
    private List<GouWuCarBean> list;

    public MyBase(Context context, List<GouWuCarBean> list) {
        this.context = context;
        this.list = list;
    }

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
            view=View.inflate(context, R.layout.gwcar_layout,null);
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
            }
        });
        viewHolder.carCheckBox.setChecked(list.get(i).isChecked());
        return view;
    }
    class ViewHolder{

        public TextView carName;
        public TextView carPrice;
        public TextView carCount;
        public ImageView carImage;
        public CheckBox carCheckBox;
    }
}
