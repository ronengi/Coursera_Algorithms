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


public class QuickFindUF implements UF {

    private int[] id;

    /**
     * Set id of each object to itself (N array accesses).
     * @param N number of objects
     */
    public QuickFindUF(int N) {
        id = new int[N];
        for (int i = 0; i < N; ++i)
            id[i] = i;
    }
    
    /**
     * Check whether p and q are in the same component (2 array accesses).
     * @param p id of first object
     * @param q id of second object
     * @return True if both in the same component, False if not
     */
    @Override
    public boolean connected(int p, int q) {
        // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        return id[p] == id[q];
    }
    
    /**
     * Change all entries with id[p] to id[q] (at most 2N + 2 array accesses).
     * @param p id of first object
     * @param q id of second object
     */
    @Override
    public void union(int p, int q) {
        // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        int pid = id[p];
        int qid = id[q];
        for (int i = 0; i < id.length; ++i)
            if (id[i] == pid)
                id[i] = qid;
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
