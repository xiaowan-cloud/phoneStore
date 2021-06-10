package com.scut.mall.controller.adminUser;


import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import com.scut.mall.entity.Message;
import com.scut.mall.entity.pojo.ResultBean;
import com.scut.mall.service.MessageService;


@Controller
@RequestMapping("/admin/message")
public class MessageController {
	@Autowired
	private MessageService messageService;
	
    @RequestMapping("/toList.html")
    public String toList() {
        return "admin/message/list";
    }
    

    @ResponseBody
    @RequestMapping("/list.do")
    public ResultBean<List<Message>> listMessage(int pageindex,
                                                 @RequestParam(value = "pageSize", defaultValue = "15") int pageSize) {
        Pageable pageable = PageRequest.of(pageindex, pageSize, Sort.by(Sort.Direction.ASC,"id"));
        List<Message> list = messageService.findAll(pageable).getContent();
        return new ResultBean<>(list);
    }

    @ResponseBody
    @RequestMapping("/getTotal.do")
    public ResultBean<Integer> getTotal() {
        Pageable pageable = new PageRequest(1, 15, null);
        int total = (int) messageService.findAllTotal();
        return new ResultBean<>(total);
    }

    @RequestMapping("/del.do")
    @ResponseBody
    public ResultBean<Boolean> del(int id) {
    	messageService.delById(id);
        return new ResultBean<>(true);
    }
    
    
   

}
