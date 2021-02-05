// -----------------------------------------------------------------------------
// Author: Matthew Clark
// Module: CO518
//  Assignment 2 (2020-21)
//  Task 1
// Program: Tower of Hanoi problem with any number of towers
// -----------------------------------------------------------------------------

import java.util.Scanner;  // Import the Scanner class
import java.io.*; // Import the I/O library

public class mc974_task1 {

    // ---------------------------------------------------------------------
    // Function: Empty Constructor
    // ---------------------------------------------------------------------
    public mc974_task1 ()
    {

    }

    // ---------------------------------------------------------------------
    // Function: Prints every move on screen and also writes it to a file
    // ---------------------------------------------------------------------
    static public int print_move (int disc, int source_tower, int destination_tower, FileWriter writer)
    {
        try {
            writer.write("\n" + disc + "\t" +  source_tower + "\t" + destination_tower);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return 0;
    }

    // ---------------------------------------------------------------------
    // Function: The recursive function for moving n discs 
    //           from s to d with t-2 buffer towers.
    //           It prints all the moves with disk numbers.
    // ---------------------------------------------------------------------
    static void towerOfHanoi(int n, int t, int from_rod, int to_rod, FileWriter writer)
    {
        // The following are hard-coded statements for an example instance (6,5,1,2)
        if (n == 0) 
            return; 
        if (n == 1) { 
            System.out.println("Move disk " + n + " from rod " + from_rod + " to rod " + to_rod); 
            return; 
        } 

        //create an array of aux rods

        int[] auxRods = new int[t-2];

        //counter int
        int c = 1;

        for(int i = 0; i < t-2; c++){
            //check if aux rod = source or destination
            if(c != from_rod && c != to_rod){
                auxRods[i]=c; 
                i++;
            }

        }

        //check if number of disks is less than or equal to number of towers -1
        if(n<=t-1){
            //move disks onto buffer towers
            for (int i = n-1; i >= 1; i--){
                //print the disks moving from source to buffer
                print_move (n-i, from_rod, auxRods[i-1], writer);
                System.out.println("Move disk " + (n-i) + " from rod " + from_rod + " to rod " + auxRods[i-1]);

            }

            //move largest disk from source to destination
            print_move (n, from_rod, to_rod, writer);
            System.out.println("Move disk " + n + " from rod " + from_rod + " to rod " + to_rod);

            //move all other disks from buffer towers to destination     
            for (int i = 1; i <= n-1; i++){
                //print the disks moving from buffers to destination
                print_move (n-i, auxRods[i-1], to_rod, writer);
                System.out.println("Move disk " + (n-i) + " from rod " + auxRods[i-1] + " to rod " + to_rod);

            }
        }
        else {
            //for when n > t-1
            //create an int for getting descending values from the auxRod list
            int g = auxRods.length - 1;
            //move smallest half of disks to buffers
            for (int i = n-1; i >= n/2; i--){
                if(g>=0){
                    //print the disks moving from source to buffers
                    print_move (n-i, from_rod, auxRods[g], writer);
                    System.out.println("Move disk " + (n-i) + " from rod " + from_rod + " to rod " + auxRods[g]);
                    g--;
                }
            }
            //int for printing out which disk is moving
            int h = (n-1)/2;
            //move all smallest disks from buffers into one stack on one buffer
            //if n is even
            if(n % 2 == 0){
                for (int i = 1; i < n/2; i++){
                    //print the disks moving from individual buffers to a stack on one buffer
                    print_move (h, auxRods[i],auxRods[0], writer);
                    System.out.println("Move disk " + (h) + " from rod " + auxRods[i] + " to rod " + auxRods[0]);
                    h--;
                }
            }
            //if n is odd
            else{
                for (int i = 1; i <= n/2; i++){
                    //print the disks moving from individual buffers to a stack on one buffer
                    print_move (h, auxRods[i],auxRods[0], writer);
                    System.out.println("Move disk " + (h) + " from rod " + auxRods[i] + " to rod " + auxRods[0]);
                    h--;
                }
            }
            //create an int for getting descending values from the auxRod list
            int f = auxRods.length - 1;
            if(n % 2 == 0){
                //int for printing out which disk is moving
                int j = (n/2)+1;
                //move largest half of disks (other than the largest disk) to buffers
                for (int i = 0; i < (n-1)/2; i++){
                    if(f>=0 && j<n){
                        //print the disks moving from source to buffers
                        print_move (j, from_rod, auxRods[f], writer);
                        System.out.println("Move disk " + j + " from rod " + from_rod + " to rod " + auxRods[f]);
                        f--;
                        j++;
                    }
                }
            }
            //if n is odd
            else{
                //int for printing out which disk is moving
                int j = (n/2)+2;
                //move largest half of disks (other than the largest disk) to buffers
                for (int i = 0; i < (n-1)/2; i++){
                    if(f>=0 && j<n){
                        //print the disks moving from source to buffers
                        print_move (j, from_rod, auxRods[f], writer);
                        System.out.println("Move disk " + j + " from rod " + from_rod + " to rod " + auxRods[f]);
                        f--;
                        j++;
                    }
                }
            }

            //move largest disk from source to destination
            print_move (n, from_rod, to_rod, writer);
            System.out.println("Move disk " + n + " from rod " + from_rod + " to rod " + to_rod);
            //move larger half of disks from buffers to destination
            if(n % 2 == 0){
                for (int i = 1; i <= auxRods.length - 1; i++){
                    //print the disks moving from buffers to destination
                    print_move (n-i, auxRods[i], to_rod, writer);
                    System.out.println("Move disk " + (n-i) + " from rod " + auxRods[i] + " to rod " + to_rod);

                }
            }
            else{
                for (int i = 1; i < auxRods.length - 1; i++){
                    //print the disks moving from buffers to destination
                    print_move (n-i, auxRods[i+1], to_rod, writer);
                    System.out.println("Move disk " + (n-i) + " from rod " + auxRods[i+1] + " to rod " + to_rod);

                }
            }
            int q = auxRods.length - 1;
            //move smallest half of disks from stack on first buffer to other buffers
            for (int i = n-1; i > n/2; i--){
                if(q>=0){
                    //print the disks moving from the first buffer to individual buffers 
                    print_move (n-i, auxRods[0], auxRods[q], writer);
                    System.out.println("Move disk " + (n-i) + " from rod " + auxRods[0] + " to rod " + auxRods[q]);
                    q--;
                }
            }

            //move largest disk of smaller half of disks from buffer to destination
            //if n is even
            if(n % 2 == 0){
                //print the largest small disk moving from first buffer to destination
                print_move (n/2, auxRods[0], to_rod, writer);
                System.out.println("Move disk " + n/2 + " from rod " + auxRods[0] + " to rod " + to_rod);
            }
            //if n is odd
            else {
                //print the largest small disk moving from first buffer to destination
                print_move (((n/2)+1), auxRods[0], to_rod, writer);
                System.out.println("Move disk " + ((n/2)+1) + " from rod " + auxRods[0] + " to rod " + to_rod);
            }
            //move all other disks from buffer towers to destination 
            //int for getting buffer towers
            int y = 1;
            //if n is even
            if(n % 2 == 0){
                //int for printing out which disk is moving
                int x = (n/2)-1;
                for (int i = n-1; i > (n/2); i--){
                    if(y<=auxRods.length){
                        //print the disks moving from buffer towers to destination
                        print_move (x, auxRods[y], to_rod, writer);
                        System.out.println("Move disk " + x + " from rod " + auxRods[y] + " to rod " + to_rod);
                        y++;
                        x--;
                    }
                }
            }
            //if n is odd
            else{
                int x = (n/2);
                for (int i = n-1; i > (n/2); i--){
                    if(y<=auxRods.length){
                        //print the disks moving from buffer towers to destination
                        print_move (x, auxRods[y], to_rod, writer);
                        System.out.println("Move disk " + x + " from rod " + auxRods[y] + " to rod " + to_rod);
                        y++;
                        x--;
                    }
                }
            }
        }
    }

    // ---------------------------------------------------------------------
    // Function: main
    // ---------------------------------------------------------------------
    public static void main(String[] args) {
        Scanner myObj = new Scanner(System.in);  // Create a Scanner object
        int n, t, s, d;
        String my_ID = new String("mc974");

        n = myObj.nextInt();  // Read user input n
        t = myObj.nextInt();  // Read user input t
        s = myObj.nextInt();  // Read user input s
        d = myObj.nextInt();  // Read user input d

        // Check the inputs for sanity
        if (n<1 || t<3 || s<1 || s>t || d<1 || d>t)
        {
            System.out.printf("Please enter proper parameters. (n>=1; t>=3; 1<=s<=t; 1<=d<=t)\n");
            return;
        }
        // Create the output file name
        String filename;
        filename = new String(my_ID + "_ToH_n" + n + "_t" + t + "_s" + s + "_d" + d +  ".txt");
        try {
            // Create the Writer object for writing to "filename"
            FileWriter writer = new FileWriter(filename, true);

            // Write the first line: n, t, s, d
            writer.write(n + "\t" + t + "\t" + s + "\t" + d + "\t");

            // Create the object of this class to solve the generalised ToH problem
            mc974_task1 ToH = new mc974_task1();

            System.out.printf("\nThe output is also written to the file %s", filename);
            System.out.println("");
            // Call the recursive function that solves the ToH problem
            ToH.towerOfHanoi(n, t, s, d, writer);

            // Close the file
            writer.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.printf("\n");
        return;
    }

}
