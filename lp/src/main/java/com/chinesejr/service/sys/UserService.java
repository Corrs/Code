package com.chinesejr.service.sys;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.chinesejr.mapper.sys.UserMapper;
import com.chinesejr.model.sys.UserModel;
import com.chinesejr.util.Md5Utils;
import com.chinesejr.util.PageConvert;
import com.chinesejr.util.StringUtil;
import com.github.pagehelper.PageHelper;

@Service
@Transactional
public class UserService {
	@Autowired
	private UserMapper mapper;
	
	private static final SimpleDateFormat sdf = new SimpleDateFormat("yyy-MM-dd HH:mm:ss");
	
	public int deleteById(Integer id) throws Exception {
    	return mapper.deleteByPrimaryKey(id);
    }

	
	private int updateBasicInfo(UserModel model) throws Exception {
		UserModel userModel = mapper.selectByPrimaryKey(model.getId());
		Field[] fields = model.getClass().getDeclaredFields();
		for (int i = 0; i < fields.length; i++) {
			fields[i].setAccessible(true);
            Object value = fields[i].get(model);
            if (null != value) {
                fields[i].set(userModel, value);
            }
            fields[i].setAccessible(false);
		}
		return mapper.updateByPrimaryKey(userModel);
	}
	
    public int save(UserModel model) throws Exception {
    	int count = 0;
    	if (model.getId() != null) {
    		if(StringUtil.isEmpty(model.getPassword())) {
    			model.setPassword(null);
    		} else {
    			model.setPassword(Md5Utils.md5(model.getPassword()));
    		}
    		count = updateBasicInfo(model);
    	} else {
    		model.setPassword(Md5Utils.md5(model.getPassword()));
    		model.setCreatedate(sdf.format(new Date()));
    		count = mapper.insert(model);
    	}
        return count;
    }

	public List<UserModel> query(UserModel model) throws Exception {
		// TODO Auto-generated method stub
		List<UserModel> result = new ArrayList<UserModel>();
    	PageConvert.getOrderBy(model);
    	if (PageConvert.getPage(model) != null && PageConvert.getRows(model) != null) {
    		result = getPage(model);
    	} 
    	else {
    		result = getAll(model);
    	}
    	
    	return result;
	}
	
	
	
	public UserModel getById(Integer id) throws Exception {
        return mapper.selectByPrimaryKey(id);
    }

	private List<UserModel> getAll(UserModel model) {
		// TODO Auto-generated method stub
		return mapper.selectAllOrderby(model);
	}

	private List<UserModel> getPage(UserModel model) {
		// TODO Auto-generated method stub
		PageHelper.startPage(model.getPage(), model.getRows());
    	return mapper.selectAllOrderby(model);
	}
	
	public Integer batchDeleteByIds(String ids) throws Exception {
		// TODO Auto-generated method stub
		List<String> idList = Arrays.asList(ids.split(","));
		return mapper.batchDeleteByIds(idList);
	}

	public boolean checkUser(UserModel model) throws Exception {
		// TODO Auto-generated method stub
		return mapper.selectOne(model) != null;
	}

	/*public boolean checkMobileExsit(UserModel model) throws Exception {
		// TODO Auto-generated method stub
		return mapper.selectOne(model) != null;
	}*/

	public UserModel selectLoginUser(UserModel model) throws Exception {
		// TODO Auto-generated method stub
		model.setPassword(Md5Utils.md5(model.getPassword()));
		return mapper.selectLoginUser(model);
	}

	public UserModel registerUser(UserModel model) throws Exception {
		// TODO Auto-generated method stub
		model.setPassword(Md5Utils.md5(model.getPassword()));
		model.setCreatedate(sdf.format(new Date()));
		
		mapper.insert(model);
		return model;
	}
	
	public UserModel updatePwd(UserModel model) throws Exception {
		model.setPassword(Md5Utils.md5(model.getPassword()));
		model.setCreatedate(sdf.format(new Date()));
		mapper.updateByPrimaryKey(model);
		return model;
	}


	public synchronized UserModel uploadFile(MultipartHttpServletRequest request, UserModel model) throws Exception {
		// TODO Auto-generated method stub
//		String imgPath = "";
		BufferedOutputStream bos = null;
		FileOutputStream fos = null;
		File newfile = null;
		ClassLoader loader = this.getClass().getClassLoader();
		String filePath = "static/img/userImgs/" + model.getUsername();
		String realPath = loader.getResource("").getPath() + filePath;
		Iterator<String> itr = request.getFileNames();
		try {
			while (itr.hasNext()) {
				String uploadedFile = itr.next();
				MultipartFile file = request.getFile(uploadedFile);
//				String mimeType = file.getContentType();
				String filename = file.getOriginalFilename();
				byte[] bytes = file.getBytes();
				
	//			FileUploadVO newFile = new FileUploadVO(filename, bytes, mimeType);
				File dir = new File(realPath);
				if (!dir.exists() && !dir.isDirectory()) {// 判断文件目录是否存在
					dir.mkdirs();
				}
				long t = new Date().getTime();
				String newFilename = t + filename.substring(filename.lastIndexOf("."));
				newfile = new File(realPath + File.separator + newFilename);
				fos = new FileOutputStream(newfile);
				bos = new BufferedOutputStream(fos);
				bos.write(bytes);
				model.setImage(filePath + File.separator + newFilename);
				this.save(model);
			}
		} finally {
			if (bos != null) {
				try {
					bos.close();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
			if (fos != null) {
				try {
					fos.close();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		}
		
		return model;
	}

	/**
	 * 根据用户名 获得自定义的图片列表
	 * @param username
	 * @return
	 * @throws Exception
	 */
	public List<String> getCustomImg(String username) throws Exception {
		// TODO Auto-generated method stub
		List<String> imgList = new ArrayList<>();
		String filePath = "static/img/userImgs/" + username;
		File[] files = getFiles(filePath);
		
		for(int i=0; files != null && i < files.length; i++) {
			File file = files[i];
			imgList.add("/" + filePath + "/" + file.getName());
		}
		
		return imgList;
	}

	/**
	 * 获得推荐默认图片列表
	 * @return
	 * @throws Exception
	 */
	public List<String> getDefaultImg() throws Exception {
		// TODO Auto-generated method stub
		List<String> imgList = new ArrayList<>();
		String filePath = "static/img/defaultImgs/";
		File[] files = getFiles(filePath);
		
		for(int i=0; files != null && i < files.length; i++) {
			File file = files[i];
			if (file.isFile() && file.getName().endsWith(".jpg")) {
				imgList.add("/" + filePath + file.getName());
			}
		}
		
		return imgList;
	}
	
	
	private File[] getFiles(String filePath) {
		File[] files = null;
		ClassLoader loader = this.getClass().getClassLoader();
		String realPath = loader.getResource("").getPath() + filePath;
		
		File dir = new File(realPath);
		if (dir.exists() && dir.isDirectory()) {// 判断文件目录是否存在
			files = dir.listFiles();
		}
		
		return files;
	}
}
