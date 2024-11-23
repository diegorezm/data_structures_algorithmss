package queue

import "fmt"

type Queue struct {
	items []int
}

func NewQueue() *Queue {
	return &Queue{items: make([]int, 0)}
}

func (q *Queue) Enqueue(item int) {
	q.items = append(q.items, item)
}

func (q *Queue) Dequeue() int {
	item := q.items[0]
	q.items = q.items[1:]
	return item
}

func (q *Queue) IsEmpty() bool {
	return len(q.items) == 0
}

func (q *Queue) Print() {
	for _, item := range q.items {
		fmt.Printf("%d ", item)
	}
	fmt.Println()
}
