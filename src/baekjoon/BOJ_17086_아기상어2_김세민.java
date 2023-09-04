package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_17086_아기상어2_김세민 {

	static BufferedReader br;
	static StringTokenizer st;
	
	static int N, M;
	static int[][] map, distance;
	static final int[] DELTA_ROW = {-1, 0, 1, 0, -1, 1, -1, 1};
	static final int[] DELTA_COL = {0, 1, 0, -1, -1, 1, 1, -1};
	static Queue<Pos> queue = new LinkedList<>();
	static int MAX = Integer.MIN_VALUE;
	
	static class Pos {
		int row;
		int col;
		
		public Pos(int row, int col) {
			super();
			this.row = row;
			this.col = col;
		}

		@Override
		public String toString() {
			return "Pos [row=" + row + ", col=" + col + "]";
		}
	}
	
	public static void BFS() {		
		while(!queue.isEmpty()) {
			Pos curr = queue.poll();
			
			for(int dir = 0; dir < 8; dir++) {
				int nextRow = curr.row + DELTA_ROW[dir];
				int nextCol = curr.col + DELTA_COL[dir];
				
				if(nextRow < 0 || nextRow >= N || nextCol < 0 || nextCol >= M) continue;
				if(map[nextRow][nextCol] == 1) continue;
				if(distance[nextRow][nextCol] > 0) continue;
				distance[nextRow][nextCol] = distance[curr.row][curr.col] + 1;
				queue.offer(new Pos(nextRow, nextCol));
			}
		}
	}
	
	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		st = new StringTokenizer(br.readLine().trim());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		map = new int[N][M];
		distance = new int[N][M];
		
		
		for(int row = 0; row < N; row++) {
			st = new StringTokenizer(br.readLine().trim());
			for(int col = 0; col < M; col++) {
				map[row][col] = Integer.parseInt(st.nextToken());
				if(map[row][col] == 1) {
					queue.offer(new Pos(row, col));
					distance[row][col] = 0;
				}
				else {
					distance[row][col] = -1;
				}
			}
		}
		BFS();
		
		for(int row = 0; row < N; row++) {
			for(int col = 0; col < M; col++) {
				if(MAX < distance[row][col]) {
					MAX = distance[row][col];
				}
			}
		}
		System.out.println(MAX);
		
	}
}
