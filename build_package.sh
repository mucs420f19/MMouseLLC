#!/bin/bash

mvn clean install
mvn package
java -cp target/mmouse-uml-0.0.2-SNAPSHOT-jar-with-dependencies.jar utility.Main
