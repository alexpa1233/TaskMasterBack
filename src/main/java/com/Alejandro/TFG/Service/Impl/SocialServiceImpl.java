package com.Alejandro.TFG.Service.Impl;

import java.time.LocalTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Alejandro.TFG.Service.SocialService;
import com.Alejandro.TFG.model.Social;
import com.Alejandro.TFG.repository.SocialRepository;

@Service
public class SocialServiceImpl implements SocialService{
    @Autowired
    private SocialRepository socialRepository;

    @Override
    public Social getSocialById(Long id) {
        return socialRepository.findById(id).orElse(null);
    }

    @Override
    public List<Social> getAllSocials() {
        return socialRepository.findAll();
    }

    @Override
    public Social saveSocial(Social social) {
        return socialRepository.save(social);
    }

    @Override
    public void deleteSocial(Long id) {
        socialRepository.deleteById(id);
    }

    
    
}
