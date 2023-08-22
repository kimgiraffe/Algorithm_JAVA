package swea;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * SWEA_3289_서로소집합
 * 초기에 {1}, {2}, ... {n} 이 각각 n개의 집합을 이루고 있다.
 * 여기에 합집합 연산과, 두 원소가 같은 집합에 포함되어 있는지를 확인하는 연산을 수행하려고 한다.
 * 연산을 수행하는 프로그램을 작성하시오.
 * 
 * @author semin.kim
 */

public class SWEA_3289_서로소집합_김세민 {

	static BufferedReader br;
	static StringTokenizer st;
	static StringBuilder sb;
	
	static int T; // 테스트 케이스의 개수
	static int set_cnt; // 초기 집합의 개수
	static int op_cnt; // 연산의 개수
	
	static int[] parents;
	
	private static void make() {
		parents = new int[set_cnt+1];
		for(int i = 1; i <= set_cnt; i++) {
			parents[i] = i; // 모든 요소는 자기만 원소로 갖는 단위 서로소 집합이 되게 한다.(자신이 곧 대표자인 루트로 표현)
		}
	}
	
	private static int find(int a) {
		if(a == parents[a]) return a;
		return parents[a] = find(parents[a]);
	}
	
	private static void union(int a, int b) {
		int aRoot = find(a);
		int bRoot = find(b);
		
		
		parents[bRoot] = aRoot;
	}
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		
		T = Integer.parseInt(br.readLine().trim()); // 테스트 케이스의 개수 입력
		
		for(int test_case = 1; test_case <= T; ++test_case) {
			st = new StringTokenizer(br.readLine().trim());
			sb = new StringBuilder();
			sb.append('#').append(test_case).append(" ");
			set_cnt = Integer.parseInt(st.nextToken()); // 초기 집합의 개수 입력
			op_cnt = Integer.parseInt(st.nextToken()); // 연산의 개수 입력
			make(); // 초기 집합 생성
			
			for(int op_idx = 0; op_idx < op_cnt; op_idx++) {
				st = new StringTokenizer(br.readLine().trim());
				int op = Integer.parseInt(st.nextToken());
				if(op == 0) { // 합집합 연산
					int set_1 = Integer.parseInt(st.nextToken());
					int set_2 = Integer.parseInt(st.nextToken());
					union(set_1, set_2);
				}
				else { // find
					int element_1 = Integer.parseInt(st.nextToken());
					int element_2 = Integer.parseInt(st.nextToken());
					
					if(find(element_1)==find(element_2)) { // 두 원소가 같은 집합에 속한 경우...
						sb.append(1);
					}else {
						sb.append(0);
					}
				}
			}
			System.out.println(sb);
		}
	}
}
