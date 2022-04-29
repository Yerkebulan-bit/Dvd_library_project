package com.dvd.controller;

import com.dvd.model.DVDLibraryDAO;

import org.apache.struts2.ServletActionContext;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import java.util.List;

import static com.opensymphony.xwork2.Action.*;

public class LoginAction {

    public String execute(){
        List<String> usernames = DVDLibraryDAO.getUsernames();

        HttpServletRequest request = ServletActionContext.getRequest();
        HttpSession session = request.getSession();

        String username = request.getParameter("Username");

        for(String parameter : usernames){
            if(parameter.equals(username)){
                session.setAttribute("Username", username);
                return SUCCESS;
            }
        }

        return ERROR;
    }
}
