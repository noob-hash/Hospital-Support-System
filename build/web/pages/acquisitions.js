/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/JavaScript.js to edit this template
 */


const numChart = document.getElementById('numberChart');
const ageGraph = document.getElementById('ageGraph');
const genderChart = document.getElementById('genderGraph');
const depGraph = document.getElementById('departmentGraph');

new Chart(numChart, {
  type: 'line',
  data: {
    labels: ['Jan', 'Feb', 'Mar', 'Apr', 'May', 'Jun', 'July', 'Aug', 'Sept', 'Oct', 'Nov', 'Dec'],
    datasets: [{
      label: '2021',
      data: [120, 65, 130, 50, 200, 133, 230, 130, 90, 120, 150, 125],
      borderWidth: 1
    },
    {
        label: '2022',
        data: [210, 52, 53, 68, 180, 126, 230, 110, 98, 90, 150],
        borderWidth: 1
      }]
  },
  options: {
    responsive:true,
    scales: {
      y: {
        beginAtZero: true
      }
    },
    elements:{
      arc:{
        hoverOffset:15
      }
    }
  }
});

new Chart(genderChart, {
  type: 'pie',
  data: {
    labels: ['Male', 'Female'],
    datasets: [{
      data: [${gender[1]},${gender[0]}],
    }]
  },
  options: {
    responsive: true,
    plugins: {
      legend: {
        display:false
      },
    },
    elements:{
      arc:{
        hoverOffset:15
      }
    }
  } 

  
});

new Chart(ageGraph, {
  type: 'doughnut',
  data: {
    labels: ['0-15', '15-15', '15-30', '30-40', '40-50','50-60','60-70','70+'],
    datasets: [{
      data: [50,90,60,120,100,40,15,90],
    }]
  },
  options: {
    responsive: true,
    plugins: {
      legend: {
        display:false
      },
    },
    elements:{
      arc:{
        hoverOffset:15
      }
    }
  }
});

new Chart(depGraph, {
  type: 'polarArea',
  data: {
    labels: ['General', 'Laboratory', 'Surgical', 'Emergency', 'Radiology', 'Eye Care'],
    datasets: [{
      data: [150, 120, 90, 125, 121 , 80],
      backgroundColor: ["#36A2EB", "#18a3ac", "#178ccb", "#99a164", "#7cd4f4", "#d7c7ac"]
    }],
  },
  options: {
    responsive: true,
    plugins: {
      legend: {
        display:false
      },
    },
    scales: {
      r: {
        ticks: {
          display: false // Remove vertical numbers
        },
        grid: {
          display: false // Removes the circulair lines
        }
      }
    },
    elements:{
      arc:{
        hoverOffset:15
      }
    }
  }
});
