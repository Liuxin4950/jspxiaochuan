package org.example.emarketmall.dao.impl;

import cqcet.aibd.soft.ObjectUtil;
import org.example.emarketmall.dao.IDataAccess;
import org.example.emarketmall.dao.ProductInfoDao;
import org.example.emarketmall.entity.ProductInfo;
import org.example.emarketmall.entity.UserInfo;
import org.example.emarketmall.utils.StringUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @Description: TODO
 * @author: april
 * @date: 2022年06月11日 22:03
 */
public class ProductInfoDaoImp implements ProductInfoDao {

    private IDataAccess dac;

    public ProductInfoDaoImp() {
        dac = new DataAccessImpl();
    }

    @Override
    public List<ProductInfo> selectProductInfoList() throws Exception {
        String sql = "select * from product_info where delFlag=0";
        return new ObjectUtil<ProductInfo>().getList(sql, ProductInfo.class);
    }

    @Override
    public List<ProductInfo> selectProductInfoList(ProductInfo productInfo) throws Exception {
        if(productInfo == null){
            return selectProductInfoList();
        }else {
            return selectProductInfoByProductParams(productInfo);
        }
    }
    private List<ProductInfo> selectProductInfoByProductParams(ProductInfo productInfo) throws Exception {
        List<ProductInfo> productInfoList = new ArrayList<>();
        if (productInfo == null) {
            return selectProductInfoList();
        }
        //是否输入了多个查询条件
        List<String> params = Arrays.asList("id", "productCode", "productName", "price", "productionDate");
        //for->多参数查询
        for (String p : params) {
            switch (p) {
                case "id":
                    if (productInfo.getId() != null) {
                        productInfoList.add(selectProductInfoById(productInfo.getId()));
                    }
                    break;
                case "productCode":
                    if (StringUtils.isNotEmpty(productInfo.getProductCode())) {
                        productInfoList.addAll(selectProductInfoByProductCode(productInfo.getProductCode()));
                    }
                    break;
                case "productName":
                    if (StringUtils.isNotEmpty(productInfo.getProductName())) {
                        productInfoList.addAll(selectProductInfoByProductName(productInfo.getProductName()));
                    }
                    break;
                case "price":
                    if (productInfo.getPrice()!= null) {
                        productInfoList.addAll(selectProductInfoByPriceRange(productInfo.getPrice()));
                    }
                    break;
                case "productionDate":
                    if (StringUtils.isNotEmpty(productInfo.getProductionDate())) {
                        productInfoList.addAll(selectProductInfoByProductionDate(productInfo.getProductionDate()));
                    }
                default:
                    break;
            }
        }
        return productInfoList;
    }

    @Override
    public ProductInfo selectProductInfoById(Integer id) throws Exception {
        return dac.getEntityById(ProductInfo.class, id);
    }

    @Override
    public int insertProductInfo(ProductInfo productInfo) {
        // 修正SQL语法：移除列名的单引号，使用VALUES关键字，修正列名拼写
        String sql = "INSERT INTO product_info " +
                "(delFlag, createdBy, createdTime, updatedBy, updatedTime, remark, " +
                "productCode, productName, oneCategoryId, twoCategoryId, " +
                "price, publishStatus, productionDate, shelfLife, description) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        if(productInfo == null){
            return 0;
        }

        System.out.println("插入数据:" + productInfo.toString());

        // 修正参数顺序和列名映射
        int i = new ObjectUtil<ProductInfo>().add(sql,
                productInfo.getDelFlag(),       // delFlag
                productInfo.getCreatedBy(),      // createdBy
                productInfo.getCreatedTime(),    // createdTime
                productInfo.getUpdatedBy(),      // updatedBy
                productInfo.getUpdatedTime(),    // updatedTime
                productInfo.getRemark(),        // remark
                productInfo.getProductCode(),   // productCode
                productInfo.getProductName(),   // productName
                productInfo.getOneCategoryId(), // oneCategoryId
                productInfo.getTwoCategoryId(), // twoCategoryId
                productInfo.getPrice(),         // price
                productInfo.getPublishStatus(), // publishStatus
                productInfo.getProductionDate(),// productionDate
                productInfo.getShelfLife(),     // shelfLife
                productInfo.getDescription()    // description
        );

        System.out.println("插入结果:" + i);
        return i;
    }

    @Override
    public int deleteProductInfoById(Integer productInfoId) {
        String sql = "update product_info set delFlag=1 where id=?";
        return new ObjectUtil<ProductInfo>().update(sql, productInfoId);
    }

    @Override
    public int updateProductOnSaleStatus(Integer productId, boolean publishStatus) {
        if (!publishStatus) {
            return 0;
        }
        return 0;
    }

    @Override
    public List<ProductInfo> selectProductInfoByPriceRange(Double maxPrice) throws Exception {
        String sql = "SELECT * FROM product_info WHERE price <= ? and delFlag=0";
        return new ObjectUtil<ProductInfo>().getList(sql, ProductInfo.class, maxPrice);
    }

    @Override
    public List<ProductInfo> selectProductInfoByProductName(String productName) throws Exception {
        String sql = "SELECT * FROM product_info WHERE productName LIKE ? and delFlag=0";
        return new ObjectUtil<ProductInfo>().getList(sql, ProductInfo.class, "%" + productName + "%");
    }

    @Override
    public List<ProductInfo> selectProductInfoByProductCode(String productCode) throws Exception {
        String sql = "SELECT * FROM product_info WHERE productCode LIKE? and delFlag=0";
        return new ObjectUtil<ProductInfo>().getList(sql, ProductInfo.class, "%" + productCode + "%");
    }

    @Override
    public int updateProductInfo(ProductInfo productInfo, int id) throws Exception {
        String sql = "update product_info set productCode=?,productName=?,price=?,publishStatus=?,productionDate=?,shelfLife=?,description=? where id=?";
        if(productInfo == null){
            return 0;
        }
        return new ObjectUtil<ProductInfo>().update(sql, productInfo.getProductCode(), productInfo.getProductName(),
                productInfo.getPrice(),productInfo.getPublishStatus(),productInfo.getProductionDate(),
                productInfo.getShelfLife(),productInfo.getDescription(),id);
    }

    @Override
    public List<ProductInfo> selectProductInfoByProductionDate(String productionDate) throws Exception {
        String sql = "SELECT * FROM product_info WHERE productionDate LIKE? and delFlag=0";
        return new ObjectUtil<ProductInfo>().getList(sql, ProductInfo.class, "%" + productionDate + "%");
    }

}