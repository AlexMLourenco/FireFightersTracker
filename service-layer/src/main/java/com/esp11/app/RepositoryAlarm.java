/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esp11.app;

import org.springframework.data.repository.CrudRepository;
/**
 *
 * @author manuel
 */
public interface RepositoryAlarm extends CrudRepository<alarm,Integer>{ 
    
}
