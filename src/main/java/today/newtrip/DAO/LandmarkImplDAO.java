package today.newtrip.DAO;

import org.springframework.scheduling.annotation.Scheduled;
import today.newtrip.FileServices.ImageService;
import today.newtrip.Model.Landmark;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Transient;
import javax.transaction.Transactional;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by romachka on 31.07.17.
 */
@Transactional
public class LandmarkImplDAO implements LandmarkDAO {
    @PersistenceContext
    EntityManager em;
    public List<Landmark> getAll() {
        List<Landmark> objects = em.createQuery("SELECT L FROM Landmark L ORDER BY L.name").getResultList();
        return objects;
    }

    public boolean add(Landmark landmark) {
        return em.merge(landmark)!=null;
    }

    public Landmark getById(long id) {
        Landmark landmark = null;
        try{
            landmark = em.createQuery("SELECT L FROM Landmark L WHERE L.id=:id", Landmark.class).setParameter("id", id)
                    .getSingleResult();
        }catch (NoResultException e){
            System.out.println("No entity found for query");
        }catch (Exception e){
            e.printStackTrace();
        }
        return landmark;
    }

    public void delete(long id) {
        ImageService.deleteFolder(em.getReference(Landmark.class, id));
        em.remove(em.getReference(Landmark.class, id));
    }

    public boolean edit(Landmark landmark) {
       return em.merge(landmark)!=null;
    }

    public List<Landmark> search(String name) {
        return null;
    }

    public Landmark getByName(String name) {
        Landmark landmark = em.createQuery("SELECT L FROM Landmark L where L.name=:name", Landmark.class)
                .setParameter("name",name).getSingleResult();
        return landmark!=null?landmark:new Landmark();
    }

    public Landmark getByDate(String date) {
        return em.createQuery("SELECT L FROM Landmark L WHERE L.date=:date", Landmark.class)
                .setParameter("date", date).getSingleResult();
    }

    public Landmark getFirst() {
        List<Landmark> l =  em.createQuery("SELECT L FROM Landmark L ORDER BY L.date", Landmark.class).getResultList();
        return l.get(0);
    }
    public List<String> getDates() {
        List<String> dates =  em.createQuery("SELECT L.date FROM Landmark L ORDER BY L.date", String.class).getResultList();
        return dates;
    }
    @Scheduled(fixedDelay=10800000)
    public void deleteOld(){
        Date date = new Date();

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

        List<Long> old = em.createQuery("SELECT L.id FROM Landmark L WHERE L.date<:date",Long.class)
                .setParameter("date",dateFormat.format(date)).getResultList();
        //TODO
        //em.createQuery("DELETE FROM Landmark L WHERE L.id in (:list)").setParameter("list", old);

        for(Long l: old) {
            em.remove(em.getReference(Landmark.class, l));
        }
    }

}
