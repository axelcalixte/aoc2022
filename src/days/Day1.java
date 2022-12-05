package days;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Day1 {

	public static void main(String[] args) {
		try {
			String input = Files.readString(Path.of("src/days/resources/day1.in"));
			List<String> groups = List.of(input.split("\n\n"));

			// Getting a list of sums of the elves' total calories count
			List<Integer> elves = groups.stream()
					.map(line -> Stream.of(line.split("\n")).flatMapToInt(s -> IntStream.of(Integer.parseInt(s))).sum())
					.collect(Collectors.toList());

			// finding the elf with the largest amount of calories
			var option = elves.stream().max(Integer::compare);
			option.ifPresent(System.out::println);

			// Summing the three biggest amounts together
			var sumThreeBiggestElves = elves.stream().sorted(Comparator.reverseOrder()).limit(3)
					.mapToInt(Integer::valueOf).sum();
			System.out.println(sumThreeBiggestElves);

		} catch (IOException e) {
			e.printStackTrace();
		}

	}
}
