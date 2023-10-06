package com.app.parkingmate.controller;


import com.app.parkingmate.domain.EventSearch;
import com.app.parkingmate.domain.Pagination;
import com.app.parkingmate.domain.VO.EventVO;
import com.app.parkingmate.service.EventService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpSession;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/event/*")
public class EventController {
    public final EventService eventService;

    @GetMapping("event")
    public void goToJoinEventList(Pagination pagination, EventSearch eventSearch, Model model){
        pagination.setTotal(eventService.selectTotal(eventSearch));
        pagination.progress();
        model.addAttribute("pagination", pagination);
        model.addAttribute("events", eventService.list(pagination));
    }

    @PostMapping("event")
    public RedirectView goToJoinEventDetail(Integer id, HttpSession session, int page){
        Optional<EventVO> foundEvent = eventService.detail(id);
        if(foundEvent.isPresent()){
            session.setAttribute("event", foundEvent.get());
            return new RedirectView("/event/event-detail");
        }
        return new RedirectView("/event/event");
    }



    @GetMapping("event-detail")
    public void goToJoinDetail(){;}



}
