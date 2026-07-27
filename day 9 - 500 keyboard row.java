class Solution {
    public String[] findWords(String[] words) {
        String[] rows = {"qwertyuiop", "asdfghjkl", "zxcvbnm"};
        int[] charRow = new int[26];

        for (int r = 0; r < 3; r++) {
            for (char c : rows[r].toCharArray()) {
                charRow[c - 'a'] = r;
            }
        }

        List<String> result = new ArrayList<>();

        for (String word : words) {
            String lower = word.toLowerCase();
            int row = charRow[lower.charAt(0) - 'a'];
            boolean sameRow = true;

            for (char c : lower.toCharArray()) {
                if (charRow[c - 'a'] != row) {
                    sameRow = false;
                    break;
                }
            }

            if (sameRow) {
                result.add(word);
            }
        }

        return result.toArray(new String[0]);
    }
}
