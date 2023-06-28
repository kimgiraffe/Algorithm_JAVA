import java.util.Scanner;

public class BOJ_2753 {
	public static void main(String args[]) {
		Scanner sc = new Scanner(System.in);
		
		int y = sc.nextShort();
		
		if((y%4 == 0 && y%100 != 0) || y % 400 == 0) {
			System.out.println(1);
		}
		else {
			System.out.println(0);
		}
		sc.close();

	}
}