/*
 * Copyright 2017 Ronen Gilead-Raz <ronengi@gmail.com>.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

/**
 *
 * @author Ronen Gilead-Raz <ronengi@gmail.com>
 */
public class MyWeightedQuickUnionUF implements UF {

    @SuppressWarnings("FieldMayBeFinal")
    private int[] id;
    private int[] sz;

    /**
     * Set id of each object to itself (N array accesses).
     * @param N number of objects
     */
    public MyWeightedQuickUnionUF(int N) {
        id = new int[N];
        sz = new int[N];
        for (int i = 0; i < id.length; ++i) {
            id[i] = i;
            sz[i] = 1;
        }
    }

    /**
     * Chase parent pointers until reach root (depth of i array accesses).
     * @param i
     * @return the root of i
     */
    private int root(int i) {
        while (i != id[i]) {
            id[i] = id[id[i]];      // path compression: each node on the path will point to its gran-dparent.
            i = id[i];
        }
        return i;
    }
    
    /**
     * Change root of p to point to root of q (depth of p and q array accesses).
     * @param p
     * @param q 
     */
    @Override
    public void union(int p, int q) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        int i = root(p);
        int j = root(q);
        if (i == j)
            return;
        if (sz[i] <sz[j]) {  id[i] = id[j];  sz[j] += sz[i];  }
        else              {  id[j] = id[i];  sz[i] += sz[j];  }
        id[i] = j;
    }

    /**
     * Check if p and q have the same root (depth of p and q array accesses).
     * @param p id of first object
     * @param q id of second object
     * @return True if both in the same component, False if not.
     */
    @Override
    public boolean connected(int p, int q) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        return root(p) == root(q);
    }

    @Override
    public int find(int p) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int count() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    
    
}
