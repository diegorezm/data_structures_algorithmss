package graph

import (
	"diegorezm/data_structures_algorithmss/queue"
	"fmt"
)

const (
	white = 0
	gray  = 1
	black = 2
)

var POINTS = map[int]bool{
	8:  true,
	9:  true,
	11: true,
	16: true,
	19: true,
	22: true,
	25: true,
}

type graph struct {
	n      int
	matrix [][]int
}

func NewGraph(n int) *graph {
	matrix := make([][]int, n)

	for i := 0; i < n; i++ {
		matrix[i] = make([]int, n)
	}

	return &graph{
		n:      n,
		matrix: matrix,
	}
}

func (g *graph) AddEdge(v1, v2, weight int, directed bool) {
	g.matrix[v1][v2] = weight
	if !directed {
		g.matrix[v2][v1] = weight
	}
}

func (g *graph) RemoveEdge(v1, v2 int) {
	p := g.matrix[v1][v2]
	g.matrix[v1][v2] = 0
	if g.matrix[v2][v1] == p {
		g.matrix[v2][v1] = 0
	}
}

func (g *graph) HasEdge(v1, v2 int) bool {
	return g.matrix[v1][v2] != 0
}

func (g *graph) BFS(v int) {
	queue := queue.NewQueue()
	colors := make([]int, g.n)
	distances := make([]int, g.n)
	predecessors := make([]int, g.n)

	for i := 0; i < g.n; i++ {
		colors[i] = white
		distances[i] = +999999
		predecessors[i] = -1
	}

	colors[v] = gray
	distances[v] = 0
	predecessors[v] = -1
	queue.Enqueue(v)

	for !queue.IsEmpty() {
		u := queue.Dequeue()
		for i := 0; i < g.n; i++ {
			// check if the node is not visited and if there is an edge
			if g.matrix[u][i] != 0 && colors[i] == white {
				// mark the node as queued
				colors[i] = gray
				// update the distances and predecessors
				distances[i] = distances[u] + g.matrix[u][i] // distances[i] = distances[u] + weight
				predecessors[i] = u
				if POINTS[i] {
					fmt.Printf("%d\n", i)
				}
				queue.Enqueue(i)
			}
		}
		// mark the node as visited
		colors[u] = black
	}
}

func (g *graph) DFS(v int) {
	visited := make([]bool, g.n)
	g.dfs(v, visited)
}

func (g *graph) dfs(v int, visited []bool) {
	visited[v] = true
	if POINTS[v] {
		fmt.Printf("%d\n", v)
	}
	for i := 0; i < g.n; i++ {
		if !visited[i] && g.HasEdge(v, i) {
			g.dfs(i, visited)
		}
	}
}

func (g *graph) Dijkstra(v int) {
	queue := queue.NewPriorityQueue(g.n)
	distances := make([]int, g.n)
	predecessors := make([]int, g.n)
	colors := make([]int, g.n)

	for i := 0; i < g.n; i++ {
		distances[i] = 999999
		predecessors[i] = -1
		colors[i] = white
	}

	distances[v] = 0
	predecessors[v] = -1

	for i := 0; i < g.n; i++ {
		queue.Enqueue(i, distances)
	}

	for !queue.IsEmpty() {
		u := queue.Dequeue()

		for z := 0; z < g.n; z++ {
			if g.matrix[u][z] != 0 && colors[z] != black {
				if (distances[u] + g.matrix[u][z]) < distances[z] {
					distances[z] = distances[u] + g.matrix[u][z]
					predecessors[z] = u
				}
			}
		}
		colors[u] = black
		queue.Sort(distances)
	}
	for i := 0; i < g.n; i++ {
		if POINTS[i] {
			fmt.Printf("%d\n", i)
		}
	}
}

func (g *graph) Print() {
	for i := 0; i < g.n; i++ {
		for j := 0; j < g.n; j++ {
			fmt.Printf("%d ", g.matrix[i][j])
		}
		fmt.Println()
	}
}
