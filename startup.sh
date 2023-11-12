#! /usr/bin/bash
cd BackEnd
./gradlew bootRun &
cd ..
cd FrontEndStuff
python3 client_server.py &
