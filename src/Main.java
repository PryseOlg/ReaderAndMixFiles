import java.io.*;
import java.nio.file.Files;

public class Main {
    public static void main(String[] args) {
        try {
            byte[] byteFile1 = readFileBytes(args[0]);
            byte[] byteFile2 = readFileBytes(args[1]);
            byte[] byteResult = joinBytes(byteFile1, byteFile2);
            writeResultFile(args[2], byteResult);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }

    }

    private static void writeResultFile(String fileName, byte[] bytes) throws IOException {
        File file = new File(fileName);
        Files.write(file.toPath(), bytes);
    }

    private static byte[] joinBytes(byte[] bytesFile1, byte[] bytesFile2) {
        int iteration = 1;
        ByteArrayOutputStream result = new ByteArrayOutputStream();
        int maxLengthArray = Math.max(bytesFile1.length, bytesFile2.length);
        int arrayIndex = 0;

        for (int i = 0; i < maxLengthArray * 2; i++){
            boolean isFirstArray = iteration % 2 != 0;
            result.write(isFirstArray ? bytesFile1[arrayIndex % bytesFile1.length] : bytesFile2[arrayIndex % bytesFile2.length]);
            iteration++;
            if (!isFirstArray){
                arrayIndex++;
            }
        }

        return result.toByteArray();
    }

    private static byte[] readFileBytes(String fileName) throws IOException {
        return new FileInputStream(fileName).readAllBytes();
    }
}
