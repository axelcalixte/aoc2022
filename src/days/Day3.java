package days;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class Day3 {
	public static void main(String[] args) {
		try {
			List<String> rucksacks = Files.lines(Path.of("src/days/resources/day3.test")).collect(Collectors.toList());

			partOne(rucksacks);
			// partTwo(rucksacks);
			partTwo2(rucksacks, 0, 0);

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	static char findDuplicatedItem(String rucksack) {
		int len = rucksack.length();
		boolean found = false;
		char item = '\0';

		for (int i = 0; i < len / 2; i++) {
			for (int j = len - 1; j > (len / 2) - 1; j--) {
				if (rucksack.charAt(i) == rucksack.charAt(j)) {
					item = rucksack.charAt(i);
					found = true;
					break;
				}
			}
			if (found)
				break;
		}
		return item;
	}

	static int getPriority(char c) {
		int priority;
		if ('a' <= c && c <= 'z') {
			priority = (int) c - 96;

		} else {
			priority = (int) c - 38;
		}
		return priority;
	}

	static void partOne(List<String> input) {
		int answer = 0;
		for (String rucksack : input) {
			char item = findDuplicatedItem(rucksack);
			answer += getPriority(item);
		}
		System.out.println("sum of priorities for part1 is: " + answer);
	}

	static void partTwo(List<String> input) {
		int answer = 0;

		Set<Character> a;
		Set<Character> b;
		Set<Character> c;

		for (int i = 0; i < input.size() - 2; i += 3) {
			a = input.get(i).chars().mapToObj(item -> (char) item).collect(Collectors.toSet());
			b = input.get(i + 1).chars().mapToObj(item -> (char) item).collect(Collectors.toSet());
			c = input.get(i + 2).chars().mapToObj(item -> (char) item).collect(Collectors.toSet());

			a.retainAll(b);
			a.retainAll(c);

			var it = a.iterator();
			answer += getPriority(it.next());
		}
		System.out.println("answer part2: " + answer);
	}

	static char findDuplicatedPart2(List<String> input) {
		char duplicated = '\0';
		boolean found = false;

		for (int i = 0; i < input.get(0).length(); i++) {
			for (int j = 0; j < input.get(1).length(); j++) {
				for (int k = 0; k < input.get(2).length(); k++) {
					if (input.get(0).charAt(i) == input.get(1).charAt(j)
							&& input.get(0).charAt(i) == input.get(2).charAt(k)) {
						duplicated = input.get(0).charAt(i);
						found = true;
						break;
					}
				}
				if (found)
					break;
			}
			if (found)
				break;
		}

		return duplicated;
	}

	static void partTwo2(List<String> input, int current, int score) {
		if (current == input.size()) {
			System.out.println("sum of priorities for part2 is: " + score);
		} else {
			var lines = input.stream().skip(current).limit(3).collect(Collectors.toList());
			score += getPriority(findDuplicatedPart2(lines));
			partTwo2(input, current + 3, score);
		}
	}

}
