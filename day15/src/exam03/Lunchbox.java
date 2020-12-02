package exam03;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;


/*
예제 입력
2        : 테스트 케이스의 수 T
3        : 참여자 수 N
2 2 2    : N개의 M -> i번째 도시락 데우는 시간(초)
2 2 2    : N개의 E -> i번째 도시락 먹는 시간(초)
3        : 참여자 수 N
1 2 3    : N개의 M -> i번째 도시락 데우는 시간(초)
1 2 1    : N개의 E -> i번째 도시락 먹는 시간(초)

예제 출력         : 최소화된 점심시간. 답은 2의 31제곱보다 작다.
8
7
*/

/*
전자레인지는 하나밖에 없다.
점심시간을 최소화하기 위해서는 전자레인지 사용 순서를 최적화해야한다.
점심시간은 전자레인지를 돌리기 시작하는 시간부터 밥을 다 먹는 시간까지이다.
전자레인지는 하나밖에 없기 때문에 도시락을 순서대로 돌려야하지만,
먹는 시간은 겹칠 수 있다. 꺼내자마자 먹으면 됨.
먹는 시간이 오래 걸리는 도시락을 먼저 돌리는 것이 유리하다. 동시에 여러 도시락 먹을 수 있으니.
먹는 시간을 내림차순으로 정렬해야하는데 microwaveTime도 같이 움직이게 정렬해야한다. 어떻게?
해시맵 키 기준 정렬, 벨류 기준 정렬 연습.
해시맵으로 묶으면 안된다.. 왜냐면 키값이 같을 수 있기 때문..
2차원배열로 일단 정렬은 해결.
 */

public class Lunchbox {
	static int count;
	static int peoples;
	static int[] microwaveTime;
	static int[] eatTime;
	
	public static void calcLunchbox() {
		int[][] lunches = new int[peoples][2];
		int microwavedTime = 0;
		int totalTime = 0;
		for (int i = 0; i < peoples; i++) {
			int[] temp = { microwaveTime[i], eatTime[i] };
			lunches[i] = temp;
		}

		Arrays.sort(lunches, (o1, o2) -> Integer.compare(o2[1], o1[1]));
		
		for (int i = 0; i < peoples; i++) {
			microwavedTime += lunches[i][0];
			totalTime = Math.max(totalTime, microwavedTime + lunches[i][1]);
		}

		System.out.println(totalTime);
	}
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		count = Integer.parseInt(br.readLine());

		for (int i = 0; i < count; i++) {
			peoples = Integer.parseInt(br.readLine());

			String[] microwaveTime_ = br.readLine().split(" ");
			microwaveTime = new int[peoples];
			for (int j = 0; j < microwaveTime_.length; j++) {
				microwaveTime[j] = Integer.parseInt(microwaveTime_[j]);
			}
			
			String[] eatTime_ = br.readLine().split(" ");
			eatTime = new int[peoples];
			for (int j = 0; j < eatTime_.length; j++) {
				eatTime[j] = Integer.parseInt(eatTime_[j]);
			}
			
			calcLunchbox();		
		}

	}

}
