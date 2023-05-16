package jmu.factory;


import jmu.dao.*;
import jmu.dao.impl.*;


public class DAOFactory {
    public static IPersonDAO getPersonDAOInstance()
    {
        return new PersonDAOImpl() ;
    }



    public static IProductDAO getProductDAOInstance(){return  new ProductDAOImpl();}
    public static IaddressDAO getaddressDAOInstance(){return  new addressDAOImpl();}
    public static IProductStateDAO getProductStatetDAOInstance() {
        return  new ProductStateImpl();
    }


    public static IWalletDAO getWalletDAOInstance() {return  new WalletDAOImpl();}


    public static IReportsDAO getReportsDAOInstance() {return  new ReportsDAOImpl();}

    public static IShopping_CartDAO getShopping_CartDAOInstance() {return  new Shopping_CartDAOImpl();}

    public static IIndentDAO getIdentDAOInstance() {return  new IndentDAOImpl();}
}
