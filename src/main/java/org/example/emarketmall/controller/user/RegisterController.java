package org.example.emarketmall.controller.user;

import com.alibaba.fastjson.JSON;
import org.example.emarketmall.common.AjaxResult;
import org.example.emarketmall.entity.UserInfo;
import org.example.emarketmall.service.user.login.RegisterService;
import org.example.emarketmall.utils.ServletUtils;
import org.example.emarketmall.utils.StringUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collection;

/**
 * @Description: TODO
 * @author: april
 * @date: 2022年06月08日 23:17
 */

@WebServlet("/signup")
public class RegisterController extends HttpServlet {
    private RegisterService registerService = new RegisterService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doProcessRequest(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doProcessRequest(req, resp);
    }

    private void doProcessRequest(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UserInfo userInfo = ServletUtils.getObjectFromPayload(req, UserInfo.class);
        if (userInfo != null) {
            String msg = registerService.register(userInfo);
            if (StringUtils.isEmpty(msg)) {
                msg = "注册服务异常";
            }
            resp.setContentType("application/json");
            resp.setCharacterEncoding("UTF-8");
            if ("注册成功".equalsIgnoreCase(msg)) {
                ServletUtils.renderString(resp, JSON.toJSONString(AjaxResult.success(msg)));
            } else {
                ServletUtils.renderString(resp, JSON.toJSONString(AjaxResult.error(msg)));
            }
            //logHeaderValue(resp);
        } else {
            resp.sendRedirect("/register.jsp");
        }
    }

    private void logHeaderValue(HttpServletResponse resp){
        Collection<String> headerNames = resp.getHeaderNames();
        headerNames.forEach(name -> {
            System.out.println(name + ": " + resp.getHeader(name));
        });
    }
}
