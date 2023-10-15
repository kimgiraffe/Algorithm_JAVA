package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.PriorityQueue;

/**
 * BOJ_1417_국회의원선거
 * 
 * 1. 다솜이를 제외한 나머지 후보들의 예상 득표 수를 우선순위 큐에 추가한다.
 * 2. 우선순위 큐에서 최댓값을 꺼내 다솜이의 예상 득표 수와 비교한다.
 * 3. 1 감소 시키고 우선순위 큐에 다시 삽입한다.
 * 4. 매수해야하는 사람의 수를 1 증가시킨다.
 * 5. 다솜이의 예상 득표 수를 1 증가시킨다.
 * 6. 다솜이가 가장 많은 예상 득표 수가 될 때까지 2~5를 반복한다.
 * 7. 매수해야하는 사람의 수를 출력한다.
 * 
 * @author semin.kim
 */

public class BOJ_1417_국회의원선거_김세민 {

	static BufferedReader br;
	static int candidateCount; // 후보의 수
	static int[] candidates;
	static int buyCount; // 매수해야하는 사람의 수
	static PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());

	private static void buy() {
		while(!pq.isEmpty() && pq.peek() >= candidates[1]) {
			int tmp = pq.poll() - 1; // 예상 득표수가 가장 많은 후보 수를 꺼내 1 감소
			pq.add(tmp); // 우선순위 큐에 삽입
			buyCount++; // 매수해야하는 사람의 수 1 증가
			candidates[1]++; // 다솜이의 예상 득표수 1 증가
		}
	}

	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		candidateCount = Integer.parseInt(br.readLine().trim());

		candidates = new int[candidateCount + 1];

		for(int idx = 1; idx <= candidateCount; idx++) {
			candidates[idx] = Integer.parseInt(br.readLine().trim());
			if(idx > 1) { // 다솜이를 제외한 후보를 찍으려는 사람 수를 우선순위큐에 삽입
				pq.offer(candidates[idx]);
			}
		}
		
		buy();
		
		System.out.println(buyCount);
	}
}
