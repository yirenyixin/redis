package jmu.servlet;

import jmu.factory.DAOFactory;
import jmu.model.Address;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/address")
public class addressServlet extends HttpServlet {
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        request.setCharacterEncoding("utf-8");
        String path = "errors.jsp";
        // 接收要操作的参数值
        String status = request.getParameter("status");
        if (status != null) {
            if("selectone".equals(status)){
                boolean flag=false;
                String id=request.getParameter("id");
                String shopid=request.getParameter("shopid");
                String uid=request.getParameter("uid");
                try {
//                    request.setAttribute("product", DAOFactory.getProductDAOInstance().get_one(id));
                    request.setAttribute("address",DAOFactory.getPersonDAOInstance());
                    flag=true;
                } catch (Exception e) {
                    e.printStackTrace();
                }
                request.setAttribute("flag", flag);
                path = "Confirm_order.jsp";
            }
            // 参数有内容，之后选择合适的方法
            // 查询全部操作
            if("selectall".equals(status)) {
                String id=request.getParameter("uid");
                Address addr=new Address();
                addr.setUserid(id);
                boolean flag = false;
                try {
                    String flag1 = request.getParameter("flag");
                    request.setAttribute("all", DAOFactory.getaddressDAOInstance()
                            .queryAll(addr));
                    flag=true;
                } catch (Exception e) {
                }
                request.setAttribute("flag", flag);
                path = "Receiving_address.jsp";
            }
            // 插入操作
            if ("insert".equals(status)) {
                String city=request.getParameter("city");
                String addr1=request.getParameter("addr");
                String flag1=request.getParameter("flag");
                String phone=request.getParameter("phone");
                String userid=request.getParameter("userid");
                String addressee=request.getParameter("addressee");
                Address addr=new Address();
                addr.setCity(city);
                addr.setAddr(addr1);
                addr.setFlag(flag1);
                addr.setPhone(phone);
                addr.setUserid(userid);
                addr.setAddressee(addressee);
                boolean flag = false;
                try {
                    DAOFactory.getaddressDAOInstance().insert(addr);
                    flag = true;
                } catch (Exception e) {
                }
                request.setAttribute("flag", flag);
                path = "insert_do.jsp";
            }
            if("update".equals(status)){
                String id=request.getParameter("id");
                String userid=request.getParameter("userid");

                Address addr=new Address();
                addr.setId(Integer.parseInt(id));
                addr.setUserid(userid);
                boolean flag = false;
                try {
                    DAOFactory.getaddressDAOInstance().update(addr);
                    flag = true;
                } catch (Exception e) {
                }
                request.setAttribute("flag", flag);
                path = "update_do.jsp";
            }
            if("delect".equals(status)) {
                String id=request.getParameter("id");
                Address addr=new Address();
                addr.setId(Integer.parseInt(id));
                boolean flag = false;
                try {
                    DAOFactory.getaddressDAOInstance().delect(addr);
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
