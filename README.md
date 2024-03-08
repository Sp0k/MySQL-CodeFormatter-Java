# MySQL Code Formatter using Java
A small program to format csv files into MySQL code files to insert the data. I created this project because my
sister and I could not get the <em>LOAD INTO</em> statement to work on our computers and did not want to write
the hundreds of lines of data by ourselves.

I still think using <em>LOAD INTO</em> is a better option, but this can solve the issue if the statement is also
blocked on your machine.

## Prerequisites
- Java JDK 11+
- <a href="https://github.com/Sp0k/GSFileManager/tree/main" target="_blank">GSFileManager</a> or GSLib

## Installation
1. Create a folder for the project.
2. Clone GSFileManager or GSLib into the project folder
3. Clone this file into the project folder

## Usage
To use this program, you just need to open the project in any IDE or terminal that can run Java. You will then
need to open the file, and modify the constans "filepathCsv" and "filepathSql" to the path of where you will store
the csv files and where you want the sql files to be stored after they are created and written.

Once this is done, you just need to run the program and follow the directive:
1. Enter the database name
2. Enter the number of files to format
3. Enter the name of the csv file
4. Enter the name of the table the data will be inserted into
5. Redo step 3 and 4 until you entered every file you wanted to.

** Keep in mind that currently, the program does not have any error handling, so make sure your file name are correct,
I will be adding error handling soon **

## License
Distributed under The Unilicense. See <b>'LICENSE'</b> for more information.

## Contact
Email - contact@gabsavard.com<br>
Website - <a href="https://gabsavard.com" target="_blank">gabsavard.com</a>
