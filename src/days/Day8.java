package days;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Day8 {
	public static void main(String[] args) {
		List<String> input;
		try {
			input = Files.readAllLines(Path.of("src/days/resources/day8.in"));
		} catch (IOException e) {
			throw new RuntimeException(e);
		}

		List<List<Integer>> forest = new ArrayList<>();
		for (String line : input) {
			forest.add(line.chars().mapToObj(c -> Integer.parseInt(String.valueOf((char) c)))
					.collect(Collectors.toList()));
		}

		partOne(forest);
		partTwo(forest);
	}

	public static void partOne(List<List<Integer>> forest) {
		int visibleOnEdges = 0;
		visibleOnEdges += forest.get(0).size() * 2;
		visibleOnEdges += (forest.size() - 2) * 2;

		int visibleInside = 0;
		for (int j = 1; j < forest.size() - 1; j++) {
			for (int i = 1; i < forest.get(j).size() - 1; i++) {

				boolean isVisible = true;
				// Visible from top ? same x, y -1
				for (int y = j - 1; y > -1; y--) {
					if (forest.get(y).get(i) >= forest.get(j).get(i)) {
						isVisible = false;
						break;
					}
				}
				if (isVisible) {
					visibleInside++;
					continue;
				}

				isVisible = true;
				// Visible from bottom ? same x, y +1
				for (int y = j + 1; y < forest.size(); y++) {
					if (forest.get(y).get(i) >= forest.get(j).get(i)) {
						isVisible = false;
						break;
					}
				}
				if (isVisible) {
					visibleInside++;
					continue;
				}

				isVisible = true;
				// Visible from right ? x+1, same y
				for (int x = i + 1; x < forest.get(0).size(); x++) {
					if (forest.get(j).get(x) >= forest.get(j).get(i)) {
						isVisible = false;
						break;
					}
				}
				if (isVisible) {
					visibleInside++;
					continue;
				}

				isVisible = true;
				// Visible from left ? x-1, same y
				for (int x = i - 1; x > -1; x--) {
					if (forest.get(j).get(x) >= forest.get(j).get(i)) {
						isVisible = false;
						break;
					}
				}
				if (isVisible) {
					visibleInside++;
				}

			}

		}
		System.out.println("partOne: " + (visibleOnEdges + visibleInside));

	}

	public static void partTwo(List<List<Integer>> forest) {
		int maxScenicScore = 0;
		for (int j = 0; j < forest.size(); j++) {
			for (int i = 0; i < forest.get(j).size(); i++) {
				int currentScenicScore = 1;

				// at the top
				int topScore = 0;
				for (int y = j - 1; y > -1; y--) {
					topScore++;
					if (forest.get(y).get(i) >= forest.get(j).get(i)) {
						break;
					}
				}

				// on the right
				int rightScore = 0;
				for (int x = i + 1; x < forest.get(0).size(); x++) {
					rightScore++;
					if (forest.get(j).get(x) >= forest.get(j).get(i)) {
						break;
					}
				}

				// at the bottom
				int bottomScore = 0;
				for (int y = j + 1; y < forest.size(); y++) {
					bottomScore++;
					if (forest.get(y).get(i) >= forest.get(j).get(i)) {
						break;
					}
				}

				// on the left
				int leftScore = 0;
				for (int x = i - 1; x > -1; x--) {
					leftScore++;
					if (forest.get(j).get(x) >= forest.get(j).get(i)) {
						break;
					}
				}

				// Computing the current Scenic score
				if (topScore > 0) {
					currentScenicScore *= topScore;
				}
				if (rightScore > 0) {
					currentScenicScore *= rightScore;
				}
				if (bottomScore > 0) {
					currentScenicScore *= bottomScore;
				}
				if (leftScore > 0) {
					currentScenicScore *= leftScore;
				}

				// changing the maxScenicScore if necessary
				if (currentScenicScore >= maxScenicScore) {
					maxScenicScore = currentScenicScore;
				}

			}
		}
		System.out.println("maxScenicScore = " + maxScenicScore);
	}

}
