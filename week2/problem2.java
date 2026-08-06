import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.LinkedList;
import java.util.Arrays;

public class EmergencyRouteValidation {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokenizer = new StringTokenizer(reader.readLine());

        if (!tokenizer.hasMoreTokens()) return;
        int N = Integer.parseInt(tokenizer.nextToken());
        int M = Integer.parseInt(tokenizer.nextToken());
        int D = Integer.parseInt(tokenizer.nextToken());

        List<List<Integer>> adj = new ArrayList<>();
        for (int i = 0; i <= N; i++) {
            adj.add(new ArrayList<>());
        }

        for (int i = 0; i < M; i++) {
            tokenizer = new StringTokenizer(reader.readLine());
            int u = Integer.parseInt(tokenizer.nextToken());
            int v = Integer.parseInt(tokenizer.nextToken());
            adj.get(u).add(v);
            adj.get(v).add(u);
        }

        int[] dist = new int[N + 1];
        Arrays.fill(dist, -1);

        Queue<Integer> queue = new LinkedList<>();
        
        // Capital city (City 1) initialization
        queue.add(1);
        dist[1] = 0;
        int reachableCount = 1;

        while (!queue.isEmpty()) {
            int curr = queue.poll();

            // If current distance is already D, we can't add more nodes within limit from here
            if (dist[curr] >= D) {
                continue;
            }

            for (int neighbor : adj.get(curr)) {
                if (dist[neighbor] == -1) { // Unvisited
                    dist[neighbor] = dist[curr] + 1;
                    if (dist[neighbor] <= D) {
                        reachableCount++;
                    }
                    queue.add(neighbor);
                }
            }
        }

        System.out.println(reachableCount);
    }
}
