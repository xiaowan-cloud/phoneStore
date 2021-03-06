package com.scut.mall.service;

import com.scut.mall.entity.ProductCategory;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * @ Author     ：Bin Liu
 * @ Description：产品种类业务逻辑接口
 * @ Modified By：
 */
public interface ProductCategoryService {
    /**
     * create by: Bin Liu
     * description: 根据id查询
     * @Param: id
     * @return
     */
    ProductCategory findById(int id);

    /**
     * create by: Bin Liu
     * description: 按分类查询所有分类
     * @Param: type
     * @return
     */
    List<ProductCategory> findAll(int type);

    /**
     * create by: Bin Liu
     * description: 按页查找所有分类
     * @Param: null
     * @return
     */
    Page<ProductCategory> findAll(int type,Pageable pageable);

    /**
     * create by: Bin Liu
     * description: 按条件查询
     * @Param: example
     * @return
     */
    List<ProductCategory> findAllExample(Example<ProductCategory> example);

    /**
     * create by: Bin Liu
     * description: 更新
     * @Param: productCategory
     * @return
     */
    void update(ProductCategory productCategory);

    /**
     * create by: Bin Liu
     * description: 创建
     * @Param: productCategory
     * @return
     */
    int create(ProductCategory productCategory);

    /**
     * create by: Bin Liu
     * description: 根据Id删除
     * @Param: id
     * @return
     */
    void delById(int id);

    /**
     * create by: Bin Liu
     * description: 通过一级分类id查找它所有的二级分类
     * @Param: cid
     * @return 
     */
    List<ProductCategory> findByParentId(int cid);
}
