package algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class DijkstraTest {

	static class Node {
		int vertex, weight;
		Node next;
		
		public Node(int vertex, int weight, Node next) {
			super();
			this.vertex = vertex;
			this.weight = weight;
			this.next = next;
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine().trim());
		int V = Integer.parseInt(st.nextToken());
		int E = Integer.parseInt(st.nextToken());
		
		st = new StringTokenizer(br.readLine().trim());
		int start = Integer.parseInt(st.nextToken());
		int end = Integer.parseInt(st.nextToken());
		
		Node[] adjList = new Node[V];
		int[] distance = new int[V];
		boolean[] visited = new boolean[V];
		
		for(int i = 0; i < E; i++) {
			st = new StringTokenizer(br.readLine().trim(), " ");
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int weight = Integer.parseInt(st.nextToken());
			adjList[from] = new Node(to, weight, adjList[from]);
		}
		
		final int INF = Integer.MAX_VALUE;
		Arrays.fill(distance, INF);
		
		distance[start] = 0;
		int min = 0, stopOver = 0;
		for(int i = 0; i < V; ++i) { // 모든 정점을 다 처리할때까지 반복
			// step1 : 미방문 정점 중 출발지에서 가장 가까운 정점을 경유지로 선택
			stopOver = -1;
			min = INF;
			for(int j = 0; j < V; j++) {
				if(!visited[j] && min > distance[j]) {
					min = distance[j];
					stopOver = j;
				}
			}
			if(stopOver == -1) break;
			
			// step2 : 방문 처리
			visited[stopOver] = true;
			// 상황에 따라 처리 : 경유지가 곧 도착지면 끝내기(출발지에서 모든 정점으로의 최단거리를 구할시에는 break하지 말것!!)
			if(stopOver == end) break;
			
			// step3 : 경유지를 이용하여 미방문 정점들의 출발지에서 자신으로의 최소비용 고려
			for(Node temp = adjList[stopOver]; temp != null; temp = temp.next) {
				
				if(!visited[temp.vertex] && distance[temp.vertex] > min + temp.weight) {
					distance[temp.vertex] = min + temp.weight;
				}
			}
		
		}
		
		System.out.println(distance[end] != INF ? distance[end] : -1);
	}
}
