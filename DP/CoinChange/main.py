def coin_exchange(target : int, coins : list[int]) -> int:
    ways = [0 for _ in range(target + 1)]
    ways[0] = 1

    for coin in coins:
        for i in range(coin, len(ways)):
            ways[i] += ways[i - coin]
    
    return ways[target]

def main():
    print(coin_exchange(3, [1,2]))

main()