package com.Alejandro.TFG.Service;

import java.time.LocalTime;
import java.util.List;

import com.Alejandro.TFG.model.Social;

public interface SocialService {
    Social getSocialById(Long id);
    Social getSocialByTaskId(Long taskId);
    List<Social> getAllSocials();
    Social saveSocial(Social social);
    void deleteSocial(Long id);
    
    
}
