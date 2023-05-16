package jmu.servlet;

import jmu.factory.DAOFactory;
import jmu.model.Person;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
@WebServlet("/Register")
public class RegisterServlet extends HttpServlet {
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        request.setCharacterEncoding("utf-8");
        String path = "register.jsp";
        // 1、接收传递的参数
        String id = request.getParameter("id");
        String name=request.getParameter("name");
        String password = request.getParameter("password");
        int flag= Integer.parseInt(request.getParameter("type"));
        // 2、将请求的内容赋值给VO类
        Person person = new Person();
        person.setId(id);
        person.setName(name);
        person.setPassword(password);
        person.setFlag(flag);
        try {
            // 进行数据库验证
            if (DAOFactory.getPersonDAOInstance().register(person)) {

                DAOFactory.getWalletDAOInstance().insert(id);
//                // 修改跳转路径
                path = "register_success.jsp";
            } else {
                // 登陆失败
                // 设置错误信息
                request.setAttribute("err", "错误的用户ID及密码！！！");
            }
        } catch (Exception e) {
        }
        // 进行跳转
        request.getRequestDispatcher(path).forward(request, response);
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        this.doPost(request, response);
    }
}
