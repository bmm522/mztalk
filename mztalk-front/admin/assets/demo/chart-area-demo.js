// Set new default font family and font color to mimic Bootstrap's default styling
Chart.defaults.global.defaultFontFamily = '-apple-system,system-ui,BlinkMacSystemFont,"Segoe UI",Roboto,"Helvetica Neue",Arial,sans-serif';
Chart.defaults.global.defaultFontColor = '#292b2c';

// let today = new Date();

// let year = today.getFullYear(); // 년도
// let month = today.getMonth() + 1;  // 월
// let date = today.getDate();  // 날짜
// let day = today.getDay();  // 요일

// let oneBeforeDate = today.getDate() - 1;
// let twoBeforeDate = today.getDate() - 2;
// let threeBeforeDate = today.getDate() - 3;

// let fourBeforeDate = today.getDate() - 4;
// let fiveBeforeDate = today.getDate() - 5;
// let sixBeforeDate = today.getDate() - 6;

// let nowDay = year + '-' + (month<9?"0"+month:month) + '-' + (date<9?"0"+date:date);
// let oneBefore = year + '-' + (month<9?"0"+month:month) + '-' +  (date<9?"0"+oneBeforeDate:oneBeforeDate);
// let twoBefore = year + '-' + (month<9?"0"+month:month) + '-' + (date<9?"0"+twoBeforeDate:twoBeforeDate);
// let threeBefore = year + '-' + (month<9?"0"+month:month) + '-' + (date<9?"0"+threeBeforeDate:threeBeforeDate);
// let fourBefore = year + '-' + (month<9?"0"+month:month) + '-' + (date<9?"0"+fourBeforeDate:fourBeforeDate);
// let fiveBefore = year + '-' + (month<9?"0"+month:month) + '-' + (date<9?"0"+fiveBeforeDate:fiveBeforeDate);
let today = new Date();

let year = today.getFullYear(); // 년도
let month = today.getMonth() + 1;  // 월
let date = today.getDate();  // 날짜
let day = today.getDay();  // 요일

let oneBeforeDate = new Date();
oneBeforeDate.setDate(oneBeforeDate.getDate() - 1);
let twoBeforeDate = new Date();
twoBeforeDate.setDate(twoBeforeDate.getDate() - 2);
let threeBeforeDate = new Date();
threeBeforeDate.setDate(threeBeforeDate.getDate() - 3);
let fourBeforeDate = new Date();
fourBeforeDate.setDate(fourBeforeDate.getDate() - 4);
let fiveBeforeDate = new Date();
fiveBeforeDate.setDate(fiveBeforeDate.getDate() - 5);
let sixBeforeDate = new Date();
sixBeforeDate.setDate(sixBeforeDate.getDate() - 6);

let nowDay = `${year}-${month<9?"0"+month:month}-${date<9?"0"+date:date}`;
let oneBefore = `${oneBeforeDate.getFullYear()}-${oneBeforeDate.getMonth()<9?"0"+(oneBeforeDate.getMonth()+1):oneBeforeDate.getMonth()+1}-${oneBeforeDate.getDate()<10?"0"+oneBeforeDate.getDate():oneBeforeDate.getDate()}`;
let twoBefore = `${twoBeforeDate.getFullYear()}-${twoBeforeDate.getMonth()<9?"0"+(twoBeforeDate.getMonth()+1):twoBeforeDate.getMonth()+1}-${twoBeforeDate.getDate()<9?"0"+twoBeforeDate.getDate():twoBeforeDate.getDate()}`;
let threeBefore = `${threeBeforeDate.getFullYear()}-${threeBeforeDate.getMonth()<9?"0"+(threeBeforeDate.getMonth()+1):threeBeforeDate.getMonth()+1}-${threeBeforeDate.getDate()<9?"0"+threeBeforeDate.getDate():threeBeforeDate.getDate()}`;
let fourBefore = `${fourBeforeDate.getFullYear()}-${fourBeforeDate.getMonth()<9?"0"+(fourBeforeDate.getMonth()+1):fourBeforeDate.getMonth()+1}-${fourBeforeDate.getDate()<9?"0"+fourBeforeDate.getDate():fourBeforeDate.getDate()}`;
let fiveBefore = `${fiveBeforeDate.getFullYear()}-${fiveBeforeDate.getMonth()<9?"0"+(fiveBeforeDate.getMonth()+1):fiveBeforeDate.getMonth()+1}-${fiveBeforeDate.getDate()<9?"0"+fiveBeforeDate.getDate():fiveBeforeDate.getDate()}`;
let sixBefore = `${sixBeforeDate.getFullYear()}-${sixBeforeDate.getMonth()<9?"0"+(sixBeforeDate.getMonth()+1):sixBeforeDate.getMonth()+1}-${sixBeforeDate.getDate()<9?"0"+sixBeforeDate.getDate():sixBeforeDate.getDate()}`;

console.log("oneBefore : " + oneBefore);


let todayData ='';
let oneBeforeData = '';
let twoBeforeData = '';
let threeBeforeData = '';
let fourBeforeData = '';
let fiveBeforeData = '';
let sixBeforeData = '';



// let auctionData = '';
// let bungData = '';
// let storyData = '';
// let mentorData = '';

// document.getElementById('card-header-div').innerHTML = ' ';






