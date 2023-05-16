package jmu.dao.impl;


import jmu.model.ProductDetail;
import jmu.dao.IProductDAO;
import jmu.db.ConnectionManager;
import jmu.utils.MyBatisUtils;

import jmu.model.Product;
import org.apache.ibatis.session.SqlSession;

import org.redisson.Redisson;
import org.redisson.api.RBloomFilter;
import org.redisson.api.RedissonClient;
import org.redisson.client.codec.StringCodec;
import org.redisson.config.Config;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import jmu.utils.SQLUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
@Service
@EnableAsync

@Component
public class ProductDAOImpl implements IProductDAO {

    // 查询全部
    public List<Product> queryAll(String flag) throws Exception
    {
        List<Product> all=new ArrayList<Product>();
        if(flag.equals("0")) {
//            SqlSession sqlSession1 = MyBatisUtils.getSession();
//            all = sqlSession1.selectList("jmu.mapper.ProductMapper.getall");
//            sqlSession1.commit();
//            sqlSession1.close();
            String sql = "SELECT product.id,product.name,product.img,product.size,product.type,product.price,product.shopid,product.color,person.name,product.stock,product.produc_packed FROM product,person where product.shopid=person.id" ;
            Connection conn=null;
            PreparedStatement pstmt=null;
            ResultSet rs=null;
            try
            {
                conn=ConnectionManager.getConnection();
                pstmt=conn.prepareStatement(sql);
                rs = pstmt.executeQuery() ;
                while(rs.next())
                {
                    Product product=new Product();
                    product.setId(rs.getInt(1));
                    product.setName(rs.getString(2));
                    product.setImg(rs.getString(3));
                    product.setSize(rs.getString(4));
                    product.setType(rs.getString(5));
                    product.setPrice(rs.getString(6));
                    product.setShopid(rs.getString(7));
                    product.setColor(rs.getString(8));
                    product.setShopname(rs.getString(9));
                    product.setStock(rs.getString(10));
                    product.setProduc_packed(rs.getString(11));
                    all.add(product);
                }
            }
            catch (Exception e)
            {
                System.out.println(e) ;
                throw new Exception("操作中出现错误！！！") ;
            }
            finally
            {
                ConnectionManager.closeResultSet(rs);
                ConnectionManager.closeStatement(pstmt);
                ConnectionManager.closeConnection(conn);
            }
        }else{
            String sql = "SELECT product.id,product.name,product.img,product.size,product.type,product.price,product.shopid,product.color,person.name FROM product,person where type=? and product.shopid=person.id" ;
            Connection conn=null;
            PreparedStatement pstmt=null;
            ResultSet rs=null;
            try
            {
                conn=ConnectionManager.getConnection();
                pstmt=conn.prepareStatement(sql);
                pstmt.setString(1,flag) ;
                rs = pstmt.executeQuery() ;
                while(rs.next())
                {
                    Product product=new Product();
                    product.setId(rs.getInt(1));
                    product.setName(rs.getString(2));
                    product.setImg(rs.getString(3));
                    product.setSize(rs.getString(4));
                    product.setType(rs.getString(5));
                    product.setPrice(rs.getString(6));
                    product.setShopid(rs.getString(7));
                    product.setColor(rs.getString(8));
                    product.setShopname(rs.getString(9));
                    all.add(product);
                }
            }
            catch (Exception e)
            {
                System.out.println(e) ;
                throw new Exception("操作中出现错误！！！") ;
            }
            finally
            {
                ConnectionManager.closeResultSet(rs);
                ConnectionManager.closeStatement(pstmt);
                ConnectionManager.closeConnection(conn);
            }
        }
        return all ;
    }
    public List<Product> queryAll1(String id) throws Exception
    {
        SqlSession sqlSession1 =MyBatisUtils.getSession();
        List<Product> all =new ArrayList<Product>();
        List<Product> all1=sqlSession1.selectList("jmu.mapper.ProductMapper.getall");
        for(int i=0;i<all1.size();i++){
            String a= String.valueOf(all1.get(i).getId());
            if(a.equals(id)) {
                all.add(all1.get(i));
            }
        }
        sqlSession1.commit();
        sqlSession1.close();
        return all ;
    }
//    public List<Product> getone(String id) throws Exception {
//        SqlSession sqlSession =MyBatisUtils.getSession();
//        List<Product> =sqlSession.selectOne("jmu.mapper.ProductMapper.getone",id);
//        sqlSession.commit();
//        sqlSession.close();
//        return product;
//    }
    public  List<Product> get_one(String id)throws Exception{
        SqlSession sqlSession=MyBatisUtils.getSession();
        List<Product> pro=sqlSession.selectOne("jmu.mapper.ProductMapper.getone",id);
        sqlSession.commit();
        sqlSession.close();
        return pro;
    }
    // 增加操作
    public void insert(Product product) throws Exception
    {

        String timestamp=String.valueOf(System.nanoTime());
        product.setProduc_packed(new StringBuffer("redis:product:packet:").append(product.getShopid()).append(":").append(timestamp).toString());
        SQLUtils sqlUtils=new SQLUtils();
        int a=sqlUtils.getID("select id from product order by id desc limit 1");
        product.setId(a);


        String sql = "INSERT INTO product(id,name,img,color,type,price,shopid,size,stock,produc_packed) VALUES(?,?,?,?,?,?,?,?,?,?)" ;
        Connection conn=null;
        PreparedStatement pstmt=null;
        try
        {
            conn= ConnectionManager.getConnection();
            pstmt=conn.prepareStatement(sql);
            pstmt.setInt(1,product.getId()); ;
            pstmt.setString(2,product.getName()) ;
            pstmt.setString(3,product.getImg()) ;
            pstmt.setString(4,product.getColor());
            pstmt.setString(5,product.getType());
            pstmt.setString(6,product.getPrice());
            pstmt.setString(7, product.getShopid());
            pstmt.setString(8, product.getSize());
            pstmt.setString(9,product.getStock());
            pstmt.setString(10,product.getProduc_packed());
            pstmt.executeUpdate() ;
        }
        catch (Exception e)
        {
            // System.out.println(e) ;
            throw new Exception("操作中出现错误！！！") ;
        }
        finally
        {
            ConnectionManager.closeStatement(pstmt);
            ConnectionManager.closeConnection(conn);
        }

        a= sqlUtils.getID("select id from product_detail order by id desc limit 1");
        ProductDetail productDetail=new ProductDetail();
        productDetail.setProduct_id(a);
        productDetail.setProduct_active(0);
        productDetail.setProduct_packed(product.getProduc_packed());
        sql = "INSERT INTO product_detail(id,product_id,product_active,product_packed,creat_time) VALUES(?,?,?,?,?)" ;
        conn=null;
        pstmt=null;
        try
        {
            conn= ConnectionManager.getConnection();
            pstmt=conn.prepareStatement(sql);
            pstmt.setInt(2,product.getId());
            pstmt.setInt(3,0);
            pstmt.setString(4, product.getProduc_packed());
            Date date = new Date();
            for(int i=0;i<Integer.valueOf(product.getStock());i++) {
                pstmt.setInt(1,a++);
                productDetail.setCreat_time(new Date());
                SimpleDateFormat dateFormat= new SimpleDateFormat("yyyy-MM-dd :hh:mm:ss");
                pstmt.setString(5,dateFormat.format(date));
                pstmt.executeUpdate();
            }
        }
        catch (Exception e)
        {
            // System.out.println(e) ;
            throw new Exception("操作中出现错误！！！") ;
        }
        finally
        {
            ConnectionManager.closeStatement(pstmt);
            ConnectionManager.closeConnection(conn);
        }


//        Config config = new Config();
//        config.useSingleServer().setAddress("redis://127.0.0.1:6379").setDatabase(0);
//
//        // 集群版
//        //config.useClusterServers().addNodeAddress("redis://127.0.0.1:6379", "redis://127.0.0.1:6379");
//
//        //构造redisson
//        RedissonClient redissonClient = Redisson.create(config);
//        //通过redisson构造rBloomFilter
//        RBloomFilter rBloomFilter = redissonClient.getBloomFilter("seckillGoodsBloomFilter", new StringCodec());
//        rBloomFilter.tryInit(10000,0.03);
//        rBloomFilter.add(product.getProduc_packed());

    }
    @Override
    public List<Product> queryAllmy(String my) throws Exception {
        List<Product> all = new ArrayList<Product>() ;
        String sql = "SELECT id,name,img,size,type,price,shopid,color,stock,produc_packed FROM product where shopid=?" ;
        Connection conn=null;
        PreparedStatement pstmt=null;
        ResultSet rs=null;
        try
        {
            conn=ConnectionManager.getConnection();
            pstmt=conn.prepareStatement(sql);
            pstmt.setString(1,my) ;
            rs = pstmt.executeQuery() ;
            while(rs.next())
            {
                Product product=new Product();
                product.setId(rs.getInt(1));
                product.setName(rs.getString(2));
                product.setImg(rs.getString(3));
                product.setSize(rs.getString(4));
                product.setType(rs.getString(5));
                product.setPrice(rs.getString(6));
                product.setShopid(rs.getString(7));
                product.setColor(rs.getString(8));
                product.setStock(rs.getString(9));
                product.setProduc_packed(rs.getString(10));
                all.add(product);
            }
        }
        catch (Exception e)
        {
            System.out.println(e) ;
            throw new Exception("操作中出现错误！！！") ;
        }
        finally
        {
            ConnectionManager.closeResultSet(rs);
            ConnectionManager.closeStatement(pstmt);
            ConnectionManager.closeConnection(conn);
        }
        return all ;
    }

