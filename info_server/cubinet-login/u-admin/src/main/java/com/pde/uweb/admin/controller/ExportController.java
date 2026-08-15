package com.pde.uweb.admin.controller;

import com.pde.uweb.admin.utils.MimeType;
import com.pde.uweb.admin.utils.SVGRasterizer;
import com.pde.uweb.admin.utils.SVGRasterizerException;
import org.apache.batik.transcoder.TranscoderException;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.Enumeration;

@Controller()
@RequestMapping("/highcharts-export")
public class ExportController {
    private static final String REQUEST_METHOD_POST = "POST";
    private static final String CONTENT_TYPE_MULTIPART = "multipart/";
    private static final String FORBIDDEN_WORD = "<!ENTITY";
    protected static Logger logger = Logger.getLogger("exportservlet");

    @RequestMapping()
    public void highchartsExport(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        try {
            boolean multi = isMultipartRequest(request);
            String svg = getParameter(request, "svg", multi);
            if (svg == null || svg.isEmpty()) {
                throw new ServletException(
                        "The required - svg - post parameter is missing");
            }

            if (svg.indexOf(FORBIDDEN_WORD) > -1 || svg.indexOf(FORBIDDEN_WORD.toLowerCase()) > -1) {
                //throw new ServletException(
                //	"The - svg - post parameter could contain a malicious attack");
            }
            String filename = getFilename(getParameter(request, "filename", multi));
            Float width = getWidth(getParameter(request, "width", multi));
            MimeType mime = getMime(getParameter(request, "type", multi));

            ExportController.writeFileContentToHttpResponse(svg, filename,
                    width, mime, response);

        } catch (IOException ioe) {
            logger.error("Oops something happened here redirect to error-page, "
                    + ioe.getMessage());
            sendError(request, response, ioe);
        } catch (ServletException sce) {
            logger.error("Oops something happened here redirect to error-page, "
                    + sce.getMessage());
            sendError(request, response, sce);
        }

    }

    /*
      * Util methods
      */

    public static void writeFileContentToHttpResponse(String svg,
                                                      String filename, Float width, MimeType mime,
                                                      HttpServletResponse response) throws IOException, ServletException {

        ByteArrayOutputStream stream = new ByteArrayOutputStream();

        if (!MimeType.SVG.equals(mime)) {
            try {
                stream = SVGRasterizer.getInstance().transcode(stream, svg,
                        mime, width);
            } catch (SVGRasterizerException sre) {
                logger.error("Error while transcoding svg file to an image", sre);
                stream.close();
                throw new ServletException(
                        "Error while transcoding svg file to an image");
            } catch (TranscoderException te) {
                logger.error("Error while transcoding svg file to an image", te);
                stream.close();
                throw new ServletException(
                        "Error while transcoding svg file to an image");
            }
        } else {
            stream.write(svg.getBytes());
        }

        // prepare response
        response.reset();
        response.setContentLength(stream.size());
        response.setCharacterEncoding("utf-8");
        response.setHeader("Content-disposition", "attachment; filename=" + filename + "." + mime.name().toLowerCase());
        response.setHeader("Content-type", mime.getType());
        // set encoding before writing to out, check this
        ServletOutputStream out = response.getOutputStream();
        // Send content to Browser
        out.write(stream.toByteArray());
        out.flush();
    }

    public static final boolean isMultipartRequest(HttpServletRequest request) {
        // inspired by org.apache.commons.fileupload
        logger.debug("content-type " + request.getContentType());
        return REQUEST_METHOD_POST.equalsIgnoreCase(request.getMethod())
                && request.getContentType() != null
                && request.getContentType().toLowerCase()
                .startsWith(CONTENT_TYPE_MULTIPART);
    }

    private String getParameter(HttpServletRequest request, String name, Boolean multi) throws IOException, ServletException {
        MultipartHttpServletRequest mRequest = (MultipartHttpServletRequest) request;
        if (multi && mRequest.getFile(name) != null) {
            return getValue(mRequest.getFile(name));
        } else {
        return request.getParameter(name);
        }
    }

    private static String getValue(MultipartFile part) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(part.getInputStream(), "UTF-8"));
        StringBuilder value = new StringBuilder();
        char[] buffer = new char[1024];
        for (int length = 0; (length = reader.read(buffer)) > 0; ) {
            value.append(buffer, 0, length);
        }
        return value.toString();
    }

    private String getFilename(String name) {
        return (name != null) ? name : "chart";
    }

    private static Float getWidth(String width) {
        if (width != null && !width.isEmpty()) {
            Float parsedWidth = Float.valueOf(width);
            if (parsedWidth.compareTo(0.0F) > 0) {
                return parsedWidth;
            }
        }
        return null;
    }

    private static MimeType getMime(String mime) {
        MimeType type = MimeType.get(mime);
        if (type != null) {
            return type;
        }
        return MimeType.PNG;
    }


    protected void sendError(HttpServletRequest request, HttpServletResponse response, Throwable ex) throws IOException,
            ServletException {
        String headers = null;
        String htmlHeader = "<HTML><HEAD><TITLE>Highcharts Export error</TITLE><style type=\"text/css\">"
                + "body {font-family: \"Trebuchet MS\", Arial, Helvetica, sans-serif;} table {border-collapse: collapse;}th {background-color:green;color:white;} td, th {border: 1px solid #98BF21;} </style></HEAD><BODY>";
        String htmlFooter = "</BODY></HTML>";

        response.setContentType("text/html");

        PrintWriter out = response.getWriter();
        Enumeration<String> e = request.getHeaderNames();
        String svg = this.getParameter(request, "svg",
                isMultipartRequest(request));

        out.println(htmlHeader);
        out.println("<h3>Error while converting SVG</h3>");
        out.println("<h4>Error message</h4>");
        out.println("<p>" + ex.getMessage() + "</p>");
        out.println("<h4>Debug steps</h4><ol>"
                + "<li>Copy the SVG:<br/><textarea cols=100 rows=5>"
                + svg
                + "</textarea></li>"
                + "<li>Go to <a href='http://validator.w3.org/#validate_by_input' target='_blank'>validator.w3.org/#validate_by_input</a></li>"
                + "<li>Paste the SVG</li>"
                + "<li>Click More Options and select SVG 1.1 for Use Doctype</li>"
                + "<li>Click the Check button</li></ol>");

        out.println("<h4>Request Headers</h4>");
        out.println("<TABLE>");
        out.println("<tr><th> Header </th><th> Value </th>");

        while (e.hasMoreElements()) {
            headers = (String) e.nextElement();
            if (headers != null) {
                out.println("<tr><td align=center><b>" + headers + "</td>");
                out.println("<td align=center>" + request.getHeader(headers)
                        + "</td></tr>");
            }
        }
        out.println("</TABLE><BR>");
        out.println(htmlFooter);

    }
}