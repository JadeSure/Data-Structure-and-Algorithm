
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Scanner;

public class SeqRepresentation {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub

		Scanner scan = null;
		String lines = "";
		// 3*i + 1 = 100
		int theLastPartLines = 147;
		int averageTimes = 100;
		Integer fileNameNums= 100;
		
		if (fileNameNums < 1000) {
			theLastPartLines = 0;
		}
		
		SequentialRepresentation seq = new SequentialRepresentation();

		try {
			scan = new Scanner(new FileInputStream(String.valueOf(fileNameNums) + " Nodes.txt"));
			int k = 0;
			while (scan.hasNextLine()) {
				lines += scan.nextLine();
				System.out.println("I'm still alive "+k);
				k++;
			}
			// System.out.println(lines);

			String arr[] = lines.split(" ");
//			System.out.println(Arrays.toString(arr));
//			 calculate time for sequential representation
			seq.<String>setRootNode(arr[0]);
			// test average time in 100 times
			double seqEstimatedTime = 0; 
			for (int q =0; q< averageTimes; ++q) {
				long seqStartTime = System.nanoTime();
				for (int i = 1; i < arr.length - theLastPartLines; i += 3) {
					
					seq.<String>splitNode(arr[i], arr[i + 1], arr[i + 2]);
					
					System.out.println("I'm still alive "+i);
				}
				long seqEndTime = System.nanoTime();
				seqEstimatedTime += ((double) (seqEndTime - seqStartTime)) / Math.pow(10, 9);
			}
			System.out.println("Cost time for sequential representation: " + seqEstimatedTime/averageTimes);
			
//**

			// task B Scenario 2, find nodes
			DataGenerator data = new DataGenerator(0, arr.length);

			// randomly select a index as the nodes to be searched, parent and children
			int[] randomArrayIndex = data.sampleWithOutReplacement(averageTimes);
			int nodesValueIndex =-1;
			double seqFNEstimatedTime = 0;
			for (int q =0; q < averageTimes; ++q) {
				nodesValueIndex = randomArrayIndex[q];
				long seqFNStartTime = System.nanoTime();
				System.out.println(seq.findNode(arr[nodesValueIndex]));
				long seqFNEndTime = System.nanoTime();
				seqFNEstimatedTime += ((double) (seqFNEndTime - seqFNStartTime)) / Math.pow(10, 9);
			}
			
			System.out.println("Cost time for sequential representation to find a node: " + seqFNEstimatedTime/averageTimes);

			// Find parent
			double seqFPEstimatedTime = 0;
			for (int q =0; q < averageTimes; ++q) {
				nodesValueIndex = randomArrayIndex[q];
				long seqFPStartTime = System.nanoTime();
				System.out.println(seq.findParent(String.valueOf(arr[nodesValueIndex])));
				long seqFPEndTime = System.nanoTime();
				seqFPEstimatedTime += ((double) (seqFPEndTime - seqFPStartTime)) / Math.pow(10, 9);
			}
			
			System.out.println("Cost time for sequential representation to find its parent: " + seqFPEstimatedTime/averageTimes);

			// Find Children
			double seqFCEstimatedTime = 0;
			for (int q = 0; q< averageTimes; ++q) {
				nodesValueIndex = randomArrayIndex[q];
				long seqFCStartTime = System.nanoTime();
				System.out.println(seq.findChildren(String.valueOf(arr[nodesValueIndex])));
				long seqFCEndTime = System.nanoTime();
				seqFCEstimatedTime += ((double) (seqFCEndTime - seqFCStartTime)) / Math.pow(10, 9);
			}
			
			System.out.println("Cost time for sequential representation to find its children: " + seqFCEstimatedTime/averageTimes);

			// task B Scenario 3, seq pre order
			
			
			PrintWriter writer3 = new PrintWriter("seqPreOrder "+fileNameNums+".txt");
			double seqPreEstimatedTime = 0;
			
			// PreOrder
			for (int q = 0; q< averageTimes; ++q) {
			long seqPreStartTime = System.nanoTime();
			seq.printInPreorder(writer3);
			long seqPreEndTime = System.nanoTime();
			seqPreEstimatedTime += ((double) (seqPreEndTime - seqPreStartTime)) / Math.pow(10, 9);
			}
			System.out.println("Cost time for sequential representation in preOrder: " + seqPreEstimatedTime/averageTimes);
			writer3.close();

			// InOrder
			PrintWriter writer4 = new PrintWriter("seqInOrder "+fileNameNums+".txt");
			double seqInEstimatedTime = 0;
			
			for(int q = 0; q < averageTimes; ++q) {
				long seqInStartTime = System.nanoTime();
				seq.printInInorder(writer4);
				long seqInEndTime = System.nanoTime();
				seqInEstimatedTime += ((double) (seqInEndTime - seqInStartTime)) / Math.pow(10, 9);
			}
			
			System.out.println("Cost time for sequential representation in inOrder: " + seqInEstimatedTime/averageTimes);
			writer4.close();
			
			// PostOrder

			PrintWriter writer5 = new PrintWriter("seqPostOrder "+fileNameNums+".txt");
			
			double seqPostEstimatedTime = 0;
			for (int q = 0; q < averageTimes; ++q) {
				long seqPostStartTime = System.nanoTime();
				seq.printInPostorder(writer5);
				long seqPostEndTime = System.nanoTime();
				seqPostEstimatedTime += ((double) (seqPostEndTime - seqPostStartTime)) / Math.pow(10, 9);
			}
			System.out.println("Cost time for sequential representation in postOrder: " + seqPostEstimatedTime/averageTimes);
			writer5.close();

			if (arr.length > 1000) {
				
				double seqGrowingEstimatedTime = 0;
				for (int q = 0; q < averageTimes; ++q) {
					long seqGrowingStartTime = System.nanoTime();
					for (int i = arr.length - theLastPartLines; i < arr.length; i += 3) {
						seq.<String>splitNode(arr[i], arr[i + 1], arr[i + 2]);
//						System.out.println("I'm still alive "+i);
					}
					long seqGrowingEndTime = System.nanoTime();
					seqGrowingEstimatedTime += ((double) (seqGrowingEndTime - seqGrowingStartTime)) / Math.pow(10, 9);
				}
				
				
				System.out.println(
						"Cost time for sequential representation for another 100 nodes: " + seqGrowingEstimatedTime/averageTimes);
				
			}
			System.out.println("=========================================================================");

		} catch (FileNotFoundException e) {
			System.out.println("Did not found this file");
		}

	}

}
