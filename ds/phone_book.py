# python3

'''
Simple phone book using direct addressing. Supports three queries:
- add number name: save name under number
- delete number: delete name associated with number
- find number: return the name associated with number, or if not found, return 
  'not found'

Input: read in a single integer n, the number of queries. n can be at most 10^5.
Phone numbers are at most 7 digits, with no leading zeroes, and consist of
decimal digits (there are 10^7 possible numbers). Then, read in n queries
(strings). Output: print the result of any 'find' queries.

Example:
Input:
7
find Alex
add 123 Jake
find 123
add 123 James
find 123
del 123
find 123

Output:
not found
Jake
James
not found

We started out with an empty phone book and tried to find Alex: not found. We
added Jake's number. When Jake called, our book saw his number and found the
name: Jake. But oops! It's not Jake on the phone... it's James! We always
confuse those two. We update the name associated with this number to James. The
next time we get a call from this number, the book displays the name: James.
Finally, we delete James's number. The next time he calls, the book displays:
not found.

The feedback for this solution was:
Good job! (Max time used: 0.68/6.00, max memory used: 98574336/671088640.)
'''

class PhoneBook:
	def __init__(self):
		self.n = 0
		self.queries = []
		self.book = [None] * (10 ** 7)

	def add(self, number, name):
		self.book[number] = name

	def delete(self, number):
		self.book[number] = None

	def find(self, number):
		name = self.book[number]
		if not name:
			return 'not found'
		else:
			return name

	def solve(self):
		self.n = int(input())
		self.queries = [input() for i in range(self.n)]
		self.processQueries()

	def processQueries(self):
		for query in self.queries:
			q = query.split()
			command, number = q[0], int(q[1])
			if command == 'add':
				self.add(number, q[2]) # if command == 'add', q[2] == name
			elif command == 'del':
				self.delete(number)
			else: # if command == 'find'
				print(self.find(number))

if __name__ == '__main__':
	x = PhoneBook()
	x.solve()
