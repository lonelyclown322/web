package com.example.lab3;

import com.example.lab3.coordinateBeans.RBean;
import com.example.lab3.coordinateBeans.XBean;
import com.example.lab3.coordinateBeans.YBean;
import org.hibernate.Session;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


@ManagedBean
@SessionScoped
public class DataTable implements Serializable {

    @ManagedProperty(value = "#{xBean}")
    private XBean xBean;

    @ManagedProperty(value = "#{yBean}")
    private YBean yBean;

    @ManagedProperty(value = "#{rBean}")
    private RBean rBean;

    private List<Hit> hits;

    public DataTable() {
        hits = new ArrayList<>();

        getAllHits();
    }

    public void save() {
        Session session = HibernateUtil.getSessionFactory().openSession();

        try {
            session.beginTransaction();
            Hit hit = new Hit(
                    xBean.getValue(),
                    yBean.getValue(),
                    rBean.getValue(),
                    isHit(xBean.getValue(),
                            yBean.getValue(),
                            rBean.getValue()));
            session.persist(hit);
            hits.add(hit);
            System.out.println(xBean.getValue());
            System.out.println(yBean.getValue());
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    public void save(Hit hit) {
        Session session = HibernateUtil.getSessionFactory().openSession();

        try {
            session.beginTransaction();
            session.persist(hit);
            hits.add(hit);
            System.out.println(hit.getX());
            System.out.println(hit.getY());
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    public List<Hit> getHits() {
        return hits;
    }

    public void setHits(List<Hit> hits) {
        this.hits = hits;
    }

    public void getAllHits() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            session.beginTransaction();
            hits = session.createQuery("SELECT h FROM Hit h",Hit.class).getResultList();
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    public boolean isHit(double x, double y, double r) {
        return (x <= 0 && y >= 0 && y <= r && x >= -r/2) ||
                (x >= 0 && y >= 0 && x*x + y*y <= r*r) ||
                (x >= 0 && y <= 0 && 2 * y >= 2 * x - r);
    }



    public XBean getxBean() {
        return xBean;
    }

    public void setxBean(XBean xBean) {
        this.xBean = xBean;
    }

    public YBean getyBean() {
        return yBean;
    }

    public void setyBean(YBean yBean) {
        this.yBean = yBean;
    }

    public RBean getrBean() {
        return rBean;
    }

    public void setrBean(RBean rBean) {
        this.rBean = rBean;
    }
}