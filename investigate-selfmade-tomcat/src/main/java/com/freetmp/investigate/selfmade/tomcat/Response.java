package com.freetmp.investigate.selfmade.tomcat;

import java.io.*;

/**
 * Created by LiuPin on 2015/3/5.
 */
public class Response {

    private static final int BUFFER_SIZE = 1024;
    Request request;
    OutputStream output;

    public Response(OutputStream output) {
        this.output = output;
    }

    public void setRequest(Request request) {
        this.request = request;
    }

    public void sendStaticResource() {
        byte[] bytes = new byte[BUFFER_SIZE];
        FileInputStream fis = null;
        try {
            File file = new File(HttpServer.WEB_ROOT, request.getUri());
            if (file.exists()) {

                StringBuilder header = new StringBuilder();
                header.append("HTTP/1.1 200 OK\r\n");

                if(file.getName().endsWith(".html")) {
                    header.append("Content-Type: text/html\r\n");
                }else if(file.getName().endsWith(".jpg")){
                    header.append("Content-Type: image/jpeg\r\n");
                }else if(file.getName().endsWith(".png")){
                    header.append("Content-Type: image/png\r\n");
                }else if(file.getName().endsWith(".js")){
                    header.append("Content-Type: application/x-javascript\r\n");
                }else if(file.getName().endsWith(".css")){
                    header.append("Content-Type: text/css\r\n");
                }
                header.append("\r\n");

                output.write(header.toString().getBytes());

                fis = new FileInputStream(file);
                int ch = fis.read(bytes, 0, BUFFER_SIZE);
                while (ch != -1) {
                    output.write(bytes, 0, ch);
                    ch = fis.read(bytes, 0, BUFFER_SIZE);
                }
                output.flush();
            }else {
                // file not found
                String errorMessage = "HTTP/1.1 404 File Not Found\r\n" +
                        "Content-Type: text/html\r\n" +
                        "Content-Length: 23\r\n" +
                        "\r\n" +
                        "<h1>File Not Found</h1>";
                output.write(errorMessage.getBytes());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
