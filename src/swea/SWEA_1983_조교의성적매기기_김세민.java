package swea;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

/**
 * SWEA_1983_조교의성적매기기_D2
 * 1. 학생별 중간, 기말, 과제 점수를 반영한 총점을 계산한다.
 * 2. 학점을 알고싶은 학생의 등수를 구한다.
 * 3. 등수에 따른 학점을 출력한다.
 * @author semin.kim
 */

public class SWEA_1983_조교의성적매기기_김세민 {

	static BufferedReader br;
	static StringTokenizer st;
	static int T;
	static int student_size;
	static int student_num;
	
	static class Pair implements Comparable<Pair>{
		int first;
		int second;
		
		Pair(int first, int second){
			this.first = first;
			this.second = second;
		}
		
		@Override
		public int compareTo(Pair o) {
			return Integer.compare(this.first, o.first);
		}
	}
	
	
	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		
		T = Integer.parseInt(br.readLine().trim()); // 테스트 케이스의 개수
		
		for(int test_case = 1; test_case <= T; test_case++) {
			st = new StringTokenizer(br.readLine());
			student_size = Integer.parseInt(st.nextToken()); // 학생 수 
			student_num = Integer.parseInt(st.nextToken()); //학점을 알고싶은 학생의 번호
			
			ArrayList<Pair> list = new ArrayList<>(); // 학생들의 성적을 저장할 리스트
			
			for(int idx = 1; idx <= student_size; idx++) {
				st = new StringTokenizer(br.readLine());
				int midTerm = Integer.parseInt(st.nextToken());
				int finalTerm = Integer.parseInt(st.nextToken());
				int assignment = Integer.parseInt(st.nextToken());
				/*중간, 기말, 과제 점수를 반영하여 리스트에 저장*/
				list.add(new Pair(midTerm * 35 + finalTerm * 45 + assignment * 20, idx));
			}
			
			Collections.sort(list); // 성적을 오름차순으로 정렬
			
			int score = 0;
			
			for(int idx = 0; idx < student_size; idx++) {
				if(list.get(idx).second == student_num) {
					score = idx * 10 / student_size; // 등수 계산
				}
			}
			
			System.out.print("#" + test_case + " ");
			switch (score) { //성적에 따른 학점 출력
			case 10:
			case 9:
				System.out.println("A+");
				break;
			case 8:
				System.out.println("A0");
				break;
			case 7:
				System.out.println("A-");
				break;
			case 6:
				System.out.println("B+");
				break;
			case 5:
				System.out.println("B0");
				break;
			case 4:
				System.out.println("B-");
				break;
			case 3:
				System.out.println("C+");
				break;
			case 2:
				System.out.println("C0");
				break;
			case 1:
				System.out.println("C-");
				break;
			default:
				break;
			}
		}
		
		br.close();
	}

}
