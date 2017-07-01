package com.chinesejr.mapper.content;

import java.util.List;

import com.chinesejr.model.content.ContentModel;
import com.chinesejr.util.ChinesejrMapper;

public interface ContentMapper extends ChinesejrMapper<ContentModel> {

	List<ContentModel> selectAllOrderby(ContentModel model);

	Integer batchDeleteByIds(List<String> idList);

}
