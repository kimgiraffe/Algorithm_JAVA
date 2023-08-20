package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

/**
 * BOJ_17616_등수찾기
 * N(2이상 10만 이하)명의 학생이 대회에 참가.
 * 두 학생 중 누가 더 잘했는지 알려줌(동점인 경우 없음)
 * 총 M번 질문.
 * 등수를 알고 싶은 학생 X와 질문에 대한 답들로부터 학생의 등수 범위 찾기
 * 
 * 
 * @author semin.kim
 */

public class BOJ_17616_등수찾기_김세민 {

	static BufferedReader br;
	static StringTokenizer st;
	
	static int student_cnt, query_cnt, student_X; // 학생 수, 질문 수, 등수를 알고 싶은 학생의 번호
	static int upper_bound = 1, lower_bound; // 가능한 가장 높은 등수, 낮은 등수
	
	static ArrayList<Integer>[] win, lose;
	static boolean[] visited;
	
	public static void bfs_win(int student, ArrayList<Integer>[] list) {
		for(int next : list[student]) {
			if(!visited[next]) {
				visited[next] = true;
				upper_bound++;
				bfs_win(next, list);
			}
		}
	}
	
	public static void bfs_lose(int student, ArrayList<Integer>[] list) {
		for(int next : list[student]) {
			if(!visited[next]) {
				visited[next] = true;
				lower_bound--;
				bfs_lose(next, list);
			}
		}
		
		
	}
	
	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		
		st = new StringTokenizer(br.readLine().trim());
		
		student_cnt = Integer.parseInt(st.nextToken());
		query_cnt = Integer.parseInt(st.nextToken());
		student_X = Integer.parseInt(st.nextToken());
		
		visited = new boolean[student_cnt + 1];
		
		win = new ArrayList[student_cnt + 1];
		lose = new ArrayList[student_cnt + 1];
		
		for(int idx = 1; idx <= student_cnt; idx++) {
			win[idx] = new ArrayList<>();
			lose[idx] = new ArrayList<>();
		}
		
		
		// 질문에 대한 답 입력
		for(int idx = 0; idx < query_cnt; idx++) {
			st = new StringTokenizer(br.readLine().trim());
			
			int better = Integer.parseInt(st.nextToken());
			int worse = Integer.parseInt(st.nextToken());
			win[worse].add(better);
			lose[better].add(worse);
		}
		visited[student_X] = true;
		
		lower_bound = student_cnt; // 가장 낮은 등수를 학생 수
		
		bfs_win(student_X, win);
		bfs_lose(student_X, lose);
		
		System.out.println(upper_bound+" "+lower_bound);
		
	}
}
