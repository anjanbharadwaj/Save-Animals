



var database = firebase.database();

console.log(database)

var map;
var markers = [];
var baldeagle;
var butterfly;
var seaotter;
var vole;
var kitfox;
var wolverine;
var beaver;
var peregrine;
var redlegfrog;
var californiacondor;
function initMap() {
  map = new google.maps.Map(document.getElementById('map'), {
    zoom: 10,
    center: new google.maps.LatLng(37, -122),
    mapTypeId: 'roadmap'
  });

butterfly = new google.maps.MarkerImage(
  'icons/butterfly.png',
  new google.maps.Size(10,10), //size
  null, //origin
  null, //anchor
  new google.maps.Size(10,10) //scale
);
seaotter = new google.maps.MarkerImage(
  'icons/sea_otter.png',
  new google.maps.Size(10,10), //size
  null, //origin
  null, //anchor
  new google.maps.Size(10,10) //scale
);
vole = new google.maps.MarkerImage(
  'icons/vole.png',
  new google.maps.Size(10,10), //size
  null, //origin
  null, //anchor
  new google.maps.Size(10,10) //scale
);
kitfox = new google.maps.MarkerImage(
  'icons/kitfox.png',
  new google.maps.Size(10,10), //size
  null, //origin
  null, //anchor
  new google.maps.Size(10,10) //scale
);
wolverine = new google.maps.MarkerImage(
  'icons/wolverine.png',
  new google.maps.Size(8,8), //size
  null, //origin
  null, //anchor
  new google.maps.Size(8,8) //scale
);
beaver = new google.maps.MarkerImage(
  'icons/beaver.png',
  new google.maps.Size(8,8), //size
  null, //origin
  null, //anchor
  new google.maps.Size(8,8) //scale
);
peregrine = new google.maps.MarkerImage(
  'icons/peregrine.png',
  new google.maps.Size(10,10), //size
  null, //origin
  null, //anchor
  new google.maps.Size(10,10) //scale
);
baldeagle = new google.maps.MarkerImage(
  'icons/baldeagle.png',
  new google.maps.Size(10,10), //size
  null, //origin
  null, //anchor
  new google.maps.Size(10,10) //scale
);
redlegfrog = new google.maps.MarkerImage(
  'icons/red_leg_frog.png',
  new google.maps.Size(10,10), //size
  null, //origin
  null, //anchor
  new google.maps.Size(10,10) //scale
);
californiacondor = new google.maps.MarkerImage(
  'icons/cali_condor.png',
  new google.maps.Size(10,10), //size
  null, //origin
  null, //anchor
  new google.maps.Size(10,10) //scale
);

// var butterflymarker = new google.maps.Marker({
//   position: new google.maps.LatLng(38.0007, -98),
//   map: map,
//   icon: butterfly //set the markers icon to the MarkerImage
// });
// var seaottermarker = new google.maps.Marker({
//   position: new google.maps.LatLng(38.0006, -98),
//   map: map,
//   icon: seaotter //set the markers icon to the MarkerImage
// });
// var volemarker = new google.maps.Marker({
//   position: new google.maps.LatLng(38.0009, -98),
//   map: map,
//   icon: vole //set the markers icon to the MarkerImage
// });
// var kitfoxmarker = new google.maps.Marker({
//   position: new google.maps.LatLng(38, -98),
//   map: map,
//   icon: kitfox //set the markers icon to the MarkerImage
// });
// var wolverinemarker = new google.maps.Marker({
//   position: new google.maps.LatLng(38.0003, -98),
//   map: map,
//   icon: wolverine //set the markers icon to the MarkerImage
// });
// var beavermarker = new google.maps.Marker({
//   position: new google.maps.LatLng(38.0004, -98),
//   map: map,
//   icon: beaver //set the markers icon to the MarkerImage
// });
// var peregrinemarker = new google.maps.Marker({
//   position: new google.maps.LatLng(38, -98.0002),
//   map: map,
//   icon: peregrine //set the markers icon to the MarkerImage
// });
// var baldeaglemarker = new google.maps.Marker({
//   position: new google.maps.LatLng(38, -98.0001),
//   map: map,
//   icon: baldeagle //set the markers icon to the MarkerImage
// });
// var redlegfrogmarker = new google.maps.Marker({
//   position: new google.maps.LatLng(38, -98.0002),
//   map: map,
//   icon: redlegfrog //set the markers icon to the MarkerImage
// });
// var californiacondormarker = new google.maps.Marker({
//   position: new google.maps.LatLng(38, -98.0001),
//   map: map,
//   icon: californiacondor //set the markers icon to the MarkerImage
// });
//
// console.log(butterflymarker);
// markers.push(butterflymarker);
// markers.push(seaottermarker);
// markers.push(volemarker);
// markers.push(kitfoxmarker);
// markers.push(wolverinemarker);
// markers.push(beavermarker);
// markers.push(peregrinemarker);
// markers.push(baldeaglemarker);
// markers.push(redlegfrogmarker);
// markers.push(californiacondormarker);

console.log("HI")
map.addListener('zoom_changed', function() {
  for (var i=0, len = markers.length; i < len; i++) {
      // set new icon depending on the value of map.getZoom()
      marker = markers[i];
      var pixelSizeAtZoom0 = 1; //the size of the icon at zoom level 0
      var maxPixelSize = 100; //restricts the maximum size of the icon, otherwise the browser will choke at higher zoom levels trying to scale an image to millions of pixels

      var zoom = map.getZoom();
      var relativePixelSize = Math.round(pixelSizeAtZoom0*Math.pow(1.25,zoom)); // use 2 to the power of current zoom to calculate relative pixel size.  Base of exponent is 2 because relative size should double every time you zoom in
      console.log("Marker: " + marker.getIcon().url + ", Rel Pizel Size: " + relativePixelSize + ", Zoom: " + zoom)

      if(relativePixelSize > maxPixelSize) relativePixelSize = maxPixelSize;
marker.setIcon(
new google.maps.MarkerImage(
 marker.getIcon().url, //marker's same icon graphic
 null,//size
 null,//origin
 null, //anchor
 new google.maps.Size(relativePixelSize, relativePixelSize) //changes the scale
));

  }

})
}
database.ref().child('/Bald Eagle/Location').on("value", function(snapshot) {
  //var bald_eagle_time = snapshot.child('/Bald Eagle/Location')
  snapshot.forEach(function(child) {
    var time = child.key
    var latitude = child.val().toString().split(",")[0]
    var longitude = child.val().toString().split(",")[1]

    var marker = new google.maps.Marker({
      position: new google.maps.LatLng(parseFloat(latitude), parseFloat(longitude)),
      map: map,
      icon: baldeagle //set the markers icon to the MarkerImage
    });
    markers.push(marker)
    console.log(child.key+": "+longitude + ", " + latitude);


  });
});
database.ref().child('/Beaver/Location').on("value", function(snapshot) {
  //var bald_eagle_time = snapshot.child('/Bald Eagle/Location')
  snapshot.forEach(function(child) {
    var time = child.key
    var latitude = child.val().toString().split(",")[0]
    var longitude = child.val().toString().split(",")[1]

    var marker = new google.maps.Marker({
      position: new google.maps.LatLng(parseFloat(latitude), parseFloat(longitude)),
      map: map,
      icon:  beaver //set the markers icon to the MarkerImage
    });
    markers.push(marker)
    console.log("BEAVER: " + child.key+": "+longitude + ", " + latitude);


  });
});
database.ref().child('/Blue Butterfly/Location').on("value", function(snapshot) {
  //var bald_eagle_time = snapshot.child('/Bald Eagle/Location')
  snapshot.forEach(function(child) {
    var time = child.key
    var latitude = child.val().toString().split(",")[0]
    var longitude = child.val().toString().split(",")[1]

    var marker = new google.maps.Marker({
      position: new google.maps.LatLng(parseFloat(latitude), parseFloat(longitude)),
      map: map,
      icon: butterfly //set the markers icon to the MarkerImage
    });
    markers.push(marker)
    console.log(child.key+": "+longitude + ", " + latitude);


  });
});
database.ref().child('/California Condor/Location').on("value", function(snapshot) {
  //var bald_eagle_time = snapshot.child('/Bald Eagle/Location')
  snapshot.forEach(function(child) {
    var time = child.key
    var latitude = child.val().toString().split(",")[0]
    var longitude = child.val().toString().split(",")[1]

    var marker = new google.maps.Marker({
      position: new google.maps.LatLng(parseFloat(latitude), parseFloat(longitude)),
      map: map,
      icon: californiacondor //set the markers icon to the MarkerImage
    });
    markers.push(marker)
    console.log(child.key+": "+longitude + ", " + latitude);


  });
});
database.ref().child('/California Vole/Location').on("value", function(snapshot) {
  //var bald_eagle_time = snapshot.child('/Bald Eagle/Location')
  snapshot.forEach(function(child) {
    var time = child.key
    var latitude = child.val().toString().split(",")[0]
    var longitude = child.val().toString().split(",")[1]

    var marker = new google.maps.Marker({
      position: new google.maps.LatLng(parseFloat(latitude), parseFloat(longitude)),
      map: map,
      icon: vole //set the markers icon to the MarkerImage
    });
    markers.push(marker)
    console.log(child.key+": "+longitude + ", " + latitude);


  });
});
database.ref().child('/Kit Fox/Location').on("value", function(snapshot) {
  //var bald_eagle_time = snapshot.child('/Bald Eagle/Location')
  snapshot.forEach(function(child) {
    var time = child.key
    var latitude = child.val().toString().split(",")[0]
    var longitude = child.val().toString().split(",")[1]

    var marker = new google.maps.Marker({
      position: new google.maps.LatLng(parseFloat(latitude), parseFloat(longitude)),
      map: map,
      icon: kitfox //set the markers icon to the MarkerImage
    });
    markers.push(marker)
    console.log(child.key+": "+longitude + ", " + latitude);


  });
});
database.ref().child('/Peregrine Falcon/Location').on("value", function(snapshot) {
  //var bald_eagle_time = snapshot.child('/Bald Eagle/Location')
  snapshot.forEach(function(child) {
    var time = child.key
    var latitude = child.val().toString().split(",")[0]
    var longitude = child.val().toString().split(",")[1]

    var marker = new google.maps.Marker({
      position: new google.maps.LatLng(parseFloat(latitude), parseFloat(longitude)),
      map: map,
      icon: peregrine //set the markers icon to the MarkerImage
    });
    markers.push(marker)
    console.log(child.key+": "+longitude + ", " + latitude);


  });
});
database.ref().child('/Red-Legged Frog/Location').on("value", function(snapshot) {
  //var bald_eagle_time = snapshot.child('/Bald Eagle/Location')
  snapshot.forEach(function(child) {
    var time = child.key
    var latitude = child.val().toString().split(",")[0]
    var longitude = child.val().toString().split(",")[1]

    var marker = new google.maps.Marker({
      position: new google.maps.LatLng(parseFloat(latitude), parseFloat(longitude)),
      map: map,
      icon: redlegfrog //set the markers icon to the MarkerImage
    });
    markers.push(marker)
    console.log(child.key+": "+longitude + ", " + latitude);


  });
});
database.ref().child('/Sea Otter/Location').on("value", function(snapshot) {
  //var bald_eagle_time = snapshot.child('/Bald Eagle/Location')
  snapshot.forEach(function(child) {
    var time = child.key
    var latitude = child.val().toString().split(",")[0]
    var longitude = child.val().toString().split(",")[1]

    var marker = new google.maps.Marker({
      position: new google.maps.LatLng(parseFloat(latitude), parseFloat(longitude)),
      map: map,
      icon: seaotter //set the markers icon to the MarkerImage
    });
    markers.push(marker)
    console.log(child.key+": "+longitude + ", " + latitude);


  });
});
database.ref().child('/Wolverine Animal/Location').on("value", function(snapshot) {
  //var bald_eagle_time = snapshot.child('/Bald Eagle/Location')
  snapshot.forEach(function(child) {
    var time = child.key
    var latitude = child.val().toString().split(",")[0]
    var longitude = child.val().toString().split(",")[1]

    var marker = new google.maps.Marker({
      position: new google.maps.LatLng(parseFloat(latitude), parseFloat(longitude)),
      map: map,
      icon: wolverine //set the markers icon to the MarkerImage
    });
    markers.push(marker)
    console.log(child.key+": "+longitude + ", " + latitude);


  });
});
