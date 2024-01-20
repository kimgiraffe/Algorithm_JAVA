package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_21921_블로그_김세민 {
    static BufferedReader br;
    static StringTokenizer st;
    static StringBuilder sb;

    static int N, X;
    static int[] visit;
    static int window;
    static int max;
    static int count = 1;

    public static void solve(){
        for(int idx = X; idx < N; idx++){
            window += visit[idx] - visit[idx - X];
            if(window == max){
                count++;
            }
            else if(window > max){
                max = window;
                count = 1;
            }
        }

        if(max == 0){
            sb.append("SAD");
        }
        else {
            sb.append(max).append("\n").append(count);
        }

        System.out.print(sb);
    }

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine().trim());
        sb = new StringBuilder();

        N = Integer.parseInt(st.nextToken());
        X = Integer.parseInt(st.nextToken());

        visit = new int[N];

        st = new StringTokenizer(br.readLine().trim());
        for(int idx = 0; idx < N; idx++){
            visit[idx] = Integer.parseInt(st.nextToken());
        }

        for(int idx = 0; idx < X; idx++){
            window += visit[idx];
        }

        max = window;

        solve();
    }
}
