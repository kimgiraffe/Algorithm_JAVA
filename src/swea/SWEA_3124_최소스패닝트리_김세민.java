package swea;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * SWEA_3124_최소스패닝트리
 * 
 * 그래프가 주어졌을 때, 그 그래프의 최소 스패닝 트리를 구하는 프로그램을 작성하시오.
 * 최소 스패닝 트리는, 주어진 그래프의 모든 정점들을 연결하는 부분 그래프 중에서 그 가중치의 합이 최소인 트리를 말한다.
 * 입력으로 주어지는 그래프는 하나의 연결 그래프임이 보장된다.
 * 
 * @author semin.kim
 *
 */

public class SWEA_3124_최소스패닝트리_김세민 {

	static BufferedReader br;
	static StringTokenizer st;
	static StringBuilder sb;

	static int T; // 테스트케이스의 개수
	static int vertexCount, edgeCount; // 정점, 간선의 개수

	static class Edge implements Comparable<Edge>{
		int from;
		int to;
		int weight;

		public Edge(int from, int to, int weight) {
			this.from = from;
			this.to = to;
			this.weight = weight;
		}

		@Override
		public String toString() {
			return "Edge [from=" + from + ", to=" + to + ", weight=" + weight + "]";
		}

		@Override
		public int compareTo(Edge o) {
			return Integer.compare(this.weight, o.weight);
		}
	}

	static Edge[] edgeList;

	static int[] parents;

	static void make() {
		parents = new int[vertexCount+1];
		for(int i = 1; i <= vertexCount; i++) {
			parents[i] = i;
		}
	}

	static int find(int a) {
		if(parents[a] == a) return a;
		return parents[a] = find(parents[a]); 
	}

	static boolean union(int a, int b) {
		int aRoot = find(a);
		int bRoot = find(b);

		if(aRoot == bRoot)  return false;
		parents[bRoot] = aRoot;
		return true;
	}
	
	static long weightSum; // 가중치의 합의 최소

	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));

		T = Integer.parseInt(br.readLine().trim()); // 테스트케이스의 개수 입력

		for(int testCase = 1; testCase <= T; testCase++) {
			sb = new StringBuilder();
			st = new StringTokenizer(br.readLine().trim());
			vertexCount = Integer.parseInt(st.nextToken()); // 정점의 개수 입력
			edgeCount = Integer.parseInt(st.nextToken()); // 간선의 개수 입력

			edgeList = new Edge[edgeCount];
			weightSum = 0; // 가중치의 합 초기화
			// 간선 정보 입력
			for(int idx = 0; idx < edgeCount; idx++) {
				st = new StringTokenizer(br.readLine().trim());
				int from = Integer.parseInt(st.nextToken());
				int to = Integer.parseInt(st.nextToken());
				int weight = Integer.parseInt(st.nextToken());
				edgeList[idx] = new Edge(from, to, weight);
			}
			
			// 간선을 가중치에 따라 오름차순으로 정렬
			Arrays.sort(edgeList);
			
			// vertexCount 개의 정점으로 make-set
			make();
			int count = 0; // 연결된 간선의 개수
			
			for(Edge edge : edgeList) {
				if(union(edge.from, edge.to)) {
					weightSum += edge.weight;
					if(++count == vertexCount) break;
				}
			}
			sb.append('#').append(testCase).append(" ").append(weightSum);
			System.out.println(sb);
		}

	}
}
