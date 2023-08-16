package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * BOJ_1260_DFS와BFS
 * 1. 정점의 개수, 간선의 개수, 탐색을 시작할 정점 번호를 입력받는다.
 * 2. 두 정점을 잇는 간선 정보를 입력받아 처리한다.
 * 3. DFS, BFS 수행한 결과를 각각 출력한다.
 * @author semin.kim
 */

public class BOJ_1260_DFS와BFS_김세민 {

	static BufferedReader br;
	static StringTokenizer st;
	static StringBuilder sb;
	
	static int vertex_cnt, edge_cnt, start_vertex_idx; // 정점, 간선의 개수, 탐색 시작 정점 번호
	static int vertex1, vertex2; // 간선의 두 정점
	static int[][] map; // 간선의 정보를 저장하는 2차원 배열
	static boolean[] visited; // 방문 여부를 저장하는 배열
	static Queue<Integer> queue = new LinkedList<Integer>();
	
	public static void DFS(int v) {
		visited[v] = true;
		sb.append(v).append(' ');
		for(int idx = 1; idx <= vertex_cnt; idx++) {
			if(map[v][idx] == 1 && !visited[idx]) {
				DFS(idx);
			}
		}
	}
	
	public static void BFS(int v) {
		visited[v] = false;
		sb.append(v).append(' ');
		
		queue.add(v);
		
		while(!queue.isEmpty()) {
			int curr = queue.poll();
			for(int idx = 1; idx <= vertex_cnt; idx++) {
				if(map[curr][idx] == 1 && visited[idx]) {
					visited[idx] = false;
					sb.append(idx).append(' ');
					queue.add(idx);
				}
			}
		}
		
	}
	
	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		st = new StringTokenizer(br.readLine().trim());
		sb = new StringBuilder();
		
		vertex_cnt = Integer.parseInt(st.nextToken()); // 정점의 개수 입력
		edge_cnt = Integer.parseInt(st.nextToken()); // 간선의 개수 입력
		start_vertex_idx = Integer.parseInt(st.nextToken()); // 탐색 시작 정점 번호 입력
		map = new int [vertex_cnt+1][vertex_cnt+1];
		visited = new boolean[vertex_cnt+1];
		
		for(int idx = 0; idx < edge_cnt; idx++) {
			st = new StringTokenizer(br.readLine().trim());
			// 두 정점의 번호 입력
			vertex1 = Integer.parseInt(st.nextToken()); 
			vertex2 = Integer.parseInt(st.nextToken());
			
			// 두 정점을 잇는 간선 생성(양방향)
			map[vertex1][vertex2] = 1;
			map[vertex2][vertex1] = 1;
		}
		
		DFS(start_vertex_idx);
		System.out.println(sb);
		sb = new StringBuilder();
		BFS(start_vertex_idx);
		System.out.println(sb);
	}
}
