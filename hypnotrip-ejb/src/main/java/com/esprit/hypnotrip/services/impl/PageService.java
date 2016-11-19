package com.esprit.hypnotrip.services.impl;

import javax.ejb.Stateless;

import com.esprit.hypnotrip.services.interfaces.PageServiceLocal;
import com.esprit.hypnotrip.services.interfaces.PageServiceRemote;

/**
 * Session Bean implementation class PageService
 */
@Stateless
public class PageService implements PageServiceRemote, PageServiceLocal {

    /**
     * Default constructor. 
     */
    public PageService() {
          
    }

}
