package com.lizikj.api.dto;

/**
 * 下载对象封装类
 * 
 * @author lijundong
 * @date 2017年11月3日 上午10:05:16
 */
public class DownLoadDTO {

	private String fileName;

	private byte[] bytes;

	public DownLoadDTO() {
		super();
	}

	public DownLoadDTO(String fileName, byte[] bytes) {
		super();
		this.fileName = fileName;
		this.bytes = bytes;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public byte[] getBytes() {
		return bytes;
	}

	public void setBytes(byte[] bytes) {
		this.bytes = bytes;
	}

}
