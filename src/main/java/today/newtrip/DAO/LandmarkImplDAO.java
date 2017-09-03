package today.newtrip.DAO;

import org.springframework.scheduling.annotation.Scheduled;
import today.newtrip.FileServices.ImageService;
import today.newtrip.Model.Landmark;

import javax.persistence.EntityManager;
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
        if (em.merge(landmark)!=null){
            return true;
        }else {
            return false;
        }
    }

    public Landmark getById(long id) {
        return em.createQuery("SELECT L FROM Landmark L WHERE L.id=:id", Landmark.class).setParameter("id", id)
                .getSingleResult();
    }

    public void delete(long id) {
        ImageService.deleteFolder(em.getReference(Landmark.class, id));
        em.remove(em.getReference(Landmark.class, id));
    }

    public boolean edit(Landmark landmark) {
        if (em.merge(landmark)!=null){
            return true;
        }else {
            return false;
        }
    }

    public List<Landmark> search(String name) {
        return null;
    }

    public Landmark getByName(String name) {
        return em.createQuery("SELECT L FROM Landmark L where L.name=:name", Landmark.class)
                .setParameter("name",name).getSingleResult();
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
