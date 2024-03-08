import java.util.Scanner;
import GSLib.Utilities.FileManager;

public class FormatData {
  static boolean isNum(String str) {
    // method variables
    char[] nums = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', '.', '-' };
    boolean isNum;

    for (int i = 0; i < str.length(); i++) {
      isNum = false;

      for (int j = 0; j < nums.length; j++) {
        if (str.charAt(i) == nums[j]) {
          isNum = true;
          break;
        }
      }

      if (!isNum)
        return false;
    }

    return true;
  }

  public static void main(String[] args) {
    // File manager
    FileManager myManager = new FileManager();

    // Scanner
    Scanner in = new Scanner(System.in);

    // Main Variables
    System.out.print("Enter the number of files to format: ");
    int n = in.nextInt();
    in.nextLine();

    // Processing
    // Formatting Data
    for (int i = 0; i < n; i++) {
      // Loop variables
      System.out.print("Enter the name of the file: ");
      String filename = in.nextLine();
      System.out.print("Enter the name of the table: ");
      String table_name = in.nextLine();
      String filepath_in = String.format("data_juliette/csv/%s.csv", filename);
      String filepath_out = String.format("data_juliette/txt/%s.txt", filename);
      String file = myManager.readFile(filepath_in);
      String fileData[] = file.split("\n");
      StringBuilder fileContent = new StringBuilder(String.format("INSERT INTO %s;\nVALUES\n", table_name));

      for (int j = 1; j < fileData.length; j++) {
        String tmp[] = fileData[j].split(",");
        String out = "(";
        for (int k = 0; k < tmp.length; k++) {
          if (!isNum(tmp[k]))
            out += String.format("\'%s\'", tmp[k]);
          else
            out += tmp[k];

          if (k < tmp.length - 1)
            out += ',';
          else
            out += ')';
        }

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
    System.out.println("Done\n");
  }
}
