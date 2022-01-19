Code Shown on the Slides
========================

This directory contains code used during the class lecture or contained in the slides.

---

To build all of the Java programs in this directory and its subdirectories, just type ``ant``. This uses the rules in the build.xml file in the current directory to issue all of the needed ``javac`` and related commands. It's *automagic*.

Type ``java A`` to run the main method in class A. Not all classes have main methods!

To delete all of the class files created by the ``ant`` command, type ``ant clean``.  Keep those ants busy!

---

For this lecture only, a Makefile is also provided. Remember those from your C days? 

Type ``make`` to build all of these programs, or ``make [program]`` to just build [program].java. Type ``make clean`` to remove all of the .class files.

