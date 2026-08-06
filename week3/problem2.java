import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;

public class MaximumLearningPoints {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokenizer = new StringTokenizer(reader.readLine());

        if (!tokenizer.hasMoreTokens()) return;
        int N = Integer.parseInt(tokenizer.nextToken());

        int[] points = new int[N];
        tokenizer = new StringTokenizer(reader.readLine());
        for (int i = 0; i < N; i++) {
            points[i] = Integer.parseInt(tokenizer.nextToken());
        }

        if (N == 0) {
            System.out.println(0);
            return;
        }
        if (N == 1) {
            System.out.println(points[0]);
            return;
        }

        // Space-optimized DP
        // prev2 represents max points up to i-2
        // prev1 represents max points up to i-1
        long prev2 = points[0];
        long prev1 = Math.max(points[0], points[1]);

        for (int i = 2; i < N; i++) {
            long current = Math.max(prev1, prev2 + points[i]);
            prev2 = prev1;
            prev1 = current;
        }

        System.out.println(prev1);
    }
}
