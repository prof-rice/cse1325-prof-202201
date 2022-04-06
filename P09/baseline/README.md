# "C++ in Color" Assignment Support Files

## example.cpp 
 
This code should print "UTA Blue" to your console in actual UTA Blue color (see [this UTA website](https://usteamcolors.com/university-of-texas-at-arlington-colors/)). The string being printed breaks down like this:

The control sequence for UTA Blue:

* \033 is the Esc key on your keyboard, which initiates an ANSI control sequence.
* [ (when following Esc) represents a Control Sequence Introducer. This signals that the terminal program should interpret upcoming characters as a control sequence to modify terminal behavior.
* 38; asks the terminal to Set Foreground Color.
* 2; states that red, green, and blue integers will follow, each between 0 and 255, inclusive
* 0;100;177 sets red to 0 (none), green to 100, and blue to 177
* m signals the end of the control sequence.

The CSI is followed by the text to be printed, in this case, "UTA Blue".

The control sequence for reset / normal colors:

* \033 and [ (as above) are the Control Sequence Introducer
* 0 means reset / normal
* m signals the end of the control sequence.

If your console prints gibberish, or prints "UTA Blue" in normal colors, you need a console that supports ANSI control codes. 

All major Linux and Mac terminals fully support ANSI control codes.

Recent versions of the Windows console should support these (see [How-to: Use ANSI colors in the terminal](https://ss64.com/nt/syntax-ansi.html)), although you should already be using a bash shell for this class (for example, [How to install and use bash on Windows](https://www.howtogeek.com/249966/how-to-install-and-use-the-linux-bash-shell-on-windows-10/) or mintty that is included with git).

## test.cpp

This simple program will import your color.h, instance a red color with it, colorize the word "Red" in red, and print it to the console.

## Makefile

This Makefile supports the following commands:

* ``make`` will create example, test, and color as described below.
* ``make example`` will make ``./example`` from example.cpp.
* ``make test`` will make ``./test`` from test.cpp.
* ``make color`` will make ``./color`` from your main.cpp, color.h, and color.cpp.
* ``make color.gch`` will test-compile color.h into color.gch. Beware that once compiled into color.gch, make will NOT recompile it if you modify color.h. Use ``make clean`` after a successful test-compile.
* ``make clean`` will delete all intermediate files and executables.



