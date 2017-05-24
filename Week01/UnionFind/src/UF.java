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


public interface UF {

    // UF(int N);                       // initialize union-find data
                                        // structure with N objects (0 to N-1)

    void union(int p, int q);           // add connection between p and q
    
    boolean connected(int p, int q);    // are p and q in the same component?

    int find(int p);                    // component identifier for p (0 to N-1)

    int count();                        // number of components

}
