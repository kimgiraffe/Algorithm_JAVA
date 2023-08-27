package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * BOJ_21608_상어초등학교
 * 교실은 N×N 크기의 격자로 나타낼 수 있다. 
 * 학교에 다니는 학생의 수는 N²이다. 오늘은 모든 학생의 자리를 정하는 날이다. 
 * 학생은 1번부터 N²까지 번호가 매겨져 있고, (r, c)는 r행 c열을 의미한다. 
 * 교실의 가장 왼쪽 윗 칸은 (1, 1)이고, 가장 오른쪽 아랫 칸은 (N, N)이다.
 * 선생님은 학생의 순서를 정했고, 각 학생이 좋아하는 학생 4명도 모두 조사했다. 
 * 이제 다음과 같은 규칙을 이용해 정해진 순서대로 학생의 자리를 정하려고 한다. 
 * 한 칸에는 학생 한 명의 자리만 있을 수 있고, |r1 - r2| + |c1 - c2| = 1을 만족하는 두 칸이 (r1, c1)과 (r2, c2)를 인접하다고 한다.
 * 1.비어있는 칸 중에서 좋아하는 학생이 인접한 칸에 가장 많은 칸으로 자리를 정한다.
 * 2.1을 만족하는 칸이 여러 개이면, 인접한 칸 중에서 비어있는 칸이 가장 많은 칸으로 자리를 정한다.
 * 3.2를 만족하는 칸도 여러 개인 경우에는 행의 번호가 가장 작은 칸으로, 그러한 칸도 여러 개이면 열의 번호가 가장 작은 칸으로 자리를 정한다.
 * 
 * @author semin.kim
 */

public class BOJ_21608_상어초등학교_김세민 {

	static BufferedReader br;
	static StringTokenizer st;

	static int mapSize;
	static int[][] map;

	static class Student {
		int number; // 번호
		int[] likes = new int[4]; // 좋아하는 학생 4명의 번호

		public Student(int number, int[] likes) {
			this.number = number;
			this.likes = likes;
		}

		@Override
		public String toString() {
			return "Student [number=" + number + ", likes=" + Arrays.toString(likes) + "]";
		}
	}

	static Student[] students;

	private static void Place(Student student) { // num : 자리를 배치할 학생의 번호
		// 비어있는 칸 중에서 좋아하는 학생이 인접한 칸이 가장 많은 칸으로 자리를 정한다.
		int maxLike = 0, maxEmptyCount = 0; // 인접한 칸에 좋아하는 학생 수의 최댓값, 인접한 칸 중 비어있는 칸의 최댓값
		int minRow = mapSize - 1, minCol = mapSize - 1; // 행, 열의 번호의 최솟값
		for(int row = 0; row < mapSize; row++) {
			for(int col = 0; col < mapSize; col++) {
				if(map[row][col] == 0) { // 비어있는 칸인 경우...
					int likeCount = 0; // 인접한 칸에 좋아하는 학생의 수
					int emptyCount = 0; // 비어있는 칸의 수
					// 인접한 칸 중 비어있는 칸 개수 세기
					if(row + 1 < mapSize) {
						if(map[row+1][col] == 0) {
							emptyCount++;
						}
					}
					if(row -1 >= 0) {
						if(map[row-1][col] == 0) {
							emptyCount++;
						}
					}
					if(col + 1 < mapSize) {
						if(map[row][col+1] == 0) {
							emptyCount++;
						}
					}
					if(col - 1 >= 0) {
						if(map[row][col-1] == 0) {
							emptyCount++;
						}
					}
					// 인접한 칸 중 좋아하는 학생 수 세기
					for(int idx = 0; idx < 4; idx++) {
						if(row + 1 < mapSize) {
							if(map[row + 1][col] == student.likes[idx]) {
								likeCount++;
							}
						}
					}
					for(int idx = 0; idx < 4; idx++) {
						if(row - 1 >= 0) {
							if(map[row - 1][col] == student.likes[idx]) {
								likeCount++;
							}
						}
					}
					for(int idx = 0; idx < 4; idx++) {
						if(col + 1 < mapSize) {
							if(map[row][col + 1] == student.likes[idx]) {
								likeCount++;
							}
						}
					}
					for(int idx = 0; idx < 4; idx++) {
						if(col - 1 >= 0) {
							if(map[row][col - 1] == student.likes[idx]) {
								likeCount++;
							}
						}
					}
					// 좋아하는 학생 수가 가장 많은 경우...
					if(maxLike < likeCount) {
						maxLike = likeCount;
						maxEmptyCount = emptyCount;
						minRow = row;
						minCol = col;
					}
					else if(maxLike == likeCount) { // 좋아하는 학생 수가 같은 경우...
						if(maxEmptyCount < emptyCount) { // 인접한 칸 중 비어있는 칸이 가장 많은 경우...
							maxEmptyCount = emptyCount;
							minRow = row;
							minCol = col;
						}
						else if(maxEmptyCount == emptyCount) { // 인접한 칸 중 비어있는 칸의 수가 같은 경우...
							if(minRow > row) { // 행의 번호가 더 작은 경우...
								minRow = row;
								minCol = col;
							}
							else if(minRow == row) { // 행의 번호가 같은 경우...
								if(minCol > col) { // 열의 번호가 더 작은 경우...
									minRow = row;
									minCol = col;
								}
							}
						}
					}
				}

			}
		}
		// 학생을 자리에 배치
		map[minRow][minCol] = student.number;
	}

