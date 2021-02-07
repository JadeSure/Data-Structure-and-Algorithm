import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class CreateNodesValues {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		Scanner scan = null;
		String lines = "";
		int alreadyExist = 4000;
		int genValuesNum = 1000100 - alreadyExist;
		int rangeOfValues = 2 * genValuesNum;

		String exportFileName = String.valueOf(genValuesNum) + " Nodes.txt";
		int iCount = (genValuesNum - 2) / 2;

		try {

			scan = new Scanner(new FileInputStream("BSP_combined.txt"));

			while (scan.hasNextLine()) {
				lines += scan.nextLine();
			}

			String arr1[] = lines.split(" ");
			boolean mark[] = new boolean[arr1.length];

			// judge how many nodes in BSP_combined.txt 3999
//			System.out.println(arr1.length); 
//			System.out.println(Arrays.toString(arr1)); 

			// method 1
			// key for numbers which appears to BSP_combined.txt
			// value for appear times
//			Map<String, Integer> hashMap = new HashMap<>();
//			for(String str : arr1) {
//				// check does have key
//				Integer num = hashMap.get(str);
//				// if it doesnt have, set it to one and put str as key
//				// if it already have, add count ++
//				hashMap.put(str, num == null? 1:num+1);
//			}
//			
//			// check the whole hashMap results and counts how many values have only one count
//			int count =0;
//			for (Map.Entry<String, Integer> entry: hashMap.entrySet()) {
////				System.out.println("Key = " + entry.getKey() + " , Value = " + entry.getValue());
//				if (entry.getValue() == 1) {
//					++count;
//				}
//			}
//			
//			// collect all the values which appear one time and create an array
//			String [] temp = new String[count];
//			int n = 0;
//			for (Map.Entry<String, Integer> entry: hashMap.entrySet()) {
//				
////				System.out.println("Key = " + entry.getKey() + " , Value = " + entry.getValue());
//				if (entry.getValue() == 1) {
//					temp [n] = entry.getKey();
//					++n;
//				}	
//			}
			// method 2

			// mark values which appear twice to true
			int count = 0;
			for (int i = 0; i < arr1.length; ++i) {
				for (int j = i + 1; j < arr1.length; ++j) {

					if (arr1[i].equals(arr1[j])) {
						mark[i] = true;
						mark[j] = true;
						count++;
					}

				}
			}
//			System.out.println(count); 
//			System.out.println(Arrays.toString(mark)); 

			// put values which appear once into a new array
			int k = 0;
			// totally, the number of 'arr1.length - 2*count' values, appear once time
			String[] temp = new String[arr1.length - 2 * count];
			for (int i = 0; i < mark.length; ++i) {
				if (mark[i] == false) {
					temp[k] = arr1[i];
					k++;
					// 2000 values appear once
//					System.out.println(k);
				}
			}
			// the counts for values that appear once
			int genValuesPeriod = temp.length;

			//because, for BSP_combined.txt, it has appeared values which covered before 4000 nodes
			// the random data generator should begain from 4000
			// in order to make the random value "random enough", we set double size for random period
			// compared with the number of generated values
			DataGenerator data = new DataGenerator(alreadyExist, rangeOfValues);
			// how many size should we create (minus BSP_combined.txt)
			int[] arr2 = data.sampleWithOutReplacement(genValuesNum - genValuesPeriod);

			// merge two arrays into a new array
			String[] finalArr = new String[genValuesNum];
			for (int i = 0; i < finalArr.length; i++) {

				if (i < temp.length) {
					finalArr[i] = temp[i];
					// random generated values
				} else {
					finalArr[i] = String.valueOf(arr2[i - temp.length]);
				}
			}

			FileWriter writer1 = new FileWriter(exportFileName);
//			FileWriter writer2 = new FileWriter("1000+100 Nodes.txt");

//				writer1.write(finalArr[0]+" "+'\n');

			for (int i = 0; i < iCount; ++i) {
				writer1.write(finalArr[i] + " ");
				writer1.write(finalArr[i * 2 + 1] + " ");
				writer1.write(finalArr[i * 2 + 2] + " " + '\n');
			}
//			System.out.println("base nodes "+finalArr.length);

//			for (int i = 499; i<549; ++i) {
//				writer2.write(finalArr[i]+ " ");
//				writer2.write(finalArr[i*2+1]+ " ");
//				writer2.write(finalArr[i*2+2]+ " "+ '\n');
//			}
//			
//			
			writer1.close();
//			writer2.close();

		} catch (FileNotFoundException e) {
			System.out.println("Did not found this file");
		}

//			DataGenerator data2 = new DataGenerator(1,10);
//			System.out.print(Arrays.toString(data2.sampleWithOutReplacement(5)));
	}
}