package today.newtrip.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import today.newtrip.DAO.LandmarkDAO;
import today.newtrip.FileServices.ImageService;
import today.newtrip.Model.Landmark;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by romachka on 31.07.17.
 */
@Controller
@RequestMapping("/admin/objects")
public class ObjectController {
    @Autowired
    LandmarkDAO landmarkDAO;
    public static Landmark LANDMARK = new Landmark();
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String listObjectGET(ModelMap model){
        model.addAttribute("objects",landmarkDAO.getAll());
        return "forward:/WEB-INF/Pages/objectList.jsp";
    }
    @RequestMapping(value = "/error", method = RequestMethod.GET)
    public String errorObjectGET(ModelMap model){
        return "forward:/WEB-INF/Pages/error.jsp";
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public String deleteObjectGET(@PathVariable long id, ModelMap model){
        landmarkDAO.delete(id);
        return "redirect:/admin/objects/";
    }
    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String addObjectGET(ModelMap model){
        List<String> disabledDates = new ArrayList<String>();
        for(String date:landmarkDAO.getDates()){
            disabledDates.add("\""+date+"\"");
        }
        model.addAttribute("object_add_disabledDates", disabledDates);
        return "forward:/WEB-INF/Pages/addObject.jsp";
    }
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String addObjectPOST(HttpServletRequest req, @RequestParam("file") MultipartFile[] files,
                                @ModelAttribute("landmark") Landmark landmark){
        if(files!=null&&files[0].getSize()>1024){
            ImageService.createFolder(landmark.getName());
            ImageService.upload(landmark.getName(),files);
        }
        else {
            return "forward:/WEB-INF/Pages/addObject.jsp";
        }
        if(landmarkDAO.add(landmark)){
            return "redirect:/admin/objects/";
        }else {
            return "forward:/WEB-INF/Pages/addObject.jsp";
        }
    }
    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    public String editObjectGET(@PathVariable long id, ModelMap model){
        Landmark l = landmarkDAO.getById(id);
        if(l!=null) {
            List<String> disabledDates = new ArrayList<String>();
            for(String date:landmarkDAO.getDates()){
                disabledDates.add("\""+date+"\"");
            }
            model.addAttribute("object_edit_disabledDates", disabledDates);
            model.addAttribute("o", landmarkDAO.getById(id));
            return "forward:/WEB-INF/Pages/editObject.jsp";
        }else {
            return "redirect:/admin/objects/error";
        }
    }
    @RequestMapping(value = "/edit/{id}", method = RequestMethod.POST)
    public String editObjectPOST(HttpServletRequest req, @RequestParam("file") MultipartFile[] files,
                                 @ModelAttribute("landmark") Landmark landmark){
        if(files!=null&&files[0].getSize()>1024){
            ImageService.upload(landmark.getName(),files);
        }
        if(landmarkDAO.edit(landmark)) {
            return "redirect:/admin/objects/";
        }else {
            return "redirect:/admin/objects/error";
        }
    }
    @RequestMapping(value = "/details/{name}", method = RequestMethod.GET)
    public String detailsObjectGET(ModelMap model, @PathVariable String name){
        Landmark landmark = landmarkDAO.getByName(name);
        model.addAttribute("o", landmark);
        model.addAttribute("images", ImageService.getImages(landmark));
        model.addAttribute("authors", landmark.splitAuthors(landmark.getAuthors()));
        return "forward:/WEB-INF/Pages/detailsObject.jsp";
    }

    @Scheduled(fixedDelay=3600000)
    public String changeObject(){
        ModelMap model = new ModelMap();
        Date date = new Date();
        SimpleDateFormat timeFormat = new SimpleDateFormat("hh");
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        if(timeFormat.format(date).equals("12")) {
             LANDMARK = landmarkDAO.getByDate(dateFormat.format(date));
                if(LANDMARK!=null) {
                    model.addAttribute("o", LANDMARK);
                    model.addAttribute("images", ImageService.getImages(LANDMARK));
                    model.addAttribute("authors", LANDMARK.splitAuthors(LANDMARK.getAuthors()));
                    return "forward:/home";
                } else {
                    LANDMARK = landmarkDAO.getFirst();
                    model.addAttribute("o", LANDMARK);
                    model.addAttribute("images", ImageService.getImages(LANDMARK));
                    model.addAttribute("authors", LANDMARK.splitAuthors(LANDMARK.getAuthors()));
                    return "forward:/home";
                }
        }else {
                    return "forward:/home";
        }
    }

}
