#include <vector>
#include <iostream>
#include <string>

std::vector<int> pascal(int row, std::vector<std::vector<int>> &res);

std::vector<std::vector<int>> generate(int numRows)
{
    std::vector<std::vector<int>> res;
    pascal(numRows, res);
    return res;
}

std::vector<int> pascal(int row, std::vector<std::vector<int>> &res)
{
    if (row == 1)
    {
        std::vector<int> base;
        base.push_back(1);
        res.push_back(base);
        return base;
    }

    std::vector<int> prev = pascal(row - 1, res);
    std::vector<int> next;
    int left = 0;
    int right = 1;
    next.push_back(1);
    while (right < prev.size())
    {
        next.push_back(prev[left] + prev[right]);
        left++;
        right++;
    }
    next.push_back(1);
    res.push_back(next);
    return next;
}


int main()
{
    std::vector<std::vector<int>> triangle = generate(5);
    for(int i = 0; i < triangle.size(); i++){
        std::cout<< "[" << " ";
        for(int j = 0; j < triangle[i].size(); j++){
            std::cout<< triangle[i][j] << " ";
        }
        std::cout<< "]" << "\n";
    }
    return 0;
}
