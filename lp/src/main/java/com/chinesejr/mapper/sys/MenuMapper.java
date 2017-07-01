package com.chinesejr.mapper.sys;

import java.util.List;

import com.chinesejr.model.sys.MenuModel;
import com.chinesejr.util.ChinesejrMapper;

/**
 * 
 * @author BobyCo
 * @since 2017-06-09 23:23
 * 菜单相关mapper
 *
 */
public interface MenuMapper extends ChinesejrMapper<MenuModel> {

	String getMaxCodeByPcode(MenuModel model);

	List<MenuModel> selectAllOrderby(MenuModel model);

	Integer batchDeleteByIds(List<String> idList);

}
