echo "SALSA Build Script v0.2.1"
echo "Please make sure the current directory is in your CLASSPATH"
echo ""
echo "Compiling SALSA Compiler Code"
javac -O `find salsac/ | grep "java$"`

echo "Compiling Language Package"
echo "java source..."
javac -O `find salsa/ | grep "java$"`

echo "Compling WWC Package"
echo "java source..."
javac -O `find wwc/ | grep "java$"`

echo "Compiling GC Package"
echo "java source..."
javac -O `find gc/ | grep "java$"`

echo "Compiling Examples"
echo "salsa source..."
java -Dsilent salsac.SalsaCompiler `find examples/ | grep "salsa$"`
echo "java source..."
javac `find examples/ | grep "java$"`
echo "salsa source..."
java -Dsilent salsac.SalsaCompiler `find gctest/ | grep "salsa$"`
echo "java source..."
javac `find gctest/ | grep "java$"`
echo "salsa source..."
java -Dsilent salsac.SalsaCompiler `find tests/ | grep "salsa$"`
echo "java source..."
javac `find tests/ | grep "java$"`

echo ""
echo "Finished!"

