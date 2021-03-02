package workTime.main.java;

import javax.ws.rs.GET;
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