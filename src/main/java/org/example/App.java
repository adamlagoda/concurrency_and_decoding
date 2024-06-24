package org.example;

/**
 * Given an encoded string, return its decoded string.
 * The encoding rule is: k[encoded_string], where the encoded_string inside the square brackets is being repeated exactly k times. Note that k is guaranteed to be a positive integer.
 * You may assume that the input string is always valid; there are no extra white spaces, square brackets are well-formed, etc. Furthermore, you may assume that the original data does not contain any digits and that digits are only for those repeat numbers, k. For example, there will not be input like 3a or 2[4].
 * The test cases are generated so that the length of the output will never exceed 105.
 *
 * Example 1:Input: s = "3[a]2[bc]"
 * Output: "aaabcbc"
 *
 * Example 2:Input: s = "3[a2[c]]"
 * Output: "accaccacc"
 *
 * Example 3:Input: s = "2[abc]3[cd]ef"
 * Output: "abcabccdcdcdef"
 *
 *
 * Constraints:
 * 1 <= s.length <= 30
 * s consists of lowercase English letters, digits, and square brackets '[]'.
 * s is guaranteed to be a valid input.
 * All the integers in s are in the range [1, 300].
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        System.out.println( "Hello World!" );
    }

    public static String decode(String encoded) {
        StringBuilder decoded = new StringBuilder();
        StringBuilder numberOfRepetitions = new StringBuilder();
        StringBuilder patternToRepeat = new StringBuilder();
        var openingBracketIndex = -1;
        var closingBracketIndex = -1;


        for (int i = 0; i < encoded.length(); i++) {
            if ('[' == encoded.charAt(i)) {
                openingBracketIndex = i;
            } else if (']' == encoded.charAt(i)) {
                closingBracketIndex = i;
            } else if (Character.isDigit(encoded.charAt(i))) {
                numberOfRepetitions.append(encoded.charAt(i));
            } else if (openingBracketIndex != -1) {
                patternToRepeat.append(encoded.charAt(i));
            }
            if (closingBracketIndex == i) {
                var count = Integer.parseInt(numberOfRepetitions.toString());
                var decodedPart = patternToRepeat.toString().repeat(count);
                decoded.append(decodedPart);
                patternToRepeat = new StringBuilder();
                numberOfRepetitions = new StringBuilder();
                openingBracketIndex = -1;
                closingBracketIndex = -1;
            }
            if (openingBracketIndex == -1 && Character.isLetter(encoded.charAt(i))) {
                decoded.append(encoded.charAt(i));
            }
        }
        return decoded.toString();
    }
}

