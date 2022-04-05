#include <iostream>
#include <algorithm>

int main(int argc, char* argv[]) {
    for(int i=1; i<argc; ++i) {
        std::string s{argv[i]};
        std::reverse(s.begin(), s.end());
        std::cout << s << std::endl;
    }
}
