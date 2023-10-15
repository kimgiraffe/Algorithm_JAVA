package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class BOJ_2346_풍선터뜨리기_김세민 {

	static BufferedReader br;
	static StringTokenizer st;
	static StringBuilder sb;
	
	static int balloonCount;
	static Deque<Pair> deque = new ArrayDeque<>();
	
	static class Pair {
		int idx;
		int num;
		
		public Pair(int idx, int num) {
			this.idx = idx;
			this.num = num;
		}

		@Override
		public String toString() {
			return "Pair [idx=" + idx + ", num=" + num + "]";
		}
		
	}
	
	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		
		balloonCount = Integer.parseInt(br.readLine().trim());
		
		st = new StringTokenizer(br.readLine().trim());
		
		for(int idx = 1; idx <= balloonCount; idx++) {
			int move = Integer.parseInt(st.nextToken());
			deque.offer(new Pair(idx, move));
		}
		
		while (!deque.isEmpty()) {
			int curr = deque.peekFirst().num;
			sb.append(deque.pollFirst().idx).append(" ");
			
			if(deque.isEmpty()) {
				break;
			}
			
			if(curr > 0) {
				for(int idx = 0; idx < curr - 1; idx++) {
					deque.offerLast(deque.peekFirst());
					deque.pollFirst();
				}
			}
			else {
				for(int idx = 0; idx < -curr; idx++) {
					deque.offerFirst(deque.peekLast());
					deque.pollLast();
				}
			}
		}
		
		System.out.println(sb);
	}
}
