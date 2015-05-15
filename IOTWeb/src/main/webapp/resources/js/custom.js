d3.json("js/bigdata.json", function(d){ /*console.log(d)*/});
//Custom
var rp3 = radialProgress(document.getElementById('container_tci'))
                .label("TCI")
                //.onClick(onClick3)
                .diameter(150)
                .minValue(0)
                .maxValue(100)
                .value(70)
                .render();


