package baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * BOJ_20304_비밀번호제작
 * https://www.acmicpc.net/problem/20304
 * 1.관리자 계정 비밀번호의 최댓값을 나타내는 정수를 입력받는다.
 * 2.로그인 시도에 사용된 비밀번호의 개수를 입력받는다.
 * 3.로그인 시도에 사용된 비밀번호 값을 입력받는다.
 * 4.
 * @author semin.kim
 */

public class BOJ_20304_비밀번호제작_김세민 {

	static BufferedReader br;
	static StringTokenizer st;
	static StringBuilder sb;
	
	static int max_password; // 관리자 계정 비밀번호의 최댓값
	static int attempt_cnt; // 로그인 시도에 사용된 비밀번호의 개수
	static int[] attemps; // 로그인 시도에 사용된 비밀번호 값을 저장하는 1차원 배열
	static int[] safety; // 비밀번호의 안전거리를 저장하는 1차원 배열
	static Queue<Integer> queue;
	static int MAX_SAFETY; //안전도가 가장 높은 비밀번호의 안전도
	
	public static void bfs() {
		while(!queue.isEmpty()) {
			int x = queue.poll();
			
			for(int idx = 0; idx < 20; idx++) {
				int next_x = (1<<idx) ^ x;
				if(next_x > max_password || safety[next_x] != Integer.MIN_VALUE) continue;
				safety[next_x]= safety[x] + 1;
				queue.offer(next_x);
				MAX_SAFETY = Math.max(MAX_SAFETY, safety[next_x]);
			}
		}
	}
	
	public static void main(String[] args) throws Exception {
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		
		max_password = Integer.parseInt(br.readLine().trim()); // 관리자 계정 비밀번호의 최댓값 입력
		attempt_cnt = Integer.parseInt(br.readLine().trim()); // 로그인 시도에 사용된 비밀번호의 개수 입력
		attemps = new int[attempt_cnt+1];
		safety = new int[1_000_001]; // 관리자 계정 비밀번호의 최댓값의 범위는 0이상 1,000,000이하의 정수
		Arrays.fill(safety, Integer.MIN_VALUE); // 안전거리 초기화
		queue = new ArrayDeque<>();
		
		st = new StringTokenizer(br.readLine().trim());

		// 로그인 시도에 사용된 비밀번호 값 입력
		for(int idx = 1; idx <= attempt_cnt; idx++) {
			attemps[idx] = Integer.parseInt(st.nextToken());
			safety[attemps[idx]] = 0; //로그인 시도에 사용된 비밀번호의 안전거리는 0
			queue.offer(attemps[idx]);
		}
		bfs();
		sb.append(MAX_SAFETY);
		System.out.println(sb);
		
	}
}
