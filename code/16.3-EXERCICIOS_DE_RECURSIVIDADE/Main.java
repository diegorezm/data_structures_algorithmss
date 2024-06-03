class Main {
  public static void insertion_sort(int[] arr) {
     insertion_sort(arr, arr.length);
  }

  public static void insertion_sort(int[] arr, int idx) {
    if (idx < 1) {
      return;
    }
    insertion_sort(arr, idx - 1);
    last_el = idx - 1;
    int i = idx - 2;
    while (i >= 0 && last_el > arr[i]) {
      arr[i + 1] = arr[i];
    }
    arr[i + 1] = last_el;
  }

  public static void main(String[] args) {
    int[] arr = { 4, 6, 1, 2 };
    insertion_sort(arr);
    for (int arr2 : arr) {
      System.out.println(arr2);
    }
  }
}