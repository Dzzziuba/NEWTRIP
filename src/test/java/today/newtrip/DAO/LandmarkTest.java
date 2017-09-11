package today.newtrip.DAO;

import org.junit.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import today.newtrip.Model.Landmark;



import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

/**
 * Created by romachka on 04.09.17.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "file:src/test/java/today/newtrip/context/context.xml" })
@Transactional
public class LandmarkTest {
    @Autowired
    LandmarkDAO landmarkDAO = new LandmarkImplDAO();
    @Before
    public  void testAddMethod(){
        Landmark landmark = new Landmark();
        landmark.setName("TEST LANDMARK");
        landmark.setDate("2020-08-29");
        landmark.setAuthors("Bran");
        assertTrue("Add method was failed",landmarkDAO.add(landmark));
    }
    @Test
    public void testGetByIdMethod(){
        Landmark landmarkLocal = landmarkDAO.getByName("TEST LANDMARK");
        assertTrue("GetById method was failed",landmarkDAO.getById(landmarkLocal.getId()) instanceof Landmark);
    }
    @Test
    public void testEditMethod(){
        Landmark landmarkLocal = landmarkDAO.getByName("TEST LANDMARK");
        landmarkLocal.setAuthors("Aria");
        landmarkDAO.edit(landmarkLocal);
        landmarkLocal = landmarkDAO.getByName("TEST LANDMARK");
        assertTrue("EditMethod method was failed",landmarkLocal.getAuthors().equals("Aria"));
    }

    @Test
    public void testGetByName(){
        assertTrue("GetByName method was failed",landmarkDAO.getByName("TEST LANDMARK").getName().equals("TEST LANDMARK"));
    }

    @Test
    public void testGetByDate(){
        assertTrue("GetByDate method was failed",landmarkDAO.getByDate("2020-08-29").getDate().equals("2020-08-29"));
    }

    @Test
    public void testGetFirst(){
        assertTrue("GetFirst method was failed",landmarkDAO.getFirst()!=null);
    }

    @Test
    public void testGetDates(){
        assertTrue("GetDates method was failed",landmarkDAO.getDates().size()>0);
    }
    @After
    public  void testDeleteMethod(){
        Landmark landmarkLocal = landmarkDAO.getByName("TEST LANDMARK");
            landmarkDAO.delete(landmarkLocal.getId());
            assertNull("Delete method was passed", landmarkDAO.getById(landmarkLocal.getId()));
    }
}
