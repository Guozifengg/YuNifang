package yunifang.bwei.com.yunifang.bean;

/**
 * 姓名：郭子锋
 * Created by Administrator on 2017/3/26.
 * 作用：
 */
public class GouWuCarBean {
    private boolean isChecked;
    private String goodsimage;
    private String goodsname;
    private int goodsprice;
    private int goodscount;

    public GouWuCarBean(String goodsimage, String goodsname, int goodsprice, int goodscount) {
        this.goodsimage = goodsimage;
        this.goodsname = goodsname;
        this.goodsprice = goodsprice;
        this.goodscount = goodscount;
    }

    public GouWuCarBean(boolean isChecked, String goodsimage, String goodsname, int goodsprice, int goodscount) {
        this.isChecked = isChecked;
        this.goodsimage = goodsimage;
        this.goodsname = goodsname;
        this.goodsprice = goodsprice;
        this.goodscount = goodscount;
    }

    public boolean isChecked() {
        return isChecked;
    }

    public String getGoodsimage() {
        return goodsimage;
    }

    public String getGoodsname() {
        return goodsname;
    }

    public int getGoodsprice() {
        return goodsprice;
    }

    public int getGoodscount() {
        return goodscount;
    }

    public void setChecked(boolean checked) {
        isChecked = checked;
    }

    public void setGoodsimage(String goodsimage) {
        this.goodsimage = goodsimage;
    }

    public void setGoodsname(String goodsname) {
        this.goodsname = goodsname;
    }

    public void setGoodsprice(int goodsprice) {
        this.goodsprice = goodsprice;
    }

    public void setGoodscount(int goodscount) {
        this.goodscount = goodscount;
    }
}
