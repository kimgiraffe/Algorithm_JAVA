package algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 순열 {
	
	static BufferedReader br;
	static StringTokenizer st;
	static StringBuilder sb;
	
	static int num_cnt, select_cnt; // 숫자의 개수, 선택할 숫자의 개수
	static int[] numList, selectList; // 숫자리스트, 순열을 저장할 리스트
	static boolean[] visited;
	
	public static void Permutation(int currentIdx) {
		if(currentIdx == select_cnt) { // 순열 생성 완료
			for(int idx = 0; idx < select_cnt; idx++) {
				sb.append(selectList[idx]).append(' ');
			}
			sb.append('\n');
			return;
		}
		for(int idx = 0; idx < num_cnt; idx++) {
			// 해당 위치의 원소를 이미 선택했다면
			if(visited[idx]) continue;
			
			visited[idx] = true; // 해당 원소를 사용 처리
			selectList[currentIdx] = numList[idx]; // 순열 리스트에 담기
			Permutation(currentIdx+1); // 다음 위치로 이동
			visited[idx] = false; // 다음에 사용하기 위해 사용 처리했던 원소를 미사용 처리
		}
	}
	
	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		st = new StringTokenizer(br.readLine().trim());
		sb = new StringBuilder();
		num_cnt = Integer.parseInt(st.nextToken()); // 숫자의 개수 입력
		select_cnt = Integer.parseInt(st.nextToken()); // 선택할 숫자의 개수 입력
		numList = new int[num_cnt];
		selectList = new int[select_cnt];
		visited = new boolean[num_cnt];
		
		st = new StringTokenizer(br.readLine().trim());
		//숫자 리스트 입력
		for(int numIdx = 0; numIdx < num_cnt; numIdx++) {
			numList[numIdx] = Integer.parseInt(st.nextToken());
		}
		Permutation(0);
		System.out.print(sb);
				
	}
}
