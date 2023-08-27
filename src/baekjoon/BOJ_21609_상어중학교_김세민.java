package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;


/**
 * BOJ_21609_상어중학교
 * 크기가 N×N인 격자에서 진행되고, 초기에 격자의 모든 칸에는 블록이 하나씩 들어있고, 블록은 검은색 블록, 무지개 블록, 일반 블록이 있다. 
 * 일반 블록은 M가지 색상이 있고, 색은 M이하의 자연수로 표현한다. 검은색 블록은 -1, 무지개 블록은 0으로 표현한다. 
 * (i, j)는 격자의 i번 행, j번 열을 의미하고, |r1 - r2| + |c1 - c2| = 1을 만족하는 두 칸 (r1, c1)과 (r2, c2)를 인접한 칸이라고 한다.
 * 블록 그룹은 연결된 블록의 집합이다. 그룹에는 일반 블록이 적어도 하나 있어야 하며, 일반 블록의 색은 모두 같아야 한다. 
 * 검은색 블록은 포함되면 안 되고, 무지개 블록은 얼마나 들어있든 상관없다. 
 * 그룹에 속한 블록의 개수는 2보다 크거나 같아야 하며, 임의의 한 블록에서 그룹에 속한 인접한 칸으로 이동해서 그룹에 속한 다른 모든 칸으로 이동할 수 있어야 한다.
 * 블록 그룹의 기준 블록은 무지개 블록이 아닌 블록 중에서 행의 번호가 가장 작은 블록, 그러한 블록이 여러개면 열의 번호가 가장 작은 블록이다.
 * 오토 플레이 기능을 만드려고 한다. 오토 플레이는 다음과 같은 과정이 블록 그룹이 존재하는 동안 계속해서 반복되어야 한다.
 * 1.크기가 가장 큰 블록 그룹을 찾는다. 그러한 블록 그룹이 여러 개라면 포함된 무지개 블록의 수가 가장 많은 블록 그룹, 
 * 그러한 블록도 여러개라면 기준 블록의 행이 가장 큰 것을, 그 것도 여러개이면 열이 가장 큰 것을 찾는다.
 * 2.1에서 찾은 블록 그룹의 모든 블록을 제거한다. 블록 그룹에 포함된 블록의 수를 B라고 했을 때, B2점을 획득한다.
 * 3.격자에 중력이 작용한다.
 * 4.격자가 90도 반시계 방향으로 회전한다.
 * 5.다시 격자에 중력이 작용한다.
 * 격자에 중력이 작용하면 검은색 블록을 제외한 모든 블록이 행의 번호가 큰 칸으로 이동한다. 이동은 다른 블록이나 격자의 경계를 만나기 전까지 계속 된다.
 * @author semin.kim
 */

public class BOJ_21609_상어중학교_김세민 {
	
	static BufferedReader br;
	static StringTokenizer st;
	
	static int mapSize; // 격자의 크기
	static int colorCount; // 색상의 개수
	static int totalScore; // 점수의 합
	static int[][] map;
	static final int[] DELTA_ROW = {-1, 0, 1, 0}; // 상 하
	static final int[] DELTA_COL = {0, 1, 0, -1}; // 우 좌
	static boolean[][] visited;
	static final int BLACK = -1, RAINBOW = 100, EMPTY = 0;
	
	static class Pos {
		int row;
		int col;

		public Pos(int row, int col) {
			this.row = row;
			this.col = col;
		}
		
		@Override
		public String toString() {
			return "Pos [row=" + row + ", col=" + col + "]";
		}
	}
	
	static class Block implements Comparable<Block>{
		int row, col, color, blockCount, rainbowCount;

		public Block(int row, int col, int color, int blockCount, int rainbowCount) {
			this.row = row;
			this.col = col;
			this.color = color;
			this.blockCount = blockCount;
			this.rainbowCount = rainbowCount;
		}

		@Override
		public String toString() {
			return "Block [row=" + row + ", col=" + col + ", color=" + color + ", blockCount=" + blockCount
					+ ", rainbowCount=" + rainbowCount + "]";
		}

		@Override
		public int compareTo(Block o) {
			if(this.blockCount >= o.blockCount) {
				if(this.blockCount == o.blockCount) {
					if(this.rainbowCount >= o.rainbowCount) {
						if(this.rainbowCount == o.rainbowCount) {
							if(this.row >= o.row) {
								if(this.row == o.row) {
									return this.col - o.col;
								}
								else {
									return 1;
								}
							} 
							else {
								return -1;
							}
						}
						else {
							return 1;
						}
					}
					else {
						return -1;
					}
				}
				else {
					return 1;
				}
			}
			else {
				return -1;
			}
		}
		
	}
	
	private static Block findMaxBlockGroup() {
		visited = new boolean[mapSize][mapSize];
		
		Block maxBlock = new Block(0, 0, EMPTY, Integer.MIN_VALUE, Integer.MIN_VALUE);
		
		for(int row = 0; row < mapSize; row++) {
			for(int col = 0; col < mapSize; col++) {
				if(map[row][col] == BLACK) continue;
				if(map[row][col] == RAINBOW) continue;
				if(map[row][col] == EMPTY) continue;
				if(visited[row][col]) continue;
				
				initRainbowVisited();
				
				Block currBlock = bfsBlock(row, col, map[row][col]);
				
				if(currBlock == null) {
					continue;
				}
				
				if(maxBlock.compareTo(currBlock) == 1) {
					maxBlock = currBlock;
				}
			}
		}
		
		if(maxBlock.color == EMPTY) {
			return null;
		}
		return maxBlock;
	}
	
