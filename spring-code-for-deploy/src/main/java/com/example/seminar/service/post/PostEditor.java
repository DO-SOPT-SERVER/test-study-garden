package com.example.seminar.service.post;

import com.example.seminar.domain.Post;
import jakarta.persistence.EntityManagerFactory;
import lombok.RequiredArgsConstructor;
import org.hibernate.ScrollableResults;
import org.hibernate.SessionFactory;
import org.hibernate.StatelessSession;
import org.hibernate.Transaction;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PostEditor {
    private final EntityManagerFactory emf;

    public void editContent(final long postId, String content) {
        SessionFactory sessionFactory = emf.unwrap(SessionFactory.class);
        StatelessSession session = sessionFactory.openStatelessSession();
        Transaction tx = session.beginTransaction();
        ScrollableResults scroll = session.createQuery("select p from Post p where p.id = :id")
                .setParameter("id", postId)
                .scroll();

        while (scroll.next()) {
            Post post = (Post) scroll.get();
            System.out.println("post = " + post.getContent());
            post.updateContent(content);
            session.update(post);
        }
        tx.commit();
        session.close();
    }
    public void editContent(Post post, String content) {
        post.updateContent(content);
    }

}
