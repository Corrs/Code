package com.chinesejr.service.sys;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.chinesejr.mapper.sys.MenuMapper;
import com.chinesejr.model.sys.MenuModel;
import com.chinesejr.util.CodeUtils;
import com.chinesejr.util.PageConvert;
import com.chinesejr.util.StringUtil;
import com.chinesejr.vo.util.TreeNodeVO;
import com.github.pagehelper.PageHelper;

@Service
@Transactional
public class MenuService {
	@Autowired
	private MenuMapper mapper;
	
	public int deleteById(Integer id) throws Exception {
    	return mapper.deleteByPrimaryKey(id);
    }

    public int save(MenuModel model) throws Exception {
    	int count;
    	
    	if (StringUtil.isEmpty(model.getPcode())) {
    		model.setPcode(null);
    	}
    	
    	if (model.getId() != null) {
    		count = mapper.updateByPrimaryKey(model);
    	} else {
    		String code = StringUtil.isEmpty(model.getPcode()) ? "" : model.getPcode();
    		model.setCode(code+this.getCode(model));
    		count = mapper.insert(model);
    	}
    	
        return count;
    }

	public List<MenuModel> query(MenuModel model) throws Exception {
		// TODO Auto-generated method stub
		List<MenuModel> result = new ArrayList<MenuModel>();
    	PageConvert.getOrderBy(model);
    	if (PageConvert.getPage(model) != null && PageConvert.getRows(model) != null) {
    		result = getPage(model);
    	} 
    	else {
    		result = getAll(model);
    	}
    	
    	return result;
	}
	
	
	
	public MenuModel getById(Integer id) throws Exception {
        return mapper.selectByPrimaryKey(id);
    }

	private List<MenuModel> getAll(MenuModel model) {
		// TODO Auto-generated method stub
		return mapper.selectAllOrderby(model);
	}

	private List<MenuModel> getPage(MenuModel model) {
		// TODO Auto-generated method stub
		PageHelper.startPage(model.getPage(), model.getRows());
    	return mapper.selectAllOrderby(model);
	}
	
	
	private String getCode(MenuModel model) {
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

	public TreeNodeVO queryTree(MenuModel model) throws Exception {
		// TODO Auto-generated method stub
		/*MenuModel model = new MenuModel();
		model.setPcode("");*/
		Map<String, TreeNodeVO> map = new HashMap<String, TreeNodeVO>();
		model.setOrderBy("CODE ASC");
		List<MenuModel> all = mapper.selectAllOrderby(model);
		TreeNodeVO vo = new TreeNodeVO();
		vo.setCode(null);
		vo.setText("系统菜单");
		map.put(null, vo);
		
		for (int i=0; i<all.size(); i++) {
			MenuModel menuModel = all.get(i);
			TreeNodeVO nodeVO = new TreeNodeVO();
			map.put(menuModel.getCode(), nodeVO);
			String pcode = menuModel.getPcode();
			TreeNodeVO pNodeVO = map.get(pcode);
//			System.out.println(pNodeVO);
			if(pNodeVO != null) {
				pNodeVO.getNodes().add(nodeVO);
			}
			nodeVO.setCode(menuModel.getCode());
			nodeVO.setText(menuModel.getName());
			nodeVO.setMenuIcon(menuModel.getIcon());
			nodeVO.setUrl(menuModel.getPath());
		}
		
		return map.get(null);
	}

	public Integer batchDeleteByIds(String ids) throws Exception {
		// TODO Auto-generated method stub
		List<String> idList = Arrays.asList(ids.split(","));
		return mapper.batchDeleteByIds(idList);
	}
}
