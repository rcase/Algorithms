package com.company;

import com.company.ADTs.*;
import com.company.Iterators.LazySieve;
import com.company.Iterators.MergeIterator;
import com.company.Iterators.MultiplesOf;
import com.company.Sorting.*;
import com.company.StdLib.*;
import com.company.Streams.RunningMedian;

import java.io.IOException;
import java.lang.String;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;

public class Main {

    public static void main(String[] args) {
        Stopwatch timer = new Stopwatch();
        System.out.println("");

        Node head = new Node();
        head.addValues(new Integer[] { 0, 1, 2, 3, 0, 4, 5, 0, 6, 7, 0, 8, 9} );
        System.out.println(head.toString());
        head.removeValue(0);
        System.out.println(head.toString());
        Node mappedHead = head.map((Integer x) -> x * x);
        System.out.println(mappedHead);
        Node filteredHead = head.filter((Integer x) -> x % 2 == 0);
        System.out.println(filteredHead);

        System.out.printf("\nTook %1.3fs\n", timer.elapsedTime());
    }

    // Euclid's algorithm for greatest common denominator
    public static int gcd(int p, int q) {
        if (q == 0) return p;
        int r = p % q;
        return gcd(q, r);
    }

    // Binary Search Algorithm
    // Only works with a sorted array
    // Returns the index of the int within the array, -1 otherwise
    public static int binarySearch(int key, int[] a) {
        int lo = 0;
        int hi = a.length - 1;
        while (lo <= hi) {
            int mid = lo + (hi - lo) / 2;
            if (key < a[mid]) hi = mid - 1;
            else if (key > a[mid]) lo = mid + 1;
            else return mid;
        }
        return -1;
    }

    // Reads the files and runs the search, using the largeT and largeW files will produce more
    // output than the console is capable of rendering
    public static void runBinarySearch() {
        try {
            List<String> tinyW = Files.readAllLines(Paths.get("./data/tinyW.txt"));
            int[] wlist = new int[tinyW.size()];
            for (int i=0; i<tinyW.size(); i++) {
                wlist[i] = Integer.parseInt(tinyW.get(i).trim());
            }
            // Sort the array
            Arrays.sort(wlist);
            // Remove duplicates by changing dupes to -1
            for (int i=0; i<wlist.length-1; i++) {
                if (wlist[i] == wlist[i + 1]) {
                    wlist[i] = -1;
                }
            }
            // Sorting it and reconstructing the array with everything above -1
            Arrays.sort(wlist);
            int x = 0;
            while (true) {
                if (wlist[x] == -1) {
                    x++;
                } else {
                    break;
                }
            }
            wlist = Arrays.copyOfRange(wlist, x, wlist.length);

            List<String> tinyT = Files.readAllLines(Paths.get("./data/tinyT.txt"));
            int[] transactions = new int[tinyT.size()];
            for (int i=0; i<tinyT.size(); i++) {
                transactions[i] = Integer.parseInt(tinyT.get(i).trim());
            }
            for (int t : transactions) {
                if (binarySearch(t, wlist) == -1) {
                    System.out.println(t);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
