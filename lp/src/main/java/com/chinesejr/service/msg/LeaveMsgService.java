package com.chinesejr.service.msg;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.chinesejr.mail.EmailUtils;
import com.chinesejr.mapper.form.FormMapper;
import com.chinesejr.mapper.msg.LeaveMsgMapper;
import com.chinesejr.model.form.FormModel;
import com.chinesejr.model.msg.LeaveMsgModel;
import com.chinesejr.util.PageConvert;
import com.github.pagehelper.PageHelper;

@Service
@Transactional
public class LeaveMsgService {

	@Autowired
	private LeaveMsgMapper mapper;
	
	@Autowired
	private FormMapper formMapper;
	
	private static final SimpleDateFormat sdf = new SimpleDateFormat("yyy-MM-dd HH:mm:ss");
	public int deleteById(Integer id) throws Exception {
    	return mapper.deleteByPrimaryKey(id);
    }
	
	/*private int updateBasicInfo(LeaveMsgModel model) throws Exception {
		LeaveMsgModel LeaveMsgModel = mapper.selectByPrimaryKey(model.getId());
		Field[] fields = model.getClass().getDeclaredFields();
		for (int i = 0; i < fields.length; i++) {
			fields[i].setAccessible(true);
            Object value = fields[i].get(model);
            if (null != value) {
                fields[i].set(LeaveMsgModel, value);
            }
            fields[i].setAccessible(false);
		}
		return mapper.updateByPrimaryKey(LeaveMsgModel);
	}*/

    public int save(LeaveMsgModel model) throws Exception {
    	int count;
    	
    	if (model.getId() != null) {
    		count = mapper.updateByPrimaryKey(model);
    	} else {
    		model.setCreatedate(sdf.format(new Date()));
    		count = mapper.insert(model);
    		Thread thread = new MailThread(model);
    		thread.start();
    	}
    	
        return count;
    }
    
    public void saveMap(Map<String, String> model) throws Exception {
    	model.put("createdate", sdf.format(new Date()));
    	mapper.saveMap(model);
    	MailMapThread mailMapThread = new MailMapThread(model, formMapper.selectAll());
    	mailMapThread.start();
    }

	public List<LeaveMsgModel> query(LeaveMsgModel model) throws Exception {
		// TODO Auto-generated method stub
		List<LeaveMsgModel> result = new ArrayList<LeaveMsgModel>();
    	PageConvert.getOrderBy(model);
    	if (PageConvert.getPage(model) != null && PageConvert.getRows(model) != null) {
    		result = getPage(model);
    	} 
    	else {
    		result = getAll(model);
    	}
    	
    	return result;
	}
	
	public LeaveMsgModel getById(Integer id) throws Exception {
        return mapper.selectByPrimaryKey(id);
    }

	private List<LeaveMsgModel> getAll(LeaveMsgModel model) {
		// TODO Auto-generated method stub
		return mapper.selectAllOrderby(model);
	}

	private List<LeaveMsgModel> getPage(LeaveMsgModel model) {
		// TODO Auto-generated method stub
		PageHelper.startPage(model.getPage(), model.getRows());
    	return mapper.selectAllOrderby(model);
	}

	public Integer batchDeleteByIds(String ids) throws Exception {
		// TODO Auto-generated method stub
		List<String> idList = Arrays.asList(ids.split(","));
		return mapper.batchDeleteByIds(idList);
	}
	
	public Integer batchDeleteMapByIds(String ids) throws Exception {
		// TODO Auto-generated method stub
		List<String> idList = Arrays.asList(ids.split(","));
		return mapper.batchDeleteMapByIds(idList);
	}

	
	
	public List<Map<String, String>> queryMap(LeaveMsgModel model) {
		// TODO Auto-generated method stub
		PageConvert.getOrderBy(model);
		if (PageConvert.getPage(model) != null && PageConvert.getRows(model) != null) {
			PageHelper.startPage(model.getPage(), model.getRows());
		}
		return mapper.selectAllMtype(model);
	}
}

class MailThread extends Thread {
	private LeaveMsgModel model;
	
	public MailThread(LeaveMsgModel model) {
		// TODO Auto-generated constructor stub
		this.model = model;
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		StringBuffer b = new StringBuffer();
		b.append("姓名：").append(model.getName()).append("<br>");
		b.append("年龄：").append(model.getAge()==null?"":model.getAge()).append("<br>");
		b.append("电话：").append(model.getPhone()).append("<br>");
		b.append("微信：").append(model.getWechat()).append("<br>");
		b.append("QQ：").append(model.getQq()).append("<br>");
		b.append("地址：").append(model.getAddress()).append("<br>");
		b.append("留言：").append(model.getMsg()).append("<br>");
		
		new EmailUtils().sendMail("用户留言", b.toString());
	}
}

class MailMapThread extends Thread {
	private Map<String, String> model;
	private List<FormModel> formList;
	
	public MailMapThread(Map<String, String> model, List<FormModel> formList) {
		// TODO Auto-generated constructor stub
		this.model = model;
		this.formList = formList;
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		StringBuffer b = new StringBuffer();
		for (int i = 0; i < formList.size(); i++) {
			FormModel formModel = formList.get(i);
			b.append(formModel.getLabel()).append("：").append(model.get(formModel.getFieldname())).append("<br>");
		}
		
		new EmailUtils().sendMail("用户留言", b.toString());
	}
}
