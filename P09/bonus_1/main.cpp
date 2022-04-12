#include "color.h"
#include <iostream>

int main() {
    Color uta_blue{0,100,177};
    Color uta_orange{245,128,38};
    Color uta_white{225,225,225};
    Color reset;
    std::cout << uta_blue   << " UTA Blue"
              << uta_orange << " UTA Orange"
              << uta_white  << " UTA White" 
              << reset << "\n\n";

    int red, green, blue;
    std::cout << "Enter red, green, and blue ints:   "; 
    std::cin >> red >> green >> blue;
    Color color{red, green, blue};
    std::cout << color << color.to_string() << reset << std::endl;
}

