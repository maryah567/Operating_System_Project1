import java.io.*;
import java.util.*;

public class SchedulingSimulator {

    static int[] pid;
    static int[] arrivalTime;
    static int[] burstTime;
    static int[] priority;
    static int n;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter the process file name: ");
        String filename = sc.nextLine().trim();

        try {
            readFile(filename);
        } catch (FileNotFoundException e) {
            System.out.println("Could not find file: " + filename);
            sc.close();
            return;
        }

        int choice = 0;
        while (choice != 3) {
            System.out.println("\n CPU Scheduling Simulator");
            System.out.println("1. First_Come, First_Served");
            System.out.println("2. Shortest Job First");
            System.out.println("3. Exit");
            System.out.print("Pick an option ");

            if (sc.hasNextInt()) {
                choice = sc.nextInt();
            } else {
                sc.next();
                System.out.println("Invalid input, try again.");
                continue;
            }

            switch (choice) {
                case 1:
                    runFCFS();
                    break;
                case 2:
                    runSJF();
                    break;
                case 3:
                    System.out.println("Exiting.");
                    break;
                default:
                    System.out.println("Invalid choice, try again.");
            }
        }

        sc.close();
    }

    static void readFile(String filename) throws FileNotFoundException {
        Scanner fileScanner = new Scanner(new File(filename));

        ArrayList<int[]> processList = new ArrayList<>();

        if (fileScanner.hasNextLine()) {
            fileScanner.nextLine();
        }

        while (fileScanner.hasNextLine()) {
            String line = fileScanner.nextLine().trim();
            if (line.isEmpty()) continue;

            String[] parts = line.split("\\s+");
            if (parts.length < 4) continue;

            int[] proc = new int[4];
            proc[0] = Integer.parseInt(parts[0]);
            proc[1] = Integer.parseInt(parts[1]);
            proc[2] = Integer.parseInt(parts[2]);
            proc[3] = Integer.parseInt(parts[3]);
            processList.add(proc);
        }
        fileScanner.close();

        n = processList.size();
        pid = new int[n];
        arrivalTime = new int[n];
        burstTime = new int[n];
        priority = new int[n];

        for (int i = 0; i < n; i++) {
            pid[i] = processList.get(i)[0];
            arrivalTime[i] = processList.get(i)[1];
            burstTime[i] = processList.get(i)[2];
            priority[i] = processList.get(i)[3];
        }

        System.out.println("Loaded " + n + " processes from " + filename);
    }

    static void runFCFS() {
        int[] order = new int[n];
        for (int i = 0; i < n; i++) order[i] = i;

        for (int i = 0; i < n - 1; i++) {
            for (int j = i + 1; j < n; j++) {
                if (arrivalTime[order[j]] < arrivalTime[order[i]]) {
                    int tmp = order[i];
                    order[i] = order[j];
                    order[j] = tmp;
                }
            }
        }

        int[] wt = new int[n];
        int[] tat = new int[n];
        int[] startTimes = new int[n];
        int[] endTimes = new int[n];

        int currentTime = 0;
        for (int i = 0; i < n; i++) {
            int idx = order[i];
            if (currentTime < arrivalTime[idx]) {
                currentTime = arrivalTime[idx];
            }
            startTimes[i] = currentTime;
            currentTime += burstTime[idx];
            endTimes[i] = currentTime;

            tat[idx] = endTimes[i] - arrivalTime[idx];
            wt[idx] = tat[idx] - burstTime[idx];
        }

        System.out.println("\n=== FCFS Scheduling ===");
        printGantt(order, startTimes, endTimes);
        printResults(wt, tat);
    }

    static void runSJF() {
        boolean[] done = new boolean[n];
        int[] wt = new int[n];
        int[] tat = new int[n];

        ArrayList<Integer> ganttOrder = new ArrayList<>();
        ArrayList<Integer> ganttStart = new ArrayList<>();
        ArrayList<Integer> ganttEnd = new ArrayList<>();

        int currentTime = 0;
        int completed = 0;

        while (completed < n) {
            int shortest = -1;
            int minBurst = Integer.MAX_VALUE;

            for (int i = 0; i < n; i++) {
                if (!done[i] && arrivalTime[i] <= currentTime) {
                    if (burstTime[i] < minBurst) {
                        minBurst = burstTime[i];
                        shortest = i;
                    } else if (burstTime[i] == minBurst && arrivalTime[i] < arrivalTime[shortest]) {
                        shortest = i;
                    }
                }
            }

            if (shortest == -1) {
                currentTime++;
                continue;
            }

            ganttOrder.add(shortest);
            ganttStart.add(currentTime);
            currentTime += burstTime[shortest];
            ganttEnd.add(currentTime);

            tat[shortest] = currentTime - arrivalTime[shortest];
            wt[shortest] = tat[shortest] - burstTime[shortest];
            done[shortest] = true;
            completed++;
        }

        int[] orderArr = new int[ganttOrder.size()];
        int[] startArr = new int[ganttStart.size()];
        int[] endArr = new int[ganttEnd.size()];
        for (int i = 0; i < ganttOrder.size(); i++) {
            orderArr[i] = ganttOrder.get(i);
            startArr[i] = ganttStart.get(i);
            endArr[i] = ganttEnd.get(i);
        }

        System.out.println("\n=== SJF Scheduling ===");
        printGantt(orderArr, startArr, endArr);
        printResults(wt, tat);
    }

    static void printGantt(int[] order, int[] startTimes, int[] endTimes) {
        System.out.println("\nGantt Chart:");

        StringBuilder topLine = new StringBuilder();
        StringBuilder bottomLine = new StringBuilder();

        for (int i = 0; i < order.length; i++) {
            String label = "P" + pid[order[i]];
            int width = String.valueOf(endTimes[i]).length() + 2;
            if (width < label.length() + 2) width = label.length() + 2;

            topLine.append("| ");
            topLine.append(label);
            for (int s = 0; s < width - label.length() - 1; s++) topLine.append(" ");
        }
        topLine.append("|");

        bottomLine.append(startTimes[0]);
        for (int i = 0; i < order.length; i++) {
            String label = "P" + pid[order[i]];
            int width = String.valueOf(endTimes[i]).length() + 2;
            if (width < label.length() + 2) width = label.length() + 2;

            String endStr = String.valueOf(endTimes[i]);
            for (int s = 0; s < width + 1 - endStr.length(); s++) bottomLine.append(" ");
            bottomLine.append(endStr);
        }

        System.out.println(topLine.toString());
        System.out.println(bottomLine.toString());
    }

    static void printResults(int[] wt, int[] tat) {
        System.out.println("\nPID\tArrival\tBurst\tWaiting\tTurnaround");

        double totalWT = 0;
        double totalTAT = 0;

        for (int i = 0; i < n; i++) {
            System.out.println(pid[i] + "\t" + arrivalTime[i] + "\t" + burstTime[i] + "\t" + wt[i] + "\t" + tat[i]);
            totalWT += wt[i];
            totalTAT += tat[i];
        }

        System.out.printf("\nAverage Waiting Time: %.2f\n", totalWT / n);
        System.out.printf("Average Turnaround Time: %.2f\n", totalTAT / n);
        System.out.print("CPU Utilization: 100%\n");
    }
}
