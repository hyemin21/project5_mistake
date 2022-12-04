<%--
  Created by IntelliJ IDEA.
  User: sinhyemin
  Date: 2022/12/05
  Time: 12:33 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@page import="com.example.dao.BoardDAO, com.example.bean.BoardVO"%>
<%
  String sid = request.getParameter("id");
  if (sid != "") {
    int id = Integer.parseInt(sid);

    BoardDAO boardDAO = new BoardDAO();
    BoardVO u = new BoardVO();
    u.setSeq(id);
    boardDAO.deleteBoard(id);
    response.sendRedirect("list");
  }
%>