    @Override
    public List<Product> queryByLike(String cond) throws Exception {
        List<Product> all = new ArrayList<Product>() ;
        String sql = "SELECT product.id,product.name,product.img,product.size,product.type,product.price,product.shopid,product.color,person.name,person.flag FROM product LEFT JOIN person on person.id=product.shopid  WHERE product.id LIKE ? or product.name LIKE ? or person.name LIKE ?" ;
        Connection conn=null;
        PreparedStatement pstmt=null;
        ResultSet rs=null;
        try
        {
            conn=ConnectionManager.getConnection();
            pstmt=conn.prepareStatement(sql);
            pstmt.setString(1,"%"+cond+"%") ;
            pstmt.setString(2,"%"+cond+"%") ;
            pstmt.setString(3,"%"+cond+"%") ;
            rs = pstmt.executeQuery() ;
            while(rs.next())
            {
                Product product=new Product();
                product.setId(rs.getInt(1));
                product.setName(rs.getString(2));
                product.setImg(rs.getString(3));
                product.setSize(rs.getString(4));
                product.setType(rs.getString(5));
                product.setPrice(rs.getString(6));
                product.setShopid(rs.getString(7));
                product.setColor(rs.getString(8));
                product.setShopname(rs.getString(9));
                all.add(product);
            }
        }
        catch (Exception e)
        {
            System.out.println(e) ;
            throw new Exception("操作中出现错误！！！") ;
        }
        finally
        {
            ConnectionManager.closeResultSet(rs);
            ConnectionManager.closeStatement(pstmt);
            ConnectionManager.closeConnection(conn);
        }
        return all ;
    }

