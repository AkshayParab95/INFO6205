/**
 * Original code:
 * Copyright © 2000–2017, Robert Sedgewick and Kevin Wayne.
 * <p>
 * Modifications:
 * Copyright (c) 2017. Phasmid Software
 */
package edu.neu.coe.info6205.union_find;

import java.util.Arrays;
import java.util.Random;

/**
 * Height-weighted Quick Union with Path Compression
 */
public class UF_HWQUPC implements UF {
    /**
     * Ensure that site p is connected to site q,
     *
     * @param p the integer representing one site
     * @param q the integer representing the other site
     */
    public void connect(int p, int q) {
        if (!isConnected(p, q)) union(p, q);
    }

    /**
     * Initializes an empty union–find data structure with {@code n} sites
     * {@code 0} through {@code n-1}. Each site is initially in its own
     * component.
     *
     * @param n               the number of sites
     * @param pathCompression whether to use path compression
     * @throws IllegalArgumentException if {@code n < 0}
     */
    public UF_HWQUPC(int n, boolean pathCompression) {
        count = n;
        parent = new int[n];
        height = new int[n];
        for (int i = 0; i < n; i++) {
            parent[i] = i;
            height[i] = 1;
        }
        this.pathCompression = pathCompression;
    }

    /**
     * Initializes an empty union–find data structure with {@code n} sites
     * {@code 0} through {@code n-1}. Each site is initially in its own
     * component.
     * This data structure uses path compression
     *
     * @param n the number of sites
     * @throws IllegalArgumentException if {@code n < 0}
     */
    public UF_HWQUPC(int n) {
        this(n, true);
    }

    public void show() {
        for (int i = 0; i < parent.length; i++) {
            System.out.printf("%d: %d, %d\n", i, parent[i], height[i]);
        }
    }

    /**
     * Returns the number of components.
     *
     * @return the number of components (between {@code 1} and {@code n})
     */
    public int components() {
        return count;
    }

    /**
     * Returns the component identifier for the component containing site {@code p}.
     *
     * @param p the integer representing one site
     * @return the component identifier for the component containing site {@code p}
     * @throws IllegalArgumentException unless {@code 0 <= p < n}
     */
    public int find(int p) {
        validate(p);
        int root = p;
        // FIXME
        while(root != parent[root]){
            if(this.pathCompression) doPathCompression(p);
            root = parent[root];
        }
        // END 
        return root;
    }

    /**
     * Returns true if the the two sites are in the same component.
     *
     * @param p the integer representing one site
     * @param q the integer representing the other site
     * @return {@code true} if the two sites {@code p} and {@code q} are in the same component;
     * {@code false} otherwise
     * @throws IllegalArgumentException unless
     *                                  both {@code 0 <= p < n} and {@code 0 <= q < n}
     */
    public boolean connected(int p, int q) {
        return find(p) == find(q);
    }

    /**
     * Merges the component containing site {@code p} with the
     * the component containing site {@code q}.
     *
     * @param p the integer representing one site
     * @param q the integer representing the other site
     * @throws IllegalArgumentException unless
     *                                  both {@code 0 <= p < n} and {@code 0 <= q < n}
     */
    public void union(int p, int q) {
        // CONSIDER can we avoid doing find again?
        mergeComponents(find(p), find(q));
        count--;
    }

    @Override
    public int size() {
        return parent.length;
    }

    /**
     * Used only by testing code
     *
     * @param pathCompression true if you want path compression
     */
    public void setPathCompression(boolean pathCompression) {
        this.pathCompression = pathCompression;
    }

    @Override
    public String toString() {
        return "UF_HWQUPC:" + "\n  count: " + count +
                "\n  path compression? " + pathCompression +
                "\n  parents: " + Arrays.toString(parent) +
                "\n  heights: " + Arrays.toString(height);
    }

