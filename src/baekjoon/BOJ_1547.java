package baekjoon;
import java.util.Scanner;

/**
 * @author kimgiraffe
 */

public class BOJ_1547 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int M = sc.nextInt(); // 컵의 위치를 바꾼 횟수
		int[] ball = {0, 1, 2, 3};		
		for(int i = 0; i < M; i++) {
			int X = sc.nextInt();
			int Y = sc.nextInt();
			int tmp = ball[X];
			ball[X] = ball[Y];
			ball[Y] = tmp;
		}
		
		for(int i = 0; i < 4; i++) {
			if(ball[i] == 1) {
				System.out.println(i);
			}
		}
		
		sc.close();
	}

}
