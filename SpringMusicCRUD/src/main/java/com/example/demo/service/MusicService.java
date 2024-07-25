package com.example.demo.service;

import java.util.Optional;

import com.example.demo.entity.Music;

public interface MusicService {

	Iterable<Music> findAll();

	void insertMusic(Music music);
	
	void updateMusic(Music music);
	
	void deleteMusic(Integer song_id);

	Optional<Music> findById(Integer song_id);

	

}
