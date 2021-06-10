package com.scut.mall.controller.user;

import cn.hutool.captcha.CaptchaUtil;
import cn.hutool.captcha.LineCaptcha;
import com.scut.mall.entity.User;
import com.scut.mall.entity.pojo.ResultBean;
import com.scut.mall.service.UserService;
import com.scut.mall.utils.WebUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.imageio.ImageIO;
import javax.security.auth.login.LoginException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;


@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    /**
     * 注册页面
     * @return
     */
     @RequestMapping("/toRegister.html")
     public  String toRegister(){
        return "mall/user/register";
    }

    /**
     * 登录页面
     * @return
     */
     @RequestMapping("/toLogin.html")
     public  String toLogin(){
          return  "mall/user/login";
      }

    /**
     * 用户登录
     *
     * @param  userName
     * @param  password
     */
     @RequestMapping("/login.do")
    public void login(String userName,String password,String code,
                      HttpServletRequest request,HttpServletResponse response) throws IOException, LoginException {


         String msg = "123";
         String code0 = WebUtils.getHttpSession().getAttribute("code").toString();
         if(code0.equals(code)){
             System.out.println("验证码正确");
             User user=userService.checkLogin(userName,password);
             if (user != null){
                 //登录成功 重定向至首页
                 request.getSession().setAttribute("user",user);
                 response.sendRedirect("/mall/index.html");
             }
             else{
                 response.sendRedirect("/mall/error.html");
                 msg = "用户名或密码错误";
                 request.getSession().setAttribute("msg",msg);
                 throw  new LoginException("用户名或密码错误");

             }
         }else {
             msg = "验证码错误";

             request.getSession().setAttribute("msg",msg);
             response.sendRedirect("/mall/error.html");
         }

     }

    /**
     * 注册
     */
     @RequestMapping("/register.do")
    public void register(String userName,String password,String phone,
                         HttpServletResponse response ,HttpServletRequest request)throws IOException, LoginException {
         User user=new User();
         user.setUserName(userName);
         user.setPassword(password);
         user.setPhone(phone);
         user.setIntegration(0);//注册时都初始化积分为0
         userService.create(user);//创建用户表单
         request.getSession().setAttribute("user",user);//获取用户信息
         response.sendRedirect("/mall/index.html");//登录完成后直接跳转主页面
    }

    /**
     * 退出登录
     */
    @RequestMapping("logout.do")
    public void logout(HttpServletRequest request,
                       HttpServletResponse response) throws IOException{
        request.getSession().removeAttribute("user");
        //未登录的首页
        response.sendRedirect("/mall/index.html");
    }
    /**
     * 验证用户名是否唯一
     * @param userName
     * @return
     */
    @ResponseBody
    @RequestMapping("/checkUsername.do")
    public ResultBean<Boolean> checkUsername(String userName){
        List<User> users = userService.findByUsername(userName);
        if (users==null||users.isEmpty()){
            return new ResultBean<>(true);
        }
        return new ResultBean<>(false);
    }

    /**
     * 错误页面
     */
    @RequestMapping("/error.html")
    public String error(HttpServletResponse response,HttpServletRequest request){
        return "error!";
    }


    /*
    *   验证码
    * */
    @RequestMapping("getCode")
    public void getCode(HttpServletResponse response, HttpSession session,HttpServletRequest request) throws IOException {
        //定义图形验证码的长和宽
        LineCaptcha lineCaptcha = CaptchaUtil.createLineCaptcha(116,36,4,5);
        session.setAttribute("code",lineCaptcha.getCode());
        ServletOutputStream outputStream = response.getOutputStream();
        ImageIO.write(lineCaptcha.getImage(),"JPEG",outputStream);

    }


}


