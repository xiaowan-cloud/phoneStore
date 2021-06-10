package com.scut.mall.service;

import com.scut.mall.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * @ Author     ：Bin Liu
 * @ Description：产品业务逻辑接口
 * @ Modified By：
 */
public interface ProductService {
    /**
     * create by: Bin Liu
     * description: 根据id查询
     * @param id
     * @return
     */
    Product findById(int id);

    /**
     * create by: Bin Liu
     * description: 分页查询所有
     * @param pageable
     * @return
     */
    Page<Product> findAll(Pageable pageable);

    /**
     * create by: Bin Liu
     * description: 查找热门商品
     * @Param: null
     * @return
     */
    List<Product> findHotProduct();

    /**
     * create by: Bin Liu
     * description: 查找所有商品
     * @param pageable
     * @return
     */
    List<Product> findNewProduct(Pageable pageable);

    /**
     * create by: Bin Liu
     * description: 根据一级分类查找商品
     * @param cId
     * @param pageable
     * @return
     */
    List<Product> findByProductCategoryId(int cId,Pageable pageable);
    
    /**
     * create by: Bin Liu
     * description: 根据二级分类查找商品
     * @param csId
     * @param pageable
     * @return 
     */
    List<Product> findByProductCategorySecondId(int csId,Pageable pageable);

    /**
     * create by: Bin Liu
     * description: 更新
     * @Param: product
     * @return
     */
    void update(Product product);

    /**
     * create by: Bin Liu
     * description: 创建
     * @Param: product
     * @return
     */
    int create(Product product);

    /**
     * create by: Bin Liu
     * description: 根据Id删除
     * @Param: id
     * @return
     */

    void deleteById(int id);
    /**
     * create by: Bin Liu
     * description: 通过标题搜索商品
     * @param keyword
     * @return 
     */
    List<Product> findByTitleIsLike(String keyword);

    List<Product> findAll();
}
