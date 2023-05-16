package jmu.servlet;

import jmu.factory.DAOFactory;
import jmu.model.Address;
import jmu.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
@Service
@WebServlet("/Product")
public class ProductServlet extends HttpServlet {
    @Autowired
    private RedisTemplate redisTemplate;
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        request.setCharacterEncoding("utf-8");
        String path = "errors.jsp";
        // 接收要操作的参数值
        String status = request.getParameter("status");
        if (status != null) {
            if ("selectbylike".equals(status)) {
                String keyword = request.getParameter("keyword");
                try {
                    request.setAttribute("all", DAOFactory.getProductDAOInstance()
                            .queryByLike(keyword));
                } catch (Exception e) {
                }
                path = "Category_page.jsp";
            }
            if("selectone".equals(status)){
                boolean flag=false;
                String id=request.getParameter("id");
                String shopid=request.getParameter("shopid");
                String uid=request.getParameter("uid");
                String num=request.getParameter("num");
                Address addr=new Address();
                addr.setUserid(uid);
                try {
                    request.setAttribute("product",DAOFactory.getProductDAOInstance().queryAll1(id));
                    request.setAttribute("person",DAOFactory.getPersonDAOInstance().queryAll1(shopid));
                    request.setAttribute("address",DAOFactory.getaddressDAOInstance().queryAll(addr));
                    request.setAttribute("buyer", DAOFactory.getWalletDAOInstance()
                            .getbalance(uid));
                    request.setAttribute("seller", DAOFactory.getWalletDAOInstance()
                            .getbalance(shopid));
                    request.getSession().setAttribute("num",num);
                    flag=true;
                } catch (Exception e) {
                    e.printStackTrace();
                }

                request.setAttribute("flag", flag);
                path = "Confirm_order.jsp";
            }
            // 参数有内容，之后选择合适的方法
            // 插入操作
            if ("insert".equals(status)) {
                boolean flag = false;

                String name=request.getParameter("product-name");
                String img=request.getParameter("img");
                String color=request.getParameter("product-color");
                String type=request.getParameter("type");
                String price=request.getParameter("product-price");
                String shop_id=request.getParameter("uId");
                String size=request.getParameter("size");
                String stock=request.getParameter("product-stock");
                Product product=new Product();
                product.setName(name);
                product.setImg(img);
                product.setColor(color);
                product.setPrice(price);
                product.setType(type);
                product.setShopid(shop_id);
                product.setSize(size);
                product.setStock(stock);
                try {
                        DAOFactory.getProductDAOInstance().insert(product);
                        flag = true;
                    } catch (Exception e) {
                    }
                request.setAttribute("flag", flag);
                path = "insert_do.jsp";
            }
            if("update".equals(status)) {

                String name = request.getParameter("product-name");
                String img = request.getParameter("img");
                String color = request.getParameter("product-color");
                String type = request.getParameter("type");
                String price = request.getParameter("product-price");
                String shop_id = request.getParameter("uId");
                String size = request.getParameter("size");
                String id=request.getParameter("id");
                String pack=request.getParameter("packed");
                int new_stock= Integer.parseInt(request.getParameter("new_stock"));
                int old_stock= Integer.parseInt(request.getParameter("old_stock"));
                boolean flag = false;
                if(new_stock<0){
                }
                else {
                    Product product = new Product();
                    product.setName(name);
                    product.setImg(img);
                    product.setColor(color);
                    product.setPrice(price);
                    product.setType(type);
                    product.setShopid(shop_id);
                    product.setSize(size);
                    product.setId(Integer.parseInt(id));
                    product.setStock(String.valueOf(new_stock));
                    try {
                    DAOFactory.getProductDAOInstance().update(product);
                    if(new_stock!=old_stock) {
                        DAOFactory.getProductDAOInstance().update_detail(product, old_stock);
                    }
                        flag = true;
                    } catch (Exception e) {
                    }
                }
                request.setAttribute("flag", flag);
                path = "update_do.jsp";
            }
            if("delete".equals(status)){
                String id=request.getParameter("id");
                Product product = new Product();
                product.setId(Integer.parseInt(id));
                boolean flag=false;
                try {
                    DAOFactory.getProductDAOInstance().delete(product);
                    flag=true;
                } catch (Exception e) {
                    e.printStackTrace();
                }
                request.setAttribute("flag", flag);
                path = "delete_do.jsp";
            }
            if ("my".equals(status)) {
                try {
                    String my=request.getParameter("id");
                    request.setAttribute("all", DAOFactory.getProductDAOInstance()
                            .queryAllmy(my));
//                request.setAttribute("buyer", DAOFactory.getWalletDAOInstance()
//                        .getbalance(my));
//                request.setAttribute("seller", DAOFactory.getWalletDAOInstance()
//                        .getbalance(my));
                    request.setAttribute("all1", DAOFactory.getWalletDAOInstance()
                            .getbalance(my));
                } catch (Exception e) {
                }
                path = "Personal_Center.jsp";
            }
            if("selectall".equals(status)) {
                boolean flag = false;
                String id= String.valueOf(request.getSession().getAttribute("uId"));
                String flag0= String.valueOf(request.getSession().getAttribute("uflag"));
                try {
                    String flag1 = request.getParameter("flag");

                    if(flag1.equals("0")) {
                        if(flag0.equals("0")) {
                            request.setAttribute("all", DAOFactory.getProductDAOInstance()
                                    .queryAll(flag1));
                            request.setAttribute("shopping_cart", DAOFactory.getShopping_CartDAOInstance()
                                    .querycartbuyer(id));//购物车数
                            request.setAttribute("order1", DAOFactory.getProductStatetDAOInstance()
                                        .queryorder1(id));//下单数
                            request.setAttribute("order2", DAOFactory.getProductStatetDAOInstance()
                                    .queryorder2(id));//送达
                            request.setAttribute("order3", DAOFactory.getProductStatetDAOInstance()
                                    .queryorder3(id));//收货
                            request.setAttribute("indent",DAOFactory.getIdentDAOInstance()
                                    .queryAll0(id));//等待确认
                            path = "list_note.jsp";
                        }
                        else{
                            request.setAttribute("all", DAOFactory.getProductDAOInstance()
                                    .queryAll(flag1));
                            request.setAttribute("order1", DAOFactory.getProductStatetDAOInstance()
                                    .queryorderShop(id));//下单数
                            request.setAttribute("order02", DAOFactory.getProductStatetDAOInstance()
                                    .queryorder2_a(id));//送达
                            request.setAttribute("order03", DAOFactory.getProductStatetDAOInstance()
                                    .queryorder3_a(id));//收货
                            path = "list_note.jsp";
                        }
                    }else{
                        request.setAttribute("all", DAOFactory.getProductDAOInstance()
                                .queryAll(flag1));
                        path = "Category_page.jsp";
                    }
                    flag=true;
                } catch (Exception e) {
                }
                request.setAttribute("flag", flag);
            }
            if("myproduct".equals(status)){
                boolean flag = false;
                String id=request.getParameter("id");
                try {
                    request.setAttribute("all", DAOFactory.getProductDAOInstance()
                            .querymyproduct(id));
                    flag=true;
                } catch (Exception e) {
                    e.printStackTrace();
                }
                request.setAttribute("flag", flag);
                path = "my_product.jsp";
            }
            if("add_page".equals(status)){
                String id=request.getParameter("id");
                String uflag=request.getParameter("uflag");
                String pagesize=request.getParameter("pagesize");
                String end=request.getParameter("end");
                String pagestart=request.getParameter("pagestart");
                String flag1 = request.getParameter("flag");
                if(Integer.parseInt(end)+4>Integer.parseInt(pagesize)){
                    end=pagesize;
                    int b=Integer.parseInt(pagestart)+4;
                    pagestart= String.valueOf(b);
                    request.getSession().setAttribute("pagesize",pagesize);
                    request.getSession().setAttribute("pagestart",pagestart);
                    request.getSession().setAttribute("end",end);
                    if(uflag.equals("0")) {
                        try {
                            request.setAttribute("all", DAOFactory.getProductDAOInstance()
                                    .queryAll(flag1));
                            request.setAttribute("shopping_cart", DAOFactory.getShopping_CartDAOInstance()
                                    .querycartbuyer(id));//购物车数
                            request.setAttribute("order1", DAOFactory.getProductStatetDAOInstance()
                                    .queryorder1(id));//下单数
                            request.setAttribute("order2", DAOFactory.getProductStatetDAOInstance()
                                    .queryorder2(id));//送达
                            request.setAttribute("order3", DAOFactory.getProductStatetDAOInstance()
                                    .queryorder3(id));//收货
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }else{
                        try {
                            request.setAttribute("all", DAOFactory.getProductDAOInstance()
                                    .queryAll(flag1));
                        request.setAttribute("order01", DAOFactory.getProductStatetDAOInstance()
                                .queryorder1_a(id));//下单数
                        request.setAttribute("order02", DAOFactory.getProductStatetDAOInstance()
                                .queryorder2_a(id));//送达
                        request.setAttribute("order03", DAOFactory.getProductStatetDAOInstance()
                                .queryorder3_a(id));//收货
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                    path = "list_note.jsp";
                }else{
                    int a=Integer.parseInt(end)+4;
                    int b=Integer.parseInt(pagestart)+4;
                    end= String.valueOf(a);
                    pagestart= String.valueOf(b);
                    request.getSession().setAttribute("pagesize",pagesize);
                    request.getSession().setAttribute("pagestart",pagestart);
                    request.getSession().setAttribute("end",end);
                    if(uflag.equals("0")) {
                        try {
                            request.setAttribute("all", DAOFactory.getProductDAOInstance()
                                    .queryAll(flag1));
                            request.setAttribute("shopping_cart", DAOFactory.getShopping_CartDAOInstance()
                                    .querycartbuyer(id));//购物车数
                            request.setAttribute("order1", DAOFactory.getProductStatetDAOInstance()
                                    .queryorder1(id));//下单数
                            request.setAttribute("order2", DAOFactory.getProductStatetDAOInstance()
                                    .queryorder2(id));//送达
                            request.setAttribute("order3", DAOFactory.getProductStatetDAOInstance()
                                    .queryorder3(id));//收货
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }else{
                        try {
                            request.setAttribute("all", DAOFactory.getProductDAOInstance()
                                    .queryAll(flag1));
                            request.setAttribute("order01", DAOFactory.getProductStatetDAOInstance()
                                    .queryorder1_a(id));//下单数
                            request.setAttribute("order02", DAOFactory.getProductStatetDAOInstance()
                                    .queryorder2_a(id));//送达
                            request.setAttribute("order03", DAOFactory.getProductStatetDAOInstance()
                                    .queryorder3_a(id));//收货
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                    path = "list_note.jsp";
                }

            }
            if("reduce_page".equals(status)){
                String id=request.getParameter("id");
                String uflag=request.getParameter("uflag");
                String pagesize=request.getParameter("pagesize");

                String end=request.getParameter("end");
                String pagestart=request.getParameter("pagestart");
                String flag1 = request.getParameter("flag");
                if(Integer.parseInt(end)>=4){
                    int b=Integer.parseInt(pagestart)-4;
                    if(b>=0) pagestart= String.valueOf(b);
                    else pagestart= String.valueOf(0);
                    b=Integer.parseInt(end);
                    if(b%4==0) {
                        b = Integer.parseInt(end) - 4;
                        end=String.valueOf(b);
                    }else{
                        b=b%4;
                        int a=Integer.parseInt(end)-b;
                        end=String.valueOf(a);
                    }
                    request.getSession().setAttribute("pagesize",pagesize);
                    request.getSession().setAttribute("pagestart",pagestart);
                    request.getSession().setAttribute("end",end);
                    if(uflag.equals("0")) {
                        try {
                            request.setAttribute("all", DAOFactory.getProductDAOInstance()
                                    .queryAll(flag1));
                            request.setAttribute("shopping_cart", DAOFactory.getShopping_CartDAOInstance()
                                    .querycartbuyer(id));//购物车数
                            request.setAttribute("order1", DAOFactory.getProductStatetDAOInstance()
                                    .queryorder1(id));//下单数
                            request.setAttribute("order2", DAOFactory.getProductStatetDAOInstance()
                                    .queryorder2(id));//送达
                            request.setAttribute("order3", DAOFactory.getProductStatetDAOInstance()
                                    .queryorder3(id));//收货
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }else{
                        try {
                            request.setAttribute("all", DAOFactory.getProductDAOInstance()
                                    .queryAll(flag1));
                            request.setAttribute("order01", DAOFactory.getProductStatetDAOInstance()
                                    .queryorder1_a(id));//下单数
                            request.setAttribute("order02", DAOFactory.getProductStatetDAOInstance()
                                    .queryorder2_a(id));//送达
                            request.setAttribute("order03", DAOFactory.getProductStatetDAOInstance()
                                    .queryorder3_a(id));//收货
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                    path = "list_note.jsp";
                }

            }
        }
        request.getRequestDispatcher(path).forward(request, response);
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        this.doPost(request, response);
    }
}
