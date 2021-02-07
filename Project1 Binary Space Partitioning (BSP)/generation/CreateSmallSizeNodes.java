import java.io.FileWriter;
import java.io.IOException;

public class CreateSmallSizeNodes {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub

		DataGenerator data = new DataGenerator(1, 2001);
		int[] arr = data.sampleWithOutReplacement(1100);

		// merge two arrays into a new array

		FileWriter writer1;

		try {
			writer1 = new FileWriter("1100 Nodes.txt");
			writer1.write(arr[0] + " " + '\n');
			for (int i = 0; i < 499; ++i) {
				writer1.write(arr[i] + " ");
				writer1.write(arr[i * 2 + 1] + " ");
				writer1.write(arr[i * 2 + 2] + " " + '\n');
			}
			System.out.println("base nodes " + arr.length);

			writer1.close();

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