    // validate that p is a valid index
    private void validate(int p) {
        int n = parent.length;
        if (p < 0 || p >= n) {
            throw new IllegalArgumentException("index " + p + " is not between 0 and " + (n - 1));
        }
    }

    private void updateParent(int p, int x) {
        parent[p] = x;
    }

    private void updateHeight(int p, int x) {
        height[p] += height[x];
    }

    /**
     * Used only by testing code
     *
     * @param i the component
     * @return the parent of the component
     */
    private int getParent(int i) {
        return parent[i];
    }

    private final int[] parent;   // parent[i] = parent of i
    private final int[] height;   // height[i] = height of subtree rooted at i
    private int count;  // number of components
    private boolean pathCompression;

    private void mergeComponents(int i, int j) {
        // FIXME make shorter root point to taller one
    	if (i == j) return;

        if (height[i] <  height[j]) {
            updateParent(i, j);
            updateHeight(j, i);
        }else {
            updateParent(j, i);
            updateHeight(i, j);
        }
        // END 
    }

    /**
     * This implements the single-pass path-halving mechanism of path compression
     */
    private void doPathCompression(int i) {
        // FIXME update parent to value of grandparent
    	if(i == parent[i]) return;
    	updateParent(i, parent[parent[i]]);
        // END 
    }
    
    public static void main(String[] args){
    	Random randomNumGenerator = new Random();
        int numberOfSites[] = {1250, 2500, 5000, 10000, 20000, 40000, 80000, 160000, 320000, 640000, 1280000, 2560000, 5120000, 10240000};
        int connectionCount;
        double ratio = 0.0;
        for (int i=0; i<numberOfSites.length; i++){
        	
            connectionCount = 0;
            int n = numberOfSites[i];
            double adjustedLinearithmic = 0.5309*n*Math.log(n);
            UF_HWQUPC unionFind = new UF_HWQUPC(n);
            while(unionFind.components() > 1){
            	connectionCount++;
                int site1 = randomNumGenerator.nextInt(n);
                int site2 = randomNumGenerator.nextInt(n);
                unionFind.connect(site1, site2);
            }
            ratio += connectionCount/adjustedLinearithmic;
            System.out.println("Number Of Sites = "+n+" Number of Connections = "+connectionCount+" Linearithmic Complexity = "+adjustedLinearithmic);
        }
    }
}

/* Output:
Number Of Sites = 1250 Number of Connections = 4030 Linearithmic Complexity = 4732.242736255413
Number Of Sites = 2500 Number of Connections = 9452 Linearithmic Complexity = 10384.465067909014
Number Of Sites = 5000 Number of Connections = 25734 Linearithmic Complexity = 22608.889326614404
Number Of Sites = 10000 Number of Connections = 45745 Linearithmic Complexity = 48897.69703482156
Number Of Sites = 20000 Number of Connections = 109180 Linearithmic Complexity = 105155.2308328286
Number Of Sites = 40000 Number of Connections = 226786 Linearithmic Complexity = 225030.1351920282
Number Of Sites = 80000 Number of Connections = 466505 Linearithmic Complexity = 479499.6174367984
Number Of Sites = 160000 Number of Connections = 1010141 Linearithmic Complexity = 1017877.9289790808
Number Of Sites = 320000 Number of Connections = 2088176 Linearithmic Complexity = 2153513.2461691294
Number Of Sites = 640000 Number of Connections = 4218314 Linearithmic Complexity = 4542541.268760195
Number Of Sites = 1280000 Number of Connections = 8451916 Linearithmic Complexity = 9556112.090364262
Number Of Sites = 2560000 Number of Connections = 20652367 Linearithmic Complexity = 2.005428328641627E7
Number Of Sites = 5120000 Number of Connections = 39716247 Linearithmic Complexity = 4.199268478420802E7
Number Of Sites = 10240000 Number of Connections = 87556349 Linearithmic Complexity = 8.775360599116704E7
*/
