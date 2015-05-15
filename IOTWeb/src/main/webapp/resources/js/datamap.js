
var map = new Datamap({
    element: document.getElementById('mapcontainer'),
    done: function(datamap) {
          datamap.svg.selectAll('.datamaps-subunit').on('click', function(geography){
            console.log(geography.properties.name);
          });  
        },
        
    scope: 'world',
    geographyConfig: {
        popupOnHover: true,
        highlightOnHover: true
    },
    fills: {
        /*'USA': '#1f77b4',
        'RUS': '#9467bd',
        'PRK': '#ff7f0e',
        'PRC': '#2ca02c',
        'IND': '#e377c2',
        'GBR': '#8c564b',
        'FRA': '#d62728',
        'PAK': '#7f7f7f',*/
        defaultFill: '#b9b9b9'
    },
    data: {
        /*'RUS': {fillKey: 'RUS'},
        'PRK': {fillKey: 'PRK'},
        'PRC': {fillKey: 'PRC'},
        'IND': {fillKey: 'IND'},
        'GBR': {fillKey: 'GBR'},
        'FRA': {fillKey: 'FRA'},
        'PAK': {fillKey: 'PAK'},
        'USA': {fillKey: 'USA'}*/
    }
});

function plotMap(data){

  map.bubbles(data, {
      popupTemplate: function (geo, data) { 
      return ['<div class="hoverinfo"><b>' +  data.name,
      '<br/>performance: ' +  data.performance,
      '<br/>Country: ' +  data.country + '',
      '<br/>Date: ' +  data.date + '',
      '</div>'].join('');
    }
  }),
 /* map.bubbles(data)*/

  /*map.svg.selectAll('.bubbles').on('click', function(d,i) {
    console.log(this.childNodes);
      
  });*/
  map.svg.selectAll('.datamaps-bubble')
      .data(data)
      .style("fill", "red")
      .on("click", function(data){
       //console.log(data.name);

        var menuRight = document.getElementById( 'cbp-spmenu-s2' ),
        body = document.body;
        document.getElementById( 'equipment' ).innerHTML = data.name; 

        classie.toggle( this, 'active' );
        classie.toggle( menuRight, 'cbp-spmenu-open' );
        disableOther( 'showRight' );

        function disableOther( button ) {
        if( button !== 'showRight' ) {
          classie.toggle( showRight, 'disabled' );
        }
      }
    });
};




function geoMap(){
  d3.json("js/bigdata.json", function(data){
    //console.log(data);
    plotMap(data);
  })
}
  

//Event binding to the bubble;
$(function(){
  geoMap();
});

d3.select(window).on('resize', function() {
  map.resize();
});


/*
  function geoMap(){
  $.ajax({
          url: "js/bigdata.json",
          type: 'POST',
          dataType: 'json',
          beforeSend: function() {
              console.log("requesting...");
          },
          success: function(data) {
            //console.log("data loaded!!!", data);
                
          },
          error: function(xhr, textStatus, errorThrown) {
            console.log("error...", xhr, textStatus, errorThrown);
             // $('#'+id+' .contentarea').html(textStatus);
          }
      })
      
}*/


