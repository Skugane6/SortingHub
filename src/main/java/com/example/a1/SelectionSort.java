package com.example.a1;

import javafx.application.Platform;

public class SelectionSort extends SortingHubController implements SortingStrategy{
    private int[] list;
    private SortingHubController controller;

    @Override
    public void run() {
        Thread thread = new Thread(()->{
            sort(list);
        });
        thread.start();
    }

    public SelectionSort(int[] list, SortingHubController controller){
        this.list = list;
        this.controller = controller;

    }
    public void sort(int[] numbers) {
        int n = numbers.length;

        // One by one move boundary of unsorted subarray
        for (int i = 0; i < n-1; i++) {
            // Find the minimum element in unsorted array
            int minIndex = i;
            for (int j = i+1; j < n; j++)
                if (numbers[j] < numbers[minIndex])
                    minIndex = j;

            // Swap the found minimum element with the first
            // element
            int temp = numbers[minIndex];
            numbers[minIndex] = numbers[i];
            numbers[i] = temp;
            Platform.runLater(()->controller.updateGraph());
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}
