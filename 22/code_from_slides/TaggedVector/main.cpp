#include "tagged_vector.h"

int main() {
    TaggedVector<std::string> v;
    v.push_back("The answer is ");    
    v.push_back("42");
    for(int i=0; i<v.size(); ++i)
        std::cout << v[i] << std::endl;
}
