# Tree of Trusted Servers

## Problem Description
A company has **N** servers connected in a **tree** structure (N nodes, N-1 edges — no cycles). Each server has a security **key** value. Starting from the root server (Node 1), a path's trust value is computed as the **XOR** of all key values along the path from the root to that server.

A server is considered **"trusted"** if the cumulative XOR value of the path from the root to that server is **greater than or equal to a threshold K**.

The task is to count how many servers in the tree are trusted.

## Input Format
N K
key_1 key_2 ... key_N
u_1 v_1
u_2 v_2
...
u_(N-1) v_(N-1)
- `N` — number of servers, `K` — trust threshold
- `key_i` — key value of server `i` (1-indexed)
- Each of the next `N-1` lines describes an edge `(u, v)` connecting two servers

## Output Format
A single integer — the count of trusted servers.

## Example
**Input**
5 3
1 2 3 4 5
1 2
1 3
2 4
2 5

**Output**

3

*(Root→2 gives XOR=3, Root→2→4 gives XOR=7, Root→2→5 gives XOR=6 — all ≥ 3. Root itself (XOR=1) and Root→3 (XOR=2) are below threshold. Trusted count = 3.)*

## Approach
- Build an adjacency list to represent the tree.
- Perform a **DFS** starting at the root (Node 1), passing down the running XOR of keys from root to current node.
- At each node, XOR in the node's own key, then check if the accumulated value is `>= K`. If so, increment the trusted count.
- Recurse into all neighbors except the parent to avoid revisiting.

## Complexity
- **Time:** O(N) — each node and edge is visited once.
- **Space:** O(N) — adjacency list + recursion stack.

## How to Run
```bash
javac TreeOfTrustedServers.java
java TreeOfTrustedServers < input.txt
```
