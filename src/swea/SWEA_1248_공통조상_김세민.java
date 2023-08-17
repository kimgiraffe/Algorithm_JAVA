package swea;
import java.util.Scanner;

class SWEA_1248_공통조상_김세민 {
	public static class Node{
		int parent, left, right;
		
		Node(int parent, int left, int right){
			this.parent = parent;
			this.left = left;
			this.right = right;
		}
	}
	
	static Node[] Tree;
	static int V, E, v1, v2, curr;
	static boolean[] visited;
	
	
	public static void main(String[] args) throws Exception {
		Scanner sc = new Scanner(System.in);
		
		int T = sc.nextInt();
		for(int test_case = 1; test_case <= T; test_case++) {
			V = sc.nextInt(); E = sc.nextInt();
			v1 = sc.nextInt(); v2 = sc.nextInt();
			
			Tree = new Node[V + 1];
			visited = new boolean[V + 1];
			
			for(int i = 0; i <= V; i++) {
				Tree[i] = new Node(0, 0, 0);
				visited[i] = false;
			}
			
			for(int i = 0; i < E; i++) {
				int parent = sc.nextInt();
				int child = sc.nextInt();
				
				if(Tree[parent].left == 0) {
					Tree[parent].left = child;
				}
				else {
					Tree[parent].right = child;
				}
				
				Tree[child].parent = parent;
			}
			
			curr = Tree[v1].parent;
			while(true) {
				visited[curr] = true;
				if(curr == 1) break;
				curr = Tree[curr].parent;
			}
			
			curr = Tree[v2].parent;
			while(true) {
				if(visited[curr]) {
					System.out.println("#" + test_case + " " + curr + " " + DFS(curr));
					break;
				}
				curr = Tree[curr].parent;
			}
		}
		
		sc.close();
	}
	
	public static int DFS(int curr) {
		int ans = 1;
		
		if(Tree[curr].left != 0) {
			ans += DFS(Tree[curr].left);
		}
		
		if(Tree[curr].right != 0) {
			ans += DFS(Tree[curr].right);
		}
		
		return ans;
	}
}