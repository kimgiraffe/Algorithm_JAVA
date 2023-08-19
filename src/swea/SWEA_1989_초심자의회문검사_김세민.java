	package swea;
	
	import java.io.BufferedReader;
	import java.io.IOException;
	import java.io.InputStreamReader;
	
	/**
	 * SWEA_1989_초심자의회문검사_D2
	 * 1. 문자열을 입력받는다.
	 * 2. StringBuffer를 이용하여 문자열을 뒤집은 값과 비교한다.
	 * 3. 동일하면 1, 아니면 0을 출력한다.
	 * @author semin.kim
	 */
	
	public class SWEA_1989_초심자의회문검사_김세민 {
	
		static BufferedReader br;
		static StringBuffer sb;
		static int T; //테스트 케이스의 개수
		static String str, reverse;
		
		public static void main(String[] args) throws NumberFormatException, IOException {
			br = new BufferedReader(new InputStreamReader(System.in));
			T = Integer.parseInt(br.readLine().trim());
			
			for(int test_case = 1; test_case <= T; test_case++) {
				str = br.readLine();
				sb = new StringBuffer(str);
				reverse = sb.reverse().toString();
				
				if(str.equals(reverse)) {
					System.out.println("#" + test_case + " " + 1);
				} else {
					System.out.println("#" + test_case + " " + 0);
				}
			}
		}
	
	}
