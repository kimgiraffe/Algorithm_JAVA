package algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 부분집합 {
	
	static BufferedReader br;
	static StringTokenizer st;
	static StringBuilder sb;
	
	static int num_cnt, select_cnt; // 숫자의 개수, 선택할 숫자의 개수
	static int[] numList; // 숫자리스트
	static boolean[] selectList;
	
	public static void powerSet(int selectIdx) {
		if(selectIdx == num_cnt) { // 조합 생성 완료
			for(int idx = 0; idx < num_cnt; idx++) {
				if(selectList[idx]) {
					sb.append(numList[idx]).append(' ');
				}
			}
			sb.append('\n');
			return;
		}
		
		// 원소를 선택하고 넘어가기
		selectList[selectIdx] = true;
		powerSet(selectIdx + 1);
		
		// 원소를 선택하지 않고 넘어가기
		selectList[selectIdx] = false;
		powerSet(selectIdx + 1);
	}
	
	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		num_cnt = Integer.parseInt(br.readLine().trim()); // 숫자의 개수 입력
		numList = new int[num_cnt];
		selectList = new boolean[num_cnt];
		
		st = new StringTokenizer(br.readLine().trim());
		//숫자 리스트 입력
		for(int numIdx = 0; numIdx < num_cnt; numIdx++) {
			numList[numIdx] = Integer.parseInt(st.nextToken());
		}
		powerSet(0);
		System.out.print(sb);
				
	}
}
