
     <style>
  #preview{
    display: none;
  }
        #test .arc2 {
            stroke-weight:0.1;
            fill: #3660b0;
        }

        #outer {
            background:#FFFFFF;
            border-radius: 5px;
            color: #000;
        }

        #div1, #div2, #div3, #div4 {
            width: 33%;
            height: 200px;
            box-sizing: border-box;
            float: left;
        }

        #div2 .arc {
            stroke-weight: 0.1;
            fill: #f0a417;
        }

        #div2 .arc2 {
            stroke-weight: 0.1;
            fill: #b00d08;
        }

        #div3 .arc {
            stroke-weight: 0.1;
            fill: #1d871b;
        }


        .selectedRadial {
            border-radius: 3px;
            background: #f4f4f4;
            color: #000;
            box-shadow: 0 1px 5px rgba(0,0,0,0.4);
            -moz-box-shadow: 0 1px 5px rgba(0,0,0,0.4);
            border: 1px solid rgba(200,200,200,0.85);
        }

        .radial {
            border-radius: 3px;
            background: #FFFFFF;
            color: #000;

        }

.component {
    fill: #ededed;
}

.component .label {
    font-family: Myriad, "Helvetic Neue", Helvetica, Arial;
    text-anchor: middle;
    fill: #0000FF;
}

.arc {
    stroke-weight:0.1;
    fill: #4e8fff;
}


.arc2 {
    stroke-weight:0.1;
    fill: #3660b0;
}


.label {
    font-family:  Myriad, "Helvetic Neue", Helvetica, Arial;
    text-anchor: middle;
}

.radial-svg {
    display: block;
    margin: 0 auto;
}




    </style>
      <link type="text/css" rel="stylesheet" href="http://ajax.googleapis.com/ajax/libs/jqueryui/1.8/themes/base/jquery-ui.css">
  <link type="text/css" rel="stylesheet" href="${contextPath}/resources/src/css/graph.css">
  <link type="text/css" rel="stylesheet" href="${contextPath}/resources/src/css/detail.css">
  <link type="text/css" rel="stylesheet" href="${contextPath}/resources/src/css/legend.css">
  <link type="text/css" rel="stylesheet" href="${contextPath}/resources/src/extensions.css?v=2">


  
<body>
  <div class="wrapper">
  
  	<!-- <section class="col-xs-12  col-md-offset-1">
  		<img src="resources/images/iot2.jpg" style="float:left; height:100px;"/>
	</section>   -->

    <!--  <header>
      <nav class="navbar navbar-inverse navbar-fixed-top">
      <div class="container-fluid">
       
        <div>        	
          
        </div>
      </div>
    </nav>
  </header> -->

<!-- <section>
<nav class="cbp-spmenu cbp-spmenu-vertical cbp-spmenu-right" id="cbp-spmenu-s2">
  <h4>Equipments</h4>
  <label id="equipment">Internet of Things Demo</label>

    <section id="multiple" data-accordion-group>
      <section data-accordion>
        <button data-control>Accordion 1</button>
        <div data-content>
          <article>2nd Level</article>
          <article data-accordion>
            <button data-control>2nd Level</button>
            <div data-content>
              <article><a href="#">2.1</a></article>
              <article>2.2</article>
              <article>2.3</article>
              <article>2.4</article>
              <article>2.5</article>
            </div>
          </article>
          <article data-accordion>
            <button data-control>2nd Level</button>
            <div data-content>
              <article>3.1</article>
              <article>3.2</article>
              <article data-accordion>
                <button data-control>3rd Level</button>
                <div data-content>

                  <article>3.1</article>
                  <article>3.2</article>
                  <article>3.3</article>
                  <article>3.4</article>
                  <article>3.5</article>

                </div>
              </article>
              <article data-accordion>
                <button data-control>3rd Level</button>
                <div data-content>

                  <article>3.1</article>
                  <article>3.2</article>
                  <article>3.3</article>
                  <article>3.4</article>
                  <article>3.5</article>

                </div>
              </article>
            </div>
          </article>
          <article>2nd Level</article>
          <article>2nd Level</article>
          <article>2nd Level</article>
        </div>
      </section>

      <section data-accordion>
        <button data-control>Accordion 2</button>
        <div data-content class="border">
          <article data-accordion>
                <button data-control>3rd Level</button>
                <div data-content>
                  <article>3.1</article>
                  <article>3.2</article>
                  <article>3.3</article>
                  <article>3.4</article>
                  <article>3.5</article>
                </div>
              </article>
            </div>
            <div data-content>
          <article>Item</article>
          <article>Item</article>
          <article>Item</article>
          <article>Item</article>
          <article>Item</article>
        </div>
      </section>

    </section>

