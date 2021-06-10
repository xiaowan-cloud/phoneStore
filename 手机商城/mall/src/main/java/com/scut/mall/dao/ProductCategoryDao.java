package com.scut.mall.dao;

import com.scut.mall.entity.ProductCategory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @ Author     ：Bin Liu
 * @ Description：产品数据访问层接口
 * @ Modified By：
 */
public interface ProductCategoryDao extends JpaRepository<ProductCategory, Integer> {
    /**
     * create by: Bin Liu
     * description: 通过id
     * @Param: null
     * @return 
     */
    ProductCategory findProductCategoryById(int id);
    /**
     * create by: Bin Liu
     * description: 通过类型查询分类
     * @Param: type
     * @return 
     */
    List<ProductCategory> findByType(int type);

    /**
     * create by: Bin Liu
     * description: 通过类型查询分类
     * @Param: type
     * @Param: pageable
     * @return 
     */
    Page<ProductCategory> findProductCategoryByType(int type, Pageable pageable);

    /**
     * create by: Bin Liu
     * description: 通过父类id查询分类
     * @Param: cid
     * @return
     */
    List<ProductCategory> findByParentId(int cid);
}
