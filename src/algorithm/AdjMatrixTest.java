package algorithm;

import java.util.ArrayDeque;
import java.util.Queue;
import java.util.Scanner;

// 무향 그래프
public class AdjMatrixTest {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int V = sc.nextInt();
		int E = sc.nextInt();
		
		int[][] adjMatrix = new int[V][V]; // 초기값 0
		// 간선 있으면 1, 없으면 0
		
		for(int i = 0; i < E; i++) {
			int from = sc.nextInt();
			int to = sc.nextInt();
			
			adjMatrix[to][from] = adjMatrix[from][to] = 1;
		}
		
		bfs(adjMatrix);
		
//		for(int[] is : adjMatrix) {
//			System.out.println(Arrays.toString(is));
//		}
		
		dfs(adjMatrix, new boolean[V], 0);
		sc.close();
	}
	
	private static void bfs(int[][] adjMatrix) {
		int size = adjMatrix.length;
		Queue<Integer> queue = new ArrayDeque<Integer>();
		boolean[] visited = new boolean[size];
		
		// 탐색 시작점 정점 0으로 하자.
		queue.offer(0);
		visited[0] = true;
		
		while(!queue.isEmpty()) {
			int current = queue.poll();
			System.out.println((char)(current+65));
			
			// 현재 정점의 인접정점을 체크하며 대기열에 넣기
			for(int i = 0; i < size; i++) {
				if(adjMatrix[current][i] != 0 && !visited[i]) {
					queue.offer(i);
					visited[i] = true; 
				}
			}
		}
	}
	
	private static void dfs(int[][] adjMatrix, boolean[] visited, int current) {
		visited[current] = true;
		System.out.println((char)(current+65));
		
		for(int i = 0, size = adjMatrix.length; i < size; i++) {
			if(adjMatrix[current][i] != 0 && !visited[i]) {
				dfs(adjMatrix, visited, i);
			}
		}
		
	}
}
