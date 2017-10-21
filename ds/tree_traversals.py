# python3

'''
Implement in-order, pre-order, and post-order traversals of a binary tree.

Input: an integer n, the number of nodes, followed by n lines of three integers
each: key (value of the node), and r and l, the 0-based indices of the left and
right child. The 0th node is the root, and an r (or l) of -1 indicates that the
node does not have a right (or left) child. There can be at most 10^5 nodes, and
each key can be at least -1 and at most 10^9. The desired output is: three lines
of space-separated integers describing the in-order, pre-order, and post-order
path of the tree.

Example:
Input:
5
4 1 2
2 3 4
5 -1 -1
1 -1 -1
3 -1 -1
Output:
1 2 3 4 5
4 2 1 3 5
1 3 2 5 4
Explanation:
The tree described in the input:
		4
	2		5
  1   3

A description of all three traversal methods can be found here:
https://en.wikipedia.org/wiki/Tree_traversal#Pre-order

The feedback for this solution was:
Good job! (Max time used: 0.86/6.00, max memory used: 30130176/536870912.)
'''

class TreeOrders:
	def __init__(self):
		self.n = 0
		self.keys = []
		self.l = []
		self.r = []

	def read(self):
		self.n = int(input())
		self.keys = [0] * self.n
		self.l = [0] * self.n
		self.r = [0] * self.n
		for i in range(self.n):
			self.keys[i], self.l[i], self.r[i] = [int(s) for s in input().split()]

	def inOrder(self):
		i = 0
		stack = []
		path = []

		while True:
			if i != -1:
				stack.append(i)
				i = self.l[i]
			elif stack:
				i = stack.pop()
				path.append(self.keys[i])
				i = self.r[i]
			else:
				break
		return path

	def preOrder(self):
		i = 0
		stack = []
		path = []

		while True:
			if i != -1:
				path.append(self.keys[i])
				stack.append(i)
				i = self.l[i]
			elif stack:
				i = self.r[stack.pop()]
			else:
				break
		return path

	def postOrder(self):
		stack = [0]
		path = []

		while stack:
			i = stack.pop()
			path.append(self.keys[i])

			left = self.l[i]
			right = self.r[i]
			if left != -1:
				stack.append(left)
			if right != -1:
				stack.append(right)
		return reversed(path)

if __name__ == '__main__':
	x = TreeOrders()
	x.read()
	print(*x.inOrder())
	print(*x.preOrder())
	print(*x.postOrder())
