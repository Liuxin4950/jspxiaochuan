package org.example.emarketmall.dao;

import org.example.emarketmall.entity.ProductInfo;
import java.util.List;
/**
 * 商品信息数据访问对象接口，定义商品信息相关的数据操作方法
 */
public interface ProductInfoDao {
    /**
     * 查询所有商品信息
     * @return 商品信息集合
     * @throws Exception 异常信息
     */
    List<ProductInfo> selectProductInfoList() throws Exception;

    /**
     * 根据条件查询商品信息列表
     * @param productInfo 商品信息
     * @return 商品信息集合
     * @throws Exception 异常信息
     */
    List<ProductInfo> selectProductInfoList(ProductInfo productInfo) throws Exception;

    /**
     * 通过ID查询商品
     * @param id 商品ID
     * @return 商品对象信息
     * @throws Exception 异常信息
     */
    ProductInfo selectProductInfoById(Integer id) throws Exception;

    /**
     * 新增商品信息
     * @param productInfo 商品信息
     * @return 结果
     */
    int insertProductInfo(ProductInfo productInfo);

    /**
     * 通过商品ID删除商品
     * @param productInfoId 商品ID
     * @return 结果
     */
    int deleteProductInfoById(Integer productInfoId);

    /**
     * 修改商品上架状态
     * @param productId 商品ID
     * @param publishStatus 上架状态，true为上架，false为下架
     * @return 结果
     */
    int updateProductOnSaleStatus(Integer productId, boolean publishStatus);

    /**
     * 通过价格区间查询商品
     * @param maxPrice 最高价格
     * @return 商品信息集合
     * @throws Exception 异常信息
     */
    List<ProductInfo> selectProductInfoByPriceRange(Double maxPrice) throws Exception;

    /**
     * 通过商品名称查询商品
     * @param productName 商品名称
     * @return 商品信息集合
     * @throws Exception 异常信息
     */
    List<ProductInfo> selectProductInfoByProductName(String productName) throws Exception;

    /**
     * 通过商品编码查询商品
     * @param productCode 商品编码
     * @return 商品信息集合
     * @throws Exception 异常信息
     */
    List<ProductInfo> selectProductInfoByProductCode(String productCode) throws Exception;

    /**
     * 更新商品，根据商品id
     * @param productInfo
     * @return
     * @throws Exception
     */
    int updateProductInfo(ProductInfo productInfo,int id) throws Exception;

    /**
     * 通过商品生产日期查询商品
     * @param productionDate 商品生产日期
     * @return 商品信息集合
     * @throws Exception 异常信息
     */
    List<ProductInfo> selectProductInfoByProductionDate(String productionDate) throws Exception;

}
