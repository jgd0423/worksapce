package exam06Interface02;

public class Main {
	public static void main(String[] args) {
//		Television tv = new Television();
//		tv.turnOn();
//		tv.setVolume(5);
//		tv.turnOff();
//		
//		Audio audio = new Audio();
//		audio.turnOn();
//		audio.setVolume(7);
//		audio.turnOff();
		
		RemoteControl rc = new Television();
		rc.turnOn();
		rc.setVolume(3);
		rc.turnOff();
		
		rc = new Audio();
		rc.turnOn();
		rc.setVolume(9);
		rc.turnOff();
	}
}
