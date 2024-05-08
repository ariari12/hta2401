package javaz.oop;

public class Tire {
	private String maker;
	private String position;
	
	public Tire(String maker, String position) {
		this.maker = maker;
		this.position = position;
	}
	
	public void setTireInfo() {
		System.out.println(maker + "를 " + position + "에 장착했습니다.");
	}

	public String getMaker() {
		return maker;
	}

	public void setMaker(String maker) {
		this.maker = maker;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}
	
}
