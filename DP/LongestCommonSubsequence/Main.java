class Main {
    public static void main(String[] args) {
        String s1 = "ayzx";
        String s2 = "axyt";
        System.out.println(lcs(s1, s2));
    }

    public static int lcs(String s1, String s2){
        int[][] dp = new int[s1.length() + 1][s2.length() + 1];

        for(int i = 1; i <= s1.length(); i++){
            for(int j = 1; j <= s2.length(); j++){
                if(s1.charAt(i - 1) == s2.charAt(j - 1)){
                    dp[i][j] = 1 + dp[i - 1][j - 1];
                }else{
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }

        for(int i = 0; i <= s1.length(); i++){
            for(int j = 0; j < s2.length(); j++){
                System.out.print(dp[i][j]);
            }
            System.out.println();
        }
        return dp[s1.length()][s2.length()];
    }
}