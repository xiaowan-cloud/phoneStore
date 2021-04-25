package com.scut.mall.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.scut.mall.entity.Message;
import org.springframework.data.jpa.repository.Query;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.List;


public interface MessageDao extends JpaRepository<Message, Integer> {

    @Query(value = "SELECT COUNT(*) from message;",nativeQuery = true)
    int findAllTotal();

    @Query(value = "SELECT * FROM message where proid=?1 order by createtime desc ",nativeQuery = true)
    List<Message> findByMessage(int proid);
}
