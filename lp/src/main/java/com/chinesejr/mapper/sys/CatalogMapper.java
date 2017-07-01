package com.chinesejr.mapper.sys;

import java.util.List;

import com.chinesejr.model.sys.CatalogModel;
import com.chinesejr.util.ChinesejrMapper;

public interface CatalogMapper extends ChinesejrMapper<CatalogModel> {

	List<CatalogModel> selectAllOrderby(CatalogModel model);

	String getMaxCodeByPcode(CatalogModel model);

	Integer batchDeleteByIds(List<String> idList);

}
