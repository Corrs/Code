package com.chinesejr.service.form;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.chinesejr.mapper.form.FormMapper;
import com.chinesejr.model.form.FormModel;
import com.chinesejr.util.PageConvert;
import com.github.pagehelper.PageHelper;

@Service
@Transactional
public class FormService {
	
	@Autowired
	private FormMapper mapper;
	
	private static final SimpleDateFormat sdf = new SimpleDateFormat("yyy-MM-dd HH:mm:ss");
	public int deleteById(Integer id) throws Exception {
		mapper.alterDropColumn(mapper.selectByPrimaryKey(id));
    	
		return mapper.deleteByPrimaryKey(id);
    }
	
	public int save(FormModel model) throws Exception {
    	int count;
    	
    	Integer fieldtype = model.getFieldtype();
    	switch (fieldtype) {
		case 1:
		case 2:
			model.setFieldTypeName("varchar(100)");
			break;

		case 3:
			model.setFieldTypeName("varchar(19)");
			break;
			
		case 4:
			model.setFieldTypeName("varchar(500)");
			break;	
			
		default:
			break;
		}
    	
    	if (model.getId() != null) {
    		count = mapper.updateByPrimaryKey(model);
    		mapper.alterEditColumn(model);
    	} else {
    		model.setCreatedate(sdf.format(new Date()));
    		model.setSn((getMaxSn()==null?0:getMaxSn())+1);
    		count = mapper.insert(model);
    		mapper.alterAddColumn(model);
    	}
    	
        return count;
    }
	
	public List<FormModel> query(FormModel model) throws Exception {
		// TODO Auto-generated method stub
		List<FormModel> result = new ArrayList<FormModel>();
    	PageConvert.getOrderBy(model);
    	if (PageConvert.getPage(model) != null && PageConvert.getRows(model) != null) {
    		result = getPage(model);
    	} 
    	else {
    		result = getAll(model);
    	}
    	
    	return result;
	}
	
	
	
	public FormModel getById(Integer id) throws Exception {
        return mapper.selectByPrimaryKey(id);
    }

	private List<FormModel> getAll(FormModel model) {
		// TODO Auto-generated method stub
		return mapper.selectAllOrderby(model);
	}

	private List<FormModel> getPage(FormModel model) {
		// TODO Auto-generated method stub
		PageHelper.startPage(model.getPage(), model.getRows());
    	return mapper.selectAllOrderby(model);
	}
	
	private Integer getMaxSn() {
		return mapper.getMaxSn();
	}
	
	public boolean validFieldname(String fieldname, Integer id) throws Exception {
		FormModel model = new FormModel();
		model.setFieldname(fieldname);
		FormModel formModel = mapper.selectOne(model);
		return formModel==null || formModel.getId()==id;
	}
	
	public Integer batchDeleteByIds(String ids) throws Exception {
		// TODO Auto-generated method stub
		List<String> idList = Arrays.asList(ids.split(","));
		return mapper.batchDeleteByIds(idList);
	}
}
