package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ_4811_알약_김세민 {
    static BufferedReader br;
    static StringBuilder sb;

    static long[][] dp = new long[32][32];

    static void init() {
        for(int h = 0; h <= 30; h++) {
            dp[0][h] = 1;
        }

        for(int w = 1; w <= 30; w++) {
            for(int h = 0; h <= 30; h++) {
                if(h == 0) dp[w][0] = dp[w-1][1];
                else dp[w][h] = dp[w-1][h+1] + dp[w][h-1];
            }
        }
    }
    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        sb = new StringBuilder();

        init();

        while(true) {
            int N = Integer.parseInt(br.readLine());
            if(N == 0) break;

            sb.append(dp[N][0]).append("\n");
        }

        System.out.println(sb);
    }
}
