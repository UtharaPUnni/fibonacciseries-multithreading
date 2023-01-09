package org.perfomatix;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.*;

class FirstThread implements Callable<List<Long>>{
    FirstThread(int n){
        this.n = n;
    }
   static  int n;
    @Override
    public List<Long> call() throws Exception {

    List<Long> fibonacci = new ArrayList<Long>();
    List<Long> fibonacciEven = new ArrayList<Long>();
    if(n>=1){
            fibonacci.add(0l);
            fibonacciEven.add(0l);
        }
        if(n>=2){
            fibonacci.add(1l);
            //fibonacciOdd.add(1l);
        }
        for(int i=2 ;i<n;i++){
            fibonacci.add(fibonacci.get(i - 1) + fibonacci.get(i - 2));
                       if(fibonacci.get(i)%2==0){
                fibonacciEven.add(fibonacci.get(i));
            }
        }
       //System.out.println(" " + fibonacci);
       // System.out.println("Even " + fibonacciEven);
        return fibonacciEven;
    }
}
class SecondThread implements Callable<List<Long>> {
    SecondThread(int n) {
        this.n = n;
    }

    static int n;

    public List<Long> call() throws InterruptedException {
        List<Long> fibonacci = new ArrayList<Long>();
        List<Long> fibonacciOdd = new ArrayList<Long>();
        if (n >= 1) {
            fibonacci.add(0l);
        }
        if (n >= 2) {
            fibonacci.add(1l);
            fibonacciOdd.add(1l);
        }
        for (int i = 2; i < n; i++) {
            fibonacci.add(fibonacci.get(i - 1) + fibonacci.get(i - 2));
            if (fibonacci.get(i) % 2 == 1) {
                fibonacciOdd.add(fibonacci.get(i));
            }
        }
        //System.out.println(" " + fibonacci);
        //System.out.println(" Odd" + fibonacciOdd);
        return fibonacciOdd;
    }

    public static void main(String args[]) throws ExecutionException {

        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the  length of Fibonacci series");
        int x = sc.nextInt();
        ExecutorService executor = Executors.newFixedThreadPool(2);
        Future<List<Long>> oddNumber = executor.submit(new SecondThread(x));
        Future<List<Long>> evenNumber = executor.submit(new FirstThread(x));
        // System.out.println(" abndfbd");


        List<Long> even;
        List<Long> odd;
        try {
            odd = oddNumber.get();
            System.out.println(" OddNumber Printing" + odd);
            even = evenNumber.get();
            System.out.println("Printing Even Number " + even);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        FileWriterFibonacci fb = new FileWriterFibonacci();
        fb.FileWtriter(even);
        fb.FileWtriter2(odd);
    }
}

     class FileWriterFibonacci {

    public synchronized void FileWtriter(List<Long> even ) {
        try {
            FileWriter fw = new FileWriter("output.txt");
            for (Long num:even){
                fw.write(" " +num);
            }
             fw.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        System.out.println("abceddf");
    }

        public synchronized void FileWtriter2(List<Long>odd ) {
             try {
                 FileWriter fw = new FileWriter("output.txt");
                 for (Long num:odd){
                     fw.write(" " +num);
                 }
                 fw.close();
             } catch (IOException e) {
                 throw new RuntimeException(e);
             }

             System.out.println("abceddf");
         }

     }






