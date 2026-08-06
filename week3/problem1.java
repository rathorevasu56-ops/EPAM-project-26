import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;
import java.util.Arrays;

public class ScholarshipDistribution {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokenizer = new StringTokenizer(reader.readLine());

        if (!tokenizer.hasMoreTokens()) return;
        int N = Integer.parseInt(tokenizer.nextToken());
        long budget = Long.parseLong(tokenizer.nextToken());

        int[] scholarships = new int[N];
        tokenizer = new StringTokenizer(reader.readLine());
        for (int i = 0; i < N; i++) {
            scholarships[i] = Integer.parseInt(tokenizer.nextToken());
        }

        // Sort requirements in ascending order (Greedy approach)
        Arrays.sort(scholarships);

        int studentCount = 0;
        for (int i = 0; i < N; i++) {
            if (budget >= scholarships[i]) {
                budget -= scholarships[i];
                studentCount++;
            } else {
                // If we can't afford this student, we can't afford any subsequent larger ones either
                break;
            }
        }

        System.out.println(studentCount);
    }
}
