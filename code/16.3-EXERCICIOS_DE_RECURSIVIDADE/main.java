class Main {
  public static int maxValue(int[] arr){
    return maxValue(arr, 0);
  }
  public static int maxValue(int[] arr, int n){
    if (n == arr.length - 1) return arr[n];
    var value = arr[n];
    if (value > maxValue(arr, n + 1)){
      return value;
    }
    return maxValue(arr, n + 1);
  }
  public static void main(String[] args) {
    int[] arr = {4,6,1,2};
    System.out.println(maxValue(arr));
  }
}