package search;

import java.util.ArrayList;

public class LinearSearch {

	public static int search(ArrayList<Integer> inputData, int searchElement) {
		int n = inputData.size();
		for (int i = 0; i < n; i++) {
			if (inputData.get(i) == searchElement)
				return i;
		}
		return -1;
	}
}
