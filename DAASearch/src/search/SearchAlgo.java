package search;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Random;
import java.util.Set;

import javax.swing.SwingUtilities;

class GraphArray{
	public GraphArray() {
		super();
	}
	int size;
	float seconds;
	String algoValue;
	public String getAlgoValue() {
		return algoValue;
	}
	public void setAlgoValue(String algoValue) {
		this.algoValue = algoValue;
	}
	public String getFoundEle() {
		return foundEle;
	}
	public void setFoundEle(String foundEle) {
		this.foundEle = foundEle;
	}
	String foundEle;
	public int getSize() {
		return size;
	}
	public void setSize(int size) {
		this.size = size;
	}
	public float getSeconds() {
		return seconds;
	}
	public void setSeconds(float seconds) {
		this.seconds = seconds;
	}
	
}

public class SearchAlgo {
	
	public static ArrayList<Integer> readInputFile(String inputFile)
			throws FileNotFoundException, IOException, URISyntaxException {
		File resource = new File(Thread.currentThread().getContextClassLoader().getResource(inputFile).toURI());

		try (BufferedReader reader = new BufferedReader(new FileReader(resource))) {
			String line;
			ArrayList<Integer> inputData = new ArrayList<>();
			while ((line = reader.readLine()) != null) {
				if (line.contains("END OF INPUT")) {
					break;
				} else {
					String[] values = line.split(" ");
					for (String value : values)
						inputData.add(Integer.parseInt(value));
				}
			}
			return inputData;
		}
	}
	
	public static void linearSearch() {
		
	}

