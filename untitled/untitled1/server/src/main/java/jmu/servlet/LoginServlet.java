package jmu.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.xm.Redisson.test;
import jmu.factory.DAOFactory;
import jmu.model.Person;

@WebServlet("/Login")
public class LoginServlet extends HttpServlet {
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        request.setCharacterEncoding("utf-8");
        String path = "errors.jsp";
        // 接收要操作的参数值
        String status = request.getParameter("status");
        if ("update".equals(status)) {
            String img=request.getParameter("img");
            String name=request.getParameter("name");
            String id= String.valueOf(request.getSession().getAttribute("uId"));
            Person person=new Person();
            person.setImg(img);
            person.setName(name);
            person.setId(id);
            boolean flag = false;
            try {
                DAOFactory.getPersonDAOInstance().update(person);
//                DAOFactory.getRevertDAOInstance().update(img,name);
                request.getSession().setAttribute("uimg", person.getImg());
                flag = true;
            } catch (Exception e) {
            }
            request.setAttribute("flag", flag);
            path = "headsuccess.jsp";
        }
        else {
             path = "login.jsp";
            // 1、接收传递的参数
            String id = request.getParameter("id");
            String password = request.getParameter("password");
            // 2、将请求的内容赋值给VO类
            Person person = new Person();
            person.setId(id);
            person.setPassword(password);


            try {
                // 进行数据库验证
                if (DAOFactory.getPersonDAOInstance().login(person)) {
                    // 如果为真，则表示用户ID和密码合法
                    // 设置用户姓名到session范围之中
                    request.getSession().setAttribute("uname", person.getName());
                    request.getSession().setAttribute("uimg", person.getImg());
                    request.getSession().setAttribute("uflag",person.getFlag());
                    request.getSession().setAttribute("uId",person.getId());
                    // 修改跳转路径
                    path = "login_success.jsp";
                } else {
                    // 登陆失败
                    // 设置错误信息
                    request.setAttribute("err", "错误的用户ID及密码！！！");
                }
            } catch (Exception e) {
            }
        }
        // 进行跳转
        request.getRequestDispatcher(path).forward(request, response);
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        this.doPost(request, response);
    }
}