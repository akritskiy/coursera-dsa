# python3

'''
Implement the first step of HeapSort: given an array of integers, convert the
array into a heap. This is done by applying a certain number of "swaps" to the
array. A swap is an operation which exchanges two elements within the array.

Note: in this problem, a binary min-heap is used. An array can be considered a
binary min-heap if every child node is greater than its parent.

The first line of the input contains a single integer, n. The next line contains
n space-separated integers, ai. n can be as large as 100,000. Each ai can be as
large as 10^9, and all ai are distinct.

The first line of the output should contain a single integer m, the total number
of swaps. m can be at most 4n. The next m lines contain the swap operations used
to convert the array, described by a pair of integers which are the 0-based
indices of the elements that were swapped.

Example:
Input:
5
5 4 3 2 1
Output:
3
1 4
0 1
1 3

Explanation:
1. swap element 1 and element 4, you have: 5 1 3 2 4
2. swap element 0 and element 1, you have: 1 5 3 2 4
3. swap element 1 and element 3, you have: 1 2 3 5 4
which is a heap: 1 is the root, 2 and 3 are children of 1, and 5 and 4 are
children of 2. Every child node is greater than its parent.

Another example:
Input:
5
1 2 3 4 5
Output:
0

Explanation: this array is already a heap.

The feedback for this solution was:
Good job! (Max time used: 0.45/3.00, max memory used: 26288128/536870912.)
'''

class BuildHeap:
	def __init__(self):
		self.n = 0
		self.heap = []
		self.swaps = []

	def read(self):
		self.n = int(input())
		self.heap = [int(s) for s in input().split()]
		assert self.n == len(self.heap)

	def solve(self):
		self.read()
		self.buildHeap()
		print(len(self.swaps))
		for swap in self.swaps:
			print(swap[0], swap[1])

	def parent(self, i):
		return (i - 1) // 2

	def leftChild(self, i):
		return i * 2 + 1

	def rightChild(self, i):
		return i * 2 + 2

	def siftDown(self, i):
		minIndex = i
		l = self.leftChild(i)
		r = self.rightChild(i)
		if l <= len(self.heap) - 1 and self.heap[l] < self.heap[minIndex]:
			minIndex = l
		if r <= len(self.heap) - 1 and self.heap[r] < self.heap[minIndex]:
			minIndex = r
		if i != minIndex:
			self.swaps.append([i, minIndex])
			self.heap[i], self.heap[minIndex] = self.heap[minIndex], self.heap[i]
			self.siftDown(minIndex)

	def buildHeap(self):
		for i in range(len(self.heap) // 2, -1, -1):
			self.siftDown(i)

if __name__ == '__main__':
	my_heap = BuildHeap()
	my_heap.solve()