</nav>
<aside>
</aside>
</section> -->



  <!-- <section class="col-xs-12 col-md-10 col-md-offset-1"> <!- map container -->
   <!-- <div class="panel-group" id="accordion_mapcontainer" role="tablist" aria-multiselectable="true">
    <div class="panel panel-default">
      <div class="panel-heading" role="tab" id="heading_mapcontainer">
        <h4 class="panel-title">
          <a data-toggle="collapse" data-parent="#accordion_mapcontainer" href="#collapse_mapcontainer" aria-expanded="true" aria-controls="collapseOne">
            <span class="glyphicon glyphicon-minus pull-right" aria-hidden="true"></span> Map Visual
          </a>
        </h4>
      </div>
      <div id="collapse_mapcontainer" class="panel-collapse collapse in" role="tabpanel" aria-labelledby="heading_mapcontainer">
        <div class="panel-body">
          <div id="mapcontainer" style="position: relative; width: 800px; height: 350px; left:100px"></div>
        </div>
      </div>
    </div>
  </div> 
  </section>-->
  
<!-- <div class="col-xs-12 col-md-10 col-md-offset-1" id="container_chart"></div> -->


  <section> <!-- real data stream container -->
    <div id="chart_container">

<div id="content">

  <form id="side_panel">
    <h4>Performance Chart</h4>
    <section><div id="legend"></div></section>
    <section>
      <div id="renderer_form" class="toggler">
        <input type="radio" name="renderer" id="area" value="area" checked>
        <label for="area">area</label>
        <input type="radio" name="renderer" id="bar" value="bar">
        <label for="bar">bar</label>
        <input type="radio" name="renderer" id="line" value="line">
        <label for="line">line</label>
        <input type="radio" name="renderer" id="scatter" value="scatterplot">
        <label for="scatter">scatter</label>
      </div>
    </section>
    <section>
      <div id="offset_form">
        <label for="stack">
          <input type="radio" name="offset" id="stack" value="zero" checked>
          <span>stack</span>
        </label>
        <label for="stream">
          <input type="radio" name="offset" id="stream" value="wiggle">
          <span>stream</span>
        </label>
        <label for="pct">
          <input type="radio" name="offset" id="pct" value="expand">
          <span>pct</span>
        </label>
        <label for="value">
          <input type="radio" name="offset" id="value" value="value">
          <span>value</span>
        </label>
      </div>
      <div id="interpolation_form">
        <label for="cardinal">
          <input type="radio" name="interpolation" id="cardinal" value="cardinal" checked>
          <span>cardinal</span>
        </label>
        <label for="linear">
          <input type="radio" name="interpolation" id="linear" value="linear">
          <span>linear</span>
        </label>
        <label for="step">
          <input type="radio" name="interpolation" id="step" value="step-after">
          <span>step</span>
        </label>
      </div>
    </section>
    <!-- <section>
      <h6>Smoothing</h6>
      <div id="smoother"></div>
    </section> -->
    <section></section>
  </form>

  <div id="chart_container">
    <div id="chart"></div>
    <div id="timeline"></div>
    <div id="preview"></div>
  </div>

</div>

  </div>
    <!--  <div class="col-xs-12 col-md-1" id="container_tci"></div>  -->

  </section>

</div>

 <div id="container_menu"></div>
    <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
    <!-- Include all compiled plugins (below), or include individual files as needed -->
    <script src="resources/js/bootstrap.min.js"></script>

    <!-- d3.js (plotting equiments in map) -->
    <script src="https://cdnjs.cloudflare.com/ajax/libs/d3/3.5.3/d3.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/topojson/1.6.9/topojson.min.js"></script>
    <script src="resources/js/datamaps.world.min.js"></script>
    <script src="resources/js/datamap.js"></script>

    <!-- slidemenu -->
    <script src="resources/js/classie.js"></script>
    <script src="resources/js/radialProgress.js"></script>

    <script src="resources/js/custom.js"></script>
    <!-- <script src="js/multichart.js"></script> -->

<!-- <script src="js/d3.layout.js"></script> -->

