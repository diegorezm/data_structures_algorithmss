package main

import (
	"diegorezm/data_structures_algorithmss/graph"
	"fmt"
)

func main() {
	g := graph.NewGraph(50)

	// Inserção das arestas
	g.AddEdge(1, 2, 3, false)
	g.AddEdge(1, 0, 1, false)
	g.AddEdge(1, 3, 11, false)

	g.AddEdge(2, 6, 1, false)
	g.AddEdge(2, 0, 3, false)

	g.AddEdge(3, 5, 3, false)
	g.AddEdge(3, 4, 5, false)
	g.AddEdge(3, 25, 7, false)

	g.AddEdge(4, 5, 3, false)
	g.AddEdge(4, 23, 7, false)
	g.AddEdge(4, 24, 11, false)
	g.AddEdge(4, 20, 5, false)

	g.AddEdge(5, 6, 1, false)
	g.AddEdge(5, 23, 3, false)

	g.AddEdge(6, 10, 11, false)
	g.AddEdge(6, 23, 3, false)

	g.AddEdge(7, 0, 1, false)
	g.AddEdge(7, 8, 7, false)
	g.AddEdge(7, 9, 5, false)

	g.AddEdge(8, 9, 7, false)
	g.AddEdge(8, 12, 5, false)

	g.AddEdge(9, 10, 7, false)

	g.AddEdge(10, 12, 5, false)
	g.AddEdge(10, 11, 7, false)
	g.AddEdge(10, 23, 3, false)

	g.AddEdge(11, 13, 5, false)
	g.AddEdge(11, 14, 7, false)
	g.AddEdge(11, 23, 7, false)

	g.AddEdge(12, 13, 7, false)
	g.AddEdge(12, 22, 3, false)

	g.AddEdge(13, 22, 5, false)
	g.AddEdge(13, 15, 7, false)

	g.AddEdge(14, 23, 5, false)
	g.AddEdge(14, 16, 5, false)
	g.AddEdge(14, 20, 7, false)

	g.AddEdge(15, 16, 5, false)
	g.AddEdge(15, 17, 5, false)

	g.AddEdge(16, 17, 5, false)
	g.AddEdge(16, 18, 5, false)
	g.AddEdge(16, 19, 5, false)

	g.AddEdge(17, 18, 5, false)

	g.AddEdge(18, 19, 3, false)

	g.AddEdge(19, 20, 7, false)
	g.AddEdge(19, 24, 11, false)

	g.AddEdge(20, 23, 7, false)
	g.AddEdge(20, 24, 7, false)

	g.AddEdge(24, 25, 11, false)

	fmt.Println()
	fmt.Println("BFS:")
	g.BFS(6)

	fmt.Println()
	fmt.Println("DFS:")
	g.DFS(6)

	fmt.Println()
	fmt.Println("Dijkstra:")
	g.Dijkstra(6)

}
