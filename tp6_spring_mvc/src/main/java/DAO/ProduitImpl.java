package DAO;

import Utils.HibernateUtils;
import java.util.List;
import org.hibernate.*;
import DAO.*;

public class ProduitImpl implements ProduitDAO {

    public void init() {
        System.out.println("spring ioc worked");
        addProduit(new Produit("PC 1", "Sony vaio 1", 7000.0));
        addProduit(new Produit("PC 2", "Sony vaio 2", 6000.0));
    }
    @Override
    public void addProduit(Produit p) {
        Session session = HibernateUtils.getSessionFactory().openSession();
        Transaction tr = null;
        try {
            tr = session.beginTransaction();
            session.save(p);
            tr.commit();
        } catch (Exception e) {
            if (tr != null) tr.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    @Override
    public void deleteProduit(Long id) {
        Session session = HibernateUtils.getSessionFactory().openSession();
        Transaction tr = null;
        try {
            tr = session.beginTransaction();
            Produit p = (Produit) session.get(Produit.class, id);
            if (p != null) {
                session.delete(p);
            }
            tr.commit();
        } catch (Exception e) {
            if (tr != null) tr.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    @Override
    public void editProduit(Produit p) {
    }

    @Override
    public void updateProduit(Produit p) {
        Session session = HibernateUtils.getSessionFactory().openSession();
        Transaction tr = null;
        try {
            tr = session.beginTransaction();
            session.update(p);
            tr.commit();
        } catch (Exception e) {
            if (tr != null) tr.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    @Override
    public List<Produit> getAllProduits() {
        Session session = HibernateUtils.getSessionFactory().openSession();
        try {
            Query query = session.createQuery("from Produit");
            return (List<Produit>) query.list();
        } finally {
            session.close();
        }
    }

    @Override
    public Produit getProduitById(Long ID) {
        Session session = HibernateUtils.getSessionFactory().openSession();
        try {
            return (Produit) session.get(Produit.class, ID);
        } finally {
            session.close();
        }
    }
}
