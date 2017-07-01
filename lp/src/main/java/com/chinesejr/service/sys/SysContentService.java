package com.chinesejr.service.sys;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang.StringEscapeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.chinesejr.mapper.sys.SysContentMapper;
import com.chinesejr.model.sys.SysContentModel;
import com.chinesejr.util.PageConvert;
import com.chinesejr.util.StringUtil;
import com.github.pagehelper.PageHelper;

@Service
@Transactional
public class SysContentService {
	@Autowired
	private SysContentMapper mapper;
	
	public int deleteById(Integer id) throws Exception {
		
		return mapper.deleteByPrimaryKey(id);
    }
	public int save(SysContentModel model) throws Exception {
    	int count;
    	
    	if(StringUtil.isNotEmpty(model.getInuse()) && model.getInuse() == 1) {
    		mapper.updateAllNotInuse();
    	}
    
    	if(StringUtil.isNotEmpty(model.getFootercontent())) {
    		model.setFootercontent(StringEscapeUtils.escapeHtml(model.getFootercontent()));
    	}
    	
    	if (model.getId() != null) {
    		count = mapper.updateByPrimaryKey(model);
    	} else {
    		count = mapper.insert(model);
    	}
    	
        return count;
    }

	public List<SysContentModel> query(SysContentModel model) throws Exception {
		// TODO Auto-generated method stub
		List<SysContentModel> result = new ArrayList<SysContentModel>();
    	PageConvert.getOrderBy(model);
    	if (PageConvert.getPage(model) != null && PageConvert.getRows(model) != null) {
    		result = getPage(model);
    	} 
    	else {
    		result = getAll(model);
    	}
    	
    	
    	for (SysContentModel sysContentModel : result) {
    		unescapeHtml(sysContentModel);
		}
    	
    	
    	return result;
	}
	
	
	
	public SysContentModel getById(Integer id) throws Exception {
		SysContentModel model = mapper.selectByPrimaryKey(id);
		unescapeHtml(model);
        return model;
    }
	
	private void unescapeHtml(SysContentModel model) {
		if(StringUtil.isNotEmpty(model.getFootercontent())) {
			model.setFootercontent(StringEscapeUtils.unescapeHtml(model.getFootercontent()));
		}
	}

	private List<SysContentModel> getAll(SysContentModel model) {
		// TODO Auto-generated method stub
		return mapper.selectAllOrderby(model);
	}

	private List<SysContentModel> getPage(SysContentModel model) {
		// TODO Auto-generated method stub
		PageHelper.startPage(model.getPage(), model.getRows());
    	return mapper.selectAllOrderby(model);
	}

	public Integer batchDeleteByIds(String ids) throws Exception {
		List<String> idList = Arrays.asList(ids.split(","));
		return mapper.batchDeleteByIds(idList);
	}
}
