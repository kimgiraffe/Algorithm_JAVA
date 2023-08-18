package baekjoon;
import java.util.*;

class BOJ_11653_소인수분해_김세민 {
	
	public static void main(String[] args) throws Exception {
		Scanner sc = new Scanner(System.in);
		
		int N = sc.nextInt();
		
		int p = 2;
		
		if(N != 1) {
			while(N > 1) {
				if(N % p == 0) {
					N /= p;
					System.out.println(p);
					p = 2;
					continue;
				}
				else if(N != 1) {
					p++;
					continue;
				}
				else {
					break;
				}
			}
		}
		
		
		sc.close();
	}
}