    @Override
    public List<Product> querymyproduct(String id) throws Exception {
        List<Product> all = new ArrayList<Product>() ;
        String sql = "SELECT id,name,img,size,type,price,shopid,color,stock,produc_packed FROM product where shopid=?" ;
        Connection conn=null;
        PreparedStatement pstmt=null;
        ResultSet rs=null;
        try
        {
            conn=ConnectionManager.getConnection();
            pstmt=conn.prepareStatement(sql);
            pstmt.setString(1,id) ;
            rs = pstmt.executeQuery() ;
            while(rs.next())
            {
                Product product=new Product();
                product.setId(rs.getInt(1));
                product.setName(rs.getString(2));
                product.setImg(rs.getString(3));
                product.setSize(rs.getString(4));
                product.setType(rs.getString(5));
                product.setPrice(rs.getString(6));
                product.setShopid(rs.getString(7));
                product.setColor(rs.getString(8));
                product.setStock(rs.getString(9));
                product.setProduc_packed(rs.getString(10));
                all.add(product);
            }
        }
        catch (Exception e)
        {
            System.out.println(e) ;
            throw new Exception("操作中出现错误！！！") ;
        }
        finally
        {
            ConnectionManager.closeResultSet(rs);
            ConnectionManager.closeStatement(pstmt);
            ConnectionManager.closeConnection(conn);
        }
        return all ;
    }

    @Override
    public void update(Product product) throws Exception {
        String sql = "update product set name=?,img=?,size=?,type=?,price=?,stock=? where id=?" ;
        Connection conn=null;
        PreparedStatement pstmt=null;
        try
        {
            conn= ConnectionManager.getConnection();
            pstmt=conn.prepareStatement(sql);
            pstmt.setString(1, product.getName()); ;
            pstmt.setString(2, product.getImg());
            pstmt.setString(3, product.getSize());
            pstmt.setString(4, product.getType());
            pstmt.setString(5, product.getPrice());
            pstmt.setString(6, product.getStock());
            pstmt.setInt(7, product.getId());
            pstmt.executeUpdate() ;
        }
        catch (Exception e)
        {
            // System.out.println(e) ;
            throw new Exception("操作中出现错误！！！") ;
        }
        finally
        {
            ConnectionManager.closeStatement(pstmt);
            ConnectionManager.closeConnection(conn);
        }
    }

