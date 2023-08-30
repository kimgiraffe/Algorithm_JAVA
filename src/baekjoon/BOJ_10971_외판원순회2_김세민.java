package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * BOJ_10971_외판원순회2
 * 
 * 1번부터 N(2이상 10이하)번 도시들 중 하나의 도시에 출발하여 중복 방문하지 않고 모든 도시를 방문하고 시작 도시로 돌아오는 경로의 최소 비용
 * 항상 순회할 수 있는 경우만 입력으로 주어짐...(사실 예외처리할 필요는 업음)
 * 
 * 1번을 시작 도시로 가정하자.
 * 1번을 제외한 다른 도시 i에 대하여  1번 도시에서 시작하여 i번 도시까지의 최소 비용을 cost(i)라 하자.
 * 순회를 하는 최소 비용은 cost(i) + weight(i, 1)가 된다.
 * 모든 cost(i) + weight(i, 1)에 대하여 최솟값을 반환한다.
 * C(s, i)를 1에서 시작하여 i로 끝나고 set S의 모든 정점을 방문하는 한 번씩만 방문하는 최소 비용이라고 하자.
 * C(s, i) = min(C(S-{i}), j) + weight(j, i))
 * 
 * @author semin.kim
 *
 */

public class BOJ_10971_외판원순회2_김세민 {

	static BufferedReader br;
	static StringTokenizer st;

	static int cityCount; // 도시의 수
	static int[][] weight; // 각 도시간 이동하는데 드는 비용
	static int[][] memo;

	static int minCost = Integer.MAX_VALUE;

	private static void TSP(int current, int visited) {
		// 모든 도시를 순회할 수 있는경유...
		if(visited == (1 << cityCount) - 1) {
			if(weight[current][0] != 0) { // 시작 도시로 다시 돌아올 수 있는 경우...
				minCost = Math.min(minCost, memo[current][visited] + weight[current][0]);
			}
			return;
		}

		for(int idx = 0; idx < cityCount; idx++) {
			/// 방문한적이 있거나 다음 도시로 갈 수 있는 경우...
			if((visited & (1 << idx)) == 0 && weight[current][idx] != 0) {
				if(memo[idx][visited | (1 << idx)] > memo[current][visited] + weight[current][idx]) {
					memo[idx][visited | (1 << idx)] = memo[current][visited] + weight[current][idx];

					// 다음 도시 방문
					TSP(idx, (visited | (1 << idx)));
				}
			}
		}
	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		br = new BufferedReader(new InputStreamReader(System.in));

		cityCount = Integer.parseInt(br.readLine().trim());

		weight = new int[cityCount][cityCount];
		memo = new int[cityCount][(1 << cityCount)];

		for(int curr = 0; curr < cityCount; curr++) {
			st = new StringTokenizer(br.readLine().trim());
			for(int next = 0; next < cityCount; next++) {
				weight[curr][next] = Integer.parseInt(st.nextToken());
			}
		}

		// memoization 배열  초기화
		for(int curr = 0; curr < cityCount; curr++) {
			Arrays.fill(memo[curr], Integer.MAX_VALUE);
		}
		memo[0][(1<<0)] = 0;
		TSP(0, (1<<0));
		System.out.println(minCost);
	}
}
