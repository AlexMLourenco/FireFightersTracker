/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.KafkaListenerService;

import org.springframework.data.repository.CrudRepository;


import java.util.List;

/**
 *
 * @author manuel
 */
public interface RepositoryGPS extends CrudRepository<FighterGPS,Integer>{   
    
    List<FighterGPS> findByName(String name);
}
