package memberz.vo;

import java.util.Date;

public class MemberVO {
	private static MemberVO instance;
	private String id;
	private String pw;
	private String name;
	private String email;
	private String photo;
	private String gender;
	private Date joinDate;
	private boolean isAdmin;
	
	private MemberVO() {  };
	
	public MemberVO(String id, String pw, String name, String email, String photo,
					String gender) {
		this.id = id;
		this.pw = pw;
		this.name = name;
		this.email = email;
		this.photo = photo;
		this.gender = gender;
	}
	
	public static MemberVO getInstance() {
		if (instance ==  null) { 
			instance = new MemberVO(); 
			}
		return instance;
	}
	
	public String getId() {
		return id;
	}


	public void setId(String id) {
		this.id = id;
	}

	public String getPw() {
		return pw;
	}

	public void setPw(String pw) {
		this.pw = pw;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public Date getJoinDate() {
		return joinDate;
	}

	public void setJoinDate(Date joinDate) {
		this.joinDate = joinDate;
	}

	public boolean getAdmin() {
		return isAdmin;
	}

	public void setAdmin(boolean isAdmin) {
		this.isAdmin = isAdmin;
	}
}
