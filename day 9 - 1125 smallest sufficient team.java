class Solution {
    public int[] smallestSufficientTeam(String[] req_skills, List<List<String>> people) {
        int n = req_skills.length;
        Map<String, Integer> skillIndex = new HashMap<>();
        for (int i = 0; i < n; i++) {
            skillIndex.put(req_skills[i], i);
        }

        int m = people.size();
        int[] peopleMask = new int[m];
        for (int i = 0; i < m; i++) {
            int mask = 0;
            for (String skill : people.get(i)) {
                mask |= (1 << skillIndex.get(skill));
            }
            peopleMask[i] = mask;
        }

        int full = 1 << n;
        // dp[mask] = list of people indices forming smallest team covering "mask"
        List<Integer>[] dp = new List[full];
        dp[0] = new ArrayList<>();

        for (int i = 0; i < m; i++) {
            int pMask = peopleMask[i];
            // iterate over existing masks; copy to avoid concurrent modification issues
            for (int mask = 0; mask < full; mask++) {
                if (dp[mask] == null) continue;

                int newMask = mask | pMask;
                if (newMask == mask) continue; // adding this person doesn't help

                List<Integer> candidate = new ArrayList<>(dp[mask]);
                candidate.add(i);

                if (dp[newMask] == null || dp[newMask].size() > candidate.size()) {
                    dp[newMask] = candidate;
                }
            }
        }

        int[] result = new int[dp[full - 1].size()];
        for (int i = 0; i < result.length; i++) {
            result[i] = dp[full - 1].get(i);
        }
        return result;
    }
}