	public static void main(String[] args) throws FileNotFoundException, IOException, URISyntaxException {

		int findEle = Integer.parseInt(args[0]);
		ArrayList<String> searchAlgoValue = new ArrayList<>();
		ArrayList<Integer> searchAlgoSize = new ArrayList<>();
		ArrayList<Integer> randomInput = new ArrayList<>();
		ArrayList<String> randomInputFile = new ArrayList<>();
		ArrayList<GraphArray> finalOpList = new ArrayList<>();
		long startMS;
		long endMS;
		
		boolean compare = false;
		for(int i=1; i<args.length; i++) {
			if(args[i].equalsIgnoreCase("linear") || args[i].equalsIgnoreCase("binary") || args[i].equalsIgnoreCase("bst") || 
					args[i].equalsIgnoreCase("rbt")) {
				searchAlgoValue.add(args[i]);
			}else if(args[i].equalsIgnoreCase("compare")){
				compare = true;
			}else if(args[i].contains(".txt")){
				randomInputFile.add(args[i]);
			}else {
				searchAlgoSize.add(Integer.parseInt(args[i]));
			}
		}
		
		if(randomInputFile.size() > 0) {
			for(String algoFile : randomInputFile) {
				//Generating Input based on size
				ArrayList<Integer> randomInputFileData = new ArrayList<>();
				randomInputFileData = readInputFile(algoFile);
			    for(String algoValue: searchAlgoValue) {
			    	GraphArray graphArray = new GraphArray();
			    	if(algoValue.equalsIgnoreCase("linear")) {
			    		graphArray.setSize(randomInputFileData.size());
			    		graphArray.setAlgoValue(algoValue);
			    		startMS = System.currentTimeMillis();
			    		int op = LinearSearch.search(randomInputFileData, findEle);
			    		endMS = System.currentTimeMillis();
			    		graphArray.setSeconds((endMS - startMS) / 1000F);
			    		graphArray.setFoundEle(String.valueOf(op));
			    		finalOpList.add(graphArray);
			    	}else if(algoValue.equalsIgnoreCase("binary")) {
			    		graphArray.setSize(randomInputFileData.size());
			    		graphArray.setAlgoValue(algoValue);
			    		ArrayList<Integer> sortedList = new ArrayList<>();
			    		for(int a : randomInputFileData) {
			    			sortedList.add(a);
			    		}
			    		Collections.sort(sortedList);
			    		startMS = System.currentTimeMillis();
			    		int op = BinarySearch.search(sortedList, findEle);
			    		endMS = System.currentTimeMillis();
			    		graphArray.setSeconds((endMS - startMS) / 1000F);
			    		graphArray.setFoundEle(String.valueOf(op));
			    		finalOpList.add(graphArray);
			    	}else if(algoValue.equalsIgnoreCase("bst")) {
			    		graphArray.setSize(randomInputFileData.size());
			    		graphArray.setAlgoValue(algoValue);
			    		BST bst = new BST();
			    		startMS = System.currentTimeMillis();
			    		int op = bst.searchReturn(randomInputFileData, findEle);
			    		endMS = System.currentTimeMillis();
			    		graphArray.setSeconds((endMS - startMS) / 1000F);
			    		graphArray.setFoundEle(String.valueOf(op));
			    		finalOpList.add(graphArray);
			    	}else {
			    		graphArray.setSize(randomInputFileData.size());
			    		graphArray.setAlgoValue(algoValue);
			    		RedBlackTree redBlackTree = new RedBlackTree();
			    		startMS = System.currentTimeMillis();
			    		int op = redBlackTree.searchReturn(randomInputFileData, findEle);
			    		endMS = System.currentTimeMillis();
			    		graphArray.setSeconds((endMS - startMS) / 1000F);
			    		graphArray.setFoundEle(String.valueOf(op));
			    		finalOpList.add(graphArray);
			    	}
			    }
			    
			}
		}else {
			for(int algoSize : searchAlgoSize) {
				//Generating Input based on input file
				Random rand = new Random();
				Set<Integer>set = new LinkedHashSet<Integer>();
			      while (set.size() < algoSize) {
			         set.add(rand.nextInt(algoSize)+1);
			      }
			    for(int a: set) {
			    	randomInput.add(a);
			    }
			    
			    for(String algoValue: searchAlgoValue) {
			    	GraphArray graphArray = new GraphArray();
			    	if(algoValue.equalsIgnoreCase("linear")) {
			    		graphArray.setSize(algoSize);
			    		graphArray.setAlgoValue(algoValue);
			    		startMS = System.currentTimeMillis();
			    		int op = LinearSearch.search(randomInput, findEle);
			    		endMS = System.currentTimeMillis();
			    		graphArray.setSeconds((endMS - startMS) / 1000F);
			    		graphArray.setFoundEle(String.valueOf(op));
			    		finalOpList.add(graphArray);
			    	}else if(algoValue.equalsIgnoreCase("binary")) {
			    		graphArray.setSize(algoSize);
			    		graphArray.setAlgoValue(algoValue);
			    		ArrayList<Integer> sortedList = new ArrayList<>();
			    		for(int a : randomInput) {
			    			sortedList.add(a);
			    		}
			    		Collections.sort(sortedList);
			    		startMS = System.currentTimeMillis();
			    		int op = BinarySearch.search(sortedList, findEle);
			    		endMS = System.currentTimeMillis();
			    		graphArray.setSeconds((endMS - startMS) / 1000F);
			    		graphArray.setFoundEle(String.valueOf(op));
			    		finalOpList.add(graphArray);
			    	}else if(algoValue.equalsIgnoreCase("bst")) {
			    		graphArray.setSize(algoSize);
			    		graphArray.setAlgoValue(algoValue);
			    		BST bst = new BST();
			    		startMS = System.currentTimeMillis();
			    		int op = bst.searchReturn(randomInput, findEle);
			    		endMS = System.currentTimeMillis();
			    		graphArray.setSeconds((endMS - startMS) / 1000F);
			    		graphArray.setFoundEle(String.valueOf(op));
			    		finalOpList.add(graphArray);
			    	}else {
			    		graphArray.setSize(algoSize);
			    		graphArray.setAlgoValue(algoValue);
			    		RedBlackTree redBlackTree = new RedBlackTree();
			    		startMS = System.currentTimeMillis();
			    		int op = redBlackTree.searchReturn(randomInput, findEle);
			    		endMS = System.currentTimeMillis();
			    		graphArray.setSeconds((endMS - startMS) / 1000F);
			    		graphArray.setFoundEle(String.valueOf(op));
			    		finalOpList.add(graphArray);
			    	}
			    }
			    
			}
		}
		
		System.out.println("\n");
		System.out.printf("\n %10s %15s %15s %10s ", "Size" , "Algorithm" , "Element" , "Seconds");
		System.out.println("\n");
		for(GraphArray graphArray : finalOpList) {
			String Ele = null;
			if(graphArray.foundEle.contains("-1")) {
				Ele = "NotFound";
			}else {
				Ele = graphArray.foundEle;
			}
			System.out.printf("\n %10d  %15s %15s %10s", graphArray.size, graphArray.algoValue, Ele, graphArray.seconds);
			
		}
		if(compare) {
			SwingUtilities.invokeLater(new Runnable() {
				@Override
				public void run() {
					new ChartGeneration(finalOpList).setVisible(true);
				}
			});
		}
	}

}



