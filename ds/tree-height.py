# python3

'''
Given a description of a tree, compute and output its height. Recall that the
height of a tree is the max distance from a leaf to a root.

You are given an arbitrary tree (not necessarily a binary tree). The first line
contains n, the number of nodes. The second line contains n integers in range -1
to n - 1. There is one root, indicated by the value -1. Otherwise, the value of
the node indicates the zero-based index of its parent.

For example:
5
4 -1 4 1 1
can be interpreted as:
- the 1st node (value = -1) is the root
- the 3rd and 4th nodes (value = 1) are children of the 1st node
- the 0th and 2nd nodes (value = 4) are children of the 4th node
The output would be 3, because the max distance from a leaf to the root is:
begin with either the 0th or 2nd node, they are children of -> the 4th node,
which is child of -> the 1st node, which is the root.

Another example:
5
-1 0 4 0 3
Output: 4, because the max distance is: begin with node 2, which is child of ->
node 4, which is child of -> node 3, which is child of -> node 0, the root.

Node height can be computed by recursively tracing the path from the node to the
root, adding 1 for each node encountered. Tree height can be computed by
computing the node height of each node, and returning the max. However, it's
naive to do this for every node, as you will compute the height of some nodes
many times. A better way, which is implemented here, is to compute the height at
each node as normal, iterating from the 0th node to the (n - 1)th node, but as
each node is visited, we save the height at that node in a nodeHeights array.
Going forward, the height from this node to the root is known, and will not have
to be calculated again. As the nodeHeights array gets closer to completion, it
takes less work to calculate the height of a node.

The feedback for this solution was:
Good job! (Max time used: 0.41/3.00, max memory used: 64401408/536870912.)
'''

import sys, threading
sys.setrecursionlimit(10**7) # max depth of recursion
threading.stack_size(2**27)  # new thread will get stack of such size

class TreeHeight:
	def __init__(self):
		self.n = 0
		self.nodes = []
		self.nodeHeights = []

	def read(self):
		self.n = int(sys.stdin.readline())
		self.nodes = list(map(int, sys.stdin.readline().split()))
		self.nodeHeights = [0] * self.n

	def nodeHeight(self, i):
		if self.nodes[i] == -1: # base case: node is root (-1), height is 1
			self.nodeHeights[i] = 1
			return 1

		# check if this node height has been calculated before
		if self.nodeHeights[i]:
			return self.nodeHeights[i]

		# else, calculate and save the node height
		height = 1 + self.nodeHeight(self.nodes[i])
		self.nodeHeights[i] = height
		return height

	def getHeight(self):
		return max([self.nodeHeight(i) for i in range(self.n)])

def main():
	tree = TreeHeight()
	tree.read()
	print(tree.getHeight())

threading.Thread(target=main).start()
