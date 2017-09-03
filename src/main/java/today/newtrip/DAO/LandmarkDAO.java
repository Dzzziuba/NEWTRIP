package today.newtrip.DAO;

import today.newtrip.Model.Landmark;

import java.util.List;

/**
 * Created by romachka on 31.07.17.
 */
public interface LandmarkDAO {

    public List<Landmark> getAll();
    public boolean add(Landmark landmark);
    public Landmark getById(long id);
    public void delete(long id);
    public boolean edit(Landmark landmark);
    public List<Landmark> search(String name);
    public Landmark getByName(String name);
    public Landmark getByDate(String date);
    public Landmark getFirst();
    public void deleteOld();
    public List<String> getDates();
}
