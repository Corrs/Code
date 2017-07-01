package com.chinesejr.vo.util;

public class FileUploadVO {
	private byte[] file;
	private String filename;
	private String mimeType;
	public FileUploadVO() {
		// TODO Auto-generated constructor stub
	}
	public FileUploadVO(String filename, byte[] file, String mimeType) {
		super();
		this.filename = filename;
		this.file = file;
		this.mimeType = mimeType;
	}
	public byte[] getFile() {
		return file;
	}
	public String getFilename() {
		return filename;
	}
	public String getMimeType() {
		return mimeType;
	}
	public void setFile(byte[] file) {
		this.file = file;
	}
	public void setFilename(String filename) {
		this.filename = filename;
	}
	public void setMimeType(String mimeType) {
		this.mimeType = mimeType;
	}
}
