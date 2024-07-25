package com.example.demo.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.entity.Music;
import com.example.demo.form.MusicForm;
import com.example.demo.service.MusicService;

@Controller
public class MusicController {

    @Autowired
    private MusicService musicService;  // Correct field name and annotation

    @GetMapping("index")
    public String indexView() {
        return "index";
    }

    @PostMapping(value = "menu", params = "select")
    public String listView(Model model) {
        Iterable<Music> list = musicService.findAll();
        model.addAttribute("list", list);
        return "list";
    }

    @PostMapping(value = "menu", params = "insert")
    public String musicInputView() {
        return "music-input";
    }

    @PostMapping("insert")
    public String musicConfirmView(MusicForm f) {
        Music music = new Music(f.getSong_id(), f.getSong_name(), f.getSinger());
        musicService.insertMusic(music);
        return "music-complete";
    }

    @PostMapping(value = "menu", params = "update")
    public String musicUpdateView(Model model) {
        Iterable<Music> list = musicService.findAll();
        model.addAttribute("list", list);
        return "music-update";
    }

    @PostMapping("/update")
    public String updateMusicForm(@RequestParam("song_id") int songId, Model model) {
        // Find music by ID and unwrap the Optional
        Music music = musicService.findById(songId).orElseThrow(() -> new IllegalArgumentException("Invalid song Id:" + songId));
        model.addAttribute("music", music);
        return "update-input";
    }


    @PostMapping("/update-music")
    public String updateMusic(@ModelAttribute Music music, Model model) {
        musicService.updateMusic(music);
        model.addAttribute("message", "更新が完了しました。");
        return "update-complete";  // Changed to "update-complete"
    }

    @PostMapping(value = "menu", params = "delete")
    public String musicDeleteView(Model model) {
        Iterable<Music> list = musicService.findAll();
        model.addAttribute("list", list);
        return "music-delete";
    }

    @PostMapping("delete")
    public String musicDelete(MusicForm f) {
        musicService.deleteMusic(f.getSong_id());
        return "delete-complete";
    }

    @GetMapping("complete")
    public String topView() {
        return "index";
    }
}
