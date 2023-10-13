package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

/**
 * BOJ_2812_크게만들기
 * 
 * [문제]
 * N자리 숫자가 주어졌을 때, 여기서 숫자 K개를 지워서 얻을 수 있는 가장 큰 수를 구하는 프로그램을 작성하시오.
 * (1<=K<N<=500,000)
 * 시간 제한 1초
 * 
 * [풀이]
 * 1. 첫번째 숫자부터 덱에 넣는다.
 * 2. 덱에 있는 숫자보다 더 크다면 덱에 있는 숫자를 모두 꺼내고 지운 개수만큼 K를 감소한다.
 * 3. 지운 개수가 적은 경우 (큰 수부터 들어오는경우) 덱의 앞에서부터 N-K 만큼 출력한다.
 * 
 * @author semin.kim
 */

public class BOJ_2812_크게만들기_김세민 {

	static BufferedReader br;
	static StringTokenizer st;
	static StringBuilder sb;

	static int digitCount, removeCount; // 자릿수, 지울 숫자의 개수
	static String number;
	static Deque<Integer> deque = new ArrayDeque<>();

	private static void makeBig() {
		for(int idx = 0; idx < digitCount; idx++) {
			while (!deque.isEmpty() && removeCount > 0 && deque.peekLast() < number.charAt(idx) -'0') {
				deque.pollLast();
				removeCount--;
			}
			deque.offerLast(number.charAt(idx)-'0');
		}
	}
	
	private static void printBiggest() {
		while(deque.size() > removeCount) {
			sb.append(deque.pollFirst());
		}
		System.out.println(sb);
	}

	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		st = new StringTokenizer(br.readLine().trim());
		sb = new StringBuilder();

		digitCount = Integer.parseInt(st.nextToken());
		removeCount = Integer.parseInt(st.nextToken());

		number = br.readLine().trim();

		makeBig();
		
		printBiggest();
	}
}
