package board;

import java.sql.Date;

//		IDX       NOT NULL NUMBER         
//		TITLE     NOT NULL VARCHAR2(100)  
//		WRITER    NOT NULL NUMBER         
//		CONTENT   NOT NULL VARCHAR2(2000) 
//		FOLDER             VARCHAR2(100)  
//		FILENAME           VARCHAR2(50)   
//		WRITEDATE NOT NULL DATE           
//		IPADDR    NOT NULL VARCHAR2(500) 



public class BoardDTO {
	
	private int idx;
	private String title;
	private int writer;
	private String content;
	private String folder, fileName;
	private Date writeDate;
	private String ipaddr;
	
	
	public int getIdx() {
		return idx;
	}
	public void setIdx(int idx) {
		this.idx = idx;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public int getWriter() {
		return writer;
	}
	public void setWriter(int writer) {
		this.writer = writer;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getFolder() {
		return folder;
	}
	public void setFolder(String folder) {
		this.folder = folder;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public Date getWriteDate() {
		return writeDate;
	}
	public void setWriteDate(Date writeDate) {
		this.writeDate = writeDate;
	}
	public String getIpaddr() {
		return ipaddr;
	}
	public void setIpaddr(String ipaddr) {
		this.ipaddr = ipaddr;
	}
	
}
