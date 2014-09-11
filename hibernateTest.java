package entity;

import java.io.Serializable;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

/*
 * test.java
 *
 * created at Feb 11, 2014 by T.Neykov t.neykov@seeburger.com
 *
 * Copyright (c) SEEBURGER AG, Germany. All Rights Reserved.
 */
public class hibernateTest implements Serializable
{
    /**
	 *
	 */
	private static final long serialVersionUID = 1L;
	Session s = null;
    Transaction tx = null;
    Configuration cfg =null;
    SessionFactory factory = null;
    public static void main(String[] args)
    {
        hibernateTest dbCon = new hibernateTest();
        dbCon.initDbConn();
         dbCon.insert(1, "Neykov", "213435", 1, 1);
       // dbCon.delete();
       // dbCon.selectUserbyId(2);
    }
/**
 * Select user by UserID from DB
 * @param id - integer
 */
   public void selectUserbyId(int id)
   {
       User tUser = new User();
       tUser =(User)s.get(User.class,id);
       System.out.println(tUser.getName());
   }
/**
 * DB connecntion initialisation
 */
    @SuppressWarnings("deprecation")
	public void initDbConn()
    {
         cfg = new Configuration();
         cfg.configure("hibernate.cfg.xml");
         factory = cfg.buildSessionFactory();
         s = factory.openSession();
         tx = s.beginTransaction();
    }
/**
 *
 */
    public void insert(int id, String name, String password, int personalDetailID,int userGroupID )
    {
        PersonDetail pdetail = new PersonDetail(personalDetailID);
        UserGroup userGroups = new UserGroup();
        userGroups.setUsersGroupsId(userGroupID);
        User user1 = new User();
        user1.setUserId(id);
        user1.setName(name);
        user1.setPassword(password);
        user1.setPersonsDetails(pdetail);
        user1.setUsersGroups(userGroups);
       // s.save(userGroups);
       //   s.save(personsDetails);
        s.save(user1);
        s.flush();
        tx.commit();
        s.close();
        System.out.println("saved");
    }
    public void delete()
    {
        User userForDelete = new User();
        userForDelete =(User)s.get(User.class,new Integer(1));
        s.delete(userForDelete);
        s.flush();
        tx.commit();
        s.close();
        System.out.println("Deleted");
    }
}

