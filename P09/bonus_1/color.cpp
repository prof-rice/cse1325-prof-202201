#include "color.h"
Color::Color() : _red{0}, _green{0}, _blue{0}, _reset{true} { }
Color::Color(int red, int green, int blue) : _red{red}, _green{green}, _blue{blue}, _reset{false} { }

std::string Color::to_string() {
    if(_reset) return "(reset)";
    return "(" + std::to_string(_red)   + ","
               + std::to_string(_green) + ","
               + std::to_string(_blue)  + ")";
}

std::string Color::colorize(std::string text) {
    if(_reset) return text;
    return "\033[38;2;" 
         + std::to_string(_red)   + ';' 
         + std::to_string(_green) + ';'
         + std::to_string(_blue)  + 'm'
         + text
         + "\033[0m";
}

std::ostream& operator<<(std::ostream& ost, const Color& color) {
    if (color._reset) {
        //  ESC[ 0 m Reset to defaults
        ost << "\033[0m";
    } else {
        //  ESC[ 38;2;⟨r⟩;⟨g⟩;⟨b⟩ m Select RGB foreground color
        //  ESC[ 48;2;⟨r⟩;⟨g⟩;⟨b⟩ m Select RGB background color
        ost << "\033[38;2;" 
            << color._red << ';' 
            << color._green << ';'
            << color._blue << 'm';
    }
    return ost;
}

