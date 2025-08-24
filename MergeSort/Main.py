
def mergesort(arr):
    if len(arr) <= 1:
        return arr
    
    left = mergesort(arr[0:int(len(arr) / 2)])
    right = mergesort(arr[int(len(arr) / 2): len(arr)])

    res = []
    l_ptr = 0
    r_ptr = 0
    while l_ptr < len(left) and r_ptr < len(right):
        if left[l_ptr] < right[r_ptr]:
            res.append(left[l_ptr])
            l_ptr += 1
        else :
            res.append(right[r_ptr])
            r_ptr += 1
    
    while l_ptr < len(left):
        res.append(left[l_ptr])
        l_ptr += 1
    
    while r_ptr < len(right):
        res.append(right[r_ptr])
        r_ptr += 1

    return res

def main():
    arr = [1,6,3,2,5,4,7,8,9,0]
    print(mergesort(arr))

main()