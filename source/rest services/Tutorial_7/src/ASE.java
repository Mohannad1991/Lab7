import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

@Path("/ASEservice")
public class ASE {
	@GET
	@Produces("application/json")
	public Response sample() throws JSONException {
		String result = "";
		return Response.status(200).entity(result).build();
	  }
	@Path("{city}")
	  @GET
	  @Produces("application/json")
	  public Response Theatres(@PathParam("city") String city) throws JSONException, IOException  {
		String city1 = city.replaceAll(" ", "%20");
		CheckInputValues c1 = new CheckInputValues();
		String url;
		if(c1.Check(city1) && c1.CheckIfString(city1)){
			url = "https://api.foursquare.com/v2/venues/search?near=" + city1 + "&categoryId=4bf58dd8d48988d17f941735&client_id=CA1CJQMHJQ1I3X4S1RM1021QJMGBPZZGIAKJC0RSYQSD4BUD&client_secret=X2VKR3PKKX0WLLQYM0AQJLFFFTHLR2I3WTZABTHPVZJNKJP2&v=20160222&limit=10";
		}
		
		else{
			url = "";
		}
		
		URL url5 = new URL(url);
		  URLConnection urlc = url5.openConnection();
		  urlc.setDoOutput(true);
	      urlc.setAllowUserInteraction(false);
	      BufferedReader br = new BufferedReader(new InputStreamReader(urlc
	              .getInputStream()));
	          String line = null;
	          StringBuilder sb = new StringBuilder();
	          while ((line=br.readLine())!=null) {
	              sb.append(line);
	          }
		
	          JSONObject data = new JSONObject(sb.toString());
	          JSONObject response = data.getJSONObject("response");
	          JSONArray venues = response.getJSONArray("venues");
	          int length = venues.length();
	          String[] name = new String[10];
	          Float [] Lat = new Float[10];
	          Float [] lon = new Float[10];
	          for(int i=0;i<length;i++){
	          	JSONObject fir = venues.getJSONObject(i);
	              name[i] = fir.getString("name");
	              JSONObject js = new JSONObject();
	              js = fir.getJSONObject("location");
	              Lat[i] = (float)js.getDouble("lat");
	             
	              lon[i] = (float) js.getDouble("lng");
//	              System.out.println(lon[i]);
	              name[i] = fir.getString("name");
	          }
	          String[] dist = new String[10];
	          String[] time = new String[10];
	          int c=0;
	          for(int i=0; i<5;i++){
	        	  String DistanceURL = "https://maps.googleapis.com/maps/api/distancematrix/json?units=imperial&origins=39.0335,-94.5756&destinations="
		  					+ Lat[i] + "," + lon[i] + "|" + Lat[i+1] + "," + lon[i+1];
	        	  URL url6 = new URL(DistanceURL);
				  URLConnection urlc1 = url6.openConnection();
				  urlc1.setDoOutput(true);
			      urlc1.setAllowUserInteraction(false);
			      BufferedReader br1 = new BufferedReader(new InputStreamReader(urlc1
			              .getInputStream()));
			          String l5 = null;
			          StringBuilder sb1 = new StringBuilder();
			          while ((l5=br1.readLine())!=null) {
			          	
			              sb1.append(l5);
			          }
			          JSONObject data5 = new JSONObject(sb1.toString());
			          
			          
			         
			          JSONArray rows = data5.getJSONArray("rows");
			          
			          for (int j = 0; j < rows.length(); j++) { // Loop over each each row
			              
			              JSONObject row = rows.getJSONObject(j); // Get row object
			              JSONArray elements = row.getJSONArray("elements"); // Get all elements for each row as an array
			              for (int k = 0; k < elements.length(); k++) { // Iterate each element in the elements array
			                  
			                  JSONObject element = elements.getJSONObject(k); // Get the element object
			                  JSONObject distance = element.getJSONObject("distance");
			                  dist[c] = distance.getString("text");// Get distance sub object
			                  JSONObject duration = element.getJSONObject("duration");
			                  time[c] = duration.getString("text");
			                  System.out.println(i+k);
			                  System.out.println(time[i+k]);
			                  c++;
			              }

			          }
//			          System.out.println("2");
//			          JSONArray elements = rows.getJSONArray(0);
//			          System.out.println("3");
//			          JSONArray first = elements.getJSONArray(0);
//			          System.out.println("4");
//			          
//			          JSONObject distance = first.getJSONObject("distance");
//			          System.out.println("5");
//			          dist[i] = distance.getString("text");
//			          System.out.println("6");
//			          JSONObject duration = first.getJSONObject("duration");
//			          System.out.println("7");
//			          time[i] = duration.getString("text");
//			          System.out.println("8");
//			          
//			          JSONObject second = elements.getJSONObject("");
//			          System.out.println("9");
//			          
//			          JSONObject distance1 = second.getJSONObject("distance");
//			          dist[i+1] = distance1.getString("text");
//			          JSONObject duration1 = second.getJSONObject("duration");
//			          time[i+1] = duration1.getString("text");
//			          System.out.println(time[i]);
			          
			          
			          
	          }
//	          String DistanceURL = "https://maps.googleapis.com/maps/api/distancematrix/json?units=imperial&origins=39.0335,-94.5756&destinations="
//	        		  					+ Lat[0] + "," + lon[0] + "|" + Lat[1] + "," + lon[1]
//	        		  				    + Lat[2] + "," + lon[2] + "|" + Lat[3] + "," + lon[3]
//	        		  				    + Lat[4] + "," + lon[4] + "|" + Lat[5] + "," + lon[5]
//	        		  				    + Lat[6] + "," + lon[6] + "|" + Lat[7] + "," + lon[7]
//	        		  				    + Lat[8] + "," + lon[8] + "|" + Lat[9] + "," + lon[9];
//	          URL url6 = new URL(DistanceURL);
//			  URLConnection urlc1 = url6.openConnection();
//			  urlc1.setDoOutput(true);
//		      urlc1.setAllowUserInteraction(false);
//		      BufferedReader br1 = new BufferedReader(new InputStreamReader(urlc1
//		              .getInputStream()));
//		          String l5 = null;
//		          StringBuilder sb1 = new StringBuilder();
//		          while ((l5=br1.readLine())!=null) {
//		          	System.out.println("API 2");
//		              System.out.println(l5);
//		              sb1.append(l5);
//		          }
	          
	          JSONArray jsonArray5 = new JSONArray();
	          for(int i=0;i<10;i++){
	        	  JSONObject js = new JSONObject();
	        	  js.put("Name", name[i]);
	        	  js.put("Distance", dist[i]);
	        	  js.put("Time", time[i]);
	        	  jsonArray5.put(js);
	          }
	          JSONObject js1 = new JSONObject();
	          js1.put("Theatres Distance from UMKC", jsonArray5);
		String result = "" + js1;
		return Response.status(200).entity(result).build();
	}
}