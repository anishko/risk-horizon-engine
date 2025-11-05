// --- Chart.js Multi-Asset Viewer with Custom Start Value ---
async function loadCSV() {
  const resp = await fetch("results.csv");   // assumes results.csv in same folder
  const text = await resp.text();
  const rows = text.trim().split(/\r?\n/).slice(1);
  return rows.map(line => line.split(","));
}

function getUniqueAssets(data) {
  return [...new Set(data.map(r => r[0]))];
}

function filterData(data, asset) {
  return data.filter(r => r[0] === asset).map(r => ({
    year: Number(r[1]),
    low: Number(r[2]),
    mid: Number(r[3]),
    high: Number(r[4])
  }));
}

let chart;

// scale points based on user’s starting amount
function scalePoints(points, startValue) {
  const base = points[0].mid;
  const ratio = startValue / base;
  return points.map(p => ({
    year: p.year,
    low: p.low * ratio,
    mid: p.mid * ratio,
    high: p.high * ratio
  }));
}

function drawChart(data, assets, startValue) {
  const ctx = document.getElementById("chart").getContext("2d");
  if (chart) chart.destroy();

  const colors = [
    "rgb(255,99,132)",
    "rgb(54,162,235)",
    "rgb(255,206,86)",
    "rgb(75,192,192)",
    "rgb(153,102,255)",
    "rgb(255,159,64)"
  ];

  const datasets = [];

  assets.forEach((asset, i) => {
    const points = scalePoints(filterData(data, asset), startValue);
    const color = colors[i % colors.length];

    datasets.push({
      label: asset + " Expected",
      data: points.map(p => p.mid),
      borderColor: color,
      borderWidth: 2,
      fill: false
    });

    // optional low/high bands
    datasets.push({
      label: asset + " Low",
      data: points.map(p => p.low),
      borderColor: color,
      borderDash: [5, 5],
      borderWidth: 1,
      fill: false
    });
    datasets.push({
      label: asset + " High",
      data: points.map(p => p.high),
      borderColor: color,
      borderDash: [5, 5],
      borderWidth: 1,
      fill: false
    });
  });

  chart = new Chart(ctx, {
    type: "line",
    data: {
      labels: filterData(data, assets[0]).map(p => p.year),
      datasets: datasets
    },
    options: {
      responsive: true,
      plugins: {
        title: { display: true, text: "Multi-Asset Growth Projection" },
        legend: { display: true }
      },
      interaction: { mode: "index", intersect: false },
      scales: {
        y: { title: { display: true, text: "Projected Value ($)" } },
        x: { title: { display: true, text: "Year" } }
      }
    }
  });
}

// main setup
(async () => {
  const data = await loadCSV();
  const assets = getUniqueAssets(data);
  const select = document.getElementById("assetSelect");
  const selectAllBtn = document.getElementById("selectAll");
  const updateBtn = document.getElementById("updateChart");

  assets.forEach(a => {
    const opt = document.createElement("option");
    opt.value = a;
    opt.textContent = a;
    select.appendChild(opt);
  });

  // select all button
  selectAllBtn.addEventListener("click", () => {
    for (let i = 0; i < select.options.length; i++) {
      select.options[i].selected = true;
    }
  });

  // update chart button
  updateBtn.addEventListener("click", () => {
    const chosen = [...select.selectedOptions].map(o => o.value);
    const startVal = parseFloat(document.getElementById("startValue").value);
    if (chosen.length === 0) {
      alert("Select at least one asset.");
      return;
    }
    drawChart(data, chosen, startVal);
  });

  // default draw first asset
  drawChart(data, [assets[0]], 100);
})();