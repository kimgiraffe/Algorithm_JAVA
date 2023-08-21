package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;


/**
 * BOJ_2252_줄세우기
 * 
 * N(1이상 32000이하)명의 학생들을 키 순서대로 줄 세우기
 * 일부 학생들의 키를 두 명씩 비교
 * 번호가 정해지지 않은 학생들을 어떻게 처리...?
 * 
 * 
 * 1. 비교를 한 번도 안한(진입차수가 0인) 학생들을 큐에 삽입
 * 2. 큐에서 원소를 꺼내 연결된 모든 간선을 제거
 * 3. 간선 제거 후 진입차수가 0이 된 정점을 큐에 삽입
 * 4. 큐가 빌 때까지 2~3을 반복 
 * @author semin.kim
 *
 */

public class BOJ_2252_줄세우기_김세민 {

	static BufferedReader br;
	static StringTokenizer st;
	static StringBuilder sb;
	
	static int student_cnt; // 학생들의 수
	static int compare_cnt; // 비교횟수
	
	static List<Integer>[] student_list;
	static int[] indegree;
	static Queue<Integer> queue = new ArrayDeque<>();

	
	public static void bfs() {
		while(!queue.isEmpty()) {
			int temp = queue.poll();
			System.out.println(temp);
			
			for(int idx = 0; idx < student_list[temp].size(); idx++) {
				if(--indegree[student_list[temp].get(idx)] == 0) { // 간선 제거 후 진입차수가 0이 된 정점
					queue.add(student_list[temp].get(idx)); // 큐에 삽입
				}
			}
		}
		
	}
	
	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		st = new StringTokenizer(br.readLine().trim());
		
		student_cnt = Integer.parseInt(st.nextToken());
		compare_cnt = Integer.parseInt(st.nextToken());
		
		indegree = new int[student_cnt + 1];
		student_list = new List[student_cnt + 1];
		for(int idx = 1; idx <= student_cnt; idx++) {
			student_list[idx] = new ArrayList<>();
		}
		
		for(int compare_idx = 0; compare_idx < compare_cnt; compare_idx++) {
			int before, after; // 두 학생의 번호 입력
			st = new StringTokenizer(br.readLine().trim());
			before = Integer.parseInt(st.nextToken());
			after = Integer.parseInt(st.nextToken());
			
			indegree[after]++; // 키가 더 큰 학생의 앞에 오는 학생의 수 1 증가
			student_list[before].add(after); // 키가 더 작은 학생의 뒤에 키가 더 큰 학생의 번호 추가
			
		}
		
		for(int idx = 1; idx <= student_cnt; idx++) { // 진입 차수가 0인 학생들을 큐에 삽입
			if(indegree[idx] == 0) {
				queue.add(idx);
			}
		}
		
		bfs();
	}
}
