package days;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Day4 {
	public static void main(String[] args) {
		try {
			var lines = Files.lines(Path.of("src/days/resources/day4.in")).collect(Collectors.toList());
			solvePart1(lines);
			solvePart2(lines);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	static boolean isContained(List<Integer> assignment) {
		return (assignment.get(0) <= assignment.get(2) && assignment.get(3) <= assignment.get(1))
				|| (assignment.get(2) <= assignment.get(0) && assignment.get(1) <= assignment.get(3));
	}

	static boolean duplicateWork(Set<Integer> one, Set<Integer> two) {
		one.retainAll(two);
		return one.size() != 0;
	}

	static void solvePart1(List<String> input) {
		int sum = 0;
		List<Integer> temp;

		for (String line : input) {
			var pairs = line.split(",");
			temp = new ArrayList<>();

			for (String pair : pairs) {
				var ranges = pair.split("-");
				temp.add(Integer.parseInt(ranges[0]));
				temp.add(Integer.parseInt(ranges[1]));
			}

			if (isContained(temp))
				sum++;
		}

		System.out.println(sum);
	}

	static void solvePart2(List<String> input) {
		int sumPart2 = 0;
		Set<Integer> one;
		Set<Integer> two;

		for (String line : input) {
			var pairs = line.split(",");
			var first = pairs[0].split("-");
			var second = pairs[1].split("-");

			// I needed to figure this out ! looks ugly though
			one = IntStream.range(Integer.parseInt(first[0]), Integer.parseInt(first[1]) + 1).boxed()
					.collect(Collectors.toSet());
			two = IntStream.range(Integer.parseInt(second[0]), Integer.parseInt(second[1]) + 1).boxed()
					.collect(Collectors.toSet());
			if (duplicateWork(one, two))
				sumPart2++;
		}

		System.out.println(sumPart2);
	}
}
