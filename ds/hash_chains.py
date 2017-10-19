# python3

'''
Hashing with chains: implement a hash table using a chaining scheme. The
polynomial hash function, prime, and x are given, and the number of buckets and
queries will be in the input.

The hash table should support four queries:
- add string: if string is not in the table, insert the string. Insertions are
  done at the head of the chain.
- del string: if string is in the table, remove it.
- find string: output 'yes' if the table contains string, 'no' otherwise.
- check i: output space-separated elements of the ith list in the table

The input is two integers, m (number of buckets) and N (number of queries),
followed by N lines, each containing one query. N is at most 5, m is at least N
/ 5 and at most N. The desired output is the result of all find and check
queries. For example:

Input:
5
6
add world
check 4
find World
find world
del world
check 4

Output:
world
no
yes
		<--(Note: the final check 4 resulted in a blank line below 'yes'.)

Explanation: To compute the hash value of 'world', we use the ASCII codes of its
characters: e.g. 'w' = 119, 'o' = 111, etc. computeHashValue('world') = (119 +
111 × 263 + 114 × 2632 + 108 × 2633 + 100 × 2634 mod 1 000 000 007) mod 5 = 4,
so 'world' is placed in the 4th bin. We check the contents of 4 and find one
element, 'world'. We test the find function: 'World' is not found, but 'world'
is. Finally, we delete 'world' and the query 'check 4' returns a blank line.

The feedback for this solution was:
Good job! (Max time used: 0.99/7.00, max memory used: 26480640/536870912.)
'''

class HashChains:
	prime = 10 ** 9 + 7 # prime and x were given
	x = 263

	def __init__(self):
		self.m = 0 # number of buckets
		self.N = 0 # number of queries
		self.buckets = []
		self.queries = []
		self.temp_hash_value = 0 # to reduce calls to computeHashValue()

	def read(self):
		self.m = int(input())
		self.N = int(input())
		self.queries = [input() for i in range(self.N)]
		self.buckets = [[] for i in range(self.m)]

	def solve(self):
		for query in self.queries:
			command, operand = query.split()
			if command == 'add':
				self.add(operand)
			elif command == 'del':
				self.delete(operand)
			elif command == 'find':
				print(self.find(operand))
			else: # if command == 'check'
				print(self.check(int(operand)))

	def computeHashValue(self, string):
		value = 0
		for i in range(len(string)):
			value = (value + ord(string[i]) * self.x ** i) % self.prime
		return value % self.m

	def find(self, string):
		self.temp_hash_value = self.computeHashValue(string)
		if string in self.buckets[self.temp_hash_value]:
			return 'yes'
		else:
			return 'no'

	def add(self, string):
		if self.find(string) == 'no':
			self.buckets[self.temp_hash_value].insert(0, string)

	def delete(self, string):
		if self.find(string) == 'yes':
			self.buckets[self.temp_hash_value].remove(string)

	def check(self, i):
		return " ".join(self.buckets[i])

if __name__ == '__main__':
	hc = HashChains()
	hc.read()
	hc.solve()
