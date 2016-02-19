package com.investigate.spring.jersey1;

import com.freetmp.investigate.persistence.querydsl.QUradioPlanarGraph;
import com.freetmp.investigate.persistence.querydsl.UradioPlanarGraph;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;

/**
 * Created by LiuPin on 2015/4/7.
 */
@Component
@Path("/login")
public class AuthenticationResource {

  @Autowired
  QueryDSL dsl;

  @GET
  @Transactional(readOnly = true)
  @Produces(APPLICATION_JSON)
  public UradioPlanarGraph login(@QueryParam("username") String username,
                                 @QueryParam("password") String password) {

    if (!StringUtils.isEmpty(username) && !StringUtils.isEmpty(password)) {
      QUradioPlanarGraph uradioPlanarGraph = QUradioPlanarGraph.uradioPlanarGraph;
      UradioPlanarGraph found;
      try {
        found = dsl.query().from(uradioPlanarGraph)
            .where(uradioPlanarGraph.id.eq(username.toUpperCase() + password))
            .uniqueResult(uradioPlanarGraph);
      } finally {
        dsl.release();
      }

      if (found != null) return found;
    }

    return null;
  }
}
