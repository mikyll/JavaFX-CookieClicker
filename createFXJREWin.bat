jlink --module-path "C:\Program Files\Java\javafx-jmods-11.0.2";bin -J-Djlink.debug=true --add-modules=cookie --add-modules=jdk.crypto.ec --output fxjreWin --compress=2 --strip-debug --no-header-files --no-man-pages

:: --add-modules jdk.crypto.ec per errore SSL handshake