package Question1;

import java.util.Arrays;

public class A_VenueDecoration {
 
    // Function to calculate the minimum cost of decorating all venues while adhering to the adjacency constraint
    public static int minCost(int[][] costs) {
        // Check if the input matrix is empty or null
        if (costs == null || costs.length == 0) {
            return 0;
        }
 
        // Get the number of venues and available themes
        int n = costs.length;
        int k = costs[0].length;
 
        // Initialize arrays to store the minimum and second minimum costs for each theme for the current venue
        int[] minCosts = new int[k];
        int[] secondMinCosts = new int[k];
 
        // Base case: Initialize the first row of the arrays with the costs of decorating the first venue
        System.arraycopy(costs[0], 0, minCosts, 0, k);
        Arrays.fill(secondMinCosts, Integer.MAX_VALUE);
 
        // Dynamic Programming Iteration: Iterate through venues starting from the second one
        for (int i = 1; i < n; i++) {
            int newMinCost, newSecondMinCost;
            newMinCost = newSecondMinCost = Integer.MAX_VALUE;
 
            // Calculate the minimum and second minimum costs for each theme considering the adjacency constraint
            for (int j = 0; j < k; j++) {
                if (minCosts[j] < newMinCost) {
                    newSecondMinCost = newMinCost;
                    newMinCost = minCosts[j];
                } else if (minCosts[j] < newSecondMinCost) {
                    newSecondMinCost = minCosts[j];
                }
            }
 
            // Update the arrays with the updated minimum and second minimum costs for the current venue
            for (int j = 0; j < k; j++) {
                minCosts[j] = costs[i][j] + ((minCosts[j] == newMinCost) ? newSecondMinCost : newMinCost);
            }
        }
 
        // Find the minimum cost among the last row of the minCosts array
        int result = Integer.MAX_VALUE;
        for (int j = 0; j < k; j++) {
            result = Math.min(result, minCosts[j]);
        }
 
        // Return the minimum cost as the final result
        return result;
    }
 
    // Main method to demonstrate the usage of the minCost function
    public static void main(String[] args) {
        // Example cost matrix
        int[][] costs = {{1, 3, 2}, {4, 6, 8}, {3, 1, 5}};
        // Call the minCost function with the example input
        int result = minCost(costs);
        // Print the result
        System.out.println("Minimum cost to decorate all venues: " + result);
    }
}