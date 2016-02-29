package com.freetmp.investigate.spring.jersey1;

import com.freetmp.investigate.persistence.querydsl.UradioPlanarGraph;
import com.investigate.spring.jersey1.RestService;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.core.util.MultivaluedMapImpl;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.ws.rs.core.MultivaluedMap;

/**
 * RestService Tester.
 *
 * @author liupin
 * @version 1.0
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:application-context.xml")
public class RestServiceTest {

    private static final String restUri = "http://localhost:9998/rest";

    @Autowired
    RestService restService;

    @Before
    public void before() throws Exception {
    }

    @After
    public void after() throws Exception {
        restService.stop();
    }


    @Test
    public void login() {
        Client client = Client.create();
        WebResource resource = client.resource(restUri + "/login");
        MultivaluedMap params = new MultivaluedMapImpl();
        params.add("username","WYL_SH_");
        params.add("password","4315510");
        UradioPlanarGraph uradioPlanarGraph = resource.queryParams(params).get(UradioPlanarGraph.class);
        System.out.println(uradioPlanarGraph);
    }

} 