function getDailyServiceTraffic() {
 
  fetch('http://localhost:8000/gateway/daily-traffic?requestTime='+ document.getElementById('input-date').value,{
    method:"GET"
  })
  .then((res)=>res.json())
  .then(res =>{

    for(let i = 0; i < res.data.length ; i++){
      console.log("res : " + res.data);

      if(res.data[i].serviceName == 'auction'){
        auctionData = res.data[i].count;
      }

      if(res.data[i].serviceName == 'mentor'){
        mentorData = res.data[i].count;
      }

      if(res.data[i].serviceName == 'story'){
        storyData = res.data[i].count;
      }

      if(res.data[i].serviceName == 'bung'){
        bungData = res.data[i].count;
      }
    }
    // console.log(mentorData);
    // console.log(bungData);
    // console.log(auctionData);
    // console.log(storyData);
      // auctionData = res.data[0].count;
      // bungData = res.data[1].count;
      // storyData = res.data[2].count;
      // mentorData = res.data[3].count;
      // myLineChart.remove();
 // console.log('모두 끝난 후 : ' + document.getElementById('input-date').value);
 document.getElementById('card-bar-div').innerHTML = '';
 document.getElementById('card-bar-div').innerHTML = '<canvas id="myBarChart" width="100%" height="40"></canvas>';
 var ctx = document.getElementById("myBarChart");
 var myLineChart = new Chart(ctx, {
   type: 'bar',
   data: {
     labels: ["Mentor", "Bung", "Auction", "Stroy"],
     datasets: [{
       label: "일일 트래픽 수",
       backgroundColor: "rgba(2,117,216,1)",
       borderColor: "rgba(2,117,216,1)",
       data: [mentorData, bungData, auctionData, storyData],
     }],
   },
   options: {
     scales: {
       xAxes: [{
         time: {
           unit: 'date'
         },
         gridLines: {
           display: false
         },
         ticks: {
           maxTicksLimit: 6
         }
       }],
       yAxes: [{
         ticks: {
           min: 0,
           max: 10000,
           maxTicksLimit: 24
         },
         gridLines: {
           display: true
         }
       }],
     },
     legend: {
       display: false
     }
   }
 });
  
  })
  // console.log('----------------------------------');
  // console.log(mentorData);
  // console.log(bungData);
  // console.log(auctionData);
  // console.log(storyData);
  
}

// const getBarChart = () => {
// document.getElementById("myBarChart").remove();
//   var ctx = document.getElementById("myBarChart");
// var chart = new Chart(ctx, {
//     // The type of chart we want to create
//     type: 'line',

//     // The data for our dataset
//     data: {
//         labels: ["Mentor", "Bung", "Auction", "Stroy"],
//         datasets: [{
//             label: 'My First dataset',
//             backgroundColor: 'rgb(255, 99, 132)',
//             borderColor: 'rgb(255, 99, 132)',
//             data: [mentorData, bungData, auctionData, storyData]
//         }]
//     },

//     // Configuration options go here
//     options: {}
// });
// }



const getBarChar = () =>{
  
  // console.log(myLineChart.data.datasets);
}





const getDailyTraffic = () =>{
  document.getElementById('card-chart-div').innerHTML = '';
  document.getElementById('card-chart-div').innerHTML = '<canvas id="myAreaChart" width="100%" height="40"></canvas>';
  fetch('http://localhost:8000/gateway/traffic?sixBefore='+sixBefore+'&fiveBefore='+fiveBefore+'&fourBefore='+fourBefore+'&threeBefore='+threeBefore+'&twoBefore='+twoBefore+'&oneBefore='+oneBefore+'&today='+nowDay,{
     method:"GET"
   })
   .then((res) => res.json())
   .then(res=>{
    for(let i = 0 ; i < res.data.length ; i++){
        
      if(res.data[i].requestTime ==nowDay){
        
        todayData = res.data[i].count;
      }

      if(res.data[i].requestTime ==oneBefore){
        oneBeforeData = res.data[i].count;
      }

      if(res.data[i].requestTime ==twoBefore){
        twoBeforeData = res.data[i].count;
      }

      if(res.data[i].requestTime ==threeBefore){
        threeBeforeData = res.data[i].count;
      }

      if(res.data[i].requestTime ==fourBefore){
        fourBeforeData = res.data[i].count;
      }

      if(res.data[i].requestTime ==fiveBefore){
        fiveBeforeData = res.data[i].count;
      }

      if(res.data[i].requestTime ==sixBefore){
        sixBeforeData = res.data[i].count;
      }
  

    }


  var ctx = document.getElementById("myAreaChart");
  var myLineChart = new Chart(ctx, {
    type: 'line',
    data: {
      labels: [sixBefore, fiveBefore, fourBefore, threeBefore, twoBefore, oneBefore, nowDay],
      datasets: [{
        label: "Sessions",
        lineTension: 0.3,
        backgroundColor: "rgba(2,117,216,0.2)",
        borderColor: "rgba(2,117,216,1)",
        pointRadius: 5,
        pointBackgroundColor: "rgba(2,117,216,1)",
        pointBorderColor: "rgba(255,255,255,0.8)",
        pointHoverRadius: 5,
        pointHoverBackgroundColor: "rgba(2,117,216,1)",
        pointHitRadius: 50,
        pointBorderWidth: 2,
        data: [ sixBeforeData, fiveBeforeData, fourBeforeData, threeBeforeData, twoBeforeData, oneBeforeData, todayData],
      }],
    },
    options: {
      scales: {
        xAxes: [{
          time: {
            unit: 'date'
          },
          gridLines: {
            display: false
          },
          ticks: {
            maxTicksLimit: 7
          }
        }],
        yAxes: [{
          ticks: {
            min: 0,
            max: 15000,
            maxTicksLimit: 20
          },
          gridLines: {
            color: "rgba(0, 0, 0, .125)",
          }
        }],
      },
      legend: {
        display: false
      }
    }
  });
   })
}










// Area Chart Example

