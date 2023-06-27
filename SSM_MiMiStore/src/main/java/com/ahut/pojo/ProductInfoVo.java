package com.ahut.pojo;

public class ProductInfoVo {

    private String pname;
    private Integer typeid;
    private Integer lprice;
    private  Integer hprice;
    private  Integer page = 1;


    public ProductInfoVo() {
    }

    public ProductInfoVo(String pname, Integer typeid, Integer lprice, Integer hprice, Integer page) {
        this.pname = pname;
        this.typeid = typeid;
        this.lprice = lprice;
        this.hprice = hprice;
        this.page = page;
    }

    /**
     * 获取
     * @return pname
     */
    public String getPname() {
        return pname;
    }

    /**
     * 设置
     * @param pname
     */
    public void setPname(String pname) {
        this.pname = pname;
    }

    /**
     * 获取
     * @return typeid
     */
    public Integer getTypeid() {
        return typeid;
    }

    /**
     * 设置
     * @param typeid
     */
    public void setTypeid(Integer typeid) {
        this.typeid = typeid;
    }

    /**
     * 获取
     * @return lprice
     */
    public Integer getLprice() {
        return lprice;
    }

    /**
     * 设置
     * @param lprice
     */
    public void setLprice(Integer lprice) {
        this.lprice = lprice;
    }

    /**
     * 获取
     * @return hprice
     */
    public Integer getHprice() {
        return hprice;
    }

    /**
     * 设置
     * @param hprice
     */
    public void setHprice(Integer hprice) {
        this.hprice = hprice;
    }

    /**
     * 获取
     * @return page
     */
    public Integer getPage() {
        return page;
    }

    /**
     * 设置
     * @param page
     */
    public void setPage(Integer page) {
        this.page = page;
    }

    public String toString() {
        return "ProductInfoVo{pname = " + pname + ", typeid = " + typeid + ", lprice = " + lprice + ", hprice = " + hprice + ", page = " + page + "}";
    }
}
