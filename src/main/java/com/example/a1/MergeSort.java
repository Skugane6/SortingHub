package com.example.a1;

import javafx.application.Platform;

public class MergeSort extends SortingHubController implements SortingStrategy{

    private int[] list;
    private static SortingHubController controller;

    @Override
    public void run() {
        Thread thread = new Thread(()->{
            sort(list);
        });
        thread.start();
    }


    public MergeSort(int[] list, SortingHubController controller){
        this.list = list;
        this.controller = controller;

    }

    public void sort(int[] numbers) {
        sortArray(numbers, 0, numbers.length-1);
    }

    private void sortArray(int[] arr, int left, int right) {
        if (left < right) {
            int mid = (left + right) / 2;
            sortArray(arr, left, mid);
            sortArray(arr, mid + 1, right);
            merge(arr, left, mid, right);
            Platform.runLater(()-> controller.updateGraph());
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void merge(int[] arr, int left, int mid, int right) {
        int n1 = mid - left + 1;
        int n2 = right - mid;

        int[] L = new int[n1];
        int[] R = new int[n2];

        for (int i = 0; i < n1; i++) {
            L[i] = arr[left + i];
        }
        for (int j = 0; j < n2; j++) {
            R[j] = arr[mid + 1 + j];
        }

        int i = 0, j = 0, k = left;
        while (i < n1 && j < n2) {
            if (L[i] <= R[j]) {
                arr[k] = L[i];
                i++;
            } else {
                arr[k] = R[j];
                j++;
            }
            k++;
        }

        while (i < n1) {
            arr[k] = L[i];
            i++;
            k++;
        }

        while (j < n2) {
            arr[k] = R[j];
            j++;
            k++;
        }
    }

}
