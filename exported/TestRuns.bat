
@echo off
color 0A

echo:
echo ---------------------------------- 
echo:
echo Made by Abdelrahman Atef Saad
echo 2101645
echo:
echo ---------------------------------- 
echo:

echo Testing Normal file case...
java -jar ./XMLParser.jar autosar.arxml
echo:
echo ----------------------------------
echo:

echo Testing NotValid file case...
java -jar ./XMLParser.jar test.txt
echo:
echo ----------------------------------
echo:

echo Testing Empty file case...
java -jar ./XMLParser.jar empty.arxml
echo:

pause 