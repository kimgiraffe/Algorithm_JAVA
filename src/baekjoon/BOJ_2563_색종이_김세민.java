package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * BOJ_2563_색종이
 * 1.색종이의 수를 입력받는다.
 * 2.각 색종이를 붙인 위치를 입력받는다.
 * 3.색종이를 붙일 때마다 도화지의 상태를 갱신한다.
 * 4.도화지에서 색종이가 붙은 검은 영역의 넓이를 출력한다.
 * @author semin.kim
 */

public class BOJ_2563_색종이_김세민 {
	
	static BufferedReader br;
	static StringTokenizer st;
	static StringBuilder sb;
	
	static final int DRAWING_PAPER_SIZE = 100; // 흰 도화지의 크기는 100으로 고정
	static final int COLORED_PAPER_SIZE = 10; // 색종이의 크기는 10으로 고정
	static int colored_paper_cnt; // 색종이의 개수
	static colored_paper[] colored_papers; // 색종이의 위치를 저장할 1차원 배열
	static boolean[][] isPosted = new boolean[DRAWING_PAPER_SIZE][DRAWING_PAPER_SIZE];
	static int posted_cnt; // 흰 도화지에 붙은 검은 영역의 개수
	
	static class colored_paper {
		int row_dist;
		int col_dist;

		public colored_paper(int width, int height) {
			this.row_dist = width;
			this.col_dist = height;
		}
	}
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		
		colored_paper_cnt = Integer.parseInt(br.readLine().trim()); // 색종이의 개수 입력
		colored_papers = new colored_paper[colored_paper_cnt];
		
		for(int idx = 0; idx < colored_paper_cnt; idx++) {
			st = new StringTokenizer(br.readLine().trim());

			colored_papers[idx] = new colored_paper(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
			// 색종이를 도화지에 붙이면 도화지의 상태를 갱신
			for(int row = colored_papers[idx].row_dist; row < colored_papers[idx].row_dist + COLORED_PAPER_SIZE; row++) {
				for(int col = colored_papers[idx].col_dist; col < colored_papers[idx].col_dist + COLORED_PAPER_SIZE; col++) {
					isPosted[row][col] = true; 
				}
			}
		}
		// 도화지의 전체 영역을 탐색하여 색종이가 붙은 검은 영역의 개수 구하기
		for(int rowIdx = 0; rowIdx < DRAWING_PAPER_SIZE; rowIdx++) {
			for(int colIdx = 0; colIdx < DRAWING_PAPER_SIZE; colIdx++) {
				if(isPosted[rowIdx][colIdx]) {
					posted_cnt++;
				}
			}
		}
		sb = new StringBuilder();
		sb.append(posted_cnt);
		System.out.println(sb);
	}
}
