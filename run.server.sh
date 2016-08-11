mvn exec:java -Dexec.mainClass=koreatech.link.StreamRtp -Djna.library.path=/Applications/VLC.app/Contents/MacOS/lib -DfailIfNoTests=false -Dexec.classpathScope=test -Dexec.args="$1 $2 $3"
