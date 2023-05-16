package jmu.servlet;

import jmu.factory.DAOFactory;
import jmu.model.Address;
import jmu.model.Shopping_Cart;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/Shopping_Cart")
public class Shopping_CartServlet extends HttpServlet {
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        request.setCharacterEncoding("utf-8");
        String path = "errors.jsp";
        // 接收要操作的参数值
        String status = request.getParameter("status");
        if (status != null) {
            // 插入操作
            if ("insert".equals(status)) {
                Shopping_Cart shopping_cart=new Shopping_Cart();
                shopping_cart.setProductid(request.getParameter("id"));
                shopping_cart.setShopid(request.getParameter("shopid"));
                shopping_cart.setBuyerid(request.getParameter("uid"));
                shopping_cart.setNum(request.getParameter("num"));
                boolean flag = false;
                try {
                    int a=DAOFactory.getShopping_CartDAOInstance().insert(shopping_cart);
                    if(a==1) flag = true;
                } catch (Exception e) {
                }
                request.setAttribute("flag", flag);
                path = "insert_shopping_cart.jsp";
            }
            if("selectall".equals(status)){
                String buyerid=request.getParameter("id");
                Address addr=new Address();
                addr.setUserid(buyerid);
                boolean flag = false;
                try {
                        request.setAttribute("all", DAOFactory.getShopping_CartDAOInstance()
                                .queryAll(buyerid));
                    request.setAttribute("address",DAOFactory.getaddressDAOInstance().queryAll(addr));
                    request.setAttribute("buyer", DAOFactory.getWalletDAOInstance()
                            .getbalance(buyerid));
//                    request.setAttribute("seller", DAOFactory.getWalletDAOInstance()
//                            .getbalance(shopid));
                        path = "Shopping_Cart.jsp";
                    flag=true;
                } catch (Exception e) {
                }
                request.setAttribute("flag", flag);
            }
            if("delete".equals(status)){
                String id=request.getParameter("id");
                Shopping_Cart shopping_cart=new Shopping_Cart();
                shopping_cart.setId(Integer.parseInt(id));
                boolean flag = false;
                try {
                    DAOFactory.getShopping_CartDAOInstance().delect(shopping_cart);
                    flag=true;
                } catch (Exception e) {
                }
                request.setAttribute("flag", flag);
                path = "update_do.jsp";
            }
        }
        request.getRequestDispatcher(path).forward(request, response);
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        this.doPost(request, response);
    }
}
