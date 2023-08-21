package swea;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * SWEA_2001_파리퇴치_D2
 * 1. 2차원 배열을 순회하여
 * 2. 입력으로 주어진 파리채의 크기에 죽일 수 있는 파리의 최대수를 갱신
 * @author semin.kim
 */

public class SWEA_2001_파리퇴치_김세민 {

	static BufferedReader br;
	static StringTokenizer st;
	static int T, flies_size, flapper_size;
	static int[][] flies;

	/**
	 * kill method 구현
	 * 1. 2차원 배열을 순회하며 한 번에 잡을 수 있는 파리의 수를 구한다.
	 * 2. 이를 최댓값과 비교하여 최댓값을 갱신한다.
	 * @return 최대로 죽일 수 있는 파리의 수
	 */
	public static int kill() {
		int killed = 0;
		for(int col = 0; col < flies_size - flapper_size + 1; col++) {
			for(int row = 0; row < flies_size - flapper_size + 1; row++) {
				int curr = 0; // 현재 파리채로 잡을 수 있는 파리의 수
				for(int flapper_col = col; flapper_col < col + flapper_size; flapper_col++) {
					for(int flapper_row = row; flapper_row < row + flapper_size; flapper_row++) {
						curr += flies[flapper_col][flapper_row];
					}
				}
				if(killed < curr) killed = curr; // 최댓값 갱신
			}
		}
		return killed;
	}

	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));

		T = Integer.parseInt(br.readLine().trim()); //테스트 케이스의 개수 입력

		for(int test_case = 1; test_case <= T; test_case++) {
			st = new StringTokenizer(br.readLine());

			flies_size = Integer.parseInt(st.nextToken()); // 파리의 개수를 저장할 2차원 배열의 크기 입력
			flapper_size = Integer.parseInt(st.nextToken()); // 파리채의 크기 입력
			flies = new int[flies_size][flies_size]; //파리의 개수 정보를 담는 2차원 배열

			for(int col = 0; col < flies_size; col++) {
				st = new StringTokenizer(br.readLine());
				for(int row = 0; row < flies_size; row++) {
					flies[col][row] = Integer.parseInt(st.nextToken()); 
				}
			}

			System.out.println("#" + test_case + " " + kill());
		}
		br.close();
	}

}
