package jmu.servlet;

import jmu.factory.DAOFactory;
import jmu.model.Wallet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/Wallet")
public class WalletServlet extends HttpServlet {
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        request.setCharacterEncoding("utf-8");
        String path = "errors.jsp";
        // 接收要操作的参数值
        String status = request.getParameter("status");
        if (status != null) {
            // 插入操作
            if ("update".equals(status)) {
                String personid=request.getParameter("id");
                String balance=request.getParameter("balance");
                Wallet wallet=new Wallet();
                wallet.setUserid(personid);
                wallet.setBalance(balance);
                boolean flag = false;
                try {
                    DAOFactory.getWalletDAOInstance().update(wallet);
                    flag = true;
                } catch (Exception e) {
                }
                request.setAttribute("flag", flag);
                path = "insert_do.jsp";
            }
        }
        request.getRequestDispatcher(path).forward(request, response);
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        this.doPost(request, response);
    }
}
