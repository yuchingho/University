/* args.js - displays input prompt arguments. */

if(!arguments.length) {
	print("Usage: $>jsc args.js -- arg list items");
}
for(arg in arguments) {
	print(arg + ' ' + arguments[arg]);
}