package org.example.emarketmall.service.product;

import junit.framework.TestCase;
import org.example.emarketmall.entity.ProductInfo;
import org.example.emarketmall.utils.DateUtils;
import org.junit.Test;

public class ProductInfoServiceTest extends TestCase {

    @Test
    public void testInsertProductInfo() {
        ProductInfoService pd = new ProductInfoService();
        ProductInfo productInfo = new ProductInfo();
        productInfo.setOneCategoryId(1);
        productInfo.setId(1);
        productInfo.setProductCode("1233");
        productInfo.setProductName("测试商品");
        productInfo.setProductionDate(DateUtils.getTime());
        productInfo.setPrice(20.0);


        int i = pd.insertProductInfo(productInfo);
        System.out.println(i);
//        assert i == 1;
    }

    @Test
    public void testInsertNullProductInfo() {
        ProductInfoService pd = new ProductInfoService();
        int i = pd.insertProductInfo(null);
        System.out.println(i);
        assertEquals(0, i);
    }

    @Test
    public void testInsertValidProductInfo() {
        ProductInfoService pd = new ProductInfoService();
        ProductInfo productInfo = new ProductInfo();
        productInfo.setOneCategoryId(2);
        productInfo.setId(2);
        productInfo.setProductCode("4567");
        productInfo.setProductName("新测试商品");
        productInfo.setProductionDate(DateUtils.getTime());
        productInfo.setPrice(30.0);

        int i = pd.insertProductInfo(productInfo);
        System.out.println(i);
        assertTrue(i > 0);
    }
}