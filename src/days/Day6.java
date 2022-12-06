package days;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashSet;
import java.util.Set;

public class Day6 {
	public static void main(String[] args) {
		try {
			String input = Files.newBufferedReader(Path.of("src/days/resources/day6.in")).readLine();
			int marker = detectMarker(input, 4);
			int message = detectMarker(input, 14);
			System.out.println(marker);
			System.out.println(message);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	static int detectMarker(String input, int size) {
		int nbChars = 0;
		Set<Character> marker = new HashSet<>();
		for (int i = 0; i < input.length(); i++) {
			for (int j = 0; j < size; j++) {
				marker.add(input.charAt(i + j));
			}
			if (marker.size() == size) {
				System.out.println(marker);
				nbChars = i + size;
				break;
			} else {
				marker = new HashSet<>();
			}
		}
		return nbChars;
	}

}
