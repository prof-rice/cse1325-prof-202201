#ifndef __COLOR_H
#define __COLOR_H

#include <iostream>

class Color {
  public:
    Color();
    Color(int red, int green, int blue);
    std::string to_string();
    std::string colorize(std::string text);
    friend std::ostream& operator<<(std::ostream& ost, const Color& color);
  private:
    int _red;
    int _green;
    int _blue;
    bool _reset;
};

#endif
