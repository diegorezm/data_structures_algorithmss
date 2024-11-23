package queue

import (
	"fmt"
)

type PriorityQueue struct {
	items []int // Armazena os índices dos elementos
	n     int   // Tamanho máximo da fila
	end   int   // Índice do último elemento
}

// Cria uma nova fila de prioridade
func NewPriorityQueue(size int) *PriorityQueue {
	return &PriorityQueue{
		items: make([]int, size),
		n:     size,
		end:   -1,
	}
}

// Ordena a fila com base nas prioridades em D (usando bubble Sort)
func (q *PriorityQueue) Sort(priorities []int) {
	for i := 0; i <= q.end; i++ {
		for j := 0; j < q.end-i; j++ {
			if priorities[q.items[j]] > priorities[q.items[j+1]] {
				q.items[j], q.items[j+1] = q.items[j+1], q.items[j]
			}
		}
	}
}

// Insere um elemento na fila com base em sua prioridade
func (q *PriorityQueue) Enqueue(item int, priorities []int) {
	if q.end+1 < q.n {
		q.end++
		q.items[q.end] = item
		q.Sort(priorities)
	} else {
		fmt.Println("ERROR: Queue is full")
	}
}

// Remove e retorna o elemento de maior prioridade (menor valor em D)
func (q *PriorityQueue) Dequeue() int {
	if q.end >= 0 {
		val := q.items[0]
		for i := 0; i < q.end; i++ {
			q.items[i] = q.items[i+1]
		}
		q.end--
		return val
	}
	fmt.Println("ERROR: Queue is empty")
	return -999999 // Valor de erro
}

func (q *PriorityQueue) IsEmpty() bool {
	return q.end == -1
}

func (q *PriorityQueue) IsFull() bool {
	return q.end+1 == q.n
}

func (q *PriorityQueue) Print() {
	if q.IsEmpty() {
		fmt.Println("Queue is empty")
		return
	}
	for i := 0; i <= q.end; i++ {
		fmt.Printf("%d ", q.items[i])
	}
	fmt.Println()
}
