#include <iostream>
#include <fstream>

int main(int argc, char* argv[]) {
    std::string file{argv[1]};
    std::ofstream ofs {file};
    if (!ofs) throw std::runtime_error{"can’t open output file " + file};

    ofs << "Writing this to " << file << std::endl;
}
