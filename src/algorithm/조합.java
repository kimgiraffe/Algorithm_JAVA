package algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 조합 {
	
	static BufferedReader br;
	static StringTokenizer st;
	static StringBuilder sb;
	
	static int num_cnt, select_cnt; // 숫자의 개수, 선택할 숫자의 개수
	static int[] numList, selectList; // 숫자리스트, 조합을 저장할 리스트
	
	public static void Combination(int currentIdx, int selectIdx) {
		if(selectIdx == select_cnt) { // 조합 생성 완료
			for(int idx = 0; idx < select_cnt; idx++) {
				sb.append(selectList[idx]).append(' ');
			}
			sb.append('\n');
			return;
		}
		// 모든 원소를 탐색했을 떄
		if(currentIdx == num_cnt) return;
		
		// 원소를 선택하고 넘어가기
		selectList[selectIdx] = numList[currentIdx];
		Combination(currentIdx + 1, selectIdx + 1);
		
		// 원소를 선택하지 않고 넘어가기
		selectList[selectIdx] = 0;
		Combination(currentIdx + 1, selectIdx);
	}
	
	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		st = new StringTokenizer(br.readLine().trim());
		sb = new StringBuilder();
		num_cnt = Integer.parseInt(st.nextToken()); // 숫자의 개수 입력
		select_cnt = Integer.parseInt(st.nextToken()); // 선택할 숫자의 개수 입력
		numList = new int[num_cnt];
		selectList = new int[select_cnt];
		
		st = new StringTokenizer(br.readLine().trim());
		//숫자 리스트 입력
		for(int numIdx = 0; numIdx < num_cnt; numIdx++) {
			numList[numIdx] = Integer.parseInt(st.nextToken());
		}
		Combination(0, 0);
		System.out.print(sb);
				
	}
}
