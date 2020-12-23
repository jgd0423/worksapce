package iot;

public interface RemoteControl {
	public boolean on();
	public boolean off();
	public boolean isStatus();
	public void setStatus(boolean status);
}
