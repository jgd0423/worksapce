package exam01;

public class Test {
	static void imsi(int[] nums) {
		nums[2] = 300;
		for (int i = 0; i < nums.length; i++) {
			System.out.println("메소드: " + nums[i]);
		}
	}
	
	public static void main(String[] args) {
		int[] nums = { 10, 20, 30, 40, 50 };
		imsi(nums); //imsi라는 메소드에 매개변수 nums를 넣어서 호출해라
		for (int i = 0; i < nums.length; i++) {
			System.out.println("메인: " + nums[i]);
		}
	}
}
