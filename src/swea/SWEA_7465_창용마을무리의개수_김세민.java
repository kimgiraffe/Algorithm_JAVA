package swea;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;


/**
 * SWEA_7465_창용마을무리의개수
 * 
 * 1. 인접 행렬을 이용하여 사람들의 관계를 저장한다.
 * 2. 인접 행렬을 순회하며 깊이 우선 탐색을 통해 방문한 적이 없고 알고 있는 관계인 경우 계속 탐색
 * 3. 마을무리의개수 출력
 * 
 * @author semin.kim
 *
 */

public class SWEA_7465_창용마을무리의개수_김세민 {
	
	static BufferedReader br;
	static StringTokenizer st;
	static StringBuilder sb;
	
	static boolean visited[];
	static int[][] relation;
	static int testcaseCount;
	static int peopleCount, relationCount, count;
	
	public static void DFS(int node) {
		if(visited[node]) { // 방문한 경우... 
			return;
		}
		visited[node] = true; // 방문처리
		
		for(int idx = 1; idx <= peopleCount; idx++) {
			if(relation[node][idx] == 1) { // 알고 있는 경우...
				DFS(idx);
			}
		}
	}
	
	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		
		testcaseCount = Integer.parseInt(br.readLine().trim());
		
		for(int testcase = 1; testcase <= testcaseCount; testcase++) {
			st = new StringTokenizer(br.readLine().trim());
			peopleCount = Integer.parseInt(st.nextToken());
			relationCount = Integer.parseInt(st.nextToken());
			
			relation = new int[peopleCount+1][peopleCount+1];
			visited = new boolean[peopleCount+1];
			
			for(int idx = 1; idx <= relationCount; idx++) {
				st = new StringTokenizer(br.readLine().trim());
				int first = Integer.parseInt(st.nextToken());
				int second = Integer.parseInt(st.nextToken());
				relation[first][second] = 1;
				relation[second][first] = 1;
			}
			
			count = 0;
			for(int idx = 1; idx <= peopleCount; idx++) {
				if(!visited[idx]) {
					DFS(idx);
					count++;
				}
			}
			
			sb.append("#").append(testcase).append(" ").append(count).append("\n");
		}
		System.out.print(sb);
	}
	
}