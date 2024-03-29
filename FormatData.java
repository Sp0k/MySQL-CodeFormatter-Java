/*
 *    █████████   █████████
 *   ███░░░░░███ ███░░░░░███        Format Data
 *  ███     ░░░ ░███    ░░░
 * ░███         ░░█████████         @author Gabriel Savard
 * ░███    █████ ░░░░░░░░███        @version 1.3
 * ░░███  ░░███  ███    ░███        since 2024-03-08
 *  ░░█████████ ░░█████████
 *   ░░░░░░░░░   ░░░░░░░░░
 *
 *  @description: The program will go through any amount of csv files
 *  and output one sql file per csv file. It can tell apart a String
 *  from a numerical value.
 *
 *  @requisites: GSFileManager
 */

import java.util.Scanner;
import GSFileManager.FileManager;

public class FormatData {
  /* FILE PATHS TO MODIFY BEFORE USE */
  // Input
  final static String filepathCsv = "PATH/TO/YOUR/CSV/FILES/"; // The '/' is required to work properly

  // Output
  final static String filepathSql = "PATH/TO/YOUR/SQL/FILES/";

  /*
   * Checks if the value requires to be between apostrophees
   *
   * @param str A String to be checked
   * 
   * @return boolean A boolean value
   */
  static boolean isNum(String str) {
    // method variables
    char[] nums = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', '.', '-' };
    boolean isNum;
    int dashCounter = 0;

    // Processing
    if (!str.equalsIgnoreCase("NULL")) {
      for (int i = 0; i < str.length(); i++) {
        isNum = false;

        // Check if the String contains numbers
        for (int j = 0; j < nums.length; j++) {
          if (str.charAt(i) == nums[j]) {
            if (str.charAt(i) == '-')
              dashCounter++;
            isNum = true;
            break;
          }
        }

        // Current character is a letter, value is a String
        if (!isNum || dashCounter >= 2)
          return false;
      }
    }

    return true;
  }

  /*
   * Main method
   */
  public static void main(String[] args) {
    // File manager
    FileManager myManager = new FileManager();

    // Scanner
    Scanner in = new Scanner(System.in);

    // Main Variables
    System.out.print("Enter the name of the Database to use: ");
    String database = in.nextLine();
    System.out.print("Enter the number of files to format: ");
    int n = in.nextInt();
    in.nextLine();
    System.out.println();

    // Processing
    // Formatting Data
    for (int i = 0; i < n; i++) {
      // Loop variables
      System.out.print(String.format("Enter the name of the file #%d: ", (i + 1)));
      String filename = in.nextLine();
      System.out.print("Enter the name of the table: ");
      String table_name = in.nextLine();
      String filepath_in = String.format("%s%s.csv", filepathCsv, filename);
      String filepath_out = String.format("%s%s.sql", filepathSql, filename);
      String file = myManager.readFile(filepath_in);
      String fileData[] = file.split("\n");
      StringBuilder fileContent = new StringBuilder(
          String.format("USE %s;\nINSERT INTO %s;\nVALUES\n", database, table_name));

      // Constructing the lines to be added
      for (int j = 1; j < fileData.length; j++) {
        // Separate the values and start the line
        String tmp[] = fileData[j].split(",");
        String out = "(";

        for (int k = 0; k < tmp.length; k++) {
          // Check if the value is a String, then adds them properly
          if (!isNum(tmp[k])) {
            // Format the Strings to be properly used
            if (tmp[k].contains("\'"))
              tmp[k] = tmp[k].replace("\'", "\'\'");

            out += String.format("\'%s\'", tmp[k]);
          } else
            out += tmp[k];

          // Verify if it's the end of the line or not
          if (k < tmp.length - 1)
            out += ',';
          else
            out += ')';
        }

        // Verify if it's the end of the file or not
        if (j < fileData.length - 1)
          out += ",\n";
        else
          out += ";\n";

        fileContent.append(out);
      }

      // Creating and writing file
      myManager.createFile(filepath_out);
      myManager.writeToFile(filepath_out, fileContent.toString());

      System.out.println();
    }

    // Finish program
    in.close();
    System.out.println("Done!");
  }
}
