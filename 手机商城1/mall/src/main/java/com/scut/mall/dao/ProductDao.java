package com.scut.mall.dao;

import com.scut.mall.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;
import java.util.List;

/**
 * @ Author     ：Bin Liu
 * @ Description：产品数据访问层接口
 * @ Modified By：
 */
public interface ProductDao extends JpaRepository<Product, Integer> {

    /**
     * create by: Bin Liu
     * description: 通过id获得产品信息
     * @Param: null
     * @return 
     */
    Product findProductById(Integer id);
    
    /**
     * create by: Bin Liu
     * description: 通过二级分类查找商品列表
     * @param csId
     * @param pageable
     * @return
     */
    List<Product> findByCsId(int csId, Pageable pageable);

    @Query(value = "SELECT * FROM  product",nativeQuery = true)
    List<Product> findAll();

    List<Product> findByCsIdIn(List<Integer> csIds, Pageable pageable);

    /**
     * create by: Bin Liu
     * description: 通过标题搜索商品
     * @param keyword
     * @return
     */
    List<Product> findByTitleIsLike(String keyword);

    /**
     * create by: Bin Liu
     * description: 查找某个日期之后上架的商品
     * @param date
     * @param pageable
     * @return
     */
    List<Product> findByDateAfter(Date date, Pageable pageable);

    /**
     * create by: Bin Liu
     * description: 查找热门商品
     * @param isHot
     * @param pageable
     * @return
     */
    List<Product> findByIsHot(int isHot,Pageable pageable);

    /**
     * create by: Bin Liu
     * description: 查询所有商品，最近上新的24个商品
     * @param pageable
     * @return
     */
    @Query(value = "SELECT * FROM (SELECT  * FROM product ORDER BY date DESC limit 0,24) a /*#pageable*/",nativeQuery = true)
    List<Product> findNew(Pageable pageable);
}
