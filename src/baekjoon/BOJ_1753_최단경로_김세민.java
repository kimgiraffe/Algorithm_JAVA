package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * BOJ_1753_최단경로
 * 방향그래프가 주어지면 주어진 시작점에서 다른 모든 정점으로의 최단 경로를 구하는 프로그램을 작성하시오. 
 * 단, 모든 간선의 가중치는 10 이하의 자연수이다.
 * 다익스트라 알고리즘을 이용하여 시작점에서 다른 모든 정점으로의 최단 경로를 구한다.
 * step1 : 미방문 정점 중 출발지에서 가장 가까운 정점을 경유지로 선택
 * step2 : 방문 처리
 * step3 : 경유지를 이용하여 미방문 정점들의 출발지에서 자신으로의 최소비용 고려
 * @author semin.kim
 *
 */

public class BOJ_1753_최단경로_김세민 {

	static class Node {
		int vertex, weight;
		Node next;
		
		public Node(int vertex, int weight, Node next) {
			this.vertex = vertex;
			this.weight = weight;
			this.next = next;
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine().trim());
		int vertexCount = Integer.parseInt(st.nextToken());
		int edgeCount = Integer.parseInt(st.nextToken());
		
		st = new StringTokenizer(br.readLine().trim());
		int start = Integer.parseInt(st.nextToken());
		
		Node[] adjList = new Node[vertexCount];
		int[] distance = new int[vertexCount];
		boolean[] visited = new boolean[vertexCount];
		
		for(int idx = 0; idx < edgeCount; idx++) {
			st = new StringTokenizer(br.readLine().trim(), " ");
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int weight = Integer.parseInt(st.nextToken());
			adjList[from-1] = new Node(to - 1, weight, adjList[from-1]);
		}
		
		final int INF = Integer.MAX_VALUE;
		Arrays.fill(distance, INF);
		
		distance[start-1] = 0;
		int min = 0, stopOver = 0;
		for(int idx = 0; idx < vertexCount; ++idx) { // 모든 정점을 다 처리할때까지 반복
			// step1 : 미방문 정점 중 출발지에서 가장 가까운 정점을 경유지로 선택
			stopOver = -1;
			min = INF;
			for(int vertex = 0; vertex < vertexCount; vertex++) {
				if(!visited[vertex] && min > distance[vertex]) {
					min = distance[vertex];
					stopOver = vertex;
				}
			}
			if(stopOver == -1) break;
			
			// step2 : 방문 처리
			visited[stopOver] = true;
			
			// step3 : 경유지를 이용하여 미방문 정점들의 출발지에서 자신으로의 최소비용 고려
			for(Node temp = adjList[stopOver]; temp != null; temp = temp.next) {
				
				if(!visited[temp.vertex] && distance[temp.vertex] > min + temp.weight) {
					distance[temp.vertex] = min + temp.weight;
				}
			}
		
		}
		
		for(int idx = 0; idx < vertexCount; idx++) {
			System.out.println(distance[idx] != INF ? distance[idx] : "INF");

		}
		
	}
}
