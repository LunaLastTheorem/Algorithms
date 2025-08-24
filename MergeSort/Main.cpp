#include <vector>
#include <iostream>

std::vector<int> mergesort(std::vector<int> &arr)
{
    if (arr.size() <= 1)
    {
        return arr;
    }

    std::vector<int> left;
    for (int i = 0; i < arr.size() / 2; i++)
    {
        left.push_back(arr[i]);
    }
    std::vector<int> right;
    for (int i = arr.size() / 2; i < arr.size(); i++)
    {
        right.push_back(arr[i]);
    }

    left = mergesort(left);
    right = mergesort(right);

    std::vector<int> res;
    int l_ptr = 0;
    int r_ptr = 0;
    while (l_ptr < left.size() && r_ptr < right.size())
    {
        if (left[l_ptr] < right[r_ptr])
        {
            res.push_back(left[l_ptr++]);
        }
        else
        {
            res.push_back(right[r_ptr++]);
        }
    }

    while (l_ptr < left.size())
    {
        res.push_back(left[l_ptr++]);
    }

    while (r_ptr < right.size())
    {
        res.push_back(right[r_ptr++]);
    }

    return res;
}

int main()
{
    std::vector<int> arr = {5, 6, 4, 7, 8, 9, 2, 3, 1};
    std::vector<int> res = mergesort(arr);
    for (int i = 0; i < res.size(); i++)
    {
        std::cout << res[i] << " ";
    }

    return 0;
}