<!-- <script src="vendor/d3.v3.js"></script> -->

 <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.6.2/jquery.min.js"></script>
  <script>
    jQuery.noConflict();
  </script>

  <script src="https://ajax.googleapis.com/ajax/libs/jqueryui/1.8.15/jquery-ui.min.js"></script>

  <script src="resources/src/js/Rickshaw.js"></script>
  <script src="resources/src/js/Rickshaw.Class.js"></script>
  <script src="resources/src/js/Rickshaw.Compat.ClassList.js"></script>
  <script src="resources/src/js/Rickshaw.Graph.js"></script>
  <script src="resources/src/js/Rickshaw.Graph.Renderer.js"></script>
  <script src="resources/src/js/Rickshaw.Graph.Renderer.Area.js"></script>
  <script src="resources/src/js/Rickshaw.Graph.Renderer.Line.js"></script>
  <script src="resources/src/js/Rickshaw.Graph.Renderer.Bar.js"></script>
  <script src="resources/src/js/Rickshaw.Graph.Renderer.ScatterPlot.js"></script>
  <script src="resources/src/js/Rickshaw.Graph.Renderer.Stack.js"></script>
  <script src="resources/src/js/Rickshaw.Graph.RangeSlider.js"></script>
  <script src="resources/src/js/Rickshaw.Graph.RangeSlider.Preview.js"></script>
  <script src="resources/src/js/Rickshaw.Graph.HoverDetail.js"></script>
  <script src="resources/src/js/Rickshaw.Graph.Annotate.js"></script>
  <script src="resources/src/js/Rickshaw.Graph.Legend.js"></script>
  <script src="resources/src/js/Rickshaw.Graph.Axis.Time.js"></script>
  <script src="resources/src/js/Rickshaw.Graph.Behavior.Series.Toggle.js"></script>
  <script src="resources/src/js/Rickshaw.Graph.Behavior.Series.Order.js"></script>
  <script src="resources/src/js/Rickshaw.Graph.Behavior.Series.Highlight.js"></script>
  <script src="resources/src/js/Rickshaw.Graph.Smoother.js"></script>
  <script src="resources/src/js/Rickshaw.Fixtures.Time.js"></script>
  <script src="resources/src/js/Rickshaw.Fixtures.Time.Local.js"></script>
  <script src="resources/src/js/Rickshaw.Fixtures.Number.js"></script>
  <script src="resources/src/js/Rickshaw.Fixtures.RandomData.js"></script>
  <script src="resources/src/js/Rickshaw.Fixtures.Color.js"></script>
  <script src="resources/src/js/Rickshaw.Color.Palette.js"></script>
  <script src="resources/src/js/Rickshaw.Graph.Axis.Y.js"></script>

  <script src="resources/js/extensions.js"></script>


<script>

// set up our data series with 150 random data points

/*var seriesData = [
                  [{"x":0,"y":53},{"x":5,"y":46},{"x":10,"y":51},{"x":15,"y":51},{"x":20,"y":53}],
                  [{"x":0,"y":24},{"x":5,"y":26},{"x":10,"y":33},{"x":15,"y":30},{"x":20,"y":36}],
                  [{"x":0,"y":18},{"x":5,"y":15},{"x":10,"y":9},{"x":15,"y":9},{"x":20,"y":5}],
                  [{"x":0,"y":72},{"x":5,"y":73},{"x":10,"y":65},{"x":15,"y":79},{"x":20,"y":73}]
                 ];*/
                 
var seriesData = [
                  [{"x":1,"y":53},{"x":2,"y":46},{"x":3,"y":51},{"x":4,"y":51},	{"x":5,"y":53},{"x":6,"y":41}, {"x":7,"y":51},{"x":8,"y":49},{"x":9,"y":46},{"x":10,"y":42}],
                  [{"x":1,"y":24},{"x":2,"y":26},{"x":3,"y":33},{"x":4,"y":30},{"x":5,"y":36},{"x":6,"y":34},  {"x":7,"y":30},{"x":8,"y":35},{"x":9,"y":28},{"x":10,"y":33}],
                  [{"x":1,"y":18},{"x":2,"y":15},{"x":3,"y":9},{"x":4,"y":9},{"x":5,"y":5},{"x":6,"y":5}, {"x":7,"y":8},{"x":8,"y":6},{"x":9,"y":20},{"x":10,"y":6}],
                  [{"x":1,"y":72},{"x":2,"y":73},{"x":3,"y":65},{"x":4,"y":79},{"x":5,"y":73},{"x":6,"y":75}, {"x":7,"y":78},{"x":8,"y":63},{"x":9,"y":79},{"x":10,"y":66}]
				 ];

var nameList = ["Random1","Random2","Random3","Random4"];


//var random = new Rickshaw.Fixtures.RandomData(100);
//console.log("random : ", random);
/* for (var i = 0; i < 100; i++) {
  random.addData(seriesData);
  //console.log("seriesData : ", seriesData);
} */

