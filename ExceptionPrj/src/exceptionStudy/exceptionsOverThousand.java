package exceptionStudy;

public class exceptionsOverThousand extends RuntimeException {
	@Override
	public String getMessage() {
		return "입력 값의 합이 1000이 넘는 오류가 발생했습니다.";
	}
}
