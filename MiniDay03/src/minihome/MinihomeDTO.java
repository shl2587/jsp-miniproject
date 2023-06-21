package minihome;

import java.sql.Date;

//		IDX         NOT NULL NUMBER         
//		BOARD                NUMBER         
//		REPLY                NUMBER         
//		CONTENT              VARCHAR2(2000) 
//		TITLE       NOT NULL VARCHAR2(20)   
//		TODAYVISIT  NOT NULL NUMBER         
//		TOTALVISIT  NOT NULL NUMBER         
//		LASTCONNECT          DATE  

public class MinihomeDTO {
	
	private int idx;
	private int board, reply;
	private String content, title;
	private int todayVisit, totalVisit;
	private Date lastConnect;
	
	
	
	public int getIdx() {
		return idx;
	}
	public void setIdx(int idx) {
		this.idx = idx;
	}
	public int getBoard() {
		return board;
	}
	public void setBoard(int board) {
		this.board = board;
	}
	public int getReply() {
		return reply;
	}
	public void setReply(int reply) {
		this.reply = reply;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public int getTodayVisit() {
		return todayVisit;
	}
	public void setTodayVisit(int todayVisit) {
		this.todayVisit = todayVisit;
	}
	public int getTotalVisit() {
		return totalVisit;
	}
	public void setTotalVisit(int totaVisit) {
		this.totalVisit = totaVisit;
	}
	public Date getLastConnect() {
		return lastConnect;
	}
	public void setLastConnect(Date lastConnect) {
		this.lastConnect = lastConnect;
	}
	
}
