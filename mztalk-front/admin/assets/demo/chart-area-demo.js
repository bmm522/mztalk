// Set new default font family and font color to mimic Bootstrap's default styling
Chart.defaults.global.defaultFontFamily = '-apple-system,system-ui,BlinkMacSystemFont,"Segoe UI",Roboto,"Helvetica Neue",Arial,sans-serif';
Chart.defaults.global.defaultFontColor = '#292b2c';

let today = new Date();

let year = today.getFullYear(); // 년도
let month = today.getMonth() + 1;  // 월
let date = today.getDate();  // 날짜
let day = today.getDay();  // 요일

let oneBeforeDate = today.getDate() - 1;
let twoBeforeDate = today.getDate() - 2;
let threeBeforeDate = today.getDate() - 3;

let fourBeforeDate = today.getDate() - 4;
let fiveBeforeDate = today.getDate() - 5;
let sixBeforeDate = today.getDate() - 6;

let nowDay = year + '-' + month + '-' + date;
let oneBefore = year + '-' + month + '-' + oneBeforeDate;
let twoBefore = year + '-' + month + '-' + twoBeforeDate;
let threeBefore = year + '-' + month + '-' + threeBeforeDate;
let fourBefore = year + '-' + month + '-' + fourBeforeDate;
let fiveBefore = year + '-' + month + '-' + fiveBeforeDate;
let sixBefore = year + '-' + month + '-' + sixBeforeDate;


let todayData ='';
let oneBeforeData = '';
let twoBeforeData = '';
let threeBeforeData = '';
let fourBeforeData = '';
let fiveBeforeData = '';
let sixBeforeData = '';





console.log(oneBeforeData);
window.onload = function(){
  getDailyTraffic();
  setInterval(getDailyTraffic, 10000);


  
   

}



const getDailyTraffic = () =>{
  fetch('http://localhost:8000/gateway/traffic?sixBefore='+sixBefore+'&fiveBefore='+fiveBefore+'&fourBefore='+fourBefore+'&threeBefore='+threeBefore+'&twoBefore='+twoBefore+'&oneBefore='+oneBefore+'&today='+nowDay,{
     method:"GET"
   })
   .then((res) => res.json())
   .then(res=>{
    oneBeforeData =res.data[0].count;
    todayData = res.data[1].count;
    twoBeforeData = res.data[2].count;
    threeBeforeData = res.data[3].count;
    fourBeforeData = res.data[4].count;
    fiveBeforeData = res.data[5].count;
    sixBeforeData = res.data[6].count;

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
            max: 100,
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

