package search;

import java.util.ArrayList;

public class BinarySearch {

	public static int binarySearch(ArrayList<Integer> arr, int left, int right, int searchElement) {
		if (right >= left) {
			int mid = left + (right - left) / 2;
			if (arr.get(mid) == searchElement)
				return mid;
			if (arr.get(mid) > searchElement)
				return binarySearch(arr, left, mid - 1, searchElement);
			return binarySearch(arr, mid + 1, right, searchElement);
		}
		return -1;
	}

	public static int search(ArrayList<Integer> inputData, int searchElement) {
		int n = inputData.size();
		return binarySearch(inputData, 0, n - 1, searchElement);
	}
}
