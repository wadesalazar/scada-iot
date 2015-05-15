var margin = {top: 20, right: 80, bottom: 30, left: 50},
width = 700, height = 340;
    /*width = 960 - margin.left - margin.right,
    height = 500 - margin.top - margin.bottom;*/

var parseDate = d3.time.format("%Y%m%d").parse;



var x = d3.time.scale()
    .range([0, width]);

var y = d3.scale.linear()
    .range([height, 0]);

var color = d3.scale.category10();

var xAxis = d3.svg.axis()
    .scale(x)
    .orient("bottom")
    .tickFormat(d3.time.format("%m"));
    

var yAxis = d3.svg.axis()
    .scale(y)
    .orient("left");

var line = d3.svg.line()
    .interpolate("basis")
    .x(function(d) { return x(d.timestamp); })
    .y(function(d) { return y(d.Performance); });

var chart = document.getElementById('container_chart');
var svg = d3.select(chart).append("svg")
    .attr("width", width + margin.left + margin.right)
    .attr("height", height + margin.top + margin.bottom)
    .append("g")
    .attr("transform", "translate(" + margin.left + "," + margin.top + ")");

d3.tsv("js/data.tsv", function(error, data) {
  color.domain(d3.keys(data[0]).filter(function(key) { return key !== "timestamp"; }));

  data.forEach(function(d) {
    d.timestamp = parseDate(d.timestamp);
    //console.log(d.timestamp);
  });

  var equipments = color.domain().map(function(name) {
    return {
      name: name,
      values: data.map(function(d) {
        //console.log("name : " , name);
        return {timestamp: d.timestamp, Performance: +d[name]};
      })
    };
  });

  x.domain(d3.extent(data, function(d) { return d.timestamp; }));
  /*x.domain([
    d3.min(equipments, function(c) { return 0;});
    d3.max(equipments, function(c) { return 5;});
    ]);*/
  y.domain([
    d3.min(equipments, function(c) { 
      
      return d3.min(c.values, function(v) { return v.Performance; }); 
    }),
    d3.max(equipments, function(c) { return d3.max(c.values, function(v) { return v.Performance; }); })
  ]);

  svg.append("g")
      .attr("class", "x axis")
      .attr("transform", "translate(0," + height + ")")
      .call(xAxis);

  svg.append("g")
      .attr("class", "y axis")
      .call(yAxis)
      .append("text")
      .attr("transform", "rotate(-90)")
      .attr("y", 6)
      .attr("dy", ".71em")
      .style("text-anchor", "end")
      .text("Performance (%)");

  var equipment = svg.selectAll(".equipment")
      .data(equipments)
      .enter().append("g")
      .attr("class", "equipment");

  equipment.append("path")
      .attr("class", "line")
      .attr("d", function(d) { return line(d.values); })
      .style("stroke", function(d) { return color(d.name); });

  equipment.append("text")
      .datum(function(d) { return {name: d.name, value: d.values[d.values.length - 1]}; })
      .attr("transform", function(d) { return "translate(" + x(d.value.timestamp) + "," + y(d.value.Performance) + ")"; })
      .attr("x", 3)
      .attr("dy", ".35em")
      .text(function(d) { return d.name; });
});
