package exam06Interface02;

public class Television implements RemoteControl {

	@Override
	public void turnOn() {
		System.out.println("-- TV를 켭니다. --");
	}

	@Override
	public void turnOff() {
		System.out.println("-- TV를 끕니다. --");
	}

	@Override
	public void setVolume(int i) {
		if (i >= MIN_VALUE && i <= MAX_VALUE) {
			System.out.println("Television의 볼륨을 " + i + "로 조정하세요.");			
		} 
	}

}
