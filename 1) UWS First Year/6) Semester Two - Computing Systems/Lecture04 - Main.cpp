// Annotated C++ program to illustrate compilation
// Daniel Livingstone
// University of the West of Scotland

// The double "//" indicates the start of a comment
// Comments are discarded during lexical analysis

#include <iostream>
#include <cstring>
// lines beginning with "#" are directions for the preproccessor
// These may include commands to edit the source code before it is
// sent to the lexer.
// The following #define tells the preproccessor to replace any
// instances of "TOTAL_COUNT" with "10"
#define TOTAL_COUNT 10

using namespace std;

void hexOut(float f); // Forward declaration of a function
// The hecOut function is actually at the end of the program.

int main() {  // C++ expects a function named "main" as the program start

	// In the following two lines, three variables are declared
	unsigned int i;	// an unsigned binary integer
	float a = 0.0f, b = 1.0f; // two floating point numbers

	for (i = 0; i < TOTAL_COUNT ; i++) {	// This loop will repeat 10 times
		a = a + 0.1f;			// increment a by 0.1 each time
		cout << "Step " << i << ": a= " << a << endl;
	}

	cout << "After loop, a = " << a << ", b = " << b << endl;

	if ( a == b )			// is a equal to b?
		cout << endl << "a is equal to b" << endl;
	else
		cout << endl << "a is NOT equal to b" << endl;

	// Print the hex of the two float representations
	hexOut(a);
	hexOut(b);


	cout << "Press return to continue..." << endl;
	getchar(); // This line waits for the keyboard to be pressed
	                // Just here to stop the console window closing before
	                // you have time to read the program output!
}

// This is an example of a C/C++ procedure, is called from the main program
// and prints out the hexadecimal of a floating point number
void hexOut(float f) {
	unsigned u;
	memcpy(&u, &f, sizeof f);
	cout << hex << u << endl;
}
