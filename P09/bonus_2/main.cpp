#include "color.h"
#include <iostream>
#include <fstream>
#include <vector>

int main(int argc, char* argv[]) {
    if(argc < 2) {
        std::cerr << "usage: " << argv[0] << " file.txt" << std::endl;
        return -1;
    }

    std::ifstream ifs{std::string(argv[1])};
    if (!ifs) {
        std::cerr << "Invalid filename: " << argv[1];
        return -2;
    }
        
    std::vector<int> color_ints {64, 128, 192, 255};
    std::vector<Color> colors;
    for(int red : color_ints)
        for(int green : color_ints)
            for(int blue : color_ints)
                colors.push_back(Color{red, green, blue});
    Color reset;
    
    std::string line;
    // while(ifs >> line)
    while(std::getline(ifs, line))
        std::cout << colors[rand() % colors.size()] << line << std::endl;
        
    std::cout << reset;
 }

