package com.chinesejr.service.content;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
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

import com.chinesejr.conf.StaticProperties;
import com.chinesejr.mapper.content.ContentMapper;
import com.chinesejr.model.content.ContentModel;
import com.chinesejr.util.PageConvert;
import com.github.pagehelper.PageHelper;

@Service
@Transactional
public class ContentService {
	@Autowired
	private ContentMapper mapper;
	
	@Autowired
	private StaticProperties sp;
	
	private static final SimpleDateFormat sdf = new SimpleDateFormat("yyy-MM-dd HH:mm:ss");
	private static final SimpleDateFormat nyr = new SimpleDateFormat("yyy-MM-dd");
	private static String nyrStr = nyr.format(new Date());
	
	public int deleteById(Integer id) throws Exception {
    	return mapper.deleteByPrimaryKey(id);
    }
	
	/*private int updateBasicInfo(ContentModel model) throws Exception {
		ContentModel contentModel = mapper.selectByPrimaryKey(model.getId());
		Field[] fields = model.getClass().getDeclaredFields();
		for (int i = 0; i < fields.length; i++) {
			fields[i].setAccessible(true);
            Object value = fields[i].get(model);
            if (null != value) {
                fields[i].set(contentModel, value);
            }
            fields[i].setAccessible(false);
		}
		return mapper.updateByPrimaryKey(contentModel);
	}*/

    public int save(ContentModel model) throws Exception {
    	int count;
    	
    	if (model.getId() != null) {
    		count = mapper.updateByPrimaryKey(model);
    	} else {
    		model.setCreatedate(sdf.format(new Date()));
    		count = mapper.insert(model);
    	}
    	
        return count;
    }

	public List<ContentModel> query(ContentModel model) throws Exception {
		// TODO Auto-generated method stub
		List<ContentModel> result = new ArrayList<ContentModel>();
    	PageConvert.getOrderBy(model);
    	if (PageConvert.getPage(model) != null && PageConvert.getRows(model) != null) {
    		result = getPage(model);
    	} 
    	else {
    		result = getAll(model);
    	}
    	
    	return result;
	}
	
	
	
	public ContentModel getById(Integer id) throws Exception {
        return mapper.selectByPrimaryKey(id);
    }

	private List<ContentModel> getAll(ContentModel model) {
		// TODO Auto-generated method stub
		return mapper.selectAllOrderby(model);
	}

	private List<ContentModel> getPage(ContentModel model) {
		// TODO Auto-generated method stub
		PageHelper.startPage(model.getPage(), model.getRows());
    	return mapper.selectAllOrderby(model);
	}

	public Integer batchDeleteByIds(String ids) throws Exception {
		// TODO Auto-generated method stub
		List<String> idList = Arrays.asList(ids.split(","));
		return mapper.batchDeleteByIds(idList);
	}

	public synchronized ContentModel uploadFile(MultipartHttpServletRequest request, ContentModel model) throws Exception {
		// TODO Auto-generated method stub
//		String imgPath = "";
		BufferedOutputStream bos = null;
		FileOutputStream fos = null;
		File newfile = null;
		ClassLoader loader = this.getClass().getClassLoader();
		String filePath = sp.getUpload();
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
				File dir = new File(filePath);
				if (!dir.exists() && !dir.isDirectory()) {// 判断文件目录是否存在
					dir.mkdirs();
				}
				long t = new Date().getTime();
				String newFilename = t + filename.substring(filename.lastIndexOf("."));
				newfile = new File(filePath + "/" + newFilename);
				fos = new FileOutputStream(newfile);
				bos = new BufferedOutputStream(fos);
				bos.write(bytes);
				model.setImage(filePath + "/" + newFilename);
				model.setRemark(filename);
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
}
