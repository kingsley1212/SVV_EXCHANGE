package com.svv.jys.code.common.entity;


import com.svv.jys.code.common.base.BaseEntity;

/**
 * Created by 74099 on 2018/7/11.
 */
public class C2cRecordEntity extends BaseEntity {
    private String id;

    private String user_id;

    private String bank_name;

    private String bank_no;

    private String true_name;

    private String coin;

    private String price;

    private String num;

    private String total;

    private String end_time;

    private String status;

    private String type;

    private String order_sn;

    private String add_time;

    private String admin_id;

    private String status_name;

    public String getStatus_name() {
        return status_name;
    }

    public void setStatus_name(String status_name) {
        this.status_name = status_name;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return this.id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getUser_id() {
        return this.user_id;
    }

    public void setBank_name(String bank_name) {
        this.bank_name = bank_name;
    }

    public String getBank_name() {
        return this.bank_name;
    }

    public void setBank_no(String bank_no) {
        this.bank_no = bank_no;
    }

    public String getBank_no() {
        return this.bank_no;
    }

    public void setTrue_name(String true_name) {
        this.true_name = true_name;
    }

    public String getTrue_name() {
        return this.true_name;
    }

    public void setCoin(String coin) {
        this.coin = coin;
    }

    public String getCoin() {
        return this.coin;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getPrice() {
        return this.price;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public String getNum() {
        return this.num;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public String getTotal() {
        return this.total;
    }

    public void setEnd_time(String end_time) {
        this.end_time = end_time;
    }

    public String getEnd_time() {
        return this.end_time;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return this.status;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getType() {
        return this.type;
    }

    public void setOrder_sn(String order_sn) {
        this.order_sn = order_sn;
    }

    public String getOrder_sn() {
        return this.order_sn;
    }

    public void setAdd_time(String add_time) {
        this.add_time = add_time;
    }

    public String getAdd_time() {
        return this.add_time;
    }

    public void setAdmin_id(String admin_id) {
        this.admin_id = admin_id;
    }

    public String getAdmin_id() {
        return this.admin_id;
    }

    @Override
    public String toString() {
        return "C2cRecordEntity{" +
                "id='" + id + '\'' +
                ", user_id='" + user_id + '\'' +
                ", bank_name='" + bank_name + '\'' +
                ", bank_no='" + bank_no + '\'' +
                ", true_name='" + true_name + '\'' +
                ", coin='" + coin + '\'' +
                ", price='" + price + '\'' +
                ", num='" + num + '\'' +
                ", total='" + total + '\'' +
                ", end_time='" + end_time + '\'' +
                ", status='" + status + '\'' +
                ", type='" + type + '\'' +
                ", order_sn='" + order_sn + '\'' +
                ", add_time='" + add_time + '\'' +
                ", admin_id='" + admin_id + '\'' +
                '}';
    }
}
