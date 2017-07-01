package com.chinesejr.service.sys;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.chinesejr.mapper.sys.CatalogMapper;
import com.chinesejr.model.sys.CatalogModel;
import com.chinesejr.util.CodeUtils;
import com.chinesejr.util.PageConvert;
import com.chinesejr.util.StringUtil;
import com.github.pagehelper.PageHelper;

@Service
@Transactional
public class CatalogService {
	@Autowired
	private CatalogMapper mapper;
	private static final SimpleDateFormat sdf = new SimpleDateFormat("yyy-MM-dd HH:mm:ss");
	
	public int deleteById(Integer id) throws Exception {
    	return mapper.deleteByPrimaryKey(id);
    }
	public int save(CatalogModel model) throws Exception {
    	int count;
    	
    	if (model.getId() != null) {
    		count = mapper.updateByPrimaryKey(model);
    	} else {
    		model.setCode(this.getCode(model));
    		model.setCreatedate(sdf.format(new Date()));
    		model.setSn(Integer.parseInt(model.getCode()));
    		count = mapper.insert(model);
    	}
    	
        return count;
    }

	public List<CatalogModel> query(CatalogModel model) throws Exception {
		// TODO Auto-generated method stub
		List<CatalogModel> result = new ArrayList<CatalogModel>();
    	PageConvert.getOrderBy(model);
    	if (PageConvert.getPage(model) != null && PageConvert.getRows(model) != null) {
    		result = getPage(model);
    	} 
    	else {
    		result = getAll(model);
    	}
    	
    	return result;
	}
	
	
	
	public CatalogModel getById(Integer id) throws Exception {
        return mapper.selectByPrimaryKey(id);
    }

	private List<CatalogModel> getAll(CatalogModel model) {
		// TODO Auto-generated method stub
		return mapper.selectAllOrderby(model);
	}

	private List<CatalogModel> getPage(CatalogModel model) {
		// TODO Auto-generated method stub
		PageHelper.startPage(model.getPage(), model.getRows());
    	return mapper.selectAllOrderby(model);
	}
	
	
	private String getCode(CatalogModel model) {
		String code = mapper.getMaxCodeByPcode(model);
		int i = 1;
		if(StringUtil.isNotEmpty(code)) {
			if(code.length() > 3) {
				code = code.substring(code.length()-3);
			} 
			i = Integer.parseInt(code) + 1;
		}
		return CodeUtils.autoGenericCode((StringUtil.isEmpty(code))?1:i, 3);
	}

	public Integer batchDeleteByIds(String ids) throws Exception {
		// TODO Auto-generated method stub
		List<String> idList = Arrays.asList(ids.split(","));
		return mapper.batchDeleteByIds(idList);
	}
}
