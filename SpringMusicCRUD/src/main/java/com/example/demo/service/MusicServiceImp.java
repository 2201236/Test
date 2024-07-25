package com.example.demo.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Music;
import com.example.demo.repository.MusicCrudRepository;

@Service
public class MusicServiceImp implements MusicService {

	@Autowired
	private MusicCrudRepository repository;
	
	@Override
	public Iterable<Music> findAll() {
		// TODO 自動生成されたメソッド・スタブ
		return repository.findAll();
	}
	@Override
	public void insertMusic(Music music) {
		repository.save(music);
	}
	@Override
	public void deleteMusic(Integer song_id) {
		repository.deleteById(song_id);
		
	}
	@Override
	public void updateMusic(Music music) {
		repository.save(music);
		
	}
	  @Override
	    public Optional<Music> findById(Integer song_id) {
	        return repository.findById(song_id);
	    }
}
