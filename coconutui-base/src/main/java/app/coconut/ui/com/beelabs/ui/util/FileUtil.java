package app.coconut.ui.com.beelabs.ui.util;

import java.io.File;

public class FileUtil {

    public static int removeDirectory(final File folder) {

        if (folder.isDirectory() == true) {
            File[] folderContents = folder.listFiles();
            int deletedFiles = 0;

            if (folderContents.length == 0) {
                if (folder.delete()) {
                    deletedFiles++;
                    return deletedFiles;
                }
            } else if (folderContents.length > 0) {

                do {

                    File lastFolder = folder;
                    File[] lastFolderContents = lastFolder.listFiles();

                    //This while loop finds the deepest path that does not contain any other folders
                    do {

                        for (File file : lastFolderContents) {

                            if (file.isDirectory()) {
                                lastFolder = file;
                                lastFolderContents = file.listFiles();
                                break;
                            } else {

                                if (file.delete()) {
                                    deletedFiles++;
                                } else {
                                    break;
                                }

                            }//End if(file.isDirectory())

                        }//End for(File file : folderContents)

                    } while (lastFolder.delete() == false);

                    deletedFiles++;
                    if (folder.exists() == false) {
                        return deletedFiles;
                    }

                } while (folder.exists());
            }
        } else {
            return -1;
        }

        return 0;
    }
}
