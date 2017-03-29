package yunifang.bwei.com.yunifang.bean;

import com.lidroid.xutils.db.annotation.Id;

/**
 * 姓名：郭子锋
 * Created by Administrator on 2017/3/27.
 * 作用：
 */
public class UsersBean {

    private int id;
    private String dbImg;
    private String dbName;
    private int dbPrice;
    private int dbCount;

    public UsersBean(int id, String dbImg, String dbName, int dbPrice, int dbCount) {
        this.id = id;
        this.dbImg = dbImg;
        this.dbName = dbName;
        this.dbPrice = dbPrice;
        this.dbCount = dbCount;
    }

    public UsersBean() {
    }

    public int getId() {
        return id;
    }

    public String getDbImg() {
        return dbImg;
    }

    public String getDbName() {
        return dbName;
    }

    public int getDbPrice() {
        return dbPrice;
    }

    public int getDbCount() {
        return dbCount;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setDbImg(String dbImg) {
        this.dbImg = dbImg;
    }

    public void setDbName(String dbName) {
        this.dbName = dbName;
    }

    public void setDbPrice(int dbPrice) {
        this.dbPrice = dbPrice;
    }

    public void setDbCount(int dbCount) {
        this.dbCount = dbCount;
    }

    @Override
    public String toString() {
        return "UsersBean{" +
                "id=" + id +
                ", dbImg='" + dbImg + '\'' +
                ", dbName='" + dbName + '\'' +
                ", dbPrice=" + dbPrice +
                ", dbCount=" + dbCount +
                '}';
    }
}
