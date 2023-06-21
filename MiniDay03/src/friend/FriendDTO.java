package friend;

//		MEMBER    NOT NULL NUMBER        
//		USERNUM   NOT NULL NUMBER        
//		STATEMENT          NUMBER        


public class FriendDTO {
	
	private int member, usernum;
	private int statement;
	
	
	
	
	public int getMember() {
		return member;
	}
	public void setMember(int member) {
		this.member = member;
	}
	public int getUsernum() {
		return usernum;
	}
	public void setUsernum(int usernum) {
		this.usernum = usernum;
	}
	public int getStatement() {
		return statement;
	}
	public void setStatement(int statement) {
		this.statement = statement;
	}
	
}
