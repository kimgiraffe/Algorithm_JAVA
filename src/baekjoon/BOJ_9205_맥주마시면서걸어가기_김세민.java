package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * BOJ_9205_맥주마시면서걸어가기
 * 
 * @author semin.kim
 *
 * [문제]
 * 출발은 상근이네 집에서 하고, 맥주 한 박스를 들고 출발한다. 
 * 맥주 한 박스에는 맥주가 20개 들어있다. 
 * 목이 마르면 안되기 때문에 50미터에 한 병씩 마시려고 한다. 즉, 50미터를 가려면 그 직전에 맥주 한 병을 마셔야 한다.
 * 상근이의 집에서 페스티벌이 열리는 곳은 매우 먼 거리이다. 
 * 따라서, 맥주를 더 구매해야 할 수도 있다.
 * 편의점에 들렸을 때, 빈 병은 버리고 새 맥주 병을 살 수 있다. 
 * 하지만, 박스에 들어있는 맥주는 20병을 넘을 수 없다. 
 * 편의점을 나선 직후에도 50미터를 가기 전에 맥주 한 병을 마셔야 한다.
 * 
 * [풀이]
 * 1. 집의 위치(출발 위치)를 큐에 삽입
 * 2. 현재 위치에서 바로 페스티벌까지 갈 수 있는 경우 바로 종료
 * 3. 편의점 리스트에서 갈 수 있는 편의점이 있는지 확인하여 해당 편의점으로 이동하고 큐에 삽입
 * 4. 2~3 과정을 반복
 * 5. 페스티벌까지 갈 수 없는 경우 sad 출력, 갈 수 있는 경우 happy 출력
 * 
 */

public class BOJ_9205_맥주마시면서걸어가기_김세민 {

	static BufferedReader br;
	static StringTokenizer st;
	static StringBuilder sb;
	
	static int testcaseCount; // 테스트케이스의 개수
	static int convenienceStoreCount; // 편의점의 개수
	static int col, row;
	static boolean[] visited;
	
	static class Position {
		int col;
		int row;
		
		public Position(int col, int row) {
			this.col = col;
			this.row = row;
		}
	}
	
	static Position house, festival;
	static Position[] convenienceStoreList;
	
	/**
	 * 맨해튼 거리 계산하여 반환하는 메서드
	 * @param col1
	 * @param row1
	 * @param col2
	 * @param row2
	 * @return
	 */
	private static int CalculateDistance(int col1, int row1, int col2, int row2) {
		return Math.abs(col1 - col2) + Math.abs(row1 - row2);
	}
	
	private static boolean bfs() {
		Queue<Position> queue = new ArrayDeque<>();
		
		queue.offer(house); // 집의 위치를 큐에 삽입
		
		while(!queue.isEmpty()) {
			Position currentPosition = queue.poll();
			
			// 현재 위치에서 바로 락 페스티벌까지 갈 수 있는 경우...
			if(CalculateDistance(currentPosition.col, currentPosition.row, festival.col, festival.row) <= 1000) {
				return true;
			}
			
			for(int idx = 0; idx < convenienceStoreCount; idx++) {
				// 이미 방문한 편의점인 경우...
				if(visited[idx]) {
					continue;
				}
				// 해당 편의점으로 갈 수 없는 경우... 
				if(CalculateDistance(currentPosition.col, currentPosition.row, convenienceStoreList[idx].col, convenienceStoreList[idx].row) > 1000) {
					continue;
				}
				
				visited[idx] = true; // 방문 처리
				queue.offer(convenienceStoreList[idx]); // 큐에 삽입
			}
		}
		return false;
	}
	
	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		
		testcaseCount = Integer.parseInt(br.readLine().trim());
		
		for(int testcase = 1; testcase <= testcaseCount; testcase++) {
			convenienceStoreCount = Integer.parseInt(br.readLine().trim());
			convenienceStoreList = new Position[convenienceStoreCount];
			visited = new boolean[convenienceStoreCount];
			
			st = new StringTokenizer(br.readLine().trim());
			col = Integer.parseInt(st.nextToken());
			row = Integer.parseInt(st.nextToken());
			house = new Position(col, row);
			
			for(int idx = 0; idx < convenienceStoreCount; idx++) {
				st = new StringTokenizer(br.readLine().trim());
				col = Integer.parseInt(st.nextToken());
				row = Integer.parseInt(st.nextToken());
				Position convenienceStore = new Position(col, row);
				convenienceStoreList[idx] = convenienceStore;
			}
			
			st = new StringTokenizer(br.readLine().trim());
			col = Integer.parseInt(st.nextToken());
			row = Integer.parseInt(st.nextToken());
			festival = new Position(col, row);
			
			if(bfs()) {
				sb.append("happy\n");
			} else {
				sb.append("sad\n");
			}
		}
		System.out.print(sb);
	}
}
