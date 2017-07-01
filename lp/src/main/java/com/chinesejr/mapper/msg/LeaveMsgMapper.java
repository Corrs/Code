package com.chinesejr.mapper.msg;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.chinesejr.model.msg.LeaveMsgModel;
import com.chinesejr.util.ChinesejrMapper;

public interface LeaveMsgMapper extends ChinesejrMapper<LeaveMsgModel> {

	List<LeaveMsgModel> selectAllOrderby(LeaveMsgModel model);

	Integer batchDeleteByIds(List<String> idList);

	List<Map<String, String>> selectAllMtype(LeaveMsgModel model);

	void saveMap(@Param("model")Map<String, String> model);

	Integer batchDeleteMapByIds(List<String> idList);
}
