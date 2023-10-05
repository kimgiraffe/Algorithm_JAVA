package swea;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

import sun.net.www.content.image.gif;
import sun.tools.jar.resources.jar;

/**
 * SWEA_5656_벽돌깨기
 * 
 * @author semin.kim
 * 
 * 1. 구슬을 왼쪽 줄부터 쏘아본다.
 * 2. 벽돌이 구슬에 명중할 경우, 깊이 우선 탐색을 통해 폭발을 수행한다.
 * 3. 폭발이 모두 끝나면 벽돌을 떨어뜨린다.
 * 4. 구슬을 모두 쏜 후, 폭발한 벽돌의 개수의 최댓값을 갱신한다.
 * 5. 초기 벽돌의 수에서 폭발한 벽돌의 수의 최댓값을 뺀 값을 출력한다.
 * 
 */

public class SWEA_5656_벽돌깨기_김세민 {

	static BufferedReader br;
	static StringTokenizer st;
	static StringBuilder sb;

	static int testcaseCount;
	static int marbleCount, width, height;
	static int[][][] map;
	static final int[] DELTA_H = {-1, 1, 0, 0};
	static final int[] DELTA_W = {0, 0, -1, 1};
	static int[][] heightList;
	static int remainingBrickCount, minRemainingBrickCount = Integer.MAX_VALUE;

	static class Postion {
		int col;
		int row;

		public Postion(int col, int row) {
			this.col = col;
			this.row = row;
		}

		@Override
		public String toString() {
			return "Postion [col=" + col + ", row=" + row + "]";
		}
	}

	private static boolean isValidRange(int h, int w) {
		return h >= 0 && w >= 0 && h < height && w < width;
	}

	private static void copyMap(int copyIdx, int pasteIdx) {
		for(int h = 0; h < height; h++) {
			for(int w = 0; w < width; w++) {
				map[pasteIdx][h][w] = map[copyIdx][h][w]; 
			}
		}
		for(int w = 0; w < width; w++) {
			heightList[pasteIdx][w] = heightList[copyIdx][w];
		}
	}

	private static int calculateSum(int idx) {
		int sum = 0;
		for(int row = 0; row < width; row++) {
			sum += height - heightList[idx][row];
		}
		
		return sum;
	}
	
	private static void shoot(int marbleIdx) {
		if(marbleIdx == marbleCount) {
			remainingBrickCount = calculateSum(marbleIdx);
			
			minRemainingBrickCount = Math.min(minRemainingBrickCount, remainingBrickCount);
			return;
		}
		
		for(int w = 0; w < width; w++) {
			int h = heightList[marbleIdx][w];
			
			copyMap(marbleIdx, marbleIdx + 1);
			
			if(h == height) {
				if(calculateSum(marbleIdx) == 0) {
					shoot(marbleIdx + 1);
					continue;
				}
				else {
					continue;
				}
			}
			
			heightList[marbleIdx+1][w]++;
			
			explode(h, w, marbleIdx + 1);
			
			drop(marbleIdx + 1);
			
			shoot(marbleIdx + 1);
		}
	}

	private static void explode(int h, int w, int marbleIdx) {
		int brickNum = map[marbleIdx][h][w];
		
		map[marbleIdx][h][w] = 0;
		
		for(int num = 1; num < brickNum; num++) {
			for(int dir = 0; dir < 4; dir++) {
				int nextH = h + DELTA_H[dir] * num;
				int nextW = w + DELTA_W[dir] * num;
				if(isValidRange(nextH, nextW)) {
					if(map[marbleIdx][nextH][nextW] == 0) continue;
					
					heightList[marbleIdx][nextW]++;
					
					explode(nextH, nextW, marbleIdx);
				}
			}
		}
	}
	
	private static void drop(int marbleIdx) {
		for(int row = 0; row < width; row++) {
			for(int col = height - 1; col >= 0; col--) {
				if(col < heightList[marbleIdx][row]) continue;
				
				if(map[marbleIdx][col][row] == 0) {
					for(int tempCol = col - 1; tempCol >= 0; tempCol--) {
						if(map[marbleIdx][tempCol][row] != 0) {
							map[marbleIdx][col][row] = map[marbleIdx][tempCol][row];
							map[marbleIdx][tempCol][row] = 0;
							break;
						}
					}
				}
			}
		}
	}

	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();

		testcaseCount = Integer.parseInt(br.readLine().trim());

		for(int testcase = 1; testcase <= testcaseCount; testcase++) {
			st = new StringTokenizer(br.readLine().trim());
			sb.append("#").append(testcase).append(" ");

			marbleCount = Integer.parseInt(st.nextToken());
			width = Integer.parseInt(st.nextToken());
			height = Integer.parseInt(st.nextToken());

			map = new int[marbleCount + 1][height][width];
			heightList = new int[marbleCount + 1][width];
			minRemainingBrickCount = Integer.MAX_VALUE;
			
			for(int row = 0; row < width; row++) {
				heightList[0][row] = height;
			}
			
			for(int col = 0; col < height; col++) {
				st = new StringTokenizer(br.readLine().trim());
				for(int row = 0; row < width; row++) {
					map[0][col][row] = Integer.parseInt(st.nextToken());
					if(heightList[0][row] == height && map[0][col][row] != 0) {
						heightList[0][row] = col;
					}
				}
			}
			
			shoot(0);
			
			sb.append(minRemainingBrickCount).append("\n");
		}

		System.out.println(sb);
	}
}
