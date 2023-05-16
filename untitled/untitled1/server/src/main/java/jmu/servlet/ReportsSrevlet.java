package jmu.servlet;

import jmu.factory.DAOFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/Reports")
public class ReportsSrevlet extends HttpServlet {
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        request.setCharacterEncoding("utf-8");
        String path = "errors.jsp";
        // 接收要操作的参数值
        String status = request.getParameter("status");
        if (status != null) {
            // 插入操作
            if ("report".equals(status)) {
                String id=request.getParameter("id");
                String flag0=request.getParameter("flag");
                boolean flag = false;
                try {
                    request.setAttribute("all", DAOFactory.getReportsDAOInstance()
                            .report(id,flag0));
                    flag = true;
                } catch (Exception e) {
                }
                request.setAttribute("flag", flag);
                path = "report.jsp";
            }
        }
        request.getRequestDispatcher(path).forward(request, response);
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        this.doPost(request, response);
    }
}
