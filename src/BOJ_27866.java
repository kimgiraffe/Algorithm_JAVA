import java.util.Scanner;

public class BOJ_27866 {
	public static void main(String args[]) {
		Scanner sc = new Scanner(System.in);
		
		String[] str = sc.next().split("");
		int i = sc.nextInt();
		
		System.out.println(str[i-1]);
		sc.close();

	}
}