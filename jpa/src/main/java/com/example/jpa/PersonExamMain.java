package com.example.jpa;

import jakarta.persistence.EntityManager;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class PersonExamMain {
    //CRUD
    private static void find() {
        EntityManager em = JPAUtil.getEntityManagerFactory().createEntityManager();
        em.getTransaction().begin();
        try {
            Passport passport = em.find(Passport.class, 1L);
            log.info("Found Passport : {}", passport.getPassportNumber());
            log.info("Passport Owner : {}", passport.getPerson().getName());

            Person person = em.find(Person.class, 2L);
            log.info("Found Person : {}", person.getName());
            log.info("Person's Passport : {}", person.getPassport().getPassportNumber());

            em.getTransaction().commit();
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            throw e;
        } finally {
            em.close();
        }
    }

    private static void create() {
        EntityManager em = JPAUtil.getEntityManagerFactory().createEntityManager();
        em.getTransaction().begin();
        try {
            Person person = new Person("kang kyungmi");
            Passport passport = new Passport("C2345678");

            //연관관계 설정.
            person.setPassport(passport);
            passport.setPerson(person);

            em.persist(person);

            em.getTransaction().commit();
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            throw e;
        } finally {
            em.close();
        }
    }
    private static void update() {
        EntityManager em = JPAUtil.getEntityManagerFactory().createEntityManager();
        em.getTransaction().begin();
        try {

            Person person = em.find(Person.class, 1L);
            person.setName("newanme");

            em.getTransaction().commit();
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            throw e;
        } finally {
            em.close();
        }
    }
    private static void delete() {
        EntityManager em = JPAUtil.getEntityManagerFactory().createEntityManager();
        em.getTransaction().begin();
        try {
//            Person person = em.find(Person.class, 1L);
//            em.remove(person);

            Passport passport = em.find(Passport.class, 2L);
            if(passport != null){
                passport.getPerson().setPassport(null);
            }
            em.remove(passport);

            em.getTransaction().commit();
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            throw e;
        } finally {
            em.close();
        }
    }
    public static void main(String[] args) {
//        find();
        delete();
    }
}
