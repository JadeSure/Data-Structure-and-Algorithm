import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;

public class mergeFile {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String fileName1 = "996100 Nodes.txt";
		String fileName2 = "1000100 Nodes.txt";
		try {
			FileReader fr1 = new FileReader("BSP_combined.txt");
			FileReader fr2 = new FileReader(fileName1);
			
			BufferedReader br1 = new BufferedReader(fr1);
			BufferedReader br2 = new BufferedReader(fr2);
			BufferedWriter bw3 = new BufferedWriter(new FileWriter(fileName2));
			
			String s1, s2;
			s1 = br1.readLine();
			s2 = br2.readLine();
			while(s1 != null) {
				bw3.write(s1);
				s1 = br1.readLine();
				bw3.newLine();
			}
			
			while(s2 != null) {
				bw3.write(s2);
				s2 = br2.readLine();
				bw3.newLine();
			}
			
			br1.close();
			br2.close();
			bw3.close();
			System.out.println("Successful");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
