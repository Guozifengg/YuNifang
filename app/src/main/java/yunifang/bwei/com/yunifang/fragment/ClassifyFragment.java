package yunifang.bwei.com.yunifang.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import yunifang.bwei.com.yunifang.R;

/**
 * 姓名：郭子锋
 * Created by Administrator on 2017/3/16.
 * 作用：
 */
public class ClassifyFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view1=View.inflate(getActivity(), R.layout.classify_layout,null);
        return view1;
    }
}
