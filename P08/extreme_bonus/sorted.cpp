#include <iostream>
#include <algorithm>
#include <vector>

int main() {
    std::vector<std::string> v;
    std::string s;
    while(std::cin) {
        std::getline(std::cin, s);
        v.push_back(s);
    }
    std::sort(v.begin(), v.end());
    for(auto s : v) std::cout << s << std::endl;
}

