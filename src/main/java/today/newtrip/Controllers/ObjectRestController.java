package today.newtrip.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import today.newtrip.DAO.LandmarkDAO;
import today.newtrip.FileServices.ImageService;
import today.newtrip.Model.Landmark;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by romachka on 11.09.17.
 */
@RestController
@RequestMapping("/rest")
public class ObjectRestController {
    @Autowired
    LandmarkDAO landmarkDAO;
    @RequestMapping(value = "/home", method = RequestMethod.GET, produces = "application/json")
    public Map<String, String> home() {
            Landmark landmark = landmarkDAO.getFirst();
            Map<String, String> map = new HashMap<String, String>();
            map.put("id", String.valueOf(landmark.getId()));
            map.put("name", landmark.getName());
            map.put("description", landmark.getDescription());
            map.put("date", landmark.getDate());
            map.put("authors", landmark.getAuthors());
            StringBuilder sb = new StringBuilder();
            List<String> images = ImageService.getImages(landmark);
            for(String s: images){
                sb.append(s);
                sb.append(",");
            }
            sb.deleteCharAt(sb.length()-1);
            map.put("images", sb.toString());
            return map;

    }
}
