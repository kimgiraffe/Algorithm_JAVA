package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_6497_전력난_김세민 {

	static BufferedReader br;
	static StringTokenizer st;
	static int houseCount, pathCount;
	static Edge[] edgeList;
	static long result; // 전체 비용 - MST 비용

	static class Edge implements Comparable<Edge> {
		int from;
		int to;
		long weight;

		@Override
		public int compareTo(Edge o) {
			return Long.compare(this.weight, o.weight);
		}

		public Edge(int from, int to, long weight) {
			super();
			this.from = from;
			this.to = to;
			this.weight = weight;
		}

		@Override
		public String toString() {
			return "Edge [from=" + from + ", to=" + to + ", weight=" + weight + "]";
		}

	}

	static int[] parents;

	static void make() {
		parents = new int[houseCount];
		for(int idx = 0; idx < houseCount; idx++) {
			parents[idx] = idx;
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

	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));

		while(true) {
			st = new StringTokenizer(br.readLine().trim());

			houseCount = Integer.parseInt(st.nextToken());
			pathCount = Integer.parseInt(st.nextToken());

			if(houseCount == 0 && pathCount == 0) { // 0 0 인 경우 입력 종료
				break;
			}

			// 변수 초기화
			edgeList = new Edge[pathCount];
			result = 0;

			for(int idx = 0; idx < pathCount; idx++) {
				st = new StringTokenizer(br.readLine().trim());
				int from = Integer.parseInt(st.nextToken());
				int to = Integer.parseInt(st.nextToken());
				int weight = Integer.parseInt(st.nextToken());

				edgeList[idx] = new Edge(from, to, weight);
				result += weight; // 전체 비용에 추가
			}

			Arrays.sort(edgeList);

			make();

			int count = 0; // 연결된 간선 개수
			for(Edge edge : edgeList) {
				if(union(edge.from, edge.to)) {
					result -= edge.weight;
					if(++count == houseCount - 1) break; // MST 생성 완료
				}
			}

			System.out.println(result);
		}
	}
}
