package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * BOJ_10166_관중석
 * 반지름이 D 인 원 위에는 좌석이 D 개가 있다.
 * 또한, 무대에서 정확히 북쪽 방향에는 모든 원들에 좌석이 있으며, 하나의 원 위에 있는 좌석들은 동일한 간격을 두고 배치되어 있다. 
 * 이번 공연에 반지름이 D1보다 같거나 크고, D2(D1 ≤ D2)보다 같거나 작은 원들에 배치된 좌석만을 활용하려고 한다. 
 * 단, 좌석을 점으로 간주했을 때, 다른 좌석에 의해 무대 중심이 가려지는 좌석은 사용하지 않고, 그렇지 않은 좌석은 모두 사용한다.
 * 원의 반지름 D1과 D2를 입력으로 받아 사용되는 좌석의 수를 출력하는 프로그램을 작성하라.
 * 
 * 각 좌석을 기약분수 형태로 나태내어 배치한다. 예) D = 6 -> 1 / 6, 1 / 3, 1 / 2, 2 / 3, 5 / 6, 1 / 1
 * 이전에 방문한 적이 없는 좌석의 경우 좌석을 사용하고 사용되는 좌석의 수를 1 증가한다.
 * 
 * @author semin.kim
 */

public class BOJ_10166_관중석_김세민 {

	static BufferedReader br;
	static StringTokenizer st;
	
	static int D1, D2, seats; //두 원의 반지름, 사용되는 좌석의 수
	static boolean[][] fraction; // 사용되는 좌석을 기약분수 형태로 저장할 2차원 배열

	/**
	 * a와 b의 최대공약수를 반환하는 메서드
	 * @param a
	 * @param b
	 * @return GCD(a, b)
	 */
	private static int GCD(int a, int b) {
		if(b == 0) {
			return a;
		}
		return GCD(b, a % b);
	}
	
	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		st = new StringTokenizer(br.readLine().trim());
		
		D1 = Integer.parseInt(st.nextToken());
		D2 = Integer.parseInt(st.nextToken());
		
		fraction = new boolean[2001][2001];
		
		for(int D = D1; D <= D2; D++) { // 두 원의 반지름을 순회
			for(int idx = 1; idx <= D; idx++) {
				int temp = GCD(D, idx);
				
				// 사용가능한 좌석인 경우...
				if(!fraction[idx/temp][D/temp]) {
					fraction[idx / temp][D / temp] = true; // 사용처리
					seats++; // 사용된 좌석의 개수 1 증가
				}
			}
		}
		System.out.println(seats);
	}
}
