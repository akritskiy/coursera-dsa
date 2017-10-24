# python3

'''
Test whether a binary search tree (BST) data structure was implemented
correctly. The requirement for a valid BST is: for any node of the tree with key
x, any node key in its left subtree must be less than x, and any node key in its
right subtree must be greater than x.

Input: an integer n, the number of nodes, followed by n lines of three integers
each: key (value of the node), and r and l, the 0-based indices of the left and
right child. The 0th node is the root. Desired output: CORRECT if the tree is a
valid BST, INCORRECT otherwise. For example:

Input:
3
2 1 2
1 -1 -1
3 -1 -1
Output: CORRECT

This describes the tree:
____2
__1___3

Input:
4
4 1 -1
2 2 3
1 -1 -1
5 -1 -1
Output: INCORRECT

The input describes the tree:
________4
____2
__1___5

5 is in the left subtree of 4, which is incorrect. If we searched for 5 in a
valid BST with root 4, we would expect 5 to be in the right subtree.

The strategy used here was to use the in-order traversal method from the tree
traversals problem. An in-order traversal of a valid BST will output the keys in
ascending order. If we find a key such that previous key > current key, we know
the BST is incorrect.

The feedback for this solution was:
Good job! (Max time used: 0.57/10.00, max memory used: 21647360/536870912.)
'''

import math

class IsBST:
	def __init__(self):
		self.n = 0

	def initArrays(self):
		self.keys = [0] * self.n
		self.l = [0] * self.n
		self.r = [0] * self.n

	def read(self):
		self.n = int(input())

		if self.n not in (0, 1):
			self.initArrays()
			for i in range(self.n):
				self.keys[i], self.l[i], self.r[i] = [int(x) for x in input().split()]

	def checkBST(self):
		if self.n in (0, 1):
			return 'CORRECT'

		i = 0 # current node index
		stack = []
		previous_key = -math.inf

		while True:
			if i != -1:
				stack.append(i)
				i = self.l[i]
			elif stack:
				i = stack.pop()
				if previous_key > self.keys[i]:
					return 'INCORRECT'
				
				previous_key = self.keys[i]
				i = self.r[i]
			else:
				break
		return 'CORRECT'

if __name__ == '__main__':
	my_bst = IsBST()
	my_bst.read()
	print(my_bst.checkBST())