# python3

'''
In this problem, simulate a scheduler.

The first line of input contains integers n (number of threads) and m (number of
jobs). The scheduler uses n threads to process m jobs.

The second line contains m integers, ti (t sub i): ti is the time in seconds it
takes a thread to process the ith job. Threads take jobs in the order they are
given. If there is one free thread, it immediately takes the next job. If a
thread has started processing a job, it doesn't interrupt until it finishes the
job. If several threads are available, the thread with smaller index (thread id)
takes the job.

This solution uses a binary min-heap to prioritize threads. The thread with
lowest workload, and where workloads are equal, the thread with lowest id, will
always be in the 0th position in the threads array. This thread is assigned the
next job. The thread id and workload before taking the new job are recorded in a
schedule array.

The desired output is the schedule array: m lines, where the ith line contains
two space-separated integers, the 0-based index of the thread which processed
the ith job (thread id) and the time in seconds when the thread will start
processing that job.

Example:
Input:
2 5
1 2 3 4 5
Output:
0 0
1 0
0 1
1 2
0 4

Explanation:
1.  the two threads try to simultaneously take jobs from the list, so thread 0 
    takes the first job and starts working on it at time 0
2.  thread 1 takes the second job and starts working on it at time 0
3.  after one second, thread 0 is done, so it takes the third job and starts
    working on it at time 1
4.  after two seconds, thread 1 is done, so it takes the fourth job and starts
    processing it at time 2
5.  finally, after two more seconds, thread 0 is done with the third job, so it
    takes the final job and starts processing it at time 4

Another example:
Input:
4 10
1 1 1 1 1 1 1 1 1 1
Output:
0 0
1 0
2 0
3 0
0 1
1 1
2 1
3 1
0 2
1 2

Explanation: at time 0, all four threads are available, so they are prioritized
by thread id. The 0th thread takes the first job and works on it at time 0, the
1st thread takes the 2nd job and works on it at time 0, etc. At time 1, all four
threads are available again, and the process is repeated.

The feedback for this solution was:
Good job! (Max time used: 3.68/6.00, max memory used: 45473792/536870912.)
'''

class ParallelProcessing:
	def __init__(self):
		self.num_threads = 0
		self.num_jobs = 0
		self.jobs = []
		self.threads = []
		self.schedule = []

	def read(self):
		nm = [int(s) for s in input().split()]
		self.num_threads = nm[0]
		self.num_jobs = nm[1]
		self.jobs = [int(s) for s in input().split()]

	def initThreads(self):
		for i in range(self.num_threads):
			self.threads.append(Thread(i))

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
		if l <= len(self.threads) - 1 and self.threads[l].workload < self.threads[minIndex].workload:
			minIndex = l
		elif l <= len(self.threads) - 1 and self.threads[l].workload == self.threads[minIndex].workload:
			# if workloads are equal, prioritize thread with lowest id
			if self.threads[l].id < self.threads[minIndex].id:
				minIndex = l

		if r <= len(self.threads) - 1 and self.threads[r].workload < self.threads[minIndex].workload:
			minIndex = r
		elif r <= len(self.threads) - 1 and self.threads[r].workload == self.threads[minIndex].workload:
			if self.threads[r].id < self.threads[minIndex].id: # prioritize thread with lowest id
				minIndex = r

		if i != minIndex:
			self.threads[i], self.threads[minIndex] = self.threads[minIndex], self.threads[i]
			self.siftDown(minIndex)

	def assignJob(self, i, jobWorkload):
		self.schedule.append([self.threads[i].id, self.threads[i].workload])
		self.threads[i].workload += jobWorkload
		self.siftDown(i)

	def solve(self):
		self.read()
		self.initThreads()
		for job in self.jobs:
			self.assignJob(0, job)
		for task in self.schedule:
			print(task[0], task[1])

class Thread:
	def __init__(self, id):
		self.id = id
		self.workload = 0

if __name__ == '__main__':
	my_pp = ParallelProcessing()
	my_pp.solve()
