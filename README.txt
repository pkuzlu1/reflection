CS542 Design Patterns
Fall 2013
PROJECT 3 README FILE

Due Date: Sunday, 10 November 2013, 11.59 PM EST
Submission Date: Sunday, 10 November 2013
Grace Period Used This Project: 18 Days
Grace Period Remaining: 0 Days
Author(s): Bedri Sendir
e-mail(s): bsendir1@binghamton.edu

PURPOSE:
----------
Match assignment requirements with features of design pattern(s) and apply the correct set of patterns.

Pattern(s) Used:

Strategy Pattern

Use Debug in the following way:
        If Debug value is 0: prints number of unique objects
        If Debug value is 1: prints out serialized output
        If Debug value is 2: everytime object's method invoked, prints out method name in detail
        If Debug value is 3: prints out constructor calls
        If Debug value is 4: no output

PARTS THAT ARE NOT COMPLETE:
------------------------------
None

BUGS:
-------
None

FILES:
-----------
-->ADD

SAMPLE OUTPUT:
---------------
Output for Debug Value 0;
Input File MyAllTypes2.txt

     [java] Unique Objects
     [java] Unique MyAllTypesFirst=4
     [java] Unique Objects
     [java] Unique MyAllTypesSecond=9


TO COMPILE:
------------
Just extract the files and then compile the designConf with ANT.
   1. cd to the folder where you downloaded Bedri_Sendir.tar.gz
   2. use these commands:
        2a) gunzip Bedri_Sendir.tar.gz
        2b) tar -xvf Bedri_Sendir.tar.gz
   3. cd to Bedri_Sendir.tar.gz
   4. cd to reflection
   5. ant compile

TO RUN:
--------
  Please run as: ant run OR java -jar build/jar/reflection.jar <DEBUG_VALUE> <InputFile> <OutputFile>
  For example: ant run OR java -jar build/jar/reflection.jar 0 MyAllTypes.txt MyAllTypesCopy.txt

EXTRA CREDIT:
-------------
N/A

BIBLIOGRAPHY:
--------------
This serves as evidence that we are in no way intending Academic Dishonesty.
Bedri SENDIR

*http://www.cs.binghamton.edu/~mgovinda/courses/cs542/p2.html

*Head First Design Patterns
Authors: Eric Freeman & Elisabeth Freeman with Kathy Sierra & Bert Bates
Publisher: O’Reilly

ACKNOWLEDGEMENT:
-----------------
E. Dede
M. Govindaraju