	private static void CalculateSatisfaction() {
		int satisfaction = 0; // 만족도의 총 합

		for(int idx = 0; idx < mapSize * mapSize; idx++) {
			int number = students[idx].number; // 학생의 번호
			int count = 0; // 인접한 칸에 앉은 좋아하는 학생의 수

			for(int row = 0; row < mapSize; row++) {
				for(int col = 0; col < mapSize; col++) {
					if(map[row][col] == number) { // 학생의 위치를 찾기
						// 인접한 칸에 앉은 좋아하는 학생의 수 구하기
						for(int likeIdx = 0; likeIdx < 4; likeIdx++) { 
							if(row + 1 < mapSize) {
								if(map[row+1][col] == students[idx].likes[likeIdx]) {
									count++;
								}
							}
							if(row - 1 >= 0) {
								if(map[row-1][col] == students[idx].likes[likeIdx]) {
									count++;
								}
							}
							if(col + 1 < mapSize) {
								if(map[row][col + 1] == students[idx].likes[likeIdx]) {
									count++;
								}
							}
							if(col - 1 >= 0) {
								if(map[row][col - 1] == students[idx].likes[likeIdx]) {
									count++;
								}
							}
						}
						
					}
				}
			}
			// 인접한 칸에 앉은 좋아하는 학생의 수에 따라 학생의 만족도 구하기
			switch (count) {
			// 1명인 경우...
			case 1:
				satisfaction += 1;
				break;
			// 2명인 경우...
			case 2:
				satisfaction += 10;
				break;
			// 3명인 경우...
			case 3:
				satisfaction += 100;
				break;
			// 4명인 경우...
			case 4:
				satisfaction += 1000;
				break;
			default:
				break;
			}
		}
		// 학생의 만족도의 합 출력
		System.out.println(satisfaction);
	}

	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		mapSize = Integer.parseInt(br.readLine().trim());

		map = new int[mapSize][mapSize];
		students = new Student[mapSize*mapSize]; // 학생의 수는 N²

		for(int idx = 0; idx < mapSize * mapSize; idx++) {
			st = new StringTokenizer(br.readLine().trim());
			int number = Integer.parseInt(st.nextToken());
			int[] likes = new int[4];
			for(int likeIdx = 0; likeIdx < 4; likeIdx++) {
				likes[likeIdx] = Integer.parseInt(st.nextToken());
			}
			students[idx] = new Student(number, likes);
		}

		// 첫번째 학생은 무조건 (1,1) 위치에 배치
		map[1][1] = students[0].number;
		for(int idx = 1; idx < mapSize * mapSize; idx++) {
			Place(students[idx]);
		}

		CalculateSatisfaction();
	}

}
