package org.example.emarketmall.controller.product;

import com.alibaba.fastjson.JSON;
import org.example.emarketmall.common.AjaxResult;
import org.example.emarketmall.common.TableDataInfo;
import org.example.emarketmall.entity.ProductInfo;
import org.example.emarketmall.service.product.ProductInfoService;
import org.example.emarketmall.utils.BeanUtils;
import org.example.emarketmall.utils.ServletUtils;
import org.example.emarketmall.utils.StringUtils;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * 商品信息控制器，处理商品相关的增删改查请求
 */
@WebServlet("/mall/product")
public class ProductInfoController extends HttpServlet {

    private ProductInfoService productInfoService = new ProductInfoService();


    protected void doGet(HttpServletRequest req, HttpServletResponse resp)throws ServletException, IOException {
        doProcessRequest(req, resp);
    }
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)throws ServletException, IOException {
        doProcessRequest(req, resp);
    }

    private void doProcessRequest(HttpServletRequest req, HttpServletResponse resp)throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        resp.setCharacterEncoding("utf-8");
        //id可能为null
        String id = ServletUtils.getParamFromPayLoad(req, "id");
        String opt= ServletUtils.getParamFromPayLoad(req, "opt");
        System.out.println(req);
        ProductInfo productInfo = ServletUtils.getObjectFromPayload(req, ProductInfo.class);
        switch (opt) {
            case "add":
                //同edit,先跳转到add.jsp页面，在用post提交product信息
                //跳转到add.jsp
                if ("GET".equalsIgnoreCase(req.getMethod())) {
                    resp.sendRedirect("/product/add.jsp");
                    return;
                }
                if ("POST".equalsIgnoreCase(req.getMethod())) {
                    if (productInfo!= null) {
                        ServletUtils.renderString(resp, JSON.toJSONString(addProductInfo(productInfo)));
                    } else {
                        ServletUtils.renderString(resp, JSON.toJSONString(AjaxResult.error("新增商品信息对象内容异常")));
                    }
                }
                break;
            case "edit":
                //先接收get请求的跳转，打开edit.jsp,此时id不为空，userInfo可以为空
                if ("GET".equalsIgnoreCase(req.getMethod())) {
                    if (StringUtils.isEmpty(id)) {
                        ServletUtils.renderString(resp, JSON.toJSONString(AjaxResult.error("编辑ID内容异常")));
                        return;
                    } else {
                        ProductInfo product = productInfoService.selectProductInfoById(Integer.parseInt(id));
                        req.setAttribute("product", product);
                        req.getRequestDispatcher("/product/edit.jsp").forward(req, resp);
                        return;
                    }
                }
                //在edit.jsp页修改后提交post请求
                if ("POST".equalsIgnoreCase(req.getMethod())) {
                    if (productInfo!= null) {
                        ServletUtils.renderString(resp, JSON.toJSONString(editProductInfo(id, productInfo)));
                    } else {
                        ServletUtils.renderString(resp, JSON.toJSONString(AjaxResult.error("编辑商品信息对象内容异常")));
                    }
                }
                break;
            case "remove":
                ServletUtils.renderString(resp, JSON.toJSONString(removeProductInfo(id)));
                break;
            case "list":
                TableDataInfo tableDataInfo = list(productInfo);
                if (tableDataInfo != null) {
                    ServletUtils.renderString(resp, JSON.toJSONString(tableDataInfo));
                } else {
                    ServletUtils.renderString(resp, JSON.toJSONString(AjaxResult.error("商品信息查询异常")));
                }
                break;
            case "detail":
                if (StringUtils.isEmpty(id)) {
                    ServletUtils.renderString(resp, JSON.toJSONString(AjaxResult.error("详情ID内容异常")));
                    return;
                } else {
                    ProductInfo product = productInfoService.selectProductInfoById(Integer.parseInt(id));
                    req.setAttribute("product", product);
                    req.getRequestDispatcher("/product/detail.jsp").forward(req, resp);
                    return;
                }
            default:
                ServletUtils.renderString(resp, JSON.toJSONString(AjaxResult.error("操作选项opt内容异常")));

        }
    }

    private TableDataInfo list(ProductInfo productInfo) {
        List<ProductInfo> productInfos = productInfoService.selectProductInfoList(productInfo);
        if (productInfos!= null) {
            return TableDataInfo.getDataTable(productInfos);
        } else {
            return null;
        }
    }

    private AjaxResult removeProductInfo(String id) {
        if (StringUtils.isEmpty(id)) {
            return AjaxResult.error("删除ID内容异常");
        }
        if (productInfoService.deleteProductInfoById(Integer.parseInt(id)) > 0) {
            return AjaxResult.success();
        } else {
            return AjaxResult.error("删除商品信息对象内容异常");
        }
    }

    private AjaxResult editProductInfo(String id, ProductInfo productInfo) {
        if (StringUtils.isEmpty(id)) {
            return AjaxResult.error("编辑ID内容异常");
        } else {
            productInfo.setId(Integer.parseInt(id));
        }
        if (productInfo != null) {
            if (productInfoService.updateProductInfo(productInfo,1) > 0) {
                return AjaxResult.success();
            }
        } else {
            return AjaxResult.error("编辑商品信息对象内容异常");
        }
        return AjaxResult.error("编辑ID内容异常");
    }

    private AjaxResult addProductInfo(ProductInfo productInfo) {
        if (productInfo == null) {
            return AjaxResult.error("新增商品信息对象内容异常");
        }
        if (productInfo.getId() != null) {
            // 新增操作不应该携带ID
            return AjaxResult.error("新增ID内容异常");
        }
        if (productInfoService.insertProductInfo(productInfo) > 0) {
            return AjaxResult.success();
        }
        return AjaxResult.error("新增商品信息对象内容异常");
    }
}
