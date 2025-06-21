package org.example.emarketmall.entity;

import org.example.emarketmall.common.BaseEntity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 商品信息
 */
public class ProductInfo extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 商品编码
     */
    private String productCode;

    /**
     * 商品名称
     */
    private String productName;

    /**
     * 一级分类ID
     */
    private Integer oneCategoryId;

    /**
     * 二级分类ID
     */
    private Integer twoCategoryId;

    /**
     * 商品价格
     */
    private Double price;

    /**
     * 上下架状态
     */
    private Integer publishStatus;

    /**
     * 生成日期
     */
    private String productionDate;

    /**
     * 商品有效期
     */
    private String shelfLife;

    /**
     * 商品描述
     */
    private String description;

    /**
     * 商品编码
     */
    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    /**
     * 商品编码
     */
    public String getProductCode() {
        return productCode;
    }

    /**
     * 商品名称
     */
    public void setProductName(String productName) {
        this.productName = productName;
    }

    /**
     * 商品名称
     */
    public String getProductName() {
        return productName;
    }

    /**
     * 一级分类ID
     */
    public void setOneCategoryId(Integer oneCategoryId) {
        this.oneCategoryId = oneCategoryId;
    }

    /**
     * 一级分类ID
     */
    public Integer getOneCategoryId() {
        return oneCategoryId;
    }

    /**
     * 二级分类ID
     */
    public void setTwoCategoryId(Integer twoCategoryId) {
        this.twoCategoryId = twoCategoryId;
    }

    /**
     * 二级分类ID
     */
    public Integer getTwoCategoryId() {
        return twoCategoryId;
    }

    /**
     * 商品价格
     */
    public void setPrice(Double price) {
        this.price = price;
    }

    /**
     * 商品价格
     */
    public Double getPrice() {
        return price;
    }

    /**
     * 上下架状态
     */
    public void setPublishStatus(Integer publishStatus) {
        this.publishStatus = publishStatus;
    }

    /**
     * 上下架状态
     */
    public Integer getPublishStatus() {
        return publishStatus;
    }

    /**
     * 生成日期
     */
    public void setProductionDate(String productionDate) {
        this.productionDate = productionDate;
    }

    /**
     * 生成日期
     */
    public String getProductionDate() {
        return productionDate;
    }

    /**
     * 商品有效期
     */
    public void setShelfLife(String shelfLife) {
        this.shelfLife = shelfLife;
    }

    /**
     * 商品有效期
     */
    public String getShelfLife() {
        return shelfLife;
    }

    /**
     * 商品描述
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * 商品描述
     */
    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return "ProductInfo{" +
                "id=" + getId() + '\'' +
                "productCode=" + productCode + '\'' +
                "productName=" + productName + '\'' +
                "oneCategoryId=" + oneCategoryId + '\'' +
                "twoCategoryId=" + twoCategoryId + '\'' +
                "price=" + price + '\'' +
                "publishStatus=" + publishStatus + '\'' +
                "productionDate=" + productionDate + '\'' +
                "shelfLife=" + shelfLife + '\'' +
                "description=" + description + '\'' +
                "delFlag=" + getDelFlag() + '\'' +
                "createBy=" + getCreatedBy() + '\'' +
                "createTime=" + getCreatedTime() + '\'' +
                "updateBy=" + getUpdatedBy() + '\'' +
                "updateTime=" + getUpdatedTime() + '\'' +
                "REMARK=" + getRemark() + '\'' +
                '}';
    }
}
