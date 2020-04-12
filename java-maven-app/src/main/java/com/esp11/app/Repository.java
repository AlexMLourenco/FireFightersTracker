/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esp11.app;

/**
 *
 * @author manuel
 */
import org.springframework.data.repository.CrudRepository;

import com.esp11.app.FireFighter;
/**
 *
 * @author manuel
 */
public interface Repository extends CrudRepository<FireFighter,Integer>{   
}