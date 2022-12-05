package days;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Collectors;

public class Day2 {
	public static void main(String[] args) {
		try {
			List<String> rounds = Files.lines(Path.of("src/days/resources/day2.in")).collect(Collectors.toList());

			getScorePart1(rounds);
			getScorePart2(rounds);

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static void getScorePart2(List<String> rounds) {
		int score = 0;
		for (String round : rounds) {
			switch (round.charAt(0)) {
			case 'A': { // against rock
				switch (round.charAt(2)) {
				case 'X':
					score += 3;
					break;
				case 'Y':
					score += 1 + 3;
					break;
				case 'Z':
					score += 2 + 6;
					break;
				}
			}
				break;
			case 'B': { // against paper
				switch (round.charAt(2)) {
				case 'X':
					score += 1;
					break;
				case 'Y':
					score += 2 + 3;
					break;
				case 'Z':
					score += 3 + 6;
					break;
				}
			}
				break;
			case 'C': { // against scissors
				switch (round.charAt(2)) {
				case 'X':
					score += 2;
					break;
				case 'Y':
					score += 3 + 3;
					break;
				case 'Z':
					score += 1 + 6;
					break;
				}
			}
				break;
			}
		}
		System.out.println(score);
	}

	private static void getScorePart1(List<String> rounds) {
		int score = 0;
		for (String round : rounds) {
			switch (round.charAt(0)) {
			case 'A': {
				switch (round.charAt(2)) {
				case 'X':
					score += 1 + 3;
				case 'Y':
					score += 2 + 6;
				case 'Z':
					score += 3;
				}
			}
				break;
			case 'B': {
				switch (round.charAt(2)) {
				case 'X':
					score += 1;
				case 'Y':
					score += 2 + 3;
				case 'Z':
					score += 3 + 6;
				}
			}
				break;
			case 'C': {
				switch (round.charAt(2)) {
				case 'X':
					score += 1 + 6;
				case 'Y':
					score += 2;
				case 'Z':
					score += 3 + 3;
				}
			}
				break;
			}
		}
		System.out.println(score);
	}

}
