package algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class MSTPrimPQTest {

	static class Vertex implements Comparable<Vertex>{
		int no, weight; // 정점 번호, 트리정점과 연결했을때의 간선비용

		public Vertex(int no, int weight) {
			super();
			this.no = no;
			this.weight = weight;
		}

		@Override
		public int compareTo(Vertex o) {
			return Integer.compare(this.weight, o.weight);
		}
		
		
	}
	
	static int V, adjMatrix[][];
	
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		V = Integer.parseInt(in.readLine());
		adjMatrix = new int[V][V];
		
		for(int i = 0; i < V; i++) {
			StringTokenizer st = new StringTokenizer(in.readLine(), " ");
			for(int j = 0; j < V; j++) {
				adjMatrix[i][j]= Integer.parseInt(st.nextToken()); 
			}
		}
		
		// 방문정점(트리정점표시)
		boolean[] visited = new boolean[V];
		int[] minEdge = new int[V]; // 자신과 트리의 정점들 간 최소 간선 비용
		PriorityQueue<Vertex> pQueue = new PriorityQueue<>();
		
		Arrays.fill(minEdge, Integer.MAX_VALUE); // 최솟값 갱신 위해 큰 값으로 설정
		minEdge[0] = 0; // 임의의 0 정점을 트리 구성의 시작으로 하기 위해 설정
		pQueue.offer(new Vertex(0, minEdge[0]));
		
		int result = 0;
		int min = 0, minVertex = 0, cnt = 0;
		
		while(!pQueue.isEmpty()) {
			// step1 : 미방문(비트리) 정점 중 최소간선비용의 정점을 선택
			Vertex curr = pQueue.poll();
			minVertex = curr.no;
			min = curr.weight;
			if(visited[minVertex]) continue;
			
			// step2 : 방문(트리) 정점에 추가
			visited[minVertex] = true; // 방문처리
			result += min; // 신장트리 비용 누적
			if(++cnt==V) break;
			
			// step3 : 트리에 추가된 새로운 정점 기준으로 비트리 정점과의 간선비용 고려
			for(int i = 0; i < V; i++) {
				if(!visited[i] && adjMatrix[minVertex][i] != 0 && minEdge[i]> adjMatrix[minVertex][i]) {
					minEdge[i]= adjMatrix[minVertex][i];
					pQueue.offer(new Vertex(i, minEdge[i]));
				}
			}
		}
		System.out.println(result);
	}
}
