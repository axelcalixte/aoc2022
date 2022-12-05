package days;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

public class Day5 {
	public static void main(String[] args) {
		try {
			String inputFile = Files.readString(Path.of("src/days/resources/day5.in"));
			List<String> input = Arrays.asList(inputFile.split("\n\n"));

			int nbStacks = (input.get(0).split("\n")[0].length() / 4) + 1;
			List<Deque<Character>> stacks = new ArrayList<>(nbStacks);
			for (int i = 0; i < nbStacks; i++) {
				stacks.add(new ArrayDeque<>());
			}

			initializeStacks(input.get(0), stacks);
			processProceduresPart1(input.get(1), stacks); // Uncomment for Part1 answer
			// processProceduresPart2(input.get(1), stacks); // Uncomment for Part2 answer
			answer(stacks);

		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	static void processProceduresPart2(String procedures, List<Deque<Character>> stacks) {
		try (Scanner procedureScan = new Scanner(procedures)) {
			while (procedureScan.hasNext()) {
				procedureScan.next();
				int move = procedureScan.nextInt();
				procedureScan.next();
				int from = procedureScan.nextInt() - 1;
				procedureScan.next();
				int to = procedureScan.nextInt() - 1;
				ArrayDeque<Character> tempQueue = new ArrayDeque<>();
				for (int i = 0; i < move; i++) {
					tempQueue.addFirst(stacks.get(from).removeLast());
				}
				for (int i = 0; i < move; i++) {
					stacks.get(to).addLast(tempQueue.removeFirst());
				}

			}
		}
	}

	static void processProceduresPart1(String procedures, List<Deque<Character>> stacks) {
		try (Scanner procedureScan = new Scanner(procedures)) {
			while (procedureScan.hasNext()) {
				procedureScan.next();
				int move = procedureScan.nextInt();
				procedureScan.next();
				int from = procedureScan.nextInt() - 1;
				procedureScan.next();
				int to = procedureScan.nextInt() - 1;
				for (int i = 0; i < move; i++) {
					stacks.get(to).addLast(stacks.get(from).removeLast());
				}
			}
		}
	}

	static void initializeStacks(String start, List<Deque<Character>> stacks) {
		List<String> initState = Arrays.asList(start.split("\n"));
		for (int i = 0; i < initState.size() - 1; i++) {
			for (int j = 1; j < initState.get(i).length() - 1; j += 4) { // only going through the chars
				char temp = initState.get(i).charAt(j);
				if (temp != ' ') {
					stacks.get(j / 4).addFirst(temp);
				}
			}
		}
	}

	static void answer(List<Deque<Character>> stacks) {
		for (Deque<Character> stack : stacks) {
			System.out.print(stack.peekLast());
		}
	}
}
