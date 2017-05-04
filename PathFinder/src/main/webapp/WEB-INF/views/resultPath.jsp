<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>
<link rel="stylesheet"
	href="https://unpkg.com/leaflet@1.0.3/dist/leaflet.css" />
<script src="https://unpkg.com/leaflet@1.0.3/dist/leaflet.js"></script>

<t:template>

	<jsp:attribute name="header">
        <%@include file="../components/navbar.jsp"%>
    </jsp:attribute>
	<jsp:attribute name="footer">
        <div id="pagefooter" class="row">
            <%@include file="../components/footer.jsp"%>
        </div>
    </jsp:attribute>

	<jsp:body>
	    <div>
	    	<h1>Miglior percorso trovato</h1>
	    	<div id="mapid" style="width: 100%; height: 400px;"></div>

            <script>
            	var srcDstPoints = ${srcDstPoints};
            	var path = ${path};
            	
            	var lineStyle = {
            		    "color": "#ff7800",
            		    "weight": 5,
            		    "opacity": 0.65
            		};
            	
            	function onEachFeature(feature, layer) {
                    // does this feature have a property named popupContent?
                    if (feature.properties) {
                        var content = "<p>" + feature.properties.popupContent + "</p>" ;
                        layer.bindPopup(content);
                    }
                }

                // Create the map and set the view on Turin
                var mymap = L.map('mapid').setView([45.064, 7.681], 13);

				L.tileLayer('https://api.tiles.mapbox.com/v4/{id}/{z}/{x}/{y}.png?access_token=pk.eyJ1IjoibWFwYm94IiwiYSI6ImNpejY4NXVycTA2emYycXBndHRqcmZ3N3gifQ.rJcFIG214AriISLbB6B5aw', {
		    		maxZoom: 18,
		    		attribution: 'Map data &copy; <a href="http://openstreetmap.org">OpenStreetMap</a> contributors, ' +
		    			'<a href="http://creativecommons.org/licenses/by-sa/2.0/">CC-BY-SA</a>, ' +
		    			'Imagery © <a href="http://mapbox.com">Mapbox</a>',
		    		id: 'mapbox.streets'
		    	}).addTo(mymap);

                // Create the points
                var stopsLayer = L.geoJSON(srcDstPoints)
                stopsLayer.addTo(mymap);
                
                // Draw the paths
                var pathLayer = L.geoJSON(path, {
                    style: lineStyle,
                    onEachFeature: onEachFeature
                }).addTo(mymap);
                pathLayer.addTo(mymap);

                mymap.fitBounds(layer.getBounds())
            </script>
        </div>
    </jsp:body>

</t:template>
