package com.scut.mall.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.scut.mall.dao.MessageDao;
import com.scut.mall.entity.Message;
import com.scut.mall.service.MessageService;
@Service
public class MessageServiceImpl implements MessageService{
	
	 @Autowired
	private MessageDao messageDao;
	
	@Override
	public Page<Message> findAll(Pageable pageable) {
		// TODO Auto-generated method stub
		return messageDao.findAll(pageable);
	}

	@Override
	public int create(Message message) {
		// TODO Auto-generated method stub
		return messageDao.save(message).getId();
	}

	@Override
	public void delById(int id) {
		
		messageDao.deleteById(id);
	}

	@Override
	public int findAllTotal() {
		return messageDao.findAllTotal();
	}

	@Override
	public List<Message> findByMessage(int proid) {
		return messageDao.findByMessage(proid);
	}


}
