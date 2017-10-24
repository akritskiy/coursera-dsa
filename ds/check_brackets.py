# python3

'''
Your friend is making a text editor for programmers. Implement a feature to find
errors in the usage of the different kinds of brackets/parens: {}, [], and ().
For convenience, if an error is found, the editor should point the user to the
problematic bracket.

e.g.
Input: {}
Output: Success

Input: foo(x)]
Output: 7
Explanation: error at the 7th position (instructions were to use one-based numbering).

Feedback for this solution:
Good job! (Max time used: 0.10/5.00, max memory used: 16224256/536870912.)
'''

class Bracket:
    def __init__(self, br_type, position):
        self.br_type = br_type
        self.position = position

    def isMatch(self, closing_br):
        if self.br_type == '(' and closing_br == ')':
            return True
        elif self.br_type == '{' and closing_br == '}':
            return True
        elif self.br_type == '[' and closing_br == ']':
            return True
        else:
            return False

def checkBrackets(text):
    open_br = ['{', '[', '(']
    close_br = ['}', ']', ')']
    stack = []

    for i, char in enumerate(text):
        if char in open_br:
            stack.append(Bracket(char, i + 1))
        elif char in close_br:
            if not stack:
                return i + 1
            else:
                last_open_br = stack.pop()
                if last_open_br.isMatch(char):
                    continue
                else:
                    return i + 1
    if not stack:
        return 'Success'
    else:
        return stack[0].position

if __name__ == "__main__":
    print(checkBrackets(input()))