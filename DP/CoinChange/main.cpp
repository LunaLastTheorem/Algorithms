#include <cstdio>
#include <iterator>
#include <iostream>

int coin_exchange(int target, int coins[], int n){
    int ways[target + 1];

    for(int i = 0; i < target+1; i++){
        ways[i] = 0;
    }

    ways[0] = 1;
    for(int i = 0; i < n; i++){
        for(int j = coins[i]; j <= target; j++){
            ways[j] += ways[j - coins[i]];
        }
    }
    return ways[target];
}

int main(){
    int coins[] = {1, 2, 5};
    int n = sizeof(coins) / sizeof(coins[0]);
    printf("%d\n", coin_exchange(5, coins, n));
    return 0;    
}