package swea;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * SWEA_1251_하나로
 * 모든 섬을 연결하는 가중치의 합이 최소인 그래프
 * 가중치는 환경 부담 세율 * 터널 길이의 제곱의 곱
 * Prim 알고리즘 이용
 * @author semin.kim
 *
 */

public class SWEA_1251_하나로_김세민 {

	static BufferedReader br;
	static StringTokenizer st;
	static StringBuilder sb;

	static class Island {
		long x;
		long y;

		public Island(long x, long y) {
			super();
			this.x = x;
			this.y = y;
		}

		public Island() {
			super();
		}

		@Override
		public String toString() {
			return "Island [x=" + x + ", y=" + y + "]";
		}
	}

	static int T;
	static int islandCount; // 섬의 개수
	static int tunnelCount;
	static double tax; // 환경부담세율
	static Island[] islands;
	static boolean[] visited;
	static long[] minEdge;

	static class Tunnel implements Comparable<Tunnel>{
		Island from, to;
		long weight;

		public Tunnel(Island from, Island to, long weight) {
			super();
			this.from = from;
			this.to = to;
			this.weight = weight;
		}

		@Override
		public String toString() {
			return "Tunnel [from=" + from + ", to=" + to + ", weight=" + weight + "]";
		}

		@Override
		public int compareTo(Tunnel o) {
			return Long.compare(this.weight, o.weight);
		}
	}

	static long adjMatrix[][];

	private static void MakeTunnel() {

		for(int from = 0; from < islandCount; from++) {
			for(int to = 0; to < islandCount; to++) {
				if(from == to) adjMatrix[from][to] = 0;
				// 해저터널 길이의 제곱
				long distance = (islands[from].x - islands[to].x) * (islands[from].x - islands[to].x)
						+ (islands[from].y - islands[to].y) * (islands[from].y - islands[to].y);
				adjMatrix[from][to] = distance;
			}
		}
	}

	public static void main(String[] args) throws IOException{
		br = new BufferedReader(new InputStreamReader(System.in));

		T = Integer.parseInt(br.readLine().trim()); //테스트 케이스의 개수 입력
		for(int testCase = 1; testCase <= T; testCase++) {
			sb = new StringBuilder();
			islandCount = Integer.parseInt(br.readLine().trim()); // 섬의 개수 입력
			islands = new Island[islandCount];
			adjMatrix = new long[islandCount][islandCount];
			visited = new boolean[islandCount];
			minEdge = new long[islandCount];

			st = new StringTokenizer(br.readLine().trim());
			for(int islandIdx = 0; islandIdx < islandCount; islandIdx++) {
				islands[islandIdx] = new Island();
				islands[islandIdx].x = Long.parseLong(st.nextToken()); // 섬들의 x좌표 입력
			}
			st = new StringTokenizer(br.readLine().trim());
			for(int islandIdx = 0; islandIdx < islandCount; islandIdx++) {
				islands[islandIdx].y = Long.parseLong(st.nextToken()); // 섬들의 y좌표 입력
			}

			tax = Double.parseDouble(br.readLine().trim()); // 환경 부담 세율 입력
			MakeTunnel(); // 모든 섬에 대해 터널 생성

			Arrays.fill(minEdge, Long.MAX_VALUE);
			minEdge[0] = 0;

			long result = 0L;
			long min = 0L;
			int minVertex = 0;

			for(int c = 0; c < islandCount; c++) {
				minVertex = -1;
				min = Long.MAX_VALUE;
				// step1 : 미방문(비트리) 정점 중 최소간선비용의 정점을 선택
				for(int i = 0; i < islandCount; i++) {
					if(!visited[i] && min > minEdge[i]) {
						minVertex = i;
						min = minEdge[i];
					}
				}

				// step2 : 방문(트리) 정점에 추가
				visited[minVertex] = true; // 방문처리
				result += min; // 신장트리 비용 누적

				// step3 : 트리에 추가된 새로운 정점 기준으로 비트리 정점과의 간선비용 고려
				for(int i = 0; i < islandCount; i++) {
					if(!visited[i] && adjMatrix[minVertex][i] != 0 && minEdge[i]> adjMatrix[minVertex][i]) {
						minEdge[i]= adjMatrix[minVertex][i]; 
					}
				}
			}
			// 환경 부담 세율을 곱하고 소수 첫째 자리에서 반올림 후 출력
			sb.append('#').append(testCase).append(" ").append((Math.round(result*tax/1.0)));
			System.out.println(sb);
		}
	}
}
