package com.scut.mall.dao;

import com.scut.mall.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserDao extends JpaRepository<User,Integer> {
    /*
     * 根据用户名，密码查询用户
     * @param username
     * @param password
     * @return
     */
    User findByUserNameAndAndPassword(String userName,String password);

    /*
     *根据用户名查询用户
     * @param userName
     * @return
     */

    List<User> findByUserName(String userName);

    /**
     * create by: Bin Liu
     * description:
     * @Param: null
     * @return
     */
    void deleteById(int id);

    /**
     * create by: Bin Liu
     * description: 通过id找到User
     * @Param: null
     * @return
     */
    User findUserById(Integer id);
}
