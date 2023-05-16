package jmu.servlet;

import jmu.factory.DAOFactory;
import jmu.model.Person;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
@WebServlet("/Indent")
public class IdentServlet extends HttpServlet {
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        request.setCharacterEncoding("utf-8");
        String path = "errors.jsp";
        // 接收要操作的参数值
        String status = request.getParameter("status");
        if ("affirm".equals(status)) {
            String id=request.getParameter("id");
            boolean flag = false;
            try {
                DAOFactory.getIdentDAOInstance().updateAffirm(id);
//                DAOFactory.getRevertDAOInstance().update(img,name);
                flag = true;
            } catch (Exception e) {
            }
            request.setAttribute("flag", flag);
            path = "headsuccess.jsp";
        }

        // 进行跳转
        request.getRequestDispatcher(path).forward(request, response);
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        this.doPost(request, response);
    }
}
