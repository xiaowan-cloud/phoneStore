package com.scut.mall.controller.user;

import com.scut.mall.entity.*;
import com.scut.mall.entity.pojo.ResultBean;
import com.scut.mall.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @ Author     ：Bin Liu
 * @ Description：产品控制层类
 * @ Modified By：
 */

@Controller
@RequestMapping("/product")
public class ProductController {
    @Autowired
    private ProductService productService;
    @Autowired
    private ProductCategoryService productCategoryService;
    @Autowired
    private ShopCartService shopCartService;
    @Autowired
    private AddressService addressService;
    @Autowired
    private MessageService messageService;
    /**
     * create by: Bin Liu
     * description: 获取商品信息
     * @param id
     * @return 
     */
    @RequestMapping("/get.do")
    public ResultBean<Product> getProduct(int id) {
        Product product = productService.findById(id);
        return new ResultBean<>(product);
    }


    /**
     * create by: Bin Liu
     * description: 打开商品详情页面
     * @param id
     * @param map
     * @return 
     */
    @RequestMapping("/get.html")
    public String toProductPage(int id, Map<String, Object> map) {
        Product product = productService.findById(id);
        map.put("product", product);

        List<Message> messageList = messageService.findByMessage(product.getId());

        map.put("messageList", messageList);
        return "mall/product/info";
    }
    
    /**
     * create by: Bin Liu
     * description: 查找热门商品
     * @Param: null
     * @return 
     */
    @ResponseBody
    @RequestMapping("/hot.do")
    public ResultBean<List<Product>> getHotProduct() {
        List<Product> products = productService.findHotProduct();
        return new ResultBean<>(products);
    }

    @RequestMapping("/search.do")
    @ResponseBody
    public ResultBean<List<Product>> toSearchPage(String keyword,Map<String, Object> map,HttpServletRequest request) {
        String serchname = (String) request.getSession().getAttribute("serchname");
        List<Product> products = productService.findAll();
        List<Product> list = new ArrayList<>();
        for (Product product:products) {
            if(product.getTitle().contains(serchname)){
                list.add(product);
            }
        }
        map.put("products", list);
        return new ResultBean<>(list);
    }


    /**
     * 关键字搜索
     *
     * @return
     */
    @RequestMapping("/serchPro.html")
    public String serchPro(String serchname,HttpServletRequest request) {
        request.getSession().setAttribute("serchname",serchname);
        return "mall/product/product";
    }

    /**
     * create by: Bin Liu
     * description:
     * @param pageNo
     * @param pageSize
     * @return
     */
    @ResponseBody
    @RequestMapping("/new.do")
    public ResultBean<List<Product>> getNewProduct(int pageNo, int pageSize) {
        Pageable pageable = new PageRequest(pageNo, pageSize);
        List<Product> products = productService.findNewProduct(pageable);
        return new ResultBean<>(products);
    }

    /**
     * create by: Bin Liu
     * description: 打开分类查看商品页面
     * @Param: null
     * @return
     */
    @RequestMapping("/category.html")
    public String toCatePage(int cid, Map<String, Object> map) {
        ProductCategory productCategory = productCategoryService.findById(cid);
        map.put("category", productCategory);
        return "mall/product/category";
    }

    @RequestMapping("/toCart.html")
    public String toCart(){
        return "mall/product/cart";
    }

    /**
     * create by: Bin Liu
     * description: 根据一级分类查找商品
     * @param cid
     * @param pageNo
     * @param pageSize
     * @return
     */
    @ResponseBody
    @RequestMapping("/category.do")
    public ResultBean<List<Product>> getCategoryProduct(int cid, int pageNo, int pageSize) {
        Pageable pageable =PageRequest.of(pageNo, pageSize, Sort.by(Sort.Direction.ASC,"id"));
        List<Product> products = productService.findByProductCategoryId(cid, pageable);
        return new ResultBean<>(products);
    }

    /**
     * create by: Bin Liu
     * description: 按二级分类查找商品
     * @param csId
     * @param pageNo
     * @param pageSize
     * @return 
     */
    @ResponseBody
    @RequestMapping("/categorySec.do")
    public ResultBean<List<Product>> getCategorySecProduct(int csId, int pageNo, int pageSize) {
        Pageable pageable =PageRequest.of(pageNo, pageSize, Sort.by(Sort.Direction.ASC,"id"));
        List<Product> products = productService.findByProductCategorySecondId(csId, pageable);
        return new ResultBean<>(products);
    }

    /**
     * 根据一级分类查询它所有的二级分类
     * @param cid
     * @return
     */
    @ResponseBody
    @RequestMapping("/getCategorySec.do")
    public ResultBean<List<ProductCategory>> getCategorySec(int cid){
        List<ProductCategory> list = productCategoryService.findByParentId(cid);
        return new ResultBean<>(list);
    }
    
    /**
     * create by: Bin Liu
     * description: 加购物车
     * @param productId
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping("/addCart.do")
    public ResultBean<Boolean> addToCart(int productId, HttpServletRequest request) throws Exception {
        shopCartService.addCart(productId, request);
        return new ResultBean<>(true);
    }

    /**
     * create by: Bin Liu
     * description: 移除购物车
     * @param productId
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping("/delCart.do")
    public ResultBean<Boolean> delToCart(int productId, HttpServletRequest request) throws Exception {
        shopCartService.remove(productId, request);
        return new ResultBean<>(true);
    }

    /**
     * create by: Bin Liu
     * description: 查看购物车商品
     * @Param: request
     * @return
     */
    @ResponseBody
    @RequestMapping("/listCart.do")
    public ResultBean<List<OrderItem>> listCart(HttpServletRequest request) throws Exception {
        List<OrderItem> orderItems = shopCartService.listCart(request);
        return new ResultBean<>(orderItems);
    }
    /**
     * create by: Cillivian
     * description:加载地址信息
     *
      * @Param: request
     * @return
     */
    @ResponseBody
    @RequestMapping("/listAddress.do")
    public ResultBean<List<Address>> listAddress(HttpServletRequest request) throws Exception {
       List<Address> addresses=addressService.findByUserId(request );
        return new ResultBean<>(addresses);
    }


    @RequestMapping(method = RequestMethod.POST, value = "/addmessage.do")
    @ResponseBody
    public String add(String content, int proid, String proname, HttpServletRequest request)  {
        Message message = new Message();
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String datetime = sf.format(new Date());
        User user = (User) request.getSession().getAttribute("user");

        message.setContent(content);
        message.setProid(proid);
        if(user!=null){
            message.setUsername(user.getUserName());
        }else{
            message.setUsername("游客");
        }

        message.setProname(proname);
        message.setCreatetime(datetime);
        int id = messageService.create(message);
        if(id==0){
            return "error";
        }
        return "success";
    }
}