    @Override
    public void delete(Product product) throws Exception {
//        SqlSession sqlSession = MyBatisUtils.getSession();//获取sqlSession
//        Note note=new Note();
//        note.setId(id);
//        sqlSession.delete("jmu.mapper.NoteMapper.delete",note);
//        sqlSession.commit();	//提交事务
//        sqlSession.close();


        String sql = "DELETE FROM product WHERE id=?" ;
        Connection conn=null;
        PreparedStatement pstmt=null;

            try {
                conn = ConnectionManager.getConnection();
                pstmt = conn.prepareStatement(sql);
                pstmt.setInt(1, product.getId());
                pstmt.executeUpdate();
            } catch (Exception e) {
                throw new Exception("操作中出现错误！！！");
            } finally {
                ConnectionManager.closeStatement(pstmt);
                ConnectionManager.closeConnection(conn);
            }

        List a=new ArrayList();
        SQLUtils sqlUtils=new SQLUtils();
        a=sqlUtils.getListID("select id from product_detail where product_active=0 and product_id="+product.getId());
        sql = "DELETE FROM product_detail WHERE id=?" ;
        conn=null;
        pstmt=null;
        try
        {
            conn= ConnectionManager.getConnection();
            pstmt=conn.prepareStatement(sql);
            for(int i=0;i<a.size();i++) {
                String q= (String) a.get(i);
                int w= Integer.parseInt(q);
                pstmt.setInt(1, w);
                pstmt.executeUpdate() ;
            }
        }
        catch (Exception e)
        {
            // System.out.println(e) ;
            throw new Exception("操作中出现错误！！！") ;
        }
        finally
        {
            ConnectionManager.closeStatement(pstmt);
            ConnectionManager.closeConnection(conn);
        }
    }

    @Override
    public void update_detail(Product product, int old_stock) throws Exception {
        SQLUtils sqlUtils=new SQLUtils();
        int x= Integer.parseInt(product.getStock());
        if(x>old_stock){
            int a=sqlUtils.getID("select id from product_detail order by id desc limit 1");
            String packed=sqlUtils.getPacked("select product_packed from product_detail where product_id="+product.getId()+" limit 1");
            String sql = "INSERT INTO product_detail(id,product_id,product_active,product_packed,creat_time) VALUES(?,?,?,?,?)" ;
        Connection conn=null;
        PreparedStatement pstmt=null;
        try
        {
            conn= ConnectionManager.getConnection();
            pstmt=conn.prepareStatement(sql);
            pstmt.setInt(2,product.getId());
            pstmt.setInt(3,0);
            pstmt.setString(4,packed);
            Date date = new Date();
            for(int i=0;i<x-old_stock;i++) {
                pstmt.setInt(1,a++);
                ProductDetail productDetail=new ProductDetail();
                productDetail.setCreat_time(new Date());
                SimpleDateFormat dateFormat= new SimpleDateFormat("yyyy-MM-dd :hh:mm:ss");
                pstmt.setString(5,dateFormat.format(date));
                pstmt.executeUpdate();
            }
        }
        catch (Exception e)
        {
            // System.out.println(e) ;
            throw new Exception("操作中出现错误！！！") ;
        }
        finally
        {
            ConnectionManager.closeStatement(pstmt);
            ConnectionManager.closeConnection(conn);
        }
        }
        else {
            List a=new ArrayList();
            a=sqlUtils.getListID("select id from product_detail where product_active=0 and product_id="+product.getId());
            String sql = "DELETE FROM product_detail WHERE id=?" ;
            Connection conn=null;
            PreparedStatement pstmt=null;
            try
            {
                conn= ConnectionManager.getConnection();
                pstmt=conn.prepareStatement(sql);
                for(int i=0;i<old_stock-x;i++) {
                    String q= (String) a.get(i);
                    int w= Integer.parseInt(q);
                    pstmt.setInt(1, w);
                    pstmt.executeUpdate() ;
                }
            }
            catch (Exception e)
            {
                // System.out.println(e) ;
                throw new Exception("操作中出现错误！！！") ;
            }
            finally
            {
                ConnectionManager.closeStatement(pstmt);
                ConnectionManager.closeConnection(conn);
            }
        }
    }
}
