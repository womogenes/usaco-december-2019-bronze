import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class lineup {

	public static void main(String[] args) throws IOException {

		String prefix = "src/";

		BufferedReader f = new BufferedReader(new FileReader(prefix + "lineup.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(prefix + "lineup.out")));

		String[] cows = { "Beatrice", "Belinda", "Bella", "Bessie", "Betsy", "Blue", "Buttercup", "Sue" };
		Map<String, Integer> name2cow = new HashMap<>();
		Map<Integer, String> cow2name = new HashMap<>();

		for (int i = 0; i < 8; i++) {
			name2cow.put(cows[i], i);
			cow2name.put(i, cows[i]);
		}

		int n = Integer.parseInt(f.readLine());
		int[][] requirements = new int[n][2];

		for (int i = 0; i < n; i++) {
			String req = f.readLine();
			String[] reqs = req.split(" ");
			requirements[i] = new int[] { name2cow.get(reqs[0]), name2cow.get(reqs[5]) };
		}
		f.close();

		/*
		for (int i = 0; i < requirements.length; i++) {
			System.out.print(requirements[i][0] + ", ");
			System.out.println(requirements[i][1]);
		}
		*/

		int[] x = { 0, 1, 2, 3, 4, 5, 6, 7 };

		List<List<Integer>> perms = permute(x);
		for (List<Integer> i : perms) {

			if (works(i, requirements)) {
				String result = "";
				for (int j = 0; j < i.size(); j++) {
					result += cow2name.get(i.get(j)) + "\n";
				}
				System.out.println(result);
				out.print(result);
				out.close();
				break;
			}
		}
	}

	public static boolean works(List<Integer> cows, int[][] requirements) {
		Map<Integer, Integer> cow2place = new HashMap<>();
		for (int i = 0; i < cows.size(); i++) {
			cow2place.put(cows.get(i), i);
		}
		for (int[] i : requirements) {
			int x = Math.abs(cow2place.get(i[0]) - cow2place.get(i[1]));
			// System.out.println(cows.stream().map(n -> String.valueOf(n)).collect(Collectors.joining("-", "{", "}")) + x);
			if (x != 1) {
				return false;
			}
		}
		return true;
	}
	
	public static List<List<Integer>> permute(int[] arr) {
		List<List<Integer>> list = new ArrayList<>();
		permuteHelper(list, new ArrayList<>(), arr);
		return list;
	}
 
	private static void permuteHelper(List<List<Integer>> list, List<Integer> resultList, int [] arr){
 
		// Base case
		if(resultList.size() == arr.length){
			list.add(new ArrayList<>(resultList));
		} 
		else{
			for(int i = 0; i < arr.length; i++){ 
 
				if(resultList.contains(arr[i])) 
				{
					// If element already exists in the list then skip
					continue; 
				}
				// Choose element
				resultList.add(arr[i]);
				// Explore
				permuteHelper(list, resultList, arr);
				// Unchoose element
				resultList.remove(resultList.size() - 1);
			}
		}
	}
}