var palette = new Rickshaw.Color.Palette( { scheme: 'classic9' } );

// instantiate our graph!
var graph = new Rickshaw.Graph( {
  element: document.getElementById("chart"),
  width: 850,
  height: 540,
  renderer: 'area',
  stroke: true,
  preserve: true,
  series: [
    {
      color: '#CCFFFF',
      data: seriesData[0],
      name: nameList[0]
    }, {
      color: '#99CCFF',
      data: seriesData[1],
      name: nameList[1]
    }, {
      color: '#66CCCC',
      data: seriesData[2],
      name: nameList[2]
    }, {
      color: '#6699FF',
      data: seriesData[3],
      name: nameList[3]
    }/*, {
      color: palette.color(),
      data: seriesData[4],
      name: 'Item - 05'
    }, {
      color: palette.color(),
      data: seriesData[5],
      name: 'Item - 06'
    }, {
      color: palette.color(),
      data: seriesData[6],
      name: 'Item - 07'
    }, {
      color: palette.color(),
      data: seriesData[7],
      name: 'Item - 08'
    }, {
      color: palette.color(),
      data: seriesData[8],
      name: 'Item - 09'
    }
 */  ]
} );

graph.render();

var preview = new Rickshaw.Graph.RangeSlider( {
  graph: graph,
  element: document.getElementById('preview'),
} );

var hoverDetail = new Rickshaw.Graph.HoverDetail( {
  graph: graph,
  xFormatter: function(x) {
	  return x*30;
  }
} );

var annotator = new Rickshaw.Graph.Annotate( {
  graph: graph,
  element: document.getElementById('timeline')
} );

var legend = new Rickshaw.Graph.Legend( {
  graph: graph,
  element: document.getElementById('legend')

} );

var shelving = new Rickshaw.Graph.Behavior.Series.Toggle( {
  graph: graph,
  legend: legend
} );

var order = new Rickshaw.Graph.Behavior.Series.Order( {
  graph: graph,
  legend: legend
} );

var highlighter = new Rickshaw.Graph.Behavior.Series.Highlight( {
  graph: graph,
  legend: legend
} );

var smoother = new Rickshaw.Graph.Smoother( {
  graph: graph,
  element: document.querySelector('#smoother')
} );

var ticksTreatment = 'glow';
var xAxis = new Rickshaw.Graph.Axis.Time( {
  graph: graph,
  ticksTreatment: ticksTreatment,
  timeFixture: new Rickshaw.Fixtures.Time.Local()
} );

xAxis.render();

var yAxis = new Rickshaw.Graph.Axis.Y( {
  graph: graph,
  tickFormat: Rickshaw.Fixtures.Number.formatKMBT,
  ticksTreatment: ticksTreatment
} );

yAxis.render();


var controls = new RenderControls( {
  element: document.querySelector('form'),
  graph: graph
} );


var messages = [];

setInterval( function() { 
	
	updateIotData();
	
  	graph.series[0].data = seriesData[0];
  	graph.series[1].data = seriesData[1];
  	graph.series[2].data = seriesData[2];
  	graph.series[3].data = seriesData[3];
  	
  	graph.series[0].name = nameList[0];
  	graph.series[1].name = nameList[1];
  	graph.series[2].name = nameList[2];
  	graph.series[3].name = nameList[3];
  	
  	graph.update();
  	
}, 5000 );

function updateIotData(){

	$.getJSON("${contextPath}/json/iotDataList", {name: "iot", time: new Date().getTime()},		
		function(data) {	        
	        for (var i in data) {
	            //alert('update: '+data[i].xyDataList[i].y)
	        	seriesData.shift();
	            seriesData.push(data[i].xyDataList);
	            nameList.shift();
	            nameList.push(data[i].tagName);
	        }		        
		}
	);
}

function addAnnotation(force) {
  if (messages.length > 0 && (force || Math.random() >= 0.95)) {
    annotator.add(seriesData[2][seriesData[2].length-1].x, messages.shift());
    annotator.update();
  }
}

addAnnotation(true);
setTimeout( function() { setInterval( addAnnotation, 6000 ) }, 6000 );

var previewXAxis = new Rickshaw.Graph.Axis.Time({
  graph: preview.previews[0],
  timeFixture: new Rickshaw.Fixtures.Time.Local(),
  ticksTreatment: ticksTreatment
});

previewXAxis.render();

</script>

    <script type="text/javascript" src="js/jquery.accordion.js"></script>
    <script type="text/javascript">
      $(function() {
        $('#multiple [data-accordion]').accordion({
          singleOpen: false
        });
      });
    </script>

  </body>

