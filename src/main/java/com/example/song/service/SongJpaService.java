package com.example.song.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import java.util.*;
import com.example.song.repository.SongRepository;
import com.example.song.model.Song;
import com.example.song.repository.SongJpaRepository;

// Write your code here
@Service
public class SongJpaService implements SongRepository {

    @Autowired
    private SongJpaRepository sJR;

    @Override
    public ArrayList<Song> allSong() {
        List<Song> songList = sJR.findAll();
        ArrayList<Song> allSongs = new ArrayList<>(songList);
        return allSongs;
    }

    @Override
    public Song getSongById(int songId) {
        try {
            Song song = sJR.findById(songId).get();
            return song;
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public Song addSong(Song song) {
        sJR.save(song);
        return song;
    }

    @Override
    public Song updateSong(int songId, Song song) {
        try {
            Song updateSong = sJR.findById(songId).get();
            if (song.getSongName() != null) {
                updateSong.setSongName(song.getSongName());
            }
            if (song.getLyricist() != null) {
                updateSong.setLyricist(song.getLyricist());
            }
            if (song.getSinger() != null) {
                updateSong.setSinger(song.getSinger());
            }
            if (song.getMusicDirector() != null) {
                updateSong.setMusicDirector(song.getMusicDirector());
            }
            sJR.save(updateSong);
            return updateSong;
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public void deleteSong(int songId) {
        try {
            sJR.deleteById(songId);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }
}