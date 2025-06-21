package org.example.emarketmall.service.product;

import org.example.emarketmall.dao.ProductInfoDao;
import org.example.emarketmall.dao.impl.ProductInfoDaoImp;
import org.example.emarketmall.entity.ProductInfo;
import org.example.emarketmall.utils.DateUtils;

import java.util.List;

public class ProductInfoService {

    private ProductInfoDao productInfoDao;

    public ProductInfoService() {productInfoDao = new ProductInfoDaoImp();}

    public List<ProductInfo> selectProductInfoList(ProductInfo productInfo) {
        try {
            return productInfoDao.selectProductInfoList(productInfo);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public List<ProductInfo> selectProductInfoList() {
        try {
            return productInfoDao.selectProductInfoList();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public ProductInfo selectProductInfoById(Integer id) {
        try {
            return productInfoDao.selectProductInfoById(id);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public int insertProductInfo(ProductInfo productInfo) {
        try {
            if (productInfo == null) {
                throw new RuntimeException("ProductInfo is null");
            }
            productInfo.setCreatedTime(DateUtils.getTime());
            return productInfoDao.insertProductInfo(productInfo);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public int deleteProductInfoById(Integer productInfoId) {
        try {
            return productInfoDao.deleteProductInfoById(productInfoId);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public int updateProductOnSaleStatus(Integer productId, boolean publishStatus) {
        try {
            return productInfoDao.updateProductOnSaleStatus(productId, publishStatus);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public List<ProductInfo> selectProductInfoByPriceRange(Double maxPrice) {
        try {
            return productInfoDao.selectProductInfoByPriceRange(maxPrice);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public List<ProductInfo> selectProductInfoByProductName(String productName) {
        try {
            return productInfoDao.selectProductInfoByProductName(productName);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public List<ProductInfo> selectProductInfoByProductCode(String productCode) {
        try {
            return productInfoDao.selectProductInfoByProductCode(productCode);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    public int updateProductInfo(ProductInfo productInfo,int id) {
        try {
            return productInfoDao.updateProductInfo(productInfo,id);
        }
        catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public List<ProductInfo> selectProductInfoByProductionDate(String productionDate) {
        try {
            return productInfoDao.selectProductInfoByProductionDate(productionDate);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