	private static Block bfsBlock(int row, int col, int color) {
		Queue<Pos> queue = new LinkedList<>();
		queue.offer(new Pos(row, col));
		visited[row][col] = true;
		
		int count = 1;
		int rainbowCount = 0;
		
		while(queue.isEmpty()) {
			Pos curr = queue.poll();
			
			for(int dir = 0; dir < 4; dir++) {
				int nextRow = curr.row + DELTA_ROW[dir];
				int nextCol = curr.col + DELTA_COL[dir];	
				
				if(nextRow < 0 || nextCol < 0 || nextRow >= mapSize || nextCol >= mapSize) continue;
				
				if(map[nextRow][nextCol] == BLACK) continue;
				if(map[nextRow][nextCol] == EMPTY) continue;
				if(visited[nextRow][nextCol]) continue;
				
				if(map[nextRow][nextCol] != color) {
					if(map[nextRow][nextCol] == RAINBOW) {
						rainbowCount++;
						count++;
						visited[nextRow][nextCol] = true;
						queue.offer(new Pos(nextRow, nextCol));
					}
					continue;
				}
				
				count++;
				visited[nextRow][nextCol] = true;
				queue.offer(new Pos(nextRow, nextCol));
			}
		}
		
		if(count < 2) {
			return null;
		} else {
			return new Block(row, col, color, count, rainbowCount);
		}
	}
	
	// 무지개 블록 방문 처리 초기화
	private static void initRainbowVisited() {
		for(int row = 0; row < mapSize; row++) {
			for(int col = 0; col < mapSize; col++) {
				if(map[row][col] == RAINBOW) {
					visited[row][col] = false;
				}
			}
		}
	}
	
	private static void applyGravity() {
		for(int row = 0; row < mapSize; row++) {
			for(int col = mapSize - 2; col >= 0; col--) {
				if(map[row][col] == BLACK) continue;
				if(map[row][col] == EMPTY) continue;
				moveBlock(row, col);
			}
		}
	}
	
	private static void moveBlock(int row, int col) {
		int nextRow = row;
		while(true) {
			nextRow++;
			if(nextRow >= mapSize) break;
			if(map[nextRow][col] == BLACK) break;
			if(map[nextRow][col] != EMPTY) break;
		}
		nextRow--;
		
		if(nextRow == row) {
			return;
		}
		
		map[nextRow][col] = map[row][col];
		map[row][col] = EMPTY;
	}
	
	private static void rotate() {
		int[][] copiedMap = new int[mapSize][mapSize];
		for(int row = 0; row < mapSize; row++) {
			for(int col = 0; col < mapSize; col++) {
				copiedMap[row][col] = map[col][mapSize - row - 1];
			}
		}
		
		for(int row = 0; row < mapSize; row++) {
			for(int col = 0; col < mapSize; col++) {
				map[row][col] = copiedMap[row][col];
			}
		}
	}
	
	private static void removeBlock(Block block) {
		Queue<Pos> queue = new LinkedList<>();
		visited = new boolean[mapSize][mapSize];
		
		queue.offer(new Pos(block.row, block.col));
		
		visited[block.row][block.col] = true;
		map[block.row][block.col] = EMPTY;
		
		while(!queue.isEmpty()) {
			Pos curr = queue.poll();
			
			for(int dir = 0; dir < 4; dir++) {
				int nextRow = curr.row + DELTA_ROW[dir];
				int nextCol = curr.col + DELTA_COL[dir];
				
				if(nextRow < 0 || nextCol < 0 || nextRow >= mapSize || nextCol >= mapSize) continue;
				if(map[nextRow][nextCol] == BLACK) continue;
				if(map[nextRow][nextCol] == EMPTY) continue;
				if(visited[nextRow][nextCol]) continue;
				
				if(map[nextRow][nextCol] != block.color) {
					if(map[nextRow][nextCol] == RAINBOW) {
						map[nextRow][nextCol] = EMPTY;
						visited[nextRow][nextCol] = true;
						queue.offer(new Pos(nextRow, nextCol));
					}
					continue;
				}
				
				map[nextRow][nextCol] = EMPTY;
				visited[nextRow][nextCol] = true;
				queue.offer(new Pos(nextRow, nextCol));

			}
		}
	}
	
	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		st = new StringTokenizer(br.readLine().trim());
		
		mapSize = Integer.parseInt(st.nextToken());
		colorCount = Integer.parseInt(st.nextToken());
		
		map = new int[mapSize][mapSize];
		visited = new boolean[mapSize][mapSize];
		
		for(int row = 0; row < mapSize; row++) {
			st = new StringTokenizer(br.readLine().trim());
			for(int col = 0; col < mapSize; col++) {
				map[row][col] = Integer.parseInt(st.nextToken());
				if(map[row][col] == -1) {
					map[row][col] = BLACK;
				} else if(map[row][col] == 0) {
					map[row][col] = RAINBOW;
				}
			}
		}
		
		while(true) {
			Block standardBlock = findMaxBlockGroup();
			if(standardBlock == null) {
				break;
			}
			
			totalScore += standardBlock.blockCount * standardBlock.blockCount;
			
			removeBlock(standardBlock);
			
			applyGravity();
			
			rotate();
			
			applyGravity();
		}
		
		System.out.println(totalScore);
	}
}
