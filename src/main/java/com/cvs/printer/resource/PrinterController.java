package com.cvs.printer.resource;

import com.cvs.printer.util.PdfReport;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.cvs.printer.representation.Printer;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.net.URI;
import java.util.concurrent.atomic.AtomicLong;

@RestController
@Api(value="printOptions", description="Operations pertaining to printing application")
public class PrinterController {

    private final AtomicLong counter = new AtomicLong();
    /*
    private ResponseEntity<InputStreamResource> responseEntity;


    @RequestMapping(value = "/print", method = RequestMethod.GET)
    public Printer print(@RequestParam(value="htmlContent") String htmlContent) {

        long id = counter.incrementAndGet();
        String pdfLink = ServletUriComponentsBuilder.fromCurrentContextPath().path("/print/inference/" + id).build().toUriString();
        try {
            responseEntity = createResponse(htmlContent);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new Printer(id, pdfLink);
    }
    */

    @ApiOperation(value = "Get the inference report PDF",response = Iterable.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully printed the application"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
            }
    )
    @RequestMapping(value = "/print", method = RequestMethod.GET,
            produces = MediaType.APPLICATION_PDF_VALUE)
    public ResponseEntity<InputStreamResource> inferenceReport(@RequestParam(value="htmlContent") String htmlContent){
        long id = counter.incrementAndGet();
        URI uri = ServletUriComponentsBuilder.fromCurrentContextPath().path("/inference/" + id).build().toUri();
        Printer p = new Printer(id, uri.toString());
        ResponseEntity<InputStreamResource> responseEntity = null;
        try {
            responseEntity = createResponse(htmlContent, uri);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return responseEntity;
    }

    private ResponseEntity<InputStreamResource> createResponse(String htmlContent, URI uri) throws IOException{
        ByteArrayInputStream bis = PdfReport.inferenceReport(htmlContent);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "inline; filename=inferenceReport.pdf");
        headers.setLocation(uri);

        return ResponseEntity
                .ok()
                .headers(headers)
                .contentType(MediaType.APPLICATION_PDF)
                .body(new InputStreamResource(bis));
    }
}
