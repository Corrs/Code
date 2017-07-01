package com.chinesejr.service.contact;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.chinesejr.mapper.contact.ContactMapper;
import com.chinesejr.model.contact.ContactModel;
import com.chinesejr.util.PageConvert;
import com.github.pagehelper.PageHelper;

@Service
@Transactional
public class ContactService {
	@Autowired
	private ContactMapper mapper;
	
	
	public int deleteById(Integer id) throws Exception {
    	return mapper.deleteByPrimaryKey(id);
    }
	
    public int save(ContactModel model) throws Exception {
    	int count;
    	
    	if (model.getId() != null) {
    		count = mapper.updateByPrimaryKey(model);
    	} else {
    		count = mapper.insert(model);
    	}
    	
        return count;
    }

	public List<ContactModel> query(ContactModel model) throws Exception {
		// TODO Auto-generated method stub
		List<ContactModel> result = new ArrayList<ContactModel>();
    	PageConvert.getOrderBy(model);
    	if (PageConvert.getPage(model) != null && PageConvert.getRows(model) != null) {
    		result = getPage(model);
    	} 
    	else {
    		result = getAll(model);
    	}
    	
    	return result;
	}
	
	
	
	public ContactModel getById(Integer id) throws Exception {
        return mapper.selectByPrimaryKey(id);
    }

	private List<ContactModel> getAll(ContactModel model) {
		// TODO Auto-generated method stub
		return mapper.selectAllOrderby(model);
	}

	private List<ContactModel> getPage(ContactModel model) {
		// TODO Auto-generated method stub
		PageHelper.startPage(model.getPage(), model.getRows());
    	return mapper.selectAllOrderby(model);
	}

	public Integer batchDeleteByIds(String ids) throws Exception {
		// TODO Auto-generated method stub
		List<String> idList = Arrays.asList(ids.split(","));
		return mapper.batchDeleteByIds(idList);
	}
}
