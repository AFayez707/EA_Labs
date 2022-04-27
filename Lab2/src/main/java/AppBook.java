package Lab2.src.main.java;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;	

public class AppBook {

	private static final SessionFactory sessionFactory;

    static {
		// If there is more than one entity, you will have to pass them as a comma delimited argument list to the method below
		sessionFactory = HibernateUtils.getSessionFactory(Arrays.asList(Book.class));
	}

    public static void main(String[] args) {
        // Hibernate placeholders
        Session session = null;
        Transaction tx = null;

        try {
            session = sessionFactory.openSession();
            tx = session.beginTransaction();

            // Create new instance of Book and set values in it
            Book book1 = new Book("Book 1", "111", "Author 1", 100.0, new Date());
            // save the book
            session.persist(book1);
            // Create new instance of Book and set values in it
            Book book2 = new Book("Book 2", "222", "Author 2", 200.0, new Date());
            // save the book
            session.persist(book2);
            // Create new instance of Book and set values in it
            Book book3 = new Book("Book 3", "333", "Author 3", 300.0, new Date());
            // save the book
            session.persist(book3);

            tx.commit();

        } catch (HibernateException e) {
            if (tx != null) {
                System.err.println("Rolling back: " + e.getMessage());
                tx.rollback();
            }
        } finally {
            if (session != null) {
                session.close();
            }
        }

        try {
            session = sessionFactory.openSession();
            tx = session.beginTransaction();

            // retieve all books
            @SuppressWarnings("unchecked")
            List<Book> bookList = session.createQuery("from Book").list();
            for (Book book : bookList) {
                System.out.println("Title= " + book.getTitle() + ", ISBN= "
                        + book.getISBN() + ", author= " + book.getAuthor()
                        + ", price= " + book.getPrice()
                        + ", Publish Data= " + book.getPublishData());
            }
            tx.commit();

        } catch (HibernateException e) {
            if (tx != null) {
                System.err.println("Rolling back: " + e.getMessage());
                tx.rollback();
            }
        } finally {
            if (session != null) {
                session.close();
            }
        }

        try {
            session = sessionFactory.openSession();
            tx = session.beginTransaction();

            // retrieve one book
            @SuppressWarnings("unchecked")
            Book loadedBook = (Book)session.get(Book.class, 1);
            loadedBook.setPrice(100000);
            loadedBook.setTitle("Changed Title");
            session.update(loadedBook);

            loadedBook = (Book)session.load(Book.class, 2);
            session.delete(loadedBook);

             tx.commit();

        } catch (HibernateException e) {
            if (tx != null) {
                System.err.println("Rolling back: " + e.getMessage());
                tx.rollback();
            }
        } finally {
            if (session != null) {
                session.close();
            }
        }

        try {
            session = sessionFactory.openSession();
            tx = session.beginTransaction();

            // retieve all books
            @SuppressWarnings("unchecked")
            List<Book> bookList = session.createQuery("from Book").list();
            for (Book book : bookList) {
                System.out.println("Title= " + book.getTitle() + ", ISBN= "
                        + book.getISBN() + ", author= " + book.getAuthor()
                        + ", price= " + book.getPrice()
                        + ", Publish Data= " + book.getPublishData());
            }
            tx.commit();

        } catch (HibernateException e) {
            if (tx != null) {
                System.err.println("Rolling back: " + e.getMessage());
                tx.rollback();
            }
        } finally {
            if (session != null) {
                session.close();
            }
        }

        // Close the SessionFactory (not mandatory)
        sessionFactory.close();
        System.exit(0);
    }
}
