sineshawmesfintesfaye@sineshaws-MacBook-Pro Operating_System_Project1 % printf 'processes.txt\n1\n6\n' | java SchedulingSimulator
Enter the process file name: Loaded 5 processes from processes.txt

 CPU Scheduling Simulator
1. First_Come, First_Served
2. Shortest Job First
3. Memory Allocation (First-Fit, Best-Fit, Worst-Fit)
4. Page Replacement (FIFO)
5. Page Replacement (LRU)
6. Exit
Pick an option 
=== FCFS Scheduling ===

Gantt Chart:
| P1 | P2 | P3 | P4 | P5 |
0    5    8   10   14   15

PID     Arrival Burst   Waiting Turnaround
1       0       5       0       5
2       2       3       3       6
3       4       2       4       6
4       6       4       4       8
5       7       1       7       8

Average Waiting Time: 3.60
Average Turnaround Time: 6.60
CPU Utilization: 100.00%

 CPU Scheduling Simulator
1. First_Come, First_Served
2. Shortest Job First
3. Memory Allocation (First-Fit, Best-Fit, Worst-Fit)
4. Page Replacement (FIFO)
5. Page Replacement (LRU)
6. Exit
Pick an option Exiting.
sineshawmesfintesfaye@sineshaws-MacBook-Pro Operating_System_Project1 % printf 'processes.txt\n2\n6\n' | java SchedulingSimulator
Enter the process file name: Loaded 5 processes from processes.txt

 CPU Scheduling Simulator
1. First_Come, First_Served
2. Shortest Job First
3. Memory Allocation (First-Fit, Best-Fit, Worst-Fit)
4. Page Replacement (FIFO)
5. Page Replacement (LRU)
6. Exit
Pick an option 
=== SJF Scheduling ===

Gantt Chart:
| P1 | P3 | P5 | P2 | P4 |
0    5    7    8   11   15

PID     Arrival Burst   Waiting Turnaround
1       0       5       0       5
2       2       3       6       9
3       4       2       1       3
4       6       4       5       9
5       7       1       0       1

Average Waiting Time: 2.40
Average Turnaround Time: 5.40
CPU Utilization: 100.00%

 CPU Scheduling Simulator
1. First_Come, First_Served
2. Shortest Job First
3. Memory Allocation (First-Fit, Best-Fit, Worst-Fit)
4. Page Replacement (FIFO)
5. Page Replacement (LRU)
6. Exit
Pick an option Exiting.
sineshawmesfintesfaye@sineshaws-MacBook-Pro Operating_System_Project1 % printf 'processes.txt\n3\n5\n100 200 300 400 500\n4\n120 250 180 400\n6\n' | java SchedulingSimulator
Enter the process file name: Loaded 5 processes from processes.txt

 CPU Scheduling Simulator
1. First_Come, First_Served
2. Shortest Job First
3. Memory Allocation (First-Fit, Best-Fit, Worst-Fit)
4. Page Replacement (FIFO)
5. Page Replacement (LRU)
6. Exit
Pick an option Enter number of memory blocks: Enter the size of each block:
  Block 1:   Block 2:   Block 3:   Block 4:   Block 5: Enter number of processes to allocate: Enter memory needed for each process:
  Process 1:   Process 2:   Process 3:   Process 4: 
=== First-Fit Allocation ===
Process Size    Block
1       120     2
2       250     3
3       180     4
4       400     5

=== Best-Fit Allocation ===
Process Size    Block
1       120     2
2       250     3
3       180     4
4       400     5

=== Worst-Fit Allocation ===
Process Size    Block
1       120     5
2       250     4
3       180     5
4       400     Not Allocated

 CPU Scheduling Simulator
1. First_Come, First_Served
2. Shortest Job First
3. Memory Allocation (First-Fit, Best-Fit, Worst-Fit)
4. Page Replacement (FIFO)
5. Page Replacement (LRU)
6. Exit
Pick an option Exiting.
sineshawmesfintesfaye@sineshaws-MacBook-Pro Operating_System_Project1 % printf 'processes.txt\n4\n3\n13\n7 0 1 2 0 3 0 4 2 3 0 3 2\n6\n' | java SchedulingSimulator
Enter the process file name: Loaded 5 processes from processes.txt

 CPU Scheduling Simulator
1. First_Come, First_Served
2. Shortest Job First
3. Memory Allocation (First-Fit, Best-Fit, Worst-Fit)
4. Page Replacement (FIFO)
5. Page Replacement (LRU)
6. Exit
Pick an option Enter number of frames: Enter number of pages in reference string: Enter the page reference string:

=== FIFO Page Replacement ===
Step    Page    Frames                  Fault
1       7       [7]             Yes
2       0       [7, 0]          Yes
3       1       [7, 0, 1]               Yes
4       2       [0, 1, 2]               Yes
5       0       [0, 1, 2]               No
6       3       [1, 2, 3]               Yes
7       0       [2, 3, 0]               Yes
8       4       [3, 0, 4]               Yes
9       2       [0, 4, 2]               Yes
10      3       [4, 2, 3]               Yes
11      0       [2, 3, 0]               Yes
12      3       [2, 3, 0]               No
13      2       [2, 3, 0]               No

Total Page Faults: 10
Hit Ratio: 23.08%

 CPU Scheduling Simulator
1. First_Come, First_Served
2. Shortest Job First
3. Memory Allocation (First-Fit, Best-Fit, Worst-Fit)
4. Page Replacement (FIFO)
5. Page Replacement (LRU)
6. Exit
Pick an option Exiting.
sineshawmesfintesfaye@sineshaws-MacBook-Pro Operating_System_Project1 % printf 'processes.txt\n5\n3\n13\n7 0 1 2 0 3 0 4 2 3 0 3 2\n6\n' | java SchedulingSimulator
Enter the process file name: Loaded 5 processes from processes.txt

 CPU Scheduling Simulator
1. First_Come, First_Served
2. Shortest Job First
3. Memory Allocation (First-Fit, Best-Fit, Worst-Fit)
4. Page Replacement (FIFO)
5. Page Replacement (LRU)
6. Exit
Pick an option Enter number of frames: Enter number of pages in reference string: Enter the page reference string:

=== LRU Page Replacement ===
Step    Page    Frames                  Fault
1       7       [7]             Yes
2       0       [7, 0]          Yes
3       1       [7, 0, 1]               Yes
4       2       [0, 1, 2]               Yes
5       0       [1, 2, 0]               No
6       3       [2, 0, 3]               Yes
7       0       [2, 3, 0]               No
8       4       [3, 0, 4]               Yes
9       2       [0, 4, 2]               Yes
10      3       [4, 2, 3]               Yes
11      0       [2, 3, 0]               Yes
12      3       [2, 0, 3]               No
13      2       [0, 3, 2]               No

Total Page Faults: 9
Hit Ratio: 30.77%

 CPU Scheduling Simulator
1. First_Come, First_Served
2. Shortest Job First
3. Memory Allocation (First-Fit, Best-Fit, Worst-Fit)
4. Page Replacement (FIFO)
5. Page Replacement (LRU)
6. Exit
Pick an option Exiting.
sineshawmesfintesfaye@sineshaws-MacBook-Pro Operating_System_Project1 % 