package com.dvd.controller;

import com.dvd.model.DVDItem;
import com.dvd.model.DVDLibrary;
import com.opensymphony.xwork2.ActionSupport;

import org.apache.struts2.ServletActionContext;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import java.util.ArrayList;
import java.util.List;

public class AddDVDAction extends ActionSupport {
    @Override
    public String execute(){
        HttpServletRequest request = ServletActionContext.getRequest();
        HttpSession session = request.getSession();

        List<String> errorMsgs = new ArrayList<>();
        request.setAttribute("errorMsgs", errorMsgs);
        try {
            String year = request.getParameter("year").trim();
            String title = request.getParameter("title").trim();
            String genre = request.getParameter("genre").trim();

            if (genre.equals("unknown")) {
                errorMsgs.add("Please, select genre of DVD");
            }

            if (title.length() == 0) {
                errorMsgs.add("Please, enter the title");
            }

            if (year.length() == 0) {
                errorMsgs.add("Please, enter the year");
            }

            if (!errorMsgs.isEmpty()) {
                return ERROR;
            }

            DVDLibrary dvdLibrary = (DVDLibrary) session.getAttribute("library");
            DVDItem dvdItem = dvdLibrary.addDVD(title, year, genre);
            session.setAttribute("dvdItem", dvdItem);
            return SUCCESS;

        } catch (Exception e) {
            e.printStackTrace(System.err);
        }

        return SUCCESS;

    }
}

