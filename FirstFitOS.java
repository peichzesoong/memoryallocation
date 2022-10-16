import java.util.Scanner;

public class FirstFitOS {
    // main class 
    public static void main(String[] args) {
       Scanner sc = new Scanner(System.in);

        //input the number of free memory blocks to run programs
        //demo input: 5
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
        //demo input: 100 500 200 300 600
        System.out.print("Enter the size of each memory blocks: ");
        for (int i = 0; i < block; i++) {
            blockSize[i] = sc.nextInt();
        }

        //input the size of program/job 
        // demo input: 212 417 112 426
        System.out.print("Enter the size of each job: ");
        for (int i = 0; i < job; i++) {
            jobSize[i] = sc.nextInt();
        }

        // to print Job List table
        System.out.println("\nJob List: \nJob Number\tMemory Requested/Job Size");
        for (int i = 0; i < job; i++) {
            System.out.println(" " + (i + 1) + "\t\t " + jobSize[i]);
        }

        // print Memory Allocation from Best Fit method
        System.out.println("\nMemory Allocation: ");
        firstFit(blockSize, block, jobSize, job);
    }
    
    // Method to allocate memory to blocks using Best-Fit 
    static void firstFit(int blockSize[], int block, int jobSize[], int job) {
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

        // Check each process and assign block according to nearest available space
        for (int i = 0; i < job; i++) {   //for every job
            // Find the first fit block for current process 
            for (int j = 0; j < block; j++) { //for every memory block

                if (blockSize[j] >= jobSize[i]) {
                    // allocate block j to handle the current job[i] 
                    allocation[i] = j;
                    // set block as not available 
                    blockSize[j] = 0;
                    break;      //stop and done with the current processing job
                }

            }
        }

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
