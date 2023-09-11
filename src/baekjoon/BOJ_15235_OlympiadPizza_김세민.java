package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * BOJ_15235_OlympiadPizza
 * 대회 참가자들에게 순서대로 피자를 나눠준다.
 * 참가자들은 첫번째 피자를 받은 후 다음 피자를 받으러 올 수 있다.
 * 
 * 
 * @author semin.kim
 */

public class BOJ_15235_OlympiadPizza_김세민 {

	static BufferedReader br;
	static StringTokenizer st;
	static StringBuilder sb;
	
	static int N;
	static Queue<Integer> queue = new LinkedList<>();
	static int[] slices;
	static ArrayList<Integer> list = new ArrayList<>();
	static int total;
	
	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		
		N = Integer.parseInt(br.readLine().trim());
		slices = new int[N+1];
		
		
		st = new StringTokenizer(br.readLine().trim());
		
		for(int idx = 1; idx <= N; idx++) {
			slices[idx] = Integer.parseInt(st.nextToken());
			total += slices[idx];
		}
		
		int idx = 1;
		while(true) {
			if(total == 0) {
				break;
			}
			if(slices[idx] > 0) {
				queue.offer(idx);
				slices[idx]--;
				total--;
			}
			
			idx = (idx + 1) % (N + 1);
		}
		while(!queue.isEmpty()) {
			list.add(queue.poll());
		}
		// System.out.println(list);
		for(int num = 1; num <= N; num++) {
			sb.append(list.lastIndexOf(num)+1).append(" ");
		}
		System.out.println(sb);
	}
}
