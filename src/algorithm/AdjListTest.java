package algorithm;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.Scanner;

// 무향 그래프
public class AdjListTest {

	static class Node {
		int vertex; // 관계를 맺고 있는 타 정점 정보
		Node next; // 연결리스트 유지를 위한 다음 노드 참조
		
		public Node(int vertex, Node next) {
			this.vertex = vertex;
			this.next = next;
		}

		@Override
		public String toString() {
			return "Node [vertex=" + vertex + ", next=" + next + "]";
		}
		
		
	}
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int V = sc.nextInt();
		int E = sc.nextInt();
		
		Node adjList[] = new Node[V]; // 헤드 리스트
		
		// 연결리스트가 정점 수만큼 관리
		for(int i = 0; i < E; i++) {
			int from = sc.nextInt();
			int to = sc.nextInt();
			adjList[from] = new Node(to, adjList[from]);
			adjList[to] = new Node(from, adjList[to]);
		}
		
		bfs(adjList);
		
//		for(Node node : adjList) { // node : 각 정점의 인접리스트의 헤드
//			System.out.println(node);
//		}
	}
	
	private static void bfs(Node adjList[]) {
		int size =  adjList.length;
		Queue<Integer> queue = new ArrayDeque<Integer>();
		boolean[] visited = new boolean[size];
		
		queue.offer(0);
		visited[0] = true;
		
		while(!queue.isEmpty()) {
			int current = queue.poll();
			System.out.println((char)(current+65));
			
			for(Node temp = adjList[current]; temp != null; temp = temp.next) {
				if(!visited[temp.vertex]) {
					queue.offer(temp.vertex);
					visited[temp.vertex] = true;
				}
			}
		}
	}
}
