package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * BOJ_17472_다리만들기2
 * 
 * @author semin.kim
 *
 * 1. 너비 우선 탐색을 통해 땅을 뮦어 섬들을 그룹화하여 번호를 부여한다.
 * 2. 각 섬을 하나의 노드로 보고 다리를 연결하여 섬들간의 다리 길이를 구하고 우선순위 큐에 삽입한다.
 * 3. 크루스칼 알고리즘을 이용하여 모든 섬을 연결하는 다리 길이의 최솟값을 구한다.
 *
 */

public class BOJ_17472_다리만들기2_김세민 {

	static BufferedReader br;
	static StringTokenizer st;

	static int colSize, rowSize; // 지도의 세로, 가로 크기
	static int[][] map;
	static final int[] DELTA_COL = {-1, 1, 0, 0};
	static final int[] DELTA_ROW = {0, 0, -1, 1};
	static boolean[][] visited;
	
	static int islandIdx = 1; // 섬을 구분하기 위한 인덱스
	static PriorityQueue<Node> pq;
	
	static int[] parents;
	
	private static void make() {
		parents = new int[islandIdx + 1];
		for(int idx = 1; idx < islandIdx; idx++) {
			parents[idx] = idx;
		}
	}
	
	private static int find(int a) {
		if(parents[a] == a) return a;
		return parents[a]= find(parents[a]); 
	}
	
	private static void union(int a, int b) {
		int aRoot = find(a);
		int bRoot = find(b);
		
		if(aRoot > bRoot) {
			parents[aRoot] = parents[bRoot];
		} else {
			parents[bRoot] = parents[aRoot];
		}
	}
	
	private static int Kruskal() {
		make();
		
		int bridgeLengthSum = 0; // 다리 길이의 합
		int bridgeCount = 0; // 만든 다리의 개수
		
		while(!pq.isEmpty()) {
			Node curr = pq.poll();
			
			if(find(curr.prev) != find(curr.next)) {
				union(curr.prev, curr.next);
				bridgeLengthSum += curr.length; // 연결한 다리 길이를 다리 길이의 합에 더해준다.
				bridgeCount++; // 만든 다리의 개수 1 증가
			}
		}
		
		// 모든 섬을 연결하는 것이 불가능한 경우...
		if(bridgeLengthSum == 0 || bridgeCount != islandIdx - 1) {
			bridgeLengthSum = -1;
		}
		
		return bridgeLengthSum;
	}
	
	static class Node implements Comparable<Node>{
		int prev, next, length;
		
		public Node(int prev, int next, int length) {
			this.prev = prev;
			this.next = next;
			this.length = length;
		}

		@Override
		public int compareTo(Node o) {
			return Integer.compare(this.length, o.length);
		}

		@Override
		public String toString() {
			return "Node [prev=" + prev + ", next=" + next + ", length=" + length + "]";
		}
		
	}
	
	static class Position {
		int col;
		int row;

		public Position(int col, int row) {
			this.col = col;
			this.row = row;
		}

		@Override
		public String toString() {
			return "Position [col=" + col + ", row=" + row + "]";
		}
	}

	/**
	 * 지도의 범위를 벗어나는지 확인하는 메서드
	 * @param col
	 * @param row
	 * @return
	 */
	private static boolean isValidRange(int col, int row) {
		return col >= 0 && row >= 0 && col < colSize && row < rowSize;
	}

	/**
	 * 섬들의 번호를 매기는 메서드
	 * @param col
	 * @param row
	 */
	private static void makeIsland(int col, int row) {
		Queue<Position> queue = new ArrayDeque<>();
		queue.offer(new Position(col, row));
		visited[col][row] = true; // 방문처리
		map[col][row] = islandIdx;
		
		while(!queue.isEmpty()) {
			Position curr = queue.poll();
			
			for(int dir = 0; dir < 4; dir++) {
				int nextCol = curr.col + DELTA_COL[dir];
				int nextRow = curr.row + DELTA_ROW[dir];
				
				// 지도의 범위를 벗어나는 경우...
				if(!isValidRange(nextCol, nextRow)) continue;
				
				// 방문한 적이 없고 바다가 아닌 경우...
				if(!visited[nextCol][nextRow] && map[nextCol][nextRow] != 0) {
					visited[nextCol][nextRow] = true;
					queue.offer(new Position(nextCol, nextRow));
					map[nextCol][nextRow] = islandIdx;
				}
			}
		}
	}

	private static void makeBridge(int col, int row, int dir, int length) {
		int temp = map[col][row];
		while(true) {
			col += DELTA_COL[dir];
			row += DELTA_ROW[dir];
			
			// 지도의 범위를 벗어나는 경우...
			if(!isValidRange(col, row)) {
				break;
			}
			
			// 같은 섬에 포함되는 땅인 경우...
			if(map[col][row] == temp) {
				break;
			}
			
			// 바다인 경우...
			if(map[col][row] == 0) {
				length++;
				continue;
			}
			
			// 바다가 아니고 같은 섬에 포함되지 않은 경우...
			if(map[col][row] != 0 && map[col][row] != temp) {
				if(length >= 2) { // 다리의 길이가 2이상인 경우...
					pq.offer(new Node(temp, map[col][row], length));
				}
				break;
			}
		}

	}

	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		st = new StringTokenizer(br.readLine().trim());

		colSize = Integer.parseInt(st.nextToken()); // 지도의 세로 크기 입력
		rowSize = Integer.parseInt(st.nextToken()); // 지도의 가로 크기 입력

		map = new int[colSize][rowSize];
		visited = new boolean[colSize][rowSize];
		pq = new PriorityQueue<>();

		for(int col = 0; col < colSize; col++) {
			st = new StringTokenizer(br.readLine().trim());
			for(int row = 0; row < rowSize; row++) {
				map[col][row] = Integer.parseInt(st.nextToken());
			}
		}

		for(int col = 0; col < colSize; col++) {
			for(int row = 0; row < rowSize; row++) {
				// 바다가 아니고 방문한 적이 없는 경우...
				if(map[col][row] != 0 && !visited[col][row]) {
					// 번호 매기기
					makeIsland(col, row);
					islandIdx++;
				}
			}
		}
		
		// 섬의 번호는 1부터 시작하므로 실제 섬의 개수는 1 빼준다.
		islandIdx--;
		
		// 지도를 순회하며 다리 건설해보기
		for(int col = 0; col < colSize; col++) {
			for(int row = 0; row < rowSize; row++) {
				if(map[col][row] != 0) {
					for(int dir = 0; dir < 4; dir++) {
						makeBridge(col, row, dir, 0);
					}
				}
			}
		}
		
		System.out.println(Kruskal());
	}
}
