package com.dvd.controller;

import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.ServletActionContext;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class SetPreferencesAction extends ActionSupport {
    @Override
    public String execute(){
        HttpSession session = ServletActionContext.getRequest().getSession();
        HttpServletRequest request = ServletActionContext.getRequest();

        String title = request.getParameter("showTitle");
        String year = request.getParameter("showYear");
        String genre = request.getParameter("showGenre");

        session.setAttribute("showTitle", "false");
        session.setAttribute("showYear", "false");
        session.setAttribute("showGenre", "false");

        if (title != null &&title.equals("showTitle")){
            session.setAttribute("showTitle", "true");
        }

        if (year != null && year.equals("showYear")){
            session.setAttribute("showYear", "true");
        }

        if (genre != null && genre.equals("showGenre")){
            session.setAttribute("showGenre", "true");
        }

        return SUCCESS;
    }
}
