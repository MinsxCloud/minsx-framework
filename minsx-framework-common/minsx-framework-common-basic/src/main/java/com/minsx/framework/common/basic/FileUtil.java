package com.minsx.framework.common.basic;

import java.io.*;

public class FileUtil {


    /**
     * write inputStream to file
     *
     * @param inputStream specified file inputStream
     * @param file        specified file
     * @throws IOException IOException
     */
    public static void InputStreamToFile(InputStream inputStream, File file) throws IOException {
        OutputStream os = new FileOutputStream(file);
        int bytesRead = 0;
        byte[] buffer = new byte[8192];
        while ((bytesRead = inputStream.read(buffer, 0, 8192)) != -1) {
            os.write(buffer, 0, bytesRead);
        }
        os.close();
        inputStream.close();
    }

    /**
     * Get content of file
     *
     * @param file specified file
     * @return the content of file
     */
    public static String getFileContent(File file) {
        StringBuilder result = new StringBuilder();
        try {
            BufferedReader br = new BufferedReader(new FileReader(file));
            String s = null;
            while ((s = br.readLine()) != null) {
                result.append(System.lineSeparator()).append(s);
            }
            br.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result.toString();
    }

    /**
     * convert bytes to byteArrayInputStream
     *
     * @param content specified bytes
     * @return ByteArrayInputStream
     */
    public static ByteArrayInputStream bytesToByteArrayInputStream(byte[] content) {
        return new ByteArrayInputStream(content);
    }


    /**
     * convert InputStream to bytes
     *
     * @param in the specified inputStream
     * @return the bytes of inputStream
     * @throws IOException IOException
     */
    public static byte[] inputStreamToByteArray(InputStream in) throws IOException {
        if (in == null) {
            return new byte[0];
        }
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        int len = -1;
        while ((len = in.read(buffer)) != -1) {
            output.write(buffer, 0, len);
        }
        output.flush();
        return output.toByteArray();
    }

    public static String inputStreamToString(InputStream in) throws IOException {
        StringBuilder out = new StringBuilder();
        InputStreamReader isr = new InputStreamReader(in, "UTF-8");
        BufferedReader br = new BufferedReader(isr);
        String line = null;
        while ((line = br.readLine()) != null) {
            out.append(line).append("\t\n");
        }
        return out.toString();
    }




}
