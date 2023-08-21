package swea;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * SWEA_4012_요리사
 * 1.총 식재료 수를 입력받는다.
 * 2.각 쌍의 시너지를 입력받는다.
 * 3.총 식재료 수를 2로 나눈만큼의 두 개의 조합에 대해 각각 시너지의 합을 구한다.
 * 4.두 음식의 시너지의 차이의 최솟값을 갱신한다. 
 * @author semin.kim
 *
 */

public class SWEA_4012_요리사_김세민 {

	static BufferedReader br;
	static StringTokenizer st;
	static StringBuilder sb;

	static int T; //테스트 케이스의 개수
	static int ingredient_cnt; // 총 식재료 수
	static boolean[] isFood1; // 첫 번째 음식인지 여부를 저장하는 배열
	static int[][] synergy; // 각 쌍의 시너지를 저장
	static int[] food1, food2;
	static int min_diff; //

	public static void combination(int cnt, int start) {
		if(cnt == (ingredient_cnt >> 1)) { // 조합 생성 완료
			int food1_synergy = 0; //첫 번째 음식의 시너지 합
			int food2_synergy = 0; //두 번째 음식의 시너지 합

			food2 = new int[ingredient_cnt >> 1];
			isFood1 = new boolean[ingredient_cnt + 1];

			// 모든 재료에 대하여 첫번째 음식에 속하는 경우 첫번째 음식으로 체크
			for(int idx = 1; idx <= ingredient_cnt; idx++) {
				isFood1[food1[idx-1]] = true;
			}

			int fodd2_idx = 0; // 두 번째 음식의 시작 인덱스

			// 첫번째 음식에 속하지 않는 경우 두번째 음식에 속함
			for(int idx = 1; idx <= ingredient_cnt; idx++) {
				if(!isFood1[idx]) {
					food2[fodd2_idx++] = idx;
				}
			}

			// 각 음식의 시너지 구하기
			for(int i = 0; i < ingredient_cnt >> 1; i++) {
				for(int j = i + 1; j < ingredient_cnt >> 1; j++) {
					food1_synergy += synergy[food1[i]][food1[j]] + synergy[food1[j]][food1[i]];
					food2_synergy += synergy[food2[i]][food2[j]] + synergy[food2[j]][food2[i]];
				}
			}
			// 두 음식의 시너지 차의 최솟값 갱신
			min_diff = Math.min(min_diff, Math.abs(food1_synergy - food2_synergy));
		} else {
			for(int idx = start; idx <= ingredient_cnt; idx++) {
				food1[cnt] = idx;
				combination(cnt + 1, idx + 1);
			}
		}
		}

		public static void main(String[] args) throws NumberFormatException, IOException {
			br = new BufferedReader(new InputStreamReader(System.in));

			T = Integer.parseInt(br.readLine().trim());
			for(int test_case = 1; test_case <= T; test_case++) {
				ingredient_cnt = Integer.parseInt(br.readLine().trim());
				synergy = new int[ingredient_cnt + 1][ingredient_cnt + 1];
				food1 = new int[ingredient_cnt];
				min_diff = Integer.MAX_VALUE;

				// 각 쌍의 시너지 입력
				for(int i = 1; i <= ingredient_cnt; i++) {
					st = new StringTokenizer(br.readLine().trim());
					for(int j = 1; j <= ingredient_cnt; j++) {
						synergy[i][j]= Integer.parseInt(st.nextToken()); 
					}
				}

				// 인덱스를 1부터 사용
				combination(0, 1);

				sb = new StringBuilder();
				sb.append('#').append(test_case).append(' ').append(min_diff);
				System.out.println(sb);
			}
		}
	}