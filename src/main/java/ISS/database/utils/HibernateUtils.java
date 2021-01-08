package ISS.database.utils;

import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

public class HibernateUtils {

    private final SessionFactory sessionFactory;
    private static HibernateUtils instance;

    private HibernateUtils(){
        StandardServiceRegistry standardRegistry = new StandardServiceRegistryBuilder()
                .configure("hibernate.cfg.xml")
                .build();

        Metadata metadata = new MetadataSources(standardRegistry)
                .getMetadataBuilder()
                .build();

        sessionFactory = metadata
                .getSessionFactoryBuilder()
                .build();
    }

    public static HibernateUtils getInstance(){
        if(instance==null){
            instance = new HibernateUtils();
        }
        return instance;
    }

    public org.hibernate.SessionFactory getSessionFactory(){
        return sessionFactory;
    }
}
