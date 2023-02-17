package days;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Day7 {

	public static void main(String[] args) {
		List<String> input;
		try {
			input = Files.readAllLines(Path.of("src/days/resources/day7.test"));
		} catch (IOException e) {
			throw new RuntimeException(e);
		}

		Map<String, Integer> directories = new HashMap<>();
		input.remove(0);
		StringBuilder currentDirectory = new StringBuilder("/");

		for (String command : input) {
			if (command.contentEquals("$ ls")) {
				continue;
			}
			if (!command.startsWith("$ ")) {
				// We are listing files or directories.
				if (!command.startsWith("dir ")) {
					// We act only on files.
					Integer filesize = Integer.parseInt(command.split(" ")[0]);
					if (directories.containsKey(currentDirectory.toString())) {
						directories.replace(currentDirectory.toString(),
								directories.get(currentDirectory.toString()) + filesize);
					} else {
						directories.put(currentDirectory.toString(), filesize);
					}
				}
			} else {
				// Here we deal with the directory changes.
				if (!command.endsWith("..")) {
					// cd forward
					currentDirectory.append("/");
					currentDirectory.append(command.split(" ")[2]);
					directories.put(currentDirectory.toString(), 0);

				} else {
					cdBackward(directories, currentDirectory);
				}

			}
		}

		while (!currentDirectory.toString().contentEquals("/")) {
			cdBackward(directories, currentDirectory);
		}

		System.out.println(directories);
		System.out.println("part1 answer is: " + partOne(directories));
		System.out.println("part2 answer is: " + partTwo(directories));

	}

	private static void cdBackward(Map<String, Integer> directories, StringBuilder currentDirectory) {
		String upperDirectory = currentDirectory.toString();
		currentDirectory.delete(currentDirectory.lastIndexOf("/"), currentDirectory.length());
		directories.replace(currentDirectory.toString(),
				directories.get(currentDirectory.toString()) + directories.get(upperDirectory));
	}

	public static Integer partOne(Map<String, Integer> d) {
		return d.values().stream().filter(integer -> integer <= 100000).mapToInt(Integer::intValue).sum();

	}

	public static Integer partTwo(Map<String, Integer> d) {
		int freeSpace = 70000000 - d.get("/");
		int toFreeUp = 30000000 - freeSpace;
		return d.values().stream().filter(integer -> integer >= toFreeUp).reduce(Integer::min).get();
	}
}
