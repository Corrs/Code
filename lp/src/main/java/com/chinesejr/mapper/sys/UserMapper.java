package com.chinesejr.mapper.sys;

import java.util.List;

import com.chinesejr.model.sys.UserModel;
import com.chinesejr.util.ChinesejrMapper;

/**
 * 
 * @author BobyCo
 * @since 2017-06-12 11:01
 * 登录相关mapper
 *
 */
public interface UserMapper extends ChinesejrMapper<UserModel> {

	List<UserModel> selectAllOrderby(UserModel model);

	Integer batchDeleteByIds(List<String> idList);

	UserModel selectLoginUser(UserModel model);


}
