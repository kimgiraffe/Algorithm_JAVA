package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * BOJ_1697_숨바꼭질
 * [문제]
 * 수빈이는 동생과 숨바꼭질을 하고 있다. 
 * 수빈이는 현재 점 N(0 ≤ N ≤ 100,000)에 있고, 동생은 점 K(0 ≤ K ≤ 100,000)에 있다. 
 * 수빈이는 걷거나 순간이동을 할 수 있다. 
 * 만약, 수빈이의 위치가 X일 때 걷는다면 1초 후에 X-1 또는 X+1로 이동하게 된다. 
 * 순간이동을 하는 경우에는 1초 후에 2*X의 위치로 이동하게 된다.
 * 수빈이와 동생의 위치가 주어졌을 때, 수빈이가 동생을 찾을 수 있는 가장 빠른 시간이 몇 초 후인지 구하는 프로그램을 작성하시오.
 * 
 * 수빈이의 위치를 큐에 삽입
 * 각 시간마다 -1, +1, *2로 이동해보고 동생을 찾을 때까지 시간 1 증가
 * 동생을 찾은 경우 시간 출력
 * 
 * @author semin.kim
 *
 */

public class BOJ_1697_숨바꼭질_김세민 {

	static BufferedReader br;
	static StringTokenizer st;

	static int subin, sister; // 수빈이와 동생의 위치
	static int time; // 수빈이가 동생을 찾을 수 있는 가장 빠른 시간
	static boolean[] visited = new boolean[200_001];

	public static void bfs(int pos) {
		Queue<Integer> queue = new LinkedList<Integer>();

		queue.add(pos); // 수빈이의 위치 큐에 삽입

		while(true) {
			int size = queue.size();
			for(int idx = 0; idx < size; idx++) {
				int curr = queue.poll();

				if(curr == sister) { // 동생을 찾은 경우...
					return;
				}
				if(curr > 0 && !visited[curr-1]) { // X - 1로 이동해보기
					queue.add(curr - 1);
					visited[curr-1] = true; // 방문처리
				}
				if(curr <= 100_000 && !visited[curr+1]) { // X + 1로 이동해보기
					queue.add(curr + 1);
					visited[curr+1] = true; // 방문처리
				}
				if(curr * 2 <= 100_000 && !visited[curr*2]) { // X * 2로 이동해보기
					queue.add(curr * 2);
					visited[curr*2] = true; // 방문처리
				}

			}
			// 동생을 찾지 못한 경우...
			time++; // 시간 1초 경과
		}
	}
	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		st = new StringTokenizer(br.readLine().trim());

		subin = Integer.parseInt(st.nextToken());
		sister = Integer.parseInt(st.nextToken());

		bfs(subin);
		System.out.println(time);
	}
}
