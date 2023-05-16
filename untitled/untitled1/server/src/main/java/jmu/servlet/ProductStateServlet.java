package jmu.servlet;

import jmu.factory.DAOFactory;
import jmu.model.ProductState;
import jmu.model.Wallet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/ProductState")
public class ProductStateServlet extends HttpServlet {
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        request.setCharacterEncoding("utf-8");
        String path = "errors.jsp";
        // 接收要操作的参数值
        String status = request.getParameter("status");
        if (status != null) {
            // 插入操作
            if ("insert".equals(status)) {

                String sellerid=request.getParameter("sellerid");
                String buyerid=request.getParameter("buyerid");
                String productid=request.getParameter("productid1");
                String shopname=request.getParameter("shopname");
                String buyername=request.getParameter("buyername");
                String city=request.getParameter("city");
                String phone=request.getParameter("phone");
                String addr=request.getParameter("addr");
                String balance_buyer=request.getParameter("balance_buyer");
                String balance_seller=request.getParameter("balance_seller");
                String price=request.getParameter("price");
                String num=request.getParameter("num");
                String cost=request.getParameter("price");
                String productname=request.getParameter("productname");
                String addressee=request.getParameter("buyername");
                int a= Integer.parseInt(price);
                int b= Integer.parseInt(price);
                String state="已下单";
                ProductState productState=new ProductState();
                productState.setReturn_goods(String.valueOf(1));
                productState.setSellerid(sellerid);
                productState.setBuyerid(buyerid);
                productState.setProductid(productid);
                productState.setState(state);
                productState.setShopname(shopname);
                productState.setBuyername(buyername);
                productState.setCity(city);
                productState.setPhone(phone);
                productState.setAddr(addr);
                productState.setNum(num);
                productState.setCost(cost);
                productState.setProductname(productname);
                productState.setAddressee(addressee);
                Wallet wallet_seller=new Wallet();
                wallet_seller.setUserid(sellerid);
                wallet_seller.setBalance(String.valueOf(b));
                Wallet wallet_buyer=new Wallet();
                wallet_buyer.setUserid(buyerid);
                wallet_buyer.setBalance(String.valueOf(a));
                boolean flag = false;
                try {
                    DAOFactory.getProductStatetDAOInstance().insert(productState);
                    DAOFactory.getWalletDAOInstance().reduce(wallet_buyer);
                    DAOFactory.getWalletDAOInstance().add(wallet_seller);
                    flag = true;
                } catch (Exception e) {
                }
                request.setAttribute("flag", flag);
                path = "insert_do.jsp";
            }
            if("confirm".equals(status)){//申请退货
                String id=request.getParameter("id");
                String flag1=request.getParameter("flag");
                boolean flag = false;
                try {
                    request.setAttribute("all", DAOFactory.getProductStatetDAOInstance()
                            .queryAllIndent(id,flag1));
                    flag = true;
                } catch (Exception e) {
                }
                request.setAttribute("flag", flag);
                path = "Indent.jsp";
            }
            if("return_goods".equals(status)){//申请退货
                String id=request.getParameter("id");
                String state=request.getParameter("state");
                boolean flag = false;
                try {
                    DAOFactory.getProductStatetDAOInstance().update_goods_state(id,state);
                    flag = true;
                } catch (Exception e) {
                }
                request.setAttribute("flag", flag);
                path = "update_do.jsp";
            }
            if("delect".equals(status)){//同意退货
                String id=request.getParameter("id");

                boolean flag = false;
                try {
                    DAOFactory.getProductStatetDAOInstance().delect_goods(id);
                    flag = true;
                } catch (Exception e) {
                }
                request.setAttribute("flag", flag);
                path = "update_do.jsp";
            }
            if("logistics".equals(status)){
                String id=request.getParameter("id");
                String flag1=request.getParameter("flag");
                boolean flag = false;
                try {
                    request.setAttribute("all", DAOFactory.getProductStatetDAOInstance()
                            .queryAllmy(id,flag1));
                    flag = true;
                } catch (Exception e) {
                }
                request.setAttribute("flag", flag);
                path = "logistics.jsp";
            }
            if("update".equals(status)){
                String id=request.getParameter("id");
                String state=request.getParameter("state");
                ProductState productState=new ProductState();
                productState.setId(Integer.parseInt(id));
                productState.setState(state);
                boolean flag = false;
                try {
                     DAOFactory.getProductStatetDAOInstance().update(productState);
                    flag = true;
                } catch (Exception e) {
                }
                request.setAttribute("flag", flag);
                path = "update_do.jsp";
            }
            if("translate".equals(status)){
                String id=request.getParameter("id");
                String flag0= String.valueOf(request.getSession().getAttribute("uflag"));
                boolean flag = false;
                try {
                    request.setAttribute("all", DAOFactory.getProductStatetDAOInstance()
                            .queryAllmytranslate(id,flag0));
                    flag = true;
                } catch (Exception e) {
                }
                request.setAttribute("flag", flag);
                path = "translate.jsp";
            }
            if("goods".equals(status)){
                String id=request.getParameter("id");
                String flag0= String.valueOf(request.getSession().getAttribute("uflag"));
                boolean flag = false;
                try {
                    request.setAttribute("all", DAOFactory.getProductStatetDAOInstance()
                            .queryAllmygoods(id,flag0));
                    flag = true;
                } catch (Exception e) {
                }
                request.setAttribute("flag", flag);
                path = "Received_goods.jsp";
            }
            if ("insertList".equals(status)) {
                boolean flag = true;
                String a0=request.getParameter("a");
                int a= Integer.parseInt(a0);
                String city=request.getParameter("city");
                String phone=request.getParameter("phone");
                String addr=request.getParameter("addr");
                String addressee=request.getParameter("buyername");
                String buyerid=request.getParameter("buyerid");
                ProductState productState=new ProductState();
                productState.setCity(city);
                productState.setPhone(phone);
                productState.setAddr(addr);
                productState.setAddressee(addressee);
                productState.setBuyerid(buyerid);
                Wallet wallet_seller=new Wallet();
                Wallet wallet_buyer=new Wallet();
                wallet_buyer.setUserid(buyerid);
                for(int i=0;i<a;i++){
                    int sum=0;
                    String shopid=request.getParameter("head_list"+String.valueOf(i)+"_shopid");
                    String shopname=request.getParameter("head_list"+String.valueOf(i)+"_shopname");
                    wallet_seller.setUserid(shopid);
                    int b= Integer.parseInt(request.getParameter("list"+String.valueOf(i)+"_b"));
                    int index=0;
                    for(int j=0;j<b;j++){
                        String check=request.getParameter("head_list"+String.valueOf(i)+"_check_"+String.valueOf(j)+"_value");
                        if(check.equals("0")) {
                            index=1;
                            String price0 = request.getParameter("head_list" + String.valueOf(i) + "_price_" + String.valueOf(j) + "_static");
                            String num = request.getParameter("head_list" + String.valueOf(i) + "_num_" + String.valueOf(j));
                            String id=request.getParameter("list"+String.valueOf(i)+"_id_"+String.valueOf(j));
                            String price = String.valueOf(Integer.parseInt(price0) * Integer.parseInt(num));
                            String productid=request.getParameter("list"+String.valueOf(i)+"_productid_"+String.valueOf(j));
                            String productname=request.getParameter("list"+i+"_productname_"+j);
                            sum += Integer.parseInt(price);
                            productState.setShopname(shopname);
                            productState.setSellerid(shopid);
                            productState.setCost(price);
                            productState.setId(Integer.parseInt(id));
                            productState.setNum(num);
                            productState.setProductid(productid);
                            productState.setProductname(productname);
                            try {
                                DAOFactory.getProductStatetDAOInstance().insert(productState);
                                DAOFactory.getShopping_CartDAOInstance().delect1(id);
                            } catch (Exception e) {
                                flag = false;
                                e.printStackTrace();
                            }
                        }
                    }
                    if(index==1) {
                        wallet_buyer.setBalance(String.valueOf(sum));
                        wallet_seller.setBalance(String.valueOf(sum));
                        try {
                            DAOFactory.getWalletDAOInstance().reduce(wallet_buyer);
                            DAOFactory.getWalletDAOInstance().add(wallet_seller);
                        } catch (Exception e) {
                            flag = false;
                            e.printStackTrace();
                        }
                    }

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
