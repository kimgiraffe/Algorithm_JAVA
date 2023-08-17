package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * BOJ_1992_쿼드트리
 * 1.영상의 크기를 입력받는다.
 * 2.주어진 영상을 한 번에 나타낼 수 있을 때까지 4등분한다.
 * 3.한 번에 나타낼 수 있다면 해당 영역의 첫 번째 값을 출력한다.
 * @author semin.kim
 *
 */

public class BOJ_1992_쿼드트리_김세민 {

	static BufferedReader br;
	static StringBuilder sb;

	static int video_size; // 영상의 크기
	static int[][] video;

	private static void zip(int size, int col, int row) {
		// 기저조건
		boolean flag = true;

		OUTER : for(int colIdx = col; colIdx < col + size; colIdx++) {
			for(int rowIdx = row; rowIdx < row + size; rowIdx++) {
				if(video[col][row] != video[colIdx][rowIdx]) {
					flag = false;
					break OUTER;
				}
			}
		}
		if(flag) {
			sb.append(video[col][row]);
		}
		else { // 유도파트
			sb.append('(');
			zip(size / 2, col, row); 
			/*■□
			 *□□
			 */
			zip(size / 2, col, row + size / 2);
			/*□■
			 *□□
			 */
			zip(size / 2, col + size / 2, row);
			/* □□
			 * ■□
			 */
			zip(size / 2, col + size / 2, row + size / 2);
			/* □□
			 * □■
			 */
			sb.append(')');
		}
	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();	

		video_size = Integer.parseInt(br.readLine().trim()); // 영상의 크기 입력
		video = new int[video_size][video_size];
		// 영상의 정보 입력
		for(int colIdx = 0; colIdx < video_size; colIdx++) {
			char[] tmp = br.readLine().trim().toCharArray();
			for(int rowIdx = 0; rowIdx < video_size; rowIdx++) {
				video[colIdx][rowIdx] = tmp[rowIdx] -'0';
			}
		}
		zip(video_size, 0, 0);
		System.out.println(sb);
	}
}
