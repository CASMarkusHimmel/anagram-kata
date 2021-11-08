package de.cas.katas;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class AnagramFinder {
	
	private static final String INPUT_FILE = "C:\\Users\\markus.himmel\\Downloads\\wordlist.txt";

	public static void main(String[] args) throws IOException {
		List<String> words = Files.readAllLines(Path.of(INPUT_FILE));
		var anagramGroups = groupAnagrams(words);
		for (List<String> group : anagramGroups) {
			System.out.println(String.join(" ", group));
		}
	}
	
	private static Collection<List<String>> groupAnagrams(Collection<String> words) {
		// We sort the characters of each string to obtain a normal form such that two strings
		// are anagrams of each other if and only if they have the same normal form. Hence, grouping
		// by the normal form groups anagrams together.
		return words.stream() //
				.collect(Collectors.groupingBy(AnagramFinder::normalize)) //
				.values();
	}
	
	private static CharArray normalize(String s) {
		char[] chars = s.toCharArray();

		for (int i = 0; i < chars.length; i++) {
			chars[i] = Character.toLowerCase(chars[i]);
		}

		Arrays.sort(chars);

		return new CharArray(chars);
	}
	
	private static class CharArray {
		private final char[] value;
		
		public CharArray(char[] value) {
			this.value = value;
		}

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + Arrays.hashCode(value);
			return result;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			CharArray other = (CharArray) obj;
			return Arrays.equals(value, other.value);
		}
	}
}
