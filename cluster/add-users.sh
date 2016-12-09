#!/bin/bash

./bin/add-user.sh -a -u admin -p password1! -g admin,analyst,kiemgmt,rest-all,kie-server
./bin/add-user.sh -a -u krisv -p password1! -g admin,analyst,rest-all,kie-server
./bin/add-user.sh -a -u john -p password1! -g analyst,Accounting,PM
./bin/add-user.sh -a -u mary -p password1! -g analyst,HR
./bin/add-user.sh -a -u sales-rep -p password1! -g analyst,sales
./bin/add-user.sh -a -u jack -p password1! -g analyst,IT
./bin/add-user.sh -a -u katy -p password1! -g analyst,HR
./bin/add-user.sh -a -u salaboy -p password1! -g admin,analyst,IT,HR,Accounting,rest-all
./bin/add-user.sh -a -u kieserver -p password1! -g kie-server

