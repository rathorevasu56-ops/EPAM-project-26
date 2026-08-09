# Emergency Route Validation

## Problem Description
A country has **N** cities and **M** bidirectional roads connecting them. The capital is **City 1**. During an emergency, only cities that can be reached from the capital within a maximum of **D** road-hops are considered "reachable" for emergency response.

The task is to determine how many cities (including the capital itself) are reachable from City 1 within distance **D**.

## Input Format

N M D
u_1 v_1
u_2 v_2
...
u_M v_M

- `N` — number of cities, `M` — number of roads, `D` — maximum allowed distance (in hops)
- Each of the next `M` lines describes a road `(u, v)` connecting two cities

## Output Format
A single integer — the number of cities reachable from City 1 within distance `D`.

## Example
**Input**

6 5 2
1 2
1 3
2 4
3 5
5 6

**Output**

5

*(Distances from City 1: city 1→0, cities 2,3→1, cities 4,5→2 — all within D=2. City 6→3, exceeds D. Reachable count = 5.)*

## Approach
- Build an adjacency list to represent the road network.
- Run a **BFS (Breadth-First Search)** starting from City 1, tracking distance of each city from the capital.
- A city is counted as reachable if its BFS distance is `<= D`.
- BFS guarantees each city's distance is found via the shortest path (fewest hops), so once a distance is recorded it's final.

## Complexity
- **Time:** O(N + M) — standard BFS over the graph.
- **Space:** O(N + M) — adjacency list, distance array, and queue.

## How to Run
```bash
javac EmergencyRouteValidation.java
java EmergencyRouteValidation < input.txt
```
