def helper(index, list) -> list[int]:
    if index == 1:
        list.append([1])
        return [1]

    prev = helper(index - 1, list)
    next = []
    next.append(1)

    for i in range(1, len(prev)):
        next.append(prev[i - 1] + prev[i])

    next.append(1)
    list.append(next)
    return next

def pascal(index) -> list[list]:
    res = []
    helper(index, res)
    return res


def main():
    print(pascal(10))

main()