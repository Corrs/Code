package com.chinesejr.mapper.form;

import java.util.List;

import com.chinesejr.model.form.FormModel;
import com.chinesejr.util.ChinesejrMapper;

public interface FormMapper extends ChinesejrMapper<FormModel> {
	Integer getMaxSn();

	Integer batchDeleteByIds(List<String> idList);

	List<FormModel> selectAllOrderby(FormModel model);

	void alterAddColumn(FormModel model);

	void alterEditColumn(FormModel model);

	void alterDropColumn(FormModel model);
}
