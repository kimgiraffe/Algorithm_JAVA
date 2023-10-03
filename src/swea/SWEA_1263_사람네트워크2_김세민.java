package swea;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * SWEA_1263_사람네트워크2
 * 
 * @author semin.kim
 * 
 * [문제]
 * Closeness Centrality(CC):Closeness는 네트워크 상에서 한 사용자가 다른 모든 사람에게 얼마나 가까운가를 나타낸다.
 * 따라서 사용자 i의 CC(i)는 다음과 같이 계산된다.
 * CC(i) = ∑ j dist(i,j) 단, dist(i,j)는 노드i로부터 노드 j까지의 최단 거리이다.
 * 
 * [풀이]
 * 1. 테스트케이스의 개수를 입력받는다.
 * 2. 사람의 수를 입력받는다.
 * 3. 사람 네트워크의 인접 행렬을 입력 받아 2차원 배열에 저장한다.
 * 4. 플로이드워샬 알고리즘을 활용하여 모든 정점을 경유 가능한 정점들로 고려한 모든 쌍 i, j의 최단 경로의 거리를 찾는다.
 * 5. 사람들 중에서 dist 합이 최소인 사람의 dist 합을 찾아 출력한다.
 * 
 */

public class SWEA_1263_사람네트워크2_김세민 {

	static BufferedReader br;
	static StringTokenizer st;
	static StringBuilder sb;
	
	static int testcaseCount; // 테스트케이스의 수
	static int peopleCount; // 사람의 수
	static int[][] adjMatrix;
	static final int INF = 987654321;
	
	private static void Floyd() {
		for(int via = 0; via < peopleCount; via++) {
			for(int src = 0; src < peopleCount; src++) {
				if(src == via) continue;
				for(int dest = 0; dest < peopleCount; dest++) {
					if(dest == src || dest == via) continue;
					adjMatrix[src][dest] = Math.min(adjMatrix[src][via] + adjMatrix[via][dest], adjMatrix[src][dest]); 
				}
			}
		}
		
		int minSum = Integer.MAX_VALUE; // dist 합이 가장 작은 사용자의 dist 합
		
		for(int col = 0; col < peopleCount; col++) {
			int sum = 0; // 사용자의 dist 합
			for(int row = 0; row < peopleCount; row++) {
				sum += adjMatrix[col][row];
			}
			// 최소 dist 합 갱신
			minSum = Math.min(sum, minSum);
		}
		
		sb.append(minSum);
		System.out.println(sb);
	}
	
	public static void main(String[] args) throws IOException{
		br = new BufferedReader(new InputStreamReader(System.in));
		
		testcaseCount = Integer.parseInt(br.readLine().trim()); // 테스트케이스의 수 입력
		
		for(int testcase = 1; testcase <= testcaseCount; testcase++) {
			st = new StringTokenizer(br.readLine().trim());
			sb = new StringBuilder();
			
			peopleCount = Integer.parseInt(st.nextToken()); // 사람의 수 입력
			adjMatrix = new int[peopleCount][peopleCount];
			
			for(int src = 0; src < peopleCount; src++) {
				for(int dest = 0; dest < peopleCount; dest++) {
					adjMatrix[src][dest] = Integer.parseInt(st.nextToken());
					if(src != dest && adjMatrix[src][dest] == 0) { // 자기 자신이거나 간선이 존재하지 않는 경우...
						adjMatrix[src][dest] = INF;
					} 
				}
			}
			
			sb.append("#").append(testcase).append(" ");
			Floyd();
		}
	}
}
