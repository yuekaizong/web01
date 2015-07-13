
package kaizong.jee.web01.b;

import java.io.Serializable;

public class BookBean implements Serializable {

    private int id;
    private String title;
    private String author;
    private String bookconcern;
    private String publish_date;
    private float price;
    private int amount;
    private String remark;

    public BookBean() {
        // TODO Auto-generated constructor stub
    }

    public BookBean(int id, String title, String author, String bookconcern, String publish_date,
            float price, int amount, String remark) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.bookconcern = bookconcern;
        this.publish_date = publish_date;
        this.price = price;
        this.amount = amount;
        this.remark = remark;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getBookconcern() {
        return bookconcern;
    }

    public void setBookconcern(String bookconcern) {
        this.bookconcern = bookconcern;
    }

    public String getPublish_date() {
        return publish_date;
    }

    public void setPublish_date(String publish_date) {
        this.publish_date = publish_date;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

}
