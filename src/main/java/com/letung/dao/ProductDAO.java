package com.letung.dao;


import com.letung.daoImpl.ProductImpl;
import com.letung.entity.DetailProduct;
import com.letung.entity.Product;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Repository
@Scope(proxyMode = ScopedProxyMode.TARGET_CLASS)
public class ProductDAO implements ProductImpl {
    @Autowired
    SessionFactory sessionFactory;

    @Override
    @Transactional
    public List<Product> getListProductLimit(int startProduct) {
        Session session = sessionFactory.getCurrentSession();
        List<Product>listProduct = new ArrayList<Product>();
        if(startProduct < 0){
            listProduct = session.createQuery("from product").getResultList();
        }
        else{
            listProduct = session.createQuery("from product limit").setFirstResult(startProduct).setMaxResults(5).getResultList();

        }
        return listProduct;
    }

    @Override
    @Transactional
    public Product getProductById(int idProduct) {
        Session session = sessionFactory.getCurrentSession();
        Product product = (Product) session.createQuery("from product where idProduct ='" + idProduct +"'").getSingleResult();
        return product;
    }

    @Override
    @Transactional
    public List<Product> getListProductByCategory(int idCategory) {
        Session session = sessionFactory.getCurrentSession();
        List<Product> listProductByCategory = session.createQuery("from product p where p.productCategory.idCategory='" + idCategory+"'").getResultList();
        return listProductByCategory;
    }

    @Override
    @Transactional
    public boolean deleteProductById(int idProduct) {
        Session session = sessionFactory.getCurrentSession();
        Product product = session.get(Product.class, idProduct);
        Set<DetailProduct> detailProducts = product.getListDetailProduct();
        /**
         * delete DetailBill
         * delete DetailProduct
         * delete Product
         */
        for (DetailProduct detailProduct:detailProducts) {
            session.createQuery("delete detailbill where detailBillId.idDetailProduct =" + detailProduct.getIdDetailProduct()).executeUpdate();
        }
        session.createQuery("delete detailproduct where product.idProduct =" + idProduct).executeUpdate();
        session.createQuery("delete product where idProduct =" + idProduct).executeUpdate();
        return false;
    }

    @Override
    @Transactional
    public boolean addProduct(Product product) {
        Session session = sessionFactory.getCurrentSession();
        int idProduct = (int) session.save(product);
        return false;
    }

    @Override
    @Transactional
    public boolean updateProduct(Product product) {
        Session session = sessionFactory.getCurrentSession();
        session.update(product);
        return false;
    }
}
