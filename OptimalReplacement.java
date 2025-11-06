import java.util.*;

public class OptimalReplacement {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.println("Please enter the number of Frames: ");
        int frames = sc.nextInt();

        System.out.println("Please enter the length of the Reference string:");
        int n = sc.nextInt();

        int[] ref = new int[n];
        int[] mem = new int[frames];
        int[][] layout = new int[n][frames];

        // Initialize memory frames as empty (-1)
        for (int i = 0; i < frames; i++)
            mem[i] = -1;

        System.out.println("Please enter the reference string: ");
        for (int i = 0; i < n; i++)
            ref[i] = sc.nextInt();

        int hit = 0, fault = 0, pointer = 0;
        boolean full = false;

        // Process each page in reference string
        for (int i = 0; i < n; i++) {
            int page = ref[i];
            boolean found = false;

            // Check if page is already in memory (hit)
            for (int j = 0; j < frames; j++) {
                if (mem[j] == page) {
                    hit++;
                    found = true;
                    break;
                }
            }

            // If not found => page fault
            if (!found) {
                fault++;

                // If frames are full, replace optimal page
                if (full) {
                    int pos = getReplaceIndex(mem, ref, i + 1);
                    mem[pos] = page;
                } else { // Fill empty frames first
                    mem[pointer++] = page;
                    if (pointer == frames) {
                        pointer = 0;
                        full = true;
                    }
                }
            }

            // Save current memory layout
            for (int j = 0; j < frames; j++)
                layout[i][j] = mem[j];
        }

        // Print memory layout
        for (int i = 0; i < frames; i++) {
            for (int j = 0; j < n; j++)
                System.out.printf("%3d ", layout[j][i]);
            System.out.println();
        }

        // Print hit and fault info
        System.out.println("The number of Hits: " + hit);
        System.out.println("Hit Ratio: " + (float) hit / n);
        System.out.println("The number of Faults: " + fault);
    }

    // Function to find which page to replace (farthest future use)
    static int getReplaceIndex(int[] mem, int[] ref, int start) {
        int farthest = start, index = -1;

        for (int i = 0; i < mem.length; i++) {
            int j;
            for (j = start; j < ref.length; j++) {
                if (mem[i] == ref[j])
                    break;
            }
            if (j == ref.length) // Page not used again
                return i;
            if (j > farthest) {  // Farthest next use
                farthest = j;
                index = i;
            }
        }
        return (index == -1) ? 0 : index;
    }
}
