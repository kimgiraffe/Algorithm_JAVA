import java.util.StringTokenizer;
import java.io.*;

/**
 * @author kimgiraffe
 */

public class BOJ_20529 {
	public static int calculate_distance(String s1, String s2) {
		int distance = 0;
		
		for(int i = 0; i < 4; i++) {
			if(s1.charAt(i) != s2.charAt(i)) {
				distance++;
			}
		}
		
		return distance;
	}
	
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int T = Integer.parseInt(br.readLine());
		
		for(int test_case = 1; test_case <= T; test_case++) {
			int N = Integer.parseInt(br.readLine());
			st = new StringTokenizer(br.readLine(), " ");
			
			if(N > 32) {
				System.out.println(0);
				continue;
			}
			
			String[] MBTI = new String[N];
			for(int idx = 0; idx < N; idx++) {
				MBTI[idx] = st.nextToken();
			}
			
			int MIN = 100;
			
			for(int i = 0; i < N - 2; i++) {
				for(int j= i + 1; j < N - 1; j++) {
					for(int k = j + 1; k < N; k++) {
						MIN = Math.min(MIN, calculate_distance(MBTI[i], MBTI[j]) + calculate_distance(MBTI[j], MBTI[k]) + calculate_distance(MBTI[k], MBTI[i]));
						if(MIN == 0) break;
						
					}
				}
			}
			System.out.println(MIN);
		}	
	}
}
