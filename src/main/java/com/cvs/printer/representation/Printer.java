package com.cvs.printer.representation;

import io.swagger.annotations.ApiModelProperty;

public class Printer {

    @ApiModelProperty(notes = "The printed application ID")
    private final long id;

    @ApiModelProperty(notes = "The generated PDF URL")
    private String pdfLink;

    public Printer(long id, String pdfLink) {
        this.id = id;
        this.pdfLink = pdfLink;
    }

    public long getId() {
        return id;
    }

    public String getPdfLink() {
        return pdfLink;
    }

}
