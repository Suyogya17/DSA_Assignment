package Question1;

import java.util.Arrays;

public class A_VenueDecoration {
    public static int minCost(int[][] costs) {
        if (costs == null || costs.length == 0) {
            return 0;
        }
         int n = costs.length;
        int k = costs[0].length;
 
        int[] minCosts = new int[k];
        int[] secondMinCosts = new int[k];
 
        System.arraycopy(costs[0], 0, minCosts, 0, k);
        Arrays.fill(secondMinCosts, Integer.MAX_VALUE);
 
        for (int i = 1; i < n; i++) {
            int newMinCost, newSecondMinCost;
            newMinCost = newSecondMinCost = Integer.MAX_VALUE;
             for (int j = 0; j < k; j++) {
                if (minCosts[j] < newMinCost) {
                    newSecondMinCost = newMinCost;
                    newMinCost = minCosts[j];
                } else if (minCosts[j] < newSecondMinCost) {
                    newSecondMinCost = minCosts[j];
                }
            }
             for (int j = 0; j < k; j++) {
                minCosts[j] = costs[i][j] + ((minCosts[j] == newMinCost) ? newSecondMinCost : newMinCost);
            }
        }
         int result = Integer.MAX_VALUE;
        for (int j = 0; j < k; j++) {
            result = Math.min(result, minCosts[j]);
        }
         return result;
    }
     public static void main(String[] args) {
        int[][] costs = {{1, 3, 2}, {4, 6, 8}, {3, 1, 5}};
        int result = minCost(costs);
        System.out.println("Minimum cost to decorate all venues: " + result);
    }
}