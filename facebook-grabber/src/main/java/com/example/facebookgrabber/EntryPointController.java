package com.example.facebookgrabber;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.connect.ConnectionRepository;
import org.springframework.social.facebook.api.Facebook;
import org.springframework.social.facebook.api.PagedList;
import org.springframework.social.facebook.api.Post;
import org.springframework.social.facebook.api.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


/**
 * @author Andriy Brezetskyy
 */
@Controller
@RequestMapping("/")
public class EntryPointController {

    @Autowired
    private Facebook facebook;
    @Autowired
    private ConnectionRepository connectionRepository;


    @GetMapping
    public String entryPoint(Model model) {
        if (connectionRepository.findPrimaryConnection(Facebook.class) == null) {
            return "redirect:/connect/facebook";
        }

        User user = facebook.fetchObject("me", User.class);
        model.addAttribute("userName", user.getName());

        /*
        model.addAttribute("facebookProfile", facebook.userOperations().getUserProfile());
        PagedList<Post> feed = facebook.feedOperations().getFeed();
        model.addAttribute("feed", feed);

        */
        return "entryPoint";
    }

}