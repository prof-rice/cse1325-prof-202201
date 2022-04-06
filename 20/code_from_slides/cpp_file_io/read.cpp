#include <iostream>
#include <fstream>

int main(int argc, char* argv[]) {
    std::string file{argv[1]};
    std::ifstream ist{file};	
    if (!ist) throw std::runtime_error{"can’t open input file " + file};

    std::string s;
    while (std::getline(ist, s)) std::cout << s << std::endl;
}
