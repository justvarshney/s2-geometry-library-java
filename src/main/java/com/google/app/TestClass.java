package com.google.app;

import java.util.List;

import com.google.common.base.Splitter;
import com.google.common.collect.Lists;
import com.google.common.geometry.S2LatLng;
import com.google.common.geometry.S2Loop;
import com.google.common.geometry.S2Point;
import com.google.common.geometry.S2Polygon;

public class TestClass {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		 final String FAR1 =
			      "0:179, -1:179, 1:180, -1:-179, 0:-179, 3:-178, 2:-180, 3:178;";
		 S2Polygon b = makePolygon(FAR1);
		 System.out.println(b);
		 System.out.println(b.getNumVertices());
		 
	}
	

	  static S2Loop makeLoop(String str) {
	    List<S2Point> vertices = Lists.newArrayList();
	    parseVertices(str, vertices);
	    return new S2Loop(vertices);
	  }

	  static S2Polygon makePolygon(String str) {
	    List<S2Loop> loops = Lists.newArrayList();

	    for (String token : Splitter.on(';').omitEmptyStrings().split(str)) {
	      S2Loop loop = makeLoop(token);
	      loop.normalize();
	      loops.add(loop);
	    }

	    return new S2Polygon(loops);
	  }
	  static void parseVertices(String str, List<S2Point> vertices) {
		    if (str == null) {
		      return;
		    }

		    for (String token : Splitter.on(',').split(str)) {
		      int colon = token.indexOf(':');
		      if (colon == -1) {
		        throw new IllegalArgumentException(
		            "Illegal string:" + token + ". Should look like '35:20'");
		      }
		      double lat = Double.parseDouble(token.substring(0, colon));
		      double lng = Double.parseDouble(token.substring(colon + 1));
		      vertices.add(S2LatLng.fromDegrees(lat, lng).toPoint());
		    }
		  }

}
