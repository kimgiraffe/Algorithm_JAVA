package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

/**
 * BOJ_13023_ABCDE
 * 총 N(5이상 2000이하)명, 친구 관계의 수 M(1이상 2000이하)
 * 다음과 같은 친구 관계를 가진 사람 A, B, C, D, E가 존재하는지 구해보려고 한다.
 * A는 B와 친구다. B는 C와 친구다. C는 D와 친구다. D는 E와 친구다.
 * 위와 같은 친구 관계가 존재하는지 안하는지 구하자
 *
 * 무향 그래프
 * 입력 받은 친구관계를 인접리스트 형태로 저장하고 각 사람에 대해 한 명씩 문제 조건에 맞는 친구 관계인지 깊이 우선 탐색으로 조사
 * 관계가 존재하는 경우... 중단하고 1출력
 * 존재하지 않은 경우... 0출력
 * @author semin.kim
 */

public class BOJ_13023_ABCDE_김세민 {
	
	static BufferedReader br;
	static StringTokenizer st;
	
	static int people_cnt, relation_cnt; // 총 인원 수, 친구 관계의 수
	static boolean arrive;
	static ArrayList<Integer>[] relationship;
	static boolean[] visited;
	
	public static void dfs(int start,int depth) {
		if(depth == 5 || arrive) { // 문제 조건에 부합하는 관계인 경우...
			arrive = true;
			return;
		}
		
		visited[start] = true; // 방문 처리
		
		for(int idx : relationship[start]) {
			if(!visited[idx]) {
				dfs(idx, depth + 1); // 다음 친구로 이동
			}
		}
		visited[start] = false; // 재사용을 위해 미사용 처리
	}
	
	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		st = new StringTokenizer(br.readLine().trim());
		
		people_cnt = Integer.parseInt(st.nextToken());
		relation_cnt = Integer.parseInt(st.nextToken());
		
		relationship = new ArrayList[people_cnt];
		visited = new boolean[people_cnt];
		
		for(int idx = 0; idx < people_cnt; idx++) {
			relationship[idx] = new ArrayList<Integer>();
		}
		
		for(int idx = 1; idx <= relation_cnt; idx++) {
			st = new StringTokenizer(br.readLine().trim());

			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			// 무향 그래프이므로 양쪽 모두 연결
			relationship[start].add(end);
			relationship[end].add(start);
		}
		
		// 모든 사람들에 대해 순서대로 친구 관계 확인하기
		for(int idx = 0; idx < people_cnt; idx++) {
			dfs(idx, 1);
			if(arrive) break; // 문제 조건에 맞는 친구 관계가 존재하는 경우... 중단
		}
		
		// 문제 조건에 맞는 친구 관계가 존재하는 경우 1 출력, 존재하지 않는 경우 0 출력
		if(arrive) System.out.println(1);
		else System.out.println(0);
	}
}
