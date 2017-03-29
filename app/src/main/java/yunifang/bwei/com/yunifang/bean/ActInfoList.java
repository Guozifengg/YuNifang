package yunifang.bwei.com.yunifang.bean;

/**
 * 姓名：郭子锋
 * Created by Administrator on 2017/3/22.
 * 作用：
 */
public class ActInfoList {
    public String activityImg;

    public ActInfoList(String activityImg) {
        this.activityImg = activityImg;
    }

    public String getActivityImg() {
        return activityImg;
    }

    @Override
    public String toString() {
        return "ActInfoList{" +
                "activityImg='" + activityImg + '\'' +
                '}';
    }
}
