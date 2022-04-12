#include "color.h"
#include <iostream>

int main() {
    Color uta_blue{0,100,177};
    Color uta_orange{245,128,38};
    Color white{225,225,225};
    std::cout << uta_blue.colorize("UTA Blue") << ' '
              << uta_orange.colorize("UTA Orange") << ' '
              << white.colorize("UTA White") << "\n\n";

    int red, green, blue;
    std::cout << "Enter red, green, and blue ints:   "; 
    std::cin >> red >> green >> blue;
    Color color{red, green, blue};
    std::cout << color.colorize(color.to_string()) << std::endl;
}
