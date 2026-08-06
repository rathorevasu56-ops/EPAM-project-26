import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;
import java.util.ArrayList;
import java.util.List;

public class TreeOfTrustedServers {
    static int trustedCount = 0;
    static int K;
    static int[] keys;
    static List<List<Integer>> adj;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokenizer = new StringTokenizer(reader.readLine());

        if (!tokenizer.hasMoreTokens()) return;
        int N = Integer.parseInt(tokenizer.nextToken());
        K = Integer.parseInt(tokenizer.nextToken());

        keys = new int[N + 1];
        tokenizer = new StringTokenizer(reader.readLine());
        for (int i = 1; i <= N; i++) {
            keys[i] = Integer.parseInt(tokenizer.nextToken());
        }

        adj = new ArrayList<>();
        for (int i = 0; i <= N; i++) {
            adj.add(new ArrayList<>());
        }

        for (int i = 0; i < N - 1; i++) {
            tokenizer = new StringTokenizer(reader.readLine());
            int u = Integer.parseInt(tokenizer.nextToken());
            int v = Integer.parseInt(tokenizer.nextToken());
            adj.get(u).add(v);
            adj.get(v).add(u);
        }

        // Start DFS from root (Node 1) with parent 0 and initial XOR 0
        dfs(1, 0, 0);

        System.out.println(trustedCount);
    }

    private static void dfs(int node, int parent, int currentXor) {
        // Accumulate XOR for the current node
        currentXor ^= keys[node];

        // Check against threshold K
        if (currentXor >= K) {
            trustedCount++;
        }

        // Traverse children
        for (int neighbor : adj.get(node)) {
            if (neighbor != parent) {
                dfs(neighbor, node, currentXor);
            }
        }
    }
}
