package exam06Interface02;

// 인터페이스 사용
// 인터페이스는 타입으로서 사용되는 곳이면 어디서는 사용이 가능하다
// 인터페이스는 필드, 매개변수, 로컬변수의 타입으로 선언가능
// 구현 객체를 대입해서 사용한다
// 인터페이스타입 변수 = 구현객체;

public interface RemoteControl {
	public static final int MIN_VALUE = 0;
	public static final int MAX_VALUE = 10;
	
	void turnOn();
	void turnOff();
	void setVolume(int i);
}
