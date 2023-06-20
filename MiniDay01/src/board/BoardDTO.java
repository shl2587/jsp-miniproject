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
	private String folder, filename;
	private Date writeDate;
	private String ipaddr;
}
