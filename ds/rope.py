# python3

'''
Implement rope, a data structure that can store a string and efficiently cut a substring and insert it in a different position. This data structure can be enhanced to become persistent -- that is, to allow access to previous versions of the string. This makes it a suitable choice for storing the text in text editors.

Given a string, process n queries. Each query is described by three integers i, j, k and means cut substring S[i..j] (i and j are 0-based) and insert it after the kth symbol of the remaining string (1-based numbering). If k = 0, S[i..j] is inserted in the beginning.

For example, given input:
hlelowrold
2
1 1 2
6 6 7
Output:
helloworld

The feedback for this solution was:
Good job! (Max time used: 57.06/120.00, max memory used: 16494592/536870912.)
'''

class Rope:
    def __init__(self):
        self.string = ''
        self.n = 0

    def read(self):
        self.string = input().strip()
        self.n = int(input())

    def solve(self):
        for x in range(self.n):
            i, j, k = [int(y) for y in input().strip().split()]
            self.processQuery(i, j, k)

    def processQuery(self, i, j, k):
        substring = self.string[i:j + 1]
        self.string = self.string[:i] + self.string[j + 1:]
        if k == 0:
            self.string = substring + self.string
        else:
            self.string = self.string[:k] + substring + self.string[k:]

if __name__ == '__main__':
    my_rope = Rope()
    my_rope.read()
    my_rope.solve()
    print(my_rope.string)