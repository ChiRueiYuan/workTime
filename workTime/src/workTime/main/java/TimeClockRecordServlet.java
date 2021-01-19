package workTime.main.java;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

@Path("/timeClockRecords")
public class TimeClockRecordServlet {
 
  @GET
  public String getPaginationByQuery() {
    return "getPaginationByQuery";
  }
 
  @GET
  @Path("/{id}")
  public String getById(@PathParam("id") int id) {
      return "getById:"+id;
  }
}