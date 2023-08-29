package swea;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * SWEA_1225_암호생성기
 * 8개의 숫자를 입력받는다.
 * 첫 번째 숫자를 1감소한 뒤, 맨 뒤로 보낸다.
 * 다음 첫 번째 숫자는 2 감소한 뒤, 맨 뒤로...
 * 이와 같은 과정을 한 사이클이라 한다.
 * 숫자가 감소할 때 0보다 작이지는 경우... 프로그램 종료
 * 
 * 8개의 숫자를 차례대로 큐에 삽입한다.
 * 큐의 가장 첫번쨰 원소를 꺼내 감소시킨 뒤, 
 * 첫번째 원소가 0이하인 경우... 큐에 맨 끝에 0을 삽입하고 중단한다.
 * 첫번째 원소가 0보다 큰 경우... 큐의 맨 끝에 감소시킨 숫자를 삽입한다.
 * @author semin.kim
 *
 */

public class SWEA_1225_암호생성기_김세민 {

	static BufferedReader br;
	static StringTokenizer st;
	static StringBuilder sb;

	static final int NUM_COUNT = 8; // 입력받는 숫자의 개수는 8로 고정
	static final int T = 10; // 테스트케이스의 개수는 10으로 고정
	static Queue<Integer> queue = new LinkedList<>();

	private static void makePassword() {
		int decrement = 1; // 감소할 숫자
		
		while(true) {
			int first = queue.poll() - decrement; // 첫번째 원소 감소시키기
			if(first <= 0) { // 0이하인 경우...
				queue.offer(0); // 큐에 0 삽입
				return; // 중단
			}
			queue.offer(first); // 큐에 감소시킨 숫자 삽입
			decrement = decrement % 5 + 1; // 다음 감소할 숫자 갱신
		}
	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		br = new BufferedReader(new InputStreamReader(System.in));

		for(int testCase = 1; testCase <= T; testCase++) {
			br.readLine().trim();
			queue.clear();
			st = new StringTokenizer(br.readLine().trim(), " ");

			for(int idx = 0; idx < NUM_COUNT; idx++) {
				queue.offer(Integer.parseInt(st.nextToken()));
			}

			sb = new StringBuilder();
			sb.append("#").append(testCase).append(" ");
			makePassword();
			
			for(int num : queue) {
				sb.append(num).append(" ");
			}

			System.out.println(sb);
		}
	}
}
