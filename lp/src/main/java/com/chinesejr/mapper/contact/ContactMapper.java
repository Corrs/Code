package com.chinesejr.mapper.contact;

import java.util.List;

import com.chinesejr.model.contact.ContactModel;
import com.chinesejr.util.ChinesejrMapper;

public interface ContactMapper extends ChinesejrMapper<ContactModel> {

	List<ContactModel> selectAllOrderby(ContactModel model);

	Integer batchDeleteByIds(List<String> idList);

}
