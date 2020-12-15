package exam06Interface02;

public class Audio implements RemoteControl {

	@Override
	public void turnOn() {
		System.out.println("-- Audio를 켭니다. --");
		
	}

	@Override
	public void turnOff() {
		System.out.println("-- Audio를 끕니다. --");
		
	}

	@Override
	public void setVolume(int i) {
		System.out.println("Audio의 볼륨을 " + i + "로 조정하세요.");
		
	}

}
