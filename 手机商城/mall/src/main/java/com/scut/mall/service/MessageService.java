package com.scut.mall.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.scut.mall.entity.Message;

import java.util.List;


public interface MessageService {
	/**
     * 分页查询所有
     *
     * @param pageable
     * @return
     */
    Page<Message> findAll(Pageable pageable);
    
    /**
     * 创建
     *
     * @param product
     * @return
     */
    int create(Message message);

    /**
     * 根据Id删除
     *
     * @param id
     * @return
     */
    void delById(int id);

    int findAllTotal();

    List<Message> findByMessage(int proid);
}
