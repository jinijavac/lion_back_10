package org.example.friendexam.controller;

import lombok.RequiredArgsConstructor;
import org.example.friendexam.domain.Friend;
import org.example.friendexam.service.FriendService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequiredArgsConstructor
@RequestMapping("/friends")
public class FriendController {
    private final FriendService friendService;

//    @GetMapping
//    public String friends(Model model){
//        Iterable<Friend> friends =  friendService.findAllFriends();
//        model.addAttribute("friends", friends);
//        return "friends/list";
//    }

    @GetMapping
    public String friends(Model model, @RequestParam(defaultValue = "1")int page,
                          @RequestParam(defaultValue = "5") int size){
        Pageable pageable = PageRequest.of(page -1 , size);

        Page<Friend> friends =  friendService.findAllFriends(pageable);
        model.addAttribute("friends", friends);
        model.addAttribute("currentPage",page);
        return "friends/list";
    }

    //친구등록 - url 몇개?  --  addForm  add    -- 메서드별로 따로 일을 할 수 있죠?
    //friends/add    --  Get   --  폼 보여줘요.    Post  등록해줘요.
    @GetMapping("/add")
    public String addForm(Model model){
        model.addAttribute("friend", new Friend());
        return "friends/form";
    }

    @PostMapping("/add")
    public String addFriend(@ModelAttribute Friend friend, RedirectAttributes redirectAttributes){
        friendService.saveFriend(friend);
        redirectAttributes.addFlashAttribute("message","친구등록 성공!!");
        return "redirect:/friends";
    }

    //친구상세페이지
    //localhost/friends/1
    @GetMapping("/{id}")
    public String detailFriend(@PathVariable Long id, Model model){
        Friend friend = friendService.findFriendById(id);
        model.addAttribute("friend",friend);
        return "friends/detail";
    }

    @GetMapping("/delete/{id}")
    public String deleteFriend(@PathVariable Long id){
        friendService.deleteFriendById(id);
        return "redirect:/friends";
    }

    //수정 localhost/friends/edit/{id}  -- Get  수정폼
    //수정 localhost/friends/edit/{id}  -- Post  수정

    @GetMapping("/edit/{id}")
    public String editForm(@PathVariable Long id, Model model){
        model.addAttribute("friend", friendService.findFriendById(id));
        return "friends/edit";
    }

    @PostMapping("/edit")
    public String editFriend(@ModelAttribute Friend friend){
        friendService.saveFriend(friend);
        return "redirect:/friends";
    }

}
