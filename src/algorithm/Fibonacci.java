package algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Fibonacci {

	static BufferedReader br;
	static int N;
	
	private static void fibonacci() {
		long ret = (long) ((1/Math.sqrt(5)) * (Math.pow((1+Math.sqrt(5))/2, N) - Math.pow((1-Math.sqrt(5))/2, N)));
		System.out.println(ret);
	}
	
	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine().trim());
		
		fibonacci();
	}
}
