set -e -o pipefail

mvn -q exec:java -Dexec.mainClass="salsac.SalsaCompiler" -Dexec.args="$*"
