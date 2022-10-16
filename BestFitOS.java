import java.util.Scanner;

public class BestFitOS {
     // main class
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        //input the number of free memory blocks to run programs
        //demo input: 4
        System.out.print("Enter number of free memory blocks to run programs: ");
        int block = sc.nextInt();

        //input the number of programs waiting to run (job)
        //demo input: 4
        System.out.print("Enter number of programs/jobs waiting to run: ");
        int job = sc.nextInt();
        
        // create array according to input sizes
        int blockSize[] = new int[block];
        int jobSize[] = new int[job];

        //input size of each memory blocks
        //demo input: 30 15 50 20
        System.out.print("Enter the size of each memory blocks: ");
        for (int i = 0; i < block; i++) {
            blockSize[i] = sc.nextInt();
        }

        //input the size of program/job 
        // demo input: 10 20 30 10
        System.out.print("Enter the size of each job: ");
        for (int i = 0; i < job; i++) {
            jobSize[i] = sc.nextInt();
        }

        // to print Job List table
        System.out.println("\nJob List: \nJob Number\tMemory Requested/Job Size");
        for (int i = 0; i < job; i++) {
            System.out.println(" " + (i + 1) + "\t\t " + jobSize[i]);
        }
        System.out.println();

        // print Memory Allocation from Best Fit method
        System.out.println("Memory Allocation: ");
        bestFit(blockSize, block, jobSize, job);
    }
    
    // Method to allocate memory to blocks using Best-Fit 
    static void bestFit(int blockSize[], int block, int jobSize[], int job) {
        // store an original default array for memory block size to refer 
        int original[] = new int[block];
        for (int i = 0; i < original.length; i++) {
            original[i] = blockSize[i];
        }
        // Stores block number (index) of the block allocated to a process 
        int allocation[] = new int[job];

        // Initialize all to -1 so no block is assigned to any process 
        for (int i = 0; i < allocation.length; i++) {
            allocation[i] = -1;
        }

        // Check each process and assign block according to size  
        for (int i = 0; i < job; i++) {   //for every job
            // Find the best fit block for current process 
            int bestFitIndex = -1;  //initializing bestFitIndex value to -1
            for (int j = 0; j < block; j++) {   //for every memory block
                if (blockSize[j] >= jobSize[i]) {
                    if (bestFitIndex == -1) {  //if the memory still available
                        bestFitIndex = j;
                    } 
                    else if (blockSize[bestFitIndex] > blockSize[j]) {
                        bestFitIndex = j;
                    }
                }
            }

            // If found a block for current process 
            if (bestFitIndex != -1) {
                // allocate bestFitIndex to handle the current job[i] 
                allocation[i] = bestFitIndex;

                // set block as not available 
               blockSize[bestFitIndex] = 0;
            }
        }

        //print memory list
        System.out.println("Job Number\tJob Size\tBlock Location\tMemory Block Size");
        for (int i = 0; i < job; i++) {
            System.out.print(" " + (i + 1) + "\t\t " + jobSize[i] + "\t\t ");
            if (allocation[i] != -1) {
                System.out.print(allocation[i] + 1 + "\t\t " + original[allocation[i]]);
            } 
            else {
                System.out.print("Not Allocated\t Not Allocated");
            }
            System.out.println();
        }
    }
}
