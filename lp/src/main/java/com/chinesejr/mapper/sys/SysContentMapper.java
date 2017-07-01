package com.chinesejr.mapper.sys;

import java.util.List;

import com.chinesejr.model.sys.SysContentModel;
import com.chinesejr.util.ChinesejrMapper;

public interface SysContentMapper extends ChinesejrMapper<SysContentModel> {

	List<SysContentModel> selectAllOrderby(SysContentModel model);

	Integer batchDeleteByIds(List<String> idList);

	void updateAllNotInuse();

}
