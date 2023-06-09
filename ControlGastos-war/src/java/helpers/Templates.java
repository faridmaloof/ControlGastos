/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package helpers;

/**
 *
 * @author farid
 */
public class Templates {
    
    /**
     * 
     * @param entidad
     * @param tabla
     * @param alerts
     * @param createRecord
     * @return 
     */
    public static String ListElements(String entidad, String tabla, String alerts, String createRecord){
        String template = """
                          <!DOCTYPE html>
                          <html lang="es">
                          
                          <head>
                            <meta charset="utf-8" />
                            <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
                            <link rel="apple-touch-icon" sizes="76x76" href="assets/img/apple-icon.png">
                            <link rel="icon" type="image/png" href="assets/img/favicon.png">
                            <title>
                              Control Gastos
                            </title>
                            <!--     Fonts and icons     -->
                            <link rel="stylesheet" type="text/css" href="https://fonts.googleapis.com/css?family=Roboto:300,400,500,700,900|Roboto+Slab:400,700" />
                            <!-- Nucleo Icons -->
                            <link href="assets/css/nucleo-icons.css" rel="stylesheet" />
                            <link href="assets/css/nucleo-svg.css" rel="stylesheet" />
                            <!-- Font Awesome Icons -->
                            <script src="https://kit.fontawesome.com/42d5adcbca.js" crossorigin="anonymous"></script>
                            <!-- Material Icons -->
                            <link href="https://fonts.googleapis.com/icon?family=Material+Icons+Round" rel="stylesheet">
                            <!-- CSS Files -->
                            <link id="pagestyle" href="assets/css/material-dashboard.css?v=3.0.4" rel="stylesheet" />
                          </head>
                          
                          <body class="g-sidenav-show  bg-gray-200">
                            <aside class="sidenav navbar navbar-vertical navbar-expand-xs border-0 border-radius-xl my-3 fixed-start ms-3   bg-gradient-dark" id="sidenav-main">
                              <div class="sidenav-header">
                                <i class="fas fa-times p-3 cursor-pointer text-white opacity-5 position-absolute end-0 top-0 d-none d-xl-none" aria-hidden="true" id="iconSidenav"></i>
                                <a class="navbar-brand m-0" href=" https://demos.creative-tim.com/material-dashboard/pages/dashboard " target="_blank">
                                  <img src="assets/img/logo-ct.png" class="navbar-brand-img h-100" alt="main_logo">
                                  <span class="ms-1 font-weight-bold text-white">Finanzas Personales</span>
                                </a>
                              </div>
                              <hr class="horizontal light mt-0 mb-2">
                              <div class="collapse navbar-collapse  w-auto " id="sidenav-collapse-main">
                                <ul class="navbar-nav">
                                  <li class="nav-item">
                                    <a class="nav-link text-white active bg-gradient-info" href="/ControlGastos-war">
                                      <div class="text-white text-center me-2 d-flex align-items-center justify-content-center">
                                        <i class="material-icons opacity-10">dashboard</i>
                                      </div>
                                      <span class="nav-link-text ms-1">Dashboard</span>
                                    </a>
                                  </li>
                                  <li class="nav-item mt-3">
                                    <h6 class="ps-4 ms-2 text-uppercase text-xs text-white font-weight-bolder opacity-8">Configuraci\u00f3n</h6>
                                  </li>
                                  <li class="nav-item">
                                    <a class="nav-link text-white " href="CuentasList">
                                      <div class="text-white text-center me-2 d-flex align-items-center justify-content-center">
                                        <i class="material-icons opacity-10">table_view</i>
                                      </div>
                                      <span class="nav-link-text ms-1">Cuentas</span>
                                    </a>
                                  </li>
                                  <li class="nav-item">
                                    <a class="nav-link text-white " href="CategoriasList">
                                      <div class="text-white text-center me-2 d-flex align-items-center justify-content-center">
                                        <i class="material-icons opacity-10">receipt_long</i>
                                      </div>
                                      <span class="nav-link-text ms-1">Categorias</span>
                                    </a>
                                  </li>
                                  <li class="nav-item">
                                    <a class="nav-link text-white " href="TercerosList">
                                      <div class="text-white text-center me-2 d-flex align-items-center justify-content-center">
                                        <i class="material-icons opacity-10">view_in_ar</i>
                                      </div>
                                      <span class="nav-link-text ms-1">Terceros</span>
                                    </a>
                                  </li>
                                  <li class="nav-item">
                                    <a class="nav-link text-white " href="TagsList">
                                      <div class="text-white text-center me-2 d-flex align-items-center justify-content-center">
                                        <i class="material-icons opacity-10">format_textdirection_r_to_l</i>
                                      </div>
                                      <span class="nav-link-text ms-1">Tags</span>
                                    </a>
                                  </li>
                                  <li class="nav-item mt-3">
                                    <a href="MovimientosList"><h6 class="ps-4 ms-2 text-uppercase text-xs text-white font-weight-bolder opacity-8">Transacciones</h6></a>
                                  </li>
                                  <li class="nav-item">
                                    <a class="nav-link text-white " href="MovimientosCreate?transaccion=ingreso">
                                      <div class="text-white text-center me-2 d-flex align-items-center justify-content-center">
                                        <i class="material-icons opacity-10">notifications</i>
                                      </div>
                                      <span class="nav-link-text ms-1">Ingresos</span>
                                    </a>
                                  </li>
                                  <li class="nav-item">
                                    <a class="nav-link text-white " href="MovimientosCreate?transaccion=egreso">
                                      <div class="text-white text-center me-2 d-flex align-items-center justify-content-center">
                                        <i class="material-icons opacity-10">person</i>
                                      </div>
                                      <span class="nav-link-text ms-1">Egresos</span>
                                    </a>
                                  </li>
                                  <li class="nav-item mt-3">
                                    <a href="MovimientosList"><h6 class="ps-4 ms-2 text-uppercase text-xs text-white font-weight-bolder opacity-8">Webservices</h6></a>
                                  </li>
                                  <li class="nav-item">
                                    <a class="nav-link text-white " href="CosumirWebservices">
                                      <div class="text-white text-center me-2 d-flex align-items-center justify-content-center">
                                        <i class="material-icons opacity-10">cloud</i>
                                      </div>
                                      <span class="nav-link-text ms-1">Clima</span>
                                    </a>
                                  </li>
                                </ul>
                              </div>
                            </aside>
                            <main class="main-content position-relative max-height-vh-100 h-100 border-radius-lg ">
                              <!-- Navbar -->
                              <nav class="navbar navbar-main navbar-expand-lg px-0 mx-4 border-radius-xl shadow-none position-sticky blur shadow-blur mt-4 left-auto top-1 z-index-sticky" id="navbarBlur" data-scroll="true">
                                <div class="container-fluid py-1 px-3">
                                  <nav aria-label="breadcrumb">
                                    <ol class="breadcrumb bg-transparent mb-0 pb-0 pt-1 px-0 me-sm-6 me-5">
                                      <li class="breadcrumb-item text-sm"><a class="opacity-5 text-dark" href="javascript:;">Configuraci\u00f3n</a></li>
                                      <li class="breadcrumb-item text-sm text-dark active" aria-current="page">{{entidad}}</li>
                                    </ol>
                                    <h6 class="font-weight-bolder mb-0">{{entidad}}</h6>
                                  </nav>
                                </div>
                              </nav>
                              <!-- End Navbar -->
                              <div class="container-fluid py-4">
                                {{alerts}}
                                <div class="row">
                                  <div class="col-xl-10 col-lg-11 col-md-12 d-flex flex-column ms-auto me-auto ms-lg-auto">
                                    <div class="card card-plain">
                                      <div class="bg-gradient-info shadow-info border-radius-lg pt-4 pb-3">
                                        <h6 class="text-white text-capitalize ps-3">{{entidad}}</h6>
                                      </div>
                                      <a class="btn  rounded-0 btn-block text-center" href="{{createRecord}}">Adicionar registro</a>
                                      <div class="card-body px-0 pb-2">
                                        <div class="table-responsive p-0">
                                            {{tabla}}
                                        </div>
                                      </div>
                                    </div>
                                  </div>
                                </div>
                              </div>
                            </main>
                            <!--   Core JS Files   -->
                            <script src="assets/js/core/popper.min.js"></script>
                            <script src="assets/js/core/bootstrap.min.js"></script>
                            <script src="assets/js/plugins/perfect-scrollbar.min.js"></script>
                            <script src="assets/js/plugins/smooth-scrollbar.min.js"></script>
                            <script src="assets/js/plugins/chartjs.min.js"></script>
                            <script>
                              var ctx = document.getElementById("chart-bars").getContext("2d");
                          
                              new Chart(ctx, {
                                type: "bar",
                                data: {
                                  labels: ["M", "T", "W", "T", "F", "S", "S"],
                                  datasets: [{
                                    label: "Sales",
                                    tension: 0.4,
                                    borderWidth: 0,
                                    borderRadius: 4,
                                    borderSkipped: false,
                                    backgroundColor: "rgba(255, 255, 255, .8)",
                                    data: [50, 20, 10, 22, 50, 10, 40],
                                    maxBarThickness: 6
                                  }, ],
                                },
                                options: {
                                  responsive: true,
                                  maintainAspectRatio: false,
                                  plugins: {
                                    legend: {
                                      display: false,
                                    }
                                  },
                                  interaction: {
                                    intersect: false,
                                    mode: 'index',
                                  },
                                  scales: {
                                    y: {
                                      grid: {
                                        drawBorder: false,
                                        display: true,
                                        drawOnChartArea: true,
                                        drawTicks: false,
                                        borderDash: [5, 5],
                                        color: 'rgba(255, 255, 255, .2)'
                                      },
                                      ticks: {
                                        suggestedMin: 0,
                                        suggestedMax: 500,
                                        beginAtZero: true,
                                        padding: 10,
                                        font: {
                                          size: 14,
                                          weight: 300,
                                          family: "Roboto",
                                          style: 'normal',
                                          lineHeight: 2
                                        },
                                        color: "#fff"
                                      },
                                    },
                                    x: {
                                      grid: {
                                        drawBorder: false,
                                        display: true,
                                        drawOnChartArea: true,
                                        drawTicks: false,
                                        borderDash: [5, 5],
                                        color: 'rgba(255, 255, 255, .2)'
                                      },
                                      ticks: {
                                        display: true,
                                        color: '#f8f9fa',
                                        padding: 10,
                                        font: {
                                          size: 14,
                                          weight: 300,
                                          family: "Roboto",
                                          style: 'normal',
                                          lineHeight: 2
                                        },
                                      }
                                    },
                                  },
                                },
                              });
                          
                          
                              var ctx2 = document.getElementById("chart-line").getContext("2d");
                          
                              new Chart(ctx2, {
                                type: "line",
                                data: {
                                  labels: ["Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"],
                                  datasets: [{
                                    label: "Mobile apps",
                                    tension: 0,
                                    borderWidth: 0,
                                    pointRadius: 5,
                                    pointBackgroundColor: "rgba(255, 255, 255, .8)",
                                    pointBorderColor: "transparent",
                                    borderColor: "rgba(255, 255, 255, .8)",
                                    borderColor: "rgba(255, 255, 255, .8)",
                                    borderWidth: 4,
                                    backgroundColor: "transparent",
                                    fill: true,
                                    data: [50, 40, 300, 320, 500, 350, 200, 230, 500],
                                    maxBarThickness: 6
                          
                                  }],
                                },
                                options: {
                                  responsive: true,
                                  maintainAspectRatio: false,
                                  plugins: {
                                    legend: {
                                      display: false,
                                    }
                                  },
                                  interaction: {
                                    intersect: false,
                                    mode: 'index',
                                  },
                                  scales: {
                                    y: {
                                      grid: {
                                        drawBorder: false,
                                        display: true,
                                        drawOnChartArea: true,
                                        drawTicks: false,
                                        borderDash: [5, 5],
                                        color: 'rgba(255, 255, 255, .2)'
                                      },
                                      ticks: {
                                        display: true,
                                        color: '#f8f9fa',
                                        padding: 10,
                                        font: {
                                          size: 14,
                                          weight: 300,
                                          family: "Roboto",
                                          style: 'normal',
                                          lineHeight: 2
                                        },
                                      }
                                    },
                                    x: {
                                      grid: {
                                        drawBorder: false,
                                        display: false,
                                        drawOnChartArea: false,
                                        drawTicks: false,
                                        borderDash: [5, 5]
                                      },
                                      ticks: {
                                        display: true,
                                        color: '#f8f9fa',
                                        padding: 10,
                                        font: {
                                          size: 14,
                                          weight: 300,
                                          family: "Roboto",
                                          style: 'normal',
                                          lineHeight: 2
                                        },
                                      }
                                    },
                                  },
                                },
                              });
                          
                              var ctx3 = document.getElementById("chart-line-tasks").getContext("2d");
                          
                              new Chart(ctx3, {
                                type: "line",
                                data: {
                                  labels: ["Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"],
                                  datasets: [{
                                    label: "Mobile apps",
                                    tension: 0,
                                    borderWidth: 0,
                                    pointRadius: 5,
                                    pointBackgroundColor: "rgba(255, 255, 255, .8)",
                                    pointBorderColor: "transparent",
                                    borderColor: "rgba(255, 255, 255, .8)",
                                    borderWidth: 4,
                                    backgroundColor: "transparent",
                                    fill: true,
                                    data: [50, 40, 300, 220, 500, 250, 400, 230, 500],
                                    maxBarThickness: 6
                          
                                  }],
                                },
                                options: {
                                  responsive: true,
                                  maintainAspectRatio: false,
                                  plugins: {
                                    legend: {
                                      display: false,
                                    }
                                  },
                                  interaction: {
                                    intersect: false,
                                    mode: 'index',
                                  },
                                  scales: {
                                    y: {
                                      grid: {
                                        drawBorder: false,
                                        display: true,
                                        drawOnChartArea: true,
                                        drawTicks: false,
                                        borderDash: [5, 5],
                                        color: 'rgba(255, 255, 255, .2)'
                                      },
                                      ticks: {
                                        display: true,
                                        padding: 10,
                                        color: '#f8f9fa',
                                        font: {
                                          size: 14,
                                          weight: 300,
                                          family: "Roboto",
                                          style: 'normal',
                                          lineHeight: 2
                                        },
                                      }
                                    },
                                    x: {
                                      grid: {
                                        drawBorder: false,
                                        display: false,
                                        drawOnChartArea: false,
                                        drawTicks: false,
                                        borderDash: [5, 5]
                                      },
                                      ticks: {
                                        display: true,
                                        color: '#f8f9fa',
                                        padding: 10,
                                        font: {
                                          size: 14,
                                          weight: 300,
                                          family: "Roboto",
                                          style: 'normal',
                                          lineHeight: 2
                                        },
                                      }
                                    },
                                  },
                                },
                              });
                            </script>
                            <script>
                              var win = navigator.platform.indexOf('Win') > -1;
                              if (win && document.querySelector('#sidenav-scrollbar')) {
                                var options = {
                                  damping: '0.5'
                                }
                                Scrollbar.init(document.querySelector('#sidenav-scrollbar'), options);
                              }
                            </script>
                            <!-- Github buttons -->
                            <script async defer src="https://buttons.github.io/buttons.js"></script>
                            <!-- Control Center for Material Dashboard: parallax effects, scripts for the example pages etc -->
                            <script src="assets/js/material-dashboard.min.js?v=3.0.4"></script>
                          </body>
                          
                          </html>""";
        template = template.replace("{{createRecord}}", createRecord);
        template = template.replace("{{entidad}}", entidad);
        template = template.replace("{{alerts}}", alerts);
        template = template.replace("{{tabla}}", tabla);
        
        return template;
    }
    
    /**
     * 
     * @param entidad
     * @param tabla
     * @return 
     */
    public static String ListElements(String entidad, String tabla){
        String template = """
                          <!DOCTYPE html>
                          <html lang="es">
                          
                          <head>
                            <meta charset="utf-8" />
                            <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
                            <link rel="apple-touch-icon" sizes="76x76" href="assets/img/apple-icon.png">
                            <link rel="icon" type="image/png" href="assets/img/favicon.png">
                            <title>
                              Control Gastos
                            </title>
                            <!--     Fonts and icons     -->
                            <link rel="stylesheet" type="text/css" href="https://fonts.googleapis.com/css?family=Roboto:300,400,500,700,900|Roboto+Slab:400,700" />
                            <!-- Nucleo Icons -->
                            <link href="assets/css/nucleo-icons.css" rel="stylesheet" />
                            <link href="assets/css/nucleo-svg.css" rel="stylesheet" />
                            <!-- Font Awesome Icons -->
                            <script src="https://kit.fontawesome.com/42d5adcbca.js" crossorigin="anonymous"></script>
                            <!-- Material Icons -->
                            <link href="https://fonts.googleapis.com/icon?family=Material+Icons+Round" rel="stylesheet">
                            <!-- CSS Files -->
                            <link id="pagestyle" href="assets/css/material-dashboard.css?v=3.0.4" rel="stylesheet" />
                          </head>
                          
                          <body class="g-sidenav-show  bg-gray-200">
                            <aside class="sidenav navbar navbar-vertical navbar-expand-xs border-0 border-radius-xl my-3 fixed-start ms-3   bg-gradient-dark" id="sidenav-main">
                              <div class="sidenav-header">
                                <i class="fas fa-times p-3 cursor-pointer text-white opacity-5 position-absolute end-0 top-0 d-none d-xl-none" aria-hidden="true" id="iconSidenav"></i>
                                <a class="navbar-brand m-0" href=" https://demos.creative-tim.com/material-dashboard/pages/dashboard " target="_blank">
                                  <img src="assets/img/logo-ct.png" class="navbar-brand-img h-100" alt="main_logo">
                                  <span class="ms-1 font-weight-bold text-white">Finanzas Personales</span>
                                </a>
                              </div>
                              <hr class="horizontal light mt-0 mb-2">
                              <div class="collapse navbar-collapse  w-auto " id="sidenav-collapse-main">
                                <ul class="navbar-nav">
                                  <li class="nav-item">
                                    <a class="nav-link text-white active bg-gradient-info" href="/ControlGastos-war">
                                      <div class="text-white text-center me-2 d-flex align-items-center justify-content-center">
                                        <i class="material-icons opacity-10">dashboard</i>
                                      </div>
                                      <span class="nav-link-text ms-1">Dashboard</span>
                                    </a>
                                  </li>
                                  <li class="nav-item mt-3">
                                    <h6 class="ps-4 ms-2 text-uppercase text-xs text-white font-weight-bolder opacity-8">Configuraci\u00f3n</h6>
                                  </li>
                                  <li class="nav-item">
                                    <a class="nav-link text-white " href="CuentasList">
                                      <div class="text-white text-center me-2 d-flex align-items-center justify-content-center">
                                        <i class="material-icons opacity-10">table_view</i>
                                      </div>
                                      <span class="nav-link-text ms-1">Cuentas</span>
                                    </a>
                                  </li>
                                  <li class="nav-item">
                                    <a class="nav-link text-white " href="CategoriasList">
                                      <div class="text-white text-center me-2 d-flex align-items-center justify-content-center">
                                        <i class="material-icons opacity-10">receipt_long</i>
                                      </div>
                                      <span class="nav-link-text ms-1">Categorias</span>
                                    </a>
                                  </li>
                                  <li class="nav-item">
                                    <a class="nav-link text-white " href="TercerosList">
                                      <div class="text-white text-center me-2 d-flex align-items-center justify-content-center">
                                        <i class="material-icons opacity-10">view_in_ar</i>
                                      </div>
                                      <span class="nav-link-text ms-1">Terceros</span>
                                    </a>
                                  </li>
                                  <li class="nav-item">
                                    <a class="nav-link text-white " href="TagsList">
                                      <div class="text-white text-center me-2 d-flex align-items-center justify-content-center">
                                        <i class="material-icons opacity-10">format_textdirection_r_to_l</i>
                                      </div>
                                      <span class="nav-link-text ms-1">Tags</span>
                                    </a>
                                  </li>
                                  <li class="nav-item mt-3">
                                    <a href="MovimientosList"><h6 class="ps-4 ms-2 text-uppercase text-xs text-white font-weight-bolder opacity-8">Transacciones</h6></a>
                                  </li>
                                  <li class="nav-item">
                                    <a class="nav-link text-white " href="MovimientosCreate?transaccion=ingreso">
                                      <div class="text-white text-center me-2 d-flex align-items-center justify-content-center">
                                        <i class="material-icons opacity-10">notifications</i>
                                      </div>
                                      <span class="nav-link-text ms-1">Ingresos</span>
                                    </a>
                                  </li>
                                  <li class="nav-item">
                                    <a class="nav-link text-white " href="MovimientosCreate?transaccion=egreso">
                                      <div class="text-white text-center me-2 d-flex align-items-center justify-content-center">
                                        <i class="material-icons opacity-10">person</i>
                                      </div>
                                      <span class="nav-link-text ms-1">Egresos</span>
                                    </a>
                                  </li>
                                  <li class="nav-item mt-3">
                                    <a href="MovimientosList"><h6 class="ps-4 ms-2 text-uppercase text-xs text-white font-weight-bolder opacity-8">Webservices</h6></a>
                                  </li>
                                  <li class="nav-item">
                                    <a class="nav-link text-white " href="CosumirWebservices">
                                      <div class="text-white text-center me-2 d-flex align-items-center justify-content-center">
                                        <i class="material-icons opacity-10">cloud</i>
                                      </div>
                                      <span class="nav-link-text ms-1">Clima</span>
                                    </a>
                                  </li>
                                </ul>
                              </div>
                            </aside>
                            <main class="main-content position-relative max-height-vh-100 h-100 border-radius-lg ">
                              <!-- Navbar -->
                              <nav class="navbar navbar-main navbar-expand-lg px-0 mx-4 border-radius-xl shadow-none position-sticky blur shadow-blur mt-4 left-auto top-1 z-index-sticky" id="navbarBlur" data-scroll="true">
                                <div class="container-fluid py-1 px-3">
                                  <nav aria-label="breadcrumb">
                                    <ol class="breadcrumb bg-transparent mb-0 pb-0 pt-1 px-0 me-sm-6 me-5">
                                      <li class="breadcrumb-item text-sm"><a class="opacity-5 text-dark" href="javascript:;">Configuraci\u00f3n</a></li>
                                      <li class="breadcrumb-item text-sm text-dark active" aria-current="page">{{entidad}}</li>
                                    </ol>
                                    <h6 class="font-weight-bolder mb-0">{{entidad}}</h6>
                                  </nav>
                                </div>
                              </nav>
                              <!-- End Navbar -->
                              <div class="container-fluid py-4">
                                <div class="row">
                                  <div class="col-xl-10 col-lg-11 col-md-12 d-flex flex-column ms-auto me-auto ms-lg-auto">
                                    <div class="card card-plain">
                                      <div class="bg-gradient-info shadow-info border-radius-lg pt-4 pb-3">
                                        <h6 class="text-white text-capitalize ps-3">{{entidad}}</h6>
                                      </div>
                                      <div class="card-body px-0 pb-2">
                                        <div class="table-responsive p-0">
                                            {{tabla}}
                                        </div>
                                      </div>
                                    </div>
                                  </div>
                                </div>
                              </div>
                            </main>
                            <!--   Core JS Files   -->
                            <script src="assets/js/core/popper.min.js"></script>
                            <script src="assets/js/core/bootstrap.min.js"></script>
                            <script src="assets/js/plugins/perfect-scrollbar.min.js"></script>
                            <script src="assets/js/plugins/smooth-scrollbar.min.js"></script>
                            <script src="assets/js/plugins/chartjs.min.js"></script>
                            <script>
                              var ctx = document.getElementById("chart-bars").getContext("2d");
                          
                              new Chart(ctx, {
                                type: "bar",
                                data: {
                                  labels: ["M", "T", "W", "T", "F", "S", "S"],
                                  datasets: [{
                                    label: "Sales",
                                    tension: 0.4,
                                    borderWidth: 0,
                                    borderRadius: 4,
                                    borderSkipped: false,
                                    backgroundColor: "rgba(255, 255, 255, .8)",
                                    data: [50, 20, 10, 22, 50, 10, 40],
                                    maxBarThickness: 6
                                  }, ],
                                },
                                options: {
                                  responsive: true,
                                  maintainAspectRatio: false,
                                  plugins: {
                                    legend: {
                                      display: false,
                                    }
                                  },
                                  interaction: {
                                    intersect: false,
                                    mode: 'index',
                                  },
                                  scales: {
                                    y: {
                                      grid: {
                                        drawBorder: false,
                                        display: true,
                                        drawOnChartArea: true,
                                        drawTicks: false,
                                        borderDash: [5, 5],
                                        color: 'rgba(255, 255, 255, .2)'
                                      },
                                      ticks: {
                                        suggestedMin: 0,
                                        suggestedMax: 500,
                                        beginAtZero: true,
                                        padding: 10,
                                        font: {
                                          size: 14,
                                          weight: 300,
                                          family: "Roboto",
                                          style: 'normal',
                                          lineHeight: 2
                                        },
                                        color: "#fff"
                                      },
                                    },
                                    x: {
                                      grid: {
                                        drawBorder: false,
                                        display: true,
                                        drawOnChartArea: true,
                                        drawTicks: false,
                                        borderDash: [5, 5],
                                        color: 'rgba(255, 255, 255, .2)'
                                      },
                                      ticks: {
                                        display: true,
                                        color: '#f8f9fa',
                                        padding: 10,
                                        font: {
                                          size: 14,
                                          weight: 300,
                                          family: "Roboto",
                                          style: 'normal',
                                          lineHeight: 2
                                        },
                                      }
                                    },
                                  },
                                },
                              });
                          
                          
                              var ctx2 = document.getElementById("chart-line").getContext("2d");
                          
                              new Chart(ctx2, {
                                type: "line",
                                data: {
                                  labels: ["Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"],
                                  datasets: [{
                                    label: "Mobile apps",
                                    tension: 0,
                                    borderWidth: 0,
                                    pointRadius: 5,
                                    pointBackgroundColor: "rgba(255, 255, 255, .8)",
                                    pointBorderColor: "transparent",
                                    borderColor: "rgba(255, 255, 255, .8)",
                                    borderColor: "rgba(255, 255, 255, .8)",
                                    borderWidth: 4,
                                    backgroundColor: "transparent",
                                    fill: true,
                                    data: [50, 40, 300, 320, 500, 350, 200, 230, 500],
                                    maxBarThickness: 6
                          
                                  }],
                                },
                                options: {
                                  responsive: true,
                                  maintainAspectRatio: false,
                                  plugins: {
                                    legend: {
                                      display: false,
                                    }
                                  },
                                  interaction: {
                                    intersect: false,
                                    mode: 'index',
                                  },
                                  scales: {
                                    y: {
                                      grid: {
                                        drawBorder: false,
                                        display: true,
                                        drawOnChartArea: true,
                                        drawTicks: false,
                                        borderDash: [5, 5],
                                        color: 'rgba(255, 255, 255, .2)'
                                      },
                                      ticks: {
                                        display: true,
                                        color: '#f8f9fa',
                                        padding: 10,
                                        font: {
                                          size: 14,
                                          weight: 300,
                                          family: "Roboto",
                                          style: 'normal',
                                          lineHeight: 2
                                        },
                                      }
                                    },
                                    x: {
                                      grid: {
                                        drawBorder: false,
                                        display: false,
                                        drawOnChartArea: false,
                                        drawTicks: false,
                                        borderDash: [5, 5]
                                      },
                                      ticks: {
                                        display: true,
                                        color: '#f8f9fa',
                                        padding: 10,
                                        font: {
                                          size: 14,
                                          weight: 300,
                                          family: "Roboto",
                                          style: 'normal',
                                          lineHeight: 2
                                        },
                                      }
                                    },
                                  },
                                },
                              });
                          
                              var ctx3 = document.getElementById("chart-line-tasks").getContext("2d");
                          
                              new Chart(ctx3, {
                                type: "line",
                                data: {
                                  labels: ["Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"],
                                  datasets: [{
                                    label: "Mobile apps",
                                    tension: 0,
                                    borderWidth: 0,
                                    pointRadius: 5,
                                    pointBackgroundColor: "rgba(255, 255, 255, .8)",
                                    pointBorderColor: "transparent",
                                    borderColor: "rgba(255, 255, 255, .8)",
                                    borderWidth: 4,
                                    backgroundColor: "transparent",
                                    fill: true,
                                    data: [50, 40, 300, 220, 500, 250, 400, 230, 500],
                                    maxBarThickness: 6
                          
                                  }],
                                },
                                options: {
                                  responsive: true,
                                  maintainAspectRatio: false,
                                  plugins: {
                                    legend: {
                                      display: false,
                                    }
                                  },
                                  interaction: {
                                    intersect: false,
                                    mode: 'index',
                                  },
                                  scales: {
                                    y: {
                                      grid: {
                                        drawBorder: false,
                                        display: true,
                                        drawOnChartArea: true,
                                        drawTicks: false,
                                        borderDash: [5, 5],
                                        color: 'rgba(255, 255, 255, .2)'
                                      },
                                      ticks: {
                                        display: true,
                                        padding: 10,
                                        color: '#f8f9fa',
                                        font: {
                                          size: 14,
                                          weight: 300,
                                          family: "Roboto",
                                          style: 'normal',
                                          lineHeight: 2
                                        },
                                      }
                                    },
                                    x: {
                                      grid: {
                                        drawBorder: false,
                                        display: false,
                                        drawOnChartArea: false,
                                        drawTicks: false,
                                        borderDash: [5, 5]
                                      },
                                      ticks: {
                                        display: true,
                                        color: '#f8f9fa',
                                        padding: 10,
                                        font: {
                                          size: 14,
                                          weight: 300,
                                          family: "Roboto",
                                          style: 'normal',
                                          lineHeight: 2
                                        },
                                      }
                                    },
                                  },
                                },
                              });
                            </script>
                            <script>
                              var win = navigator.platform.indexOf('Win') > -1;
                              if (win && document.querySelector('#sidenav-scrollbar')) {
                                var options = {
                                  damping: '0.5'
                                }
                                Scrollbar.init(document.querySelector('#sidenav-scrollbar'), options);
                              }
                            </script>
                            <!-- Github buttons -->
                            <script async defer src="https://buttons.github.io/buttons.js"></script>
                            <!-- Control Center for Material Dashboard: parallax effects, scripts for the example pages etc -->
                            <script src="assets/js/material-dashboard.min.js?v=3.0.4"></script>
                          </body>
                          
                          </html>""";
        template = template.replace("{{entidad}}", entidad);
        template = template.replace("{{tabla}}", tabla);
        
        return template;
    }
      
    /**
     * 
     * @param entidad
     * @param campos
     * @param cancel
     * @return 
     */
    public static String Form(String entidad, String campos, String cancel){
        String template = """
                          <!DOCTYPE html>
                          <html lang="es">
                          
                          <head>
                            <meta charset="utf-8" />
                            <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
                            <link rel="apple-touch-icon" sizes="76x76" href="assets/img/apple-icon.png">
                            <link rel="icon" type="image/png" href="assets/img/favicon.png">
                            <title>
                              Control Gastos
                            </title>
                            <!--     Fonts and icons     -->
                            <link rel="stylesheet" type="text/css" href="https://fonts.googleapis.com/css?family=Roboto:300,400,500,700,900|Roboto+Slab:400,700" />
                            <!-- Nucleo Icons -->
                            <link href="assets/css/nucleo-icons.css" rel="stylesheet" />
                            <link href="assets/css/nucleo-svg.css" rel="stylesheet" />
                            <!-- Font Awesome Icons -->
                            <script src="https://kit.fontawesome.com/42d5adcbca.js" crossorigin="anonymous"></script>
                            <!-- Material Icons -->
                            <link href="https://fonts.googleapis.com/icon?family=Material+Icons+Round" rel="stylesheet">
                            <!-- CSS Files -->
                            <link id="pagestyle" href="assets/css/material-dashboard.css?v=3.0.4" rel="stylesheet" />
                          </head>
                          
                          <body class="g-sidenav-show  bg-gray-200">
                            <aside class="sidenav navbar navbar-vertical navbar-expand-xs border-0 border-radius-xl my-3 fixed-start ms-3   bg-gradient-dark" id="sidenav-main">
                              <div class="sidenav-header">
                                <i class="fas fa-times p-3 cursor-pointer text-white opacity-5 position-absolute end-0 top-0 d-none d-xl-none" aria-hidden="true" id="iconSidenav"></i>
                                <a class="navbar-brand m-0" href=" https://demos.creative-tim.com/material-dashboard/pages/dashboard " target="_blank">
                                  <img src="assets/img/logo-ct.png" class="navbar-brand-img h-100" alt="main_logo">
                                  <span class="ms-1 font-weight-bold text-white">Finanzas Personales</span>
                                </a>
                              </div>
                              <hr class="horizontal light mt-0 mb-2">
                              <div class="collapse navbar-collapse  w-auto " id="sidenav-collapse-main">
                                <ul class="navbar-nav">
                                  <li class="nav-item">
                                    <a class="nav-link text-white active bg-gradient-info" href="/ControlGastos-war">
                                      <div class="text-white text-center me-2 d-flex align-items-center justify-content-center">
                                        <i class="material-icons opacity-10">dashboard</i>
                                      </div>
                                      <span class="nav-link-text ms-1">Dashboard</span>
                                    </a>
                                  </li>
                                  <li class="nav-item mt-3">
                                    <h6 class="ps-4 ms-2 text-uppercase text-xs text-white font-weight-bolder opacity-8">Configuraci\u00f3n</h6>
                                  </li>
                                  <li class="nav-item">
                                    <a class="nav-link text-white " href="CuentasList">
                                      <div class="text-white text-center me-2 d-flex align-items-center justify-content-center">
                                        <i class="material-icons opacity-10">table_view</i>
                                      </div>
                                      <span class="nav-link-text ms-1">Cuentas</span>
                                    </a>
                                  </li>
                                  <li class="nav-item">
                                    <a class="nav-link text-white " href="CategoriasList">
                                      <div class="text-white text-center me-2 d-flex align-items-center justify-content-center">
                                        <i class="material-icons opacity-10">receipt_long</i>
                                      </div>
                                      <span class="nav-link-text ms-1">Categorias</span>
                                    </a>
                                  </li>
                                  <li class="nav-item">
                                    <a class="nav-link text-white " href="TercerosList">
                                      <div class="text-white text-center me-2 d-flex align-items-center justify-content-center">
                                        <i class="material-icons opacity-10">view_in_ar</i>
                                      </div>
                                      <span class="nav-link-text ms-1">Terceros</span>
                                    </a>
                                  </li>
                                  <li class="nav-item">
                                    <a class="nav-link text-white " href="TagsList">
                                      <div class="text-white text-center me-2 d-flex align-items-center justify-content-center">
                                        <i class="material-icons opacity-10">format_textdirection_r_to_l</i>
                                      </div>
                                      <span class="nav-link-text ms-1">Tags</span>
                                    </a>
                                  </li>
                                  <li class="nav-item mt-3">
                                    <a href="MovimientosList"><h6 class="ps-4 ms-2 text-uppercase text-xs text-white font-weight-bolder opacity-8">Transacciones</h6></a>
                                  </li>
                                  <li class="nav-item">
                                    <a class="nav-link text-white " href="MovimientosCreate?transaccion=ingreso">
                                      <div class="text-white text-center me-2 d-flex align-items-center justify-content-center">
                                        <i class="material-icons opacity-10">notifications</i>
                                      </div>
                                      <span class="nav-link-text ms-1">Ingresos</span>
                                    </a>
                                  </li>
                                  <li class="nav-item">
                                    <a class="nav-link text-white " href="MovimientosCreate?transaccion=egreso">
                                      <div class="text-white text-center me-2 d-flex align-items-center justify-content-center">
                                        <i class="material-icons opacity-10">person</i>
                                      </div>
                                      <span class="nav-link-text ms-1">Egresos</span>
                                    </a>
                                  </li>
                                </ul>
                              </div>
                            </aside>
                            <main class="main-content position-relative max-height-vh-100 h-100 border-radius-lg ">
                              <!-- Navbar -->
                              <nav class="navbar navbar-main navbar-expand-lg px-0 mx-4 border-radius-xl shadow-none position-sticky blur shadow-blur mt-4 left-auto top-1 z-index-sticky" id="navbarBlur" data-scroll="true">
                                <div class="container-fluid py-1 px-3">
                                  <nav aria-label="breadcrumb">
                                    <ol class="breadcrumb bg-transparent mb-0 pb-0 pt-1 px-0 me-sm-6 me-5">
                                      <li class="breadcrumb-item text-sm"><a class="opacity-5 text-dark" href="javascript:;">Configuraci\u00f3n</a></li>
                                      <li class="breadcrumb-item text-sm text-dark active" aria-current="page">{{entidad}}</li>
                                    </ol>
                                    <h6 class="font-weight-bolder mb-0">{{entidad}}</h6>
                                  </nav>
                                </div>
                              </nav>
                              <!-- End Navbar -->
                              <div class="container-fluid py-4">
                                <div class="row">
                                  <div class="col-xl-10 col-lg-11 col-md-12 d-flex flex-column ms-auto me-auto ms-lg-auto">
                                    <div class="card card-plain">
                                      <div class="bg-gradient-info shadow-info border-radius-lg pt-4 pb-3">
                                        <h6 class="text-white text-capitalize ps-3">{{entidad}}</h6>
                                      </div>
                                      <div class="card-body px-0 pb-2">
                                        <form role="form" method="POST">
                                          {{fields}}
                                          <div class="text-end">
                                            <a href="{{cancel}}" class="btn btn-lg-2 bg-gradient-danger btn-lg mt-4 mb-0">Cancelar</a>
                                            <button type="submit" class="btn btn-lg-2 bg-gradient-success btn-lg mt-4 mb-0">Guardar</button>
                                          </div>
                                        </form>
                                      </div>
                                    </div>
                                  </div>
                                </div>
                              </div>
                            </main>
                            <!--   Core JS Files   -->
                            <script src="assets/js/core/popper.min.js"></script>
                            <script src="assets/js/core/bootstrap.min.js"></script>
                            <script src="assets/js/plugins/perfect-scrollbar.min.js"></script>
                            <script src="assets/js/plugins/smooth-scrollbar.min.js"></script>
                            <script src="assets/js/plugins/chartjs.min.js"></script>
                            <script>
                              var ctx = document.getElementById("chart-bars").getContext("2d");
                          
                              new Chart(ctx, {
                                type: "bar",
                                data: {
                                  labels: ["M", "T", "W", "T", "F", "S", "S"],
                                  datasets: [{
                                    label: "Sales",
                                    tension: 0.4,
                                    borderWidth: 0,
                                    borderRadius: 4,
                                    borderSkipped: false,
                                    backgroundColor: "rgba(255, 255, 255, .8)",
                                    data: [50, 20, 10, 22, 50, 10, 40],
                                    maxBarThickness: 6
                                  }, ],
                                },
                                options: {
                                  responsive: true,
                                  maintainAspectRatio: false,
                                  plugins: {
                                    legend: {
                                      display: false,
                                    }
                                  },
                                  interaction: {
                                    intersect: false,
                                    mode: 'index',
                                  },
                                  scales: {
                                    y: {
                                      grid: {
                                        drawBorder: false,
                                        display: true,
                                        drawOnChartArea: true,
                                        drawTicks: false,
                                        borderDash: [5, 5],
                                        color: 'rgba(255, 255, 255, .2)'
                                      },
                                      ticks: {
                                        suggestedMin: 0,
                                        suggestedMax: 500,
                                        beginAtZero: true,
                                        padding: 10,
                                        font: {
                                          size: 14,
                                          weight: 300,
                                          family: "Roboto",
                                          style: 'normal',
                                          lineHeight: 2
                                        },
                                        color: "#fff"
                                      },
                                    },
                                    x: {
                                      grid: {
                                        drawBorder: false,
                                        display: true,
                                        drawOnChartArea: true,
                                        drawTicks: false,
                                        borderDash: [5, 5],
                                        color: 'rgba(255, 255, 255, .2)'
                                      },
                                      ticks: {
                                        display: true,
                                        color: '#f8f9fa',
                                        padding: 10,
                                        font: {
                                          size: 14,
                                          weight: 300,
                                          family: "Roboto",
                                          style: 'normal',
                                          lineHeight: 2
                                        },
                                      }
                                    },
                                  },
                                },
                              });
                          
                          
                              var ctx2 = document.getElementById("chart-line").getContext("2d");
                          
                              new Chart(ctx2, {
                                type: "line",
                                data: {
                                  labels: ["Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"],
                                  datasets: [{
                                    label: "Mobile apps",
                                    tension: 0,
                                    borderWidth: 0,
                                    pointRadius: 5,
                                    pointBackgroundColor: "rgba(255, 255, 255, .8)",
                                    pointBorderColor: "transparent",
                                    borderColor: "rgba(255, 255, 255, .8)",
                                    borderColor: "rgba(255, 255, 255, .8)",
                                    borderWidth: 4,
                                    backgroundColor: "transparent",
                                    fill: true,
                                    data: [50, 40, 300, 320, 500, 350, 200, 230, 500],
                                    maxBarThickness: 6
                          
                                  }],
                                },
                                options: {
                                  responsive: true,
                                  maintainAspectRatio: false,
                                  plugins: {
                                    legend: {
                                      display: false,
                                    }
                                  },
                                  interaction: {
                                    intersect: false,
                                    mode: 'index',
                                  },
                                  scales: {
                                    y: {
                                      grid: {
                                        drawBorder: false,
                                        display: true,
                                        drawOnChartArea: true,
                                        drawTicks: false,
                                        borderDash: [5, 5],
                                        color: 'rgba(255, 255, 255, .2)'
                                      },
                                      ticks: {
                                        display: true,
                                        color: '#f8f9fa',
                                        padding: 10,
                                        font: {
                                          size: 14,
                                          weight: 300,
                                          family: "Roboto",
                                          style: 'normal',
                                          lineHeight: 2
                                        },
                                      }
                                    },
                                    x: {
                                      grid: {
                                        drawBorder: false,
                                        display: false,
                                        drawOnChartArea: false,
                                        drawTicks: false,
                                        borderDash: [5, 5]
                                      },
                                      ticks: {
                                        display: true,
                                        color: '#f8f9fa',
                                        padding: 10,
                                        font: {
                                          size: 14,
                                          weight: 300,
                                          family: "Roboto",
                                          style: 'normal',
                                          lineHeight: 2
                                        },
                                      }
                                    },
                                  },
                                },
                              });
                          
                              var ctx3 = document.getElementById("chart-line-tasks").getContext("2d");
                          
                              new Chart(ctx3, {
                                type: "line",
                                data: {
                                  labels: ["Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"],
                                  datasets: [{
                                    label: "Mobile apps",
                                    tension: 0,
                                    borderWidth: 0,
                                    pointRadius: 5,
                                    pointBackgroundColor: "rgba(255, 255, 255, .8)",
                                    pointBorderColor: "transparent",
                                    borderColor: "rgba(255, 255, 255, .8)",
                                    borderWidth: 4,
                                    backgroundColor: "transparent",
                                    fill: true,
                                    data: [50, 40, 300, 220, 500, 250, 400, 230, 500],
                                    maxBarThickness: 6
                          
                                  }],
                                },
                                options: {
                                  responsive: true,
                                  maintainAspectRatio: false,
                                  plugins: {
                                    legend: {
                                      display: false,
                                    }
                                  },
                                  interaction: {
                                    intersect: false,
                                    mode: 'index',
                                  },
                                  scales: {
                                    y: {
                                      grid: {
                                        drawBorder: false,
                                        display: true,
                                        drawOnChartArea: true,
                                        drawTicks: false,
                                        borderDash: [5, 5],
                                        color: 'rgba(255, 255, 255, .2)'
                                      },
                                      ticks: {
                                        display: true,
                                        padding: 10,
                                        color: '#f8f9fa',
                                        font: {
                                          size: 14,
                                          weight: 300,
                                          family: "Roboto",
                                          style: 'normal',
                                          lineHeight: 2
                                        },
                                      }
                                    },
                                    x: {
                                      grid: {
                                        drawBorder: false,
                                        display: false,
                                        drawOnChartArea: false,
                                        drawTicks: false,
                                        borderDash: [5, 5]
                                      },
                                      ticks: {
                                        display: true,
                                        color: '#f8f9fa',
                                        padding: 10,
                                        font: {
                                          size: 14,
                                          weight: 300,
                                          family: "Roboto",
                                          style: 'normal',
                                          lineHeight: 2
                                        },
                                      }
                                    },
                                  },
                                },
                              });
                            </script>
                            <script>
                              var win = navigator.platform.indexOf('Win') > -1;
                              if (win && document.querySelector('#sidenav-scrollbar')) {
                                var options = {
                                  damping: '0.5'
                                }
                                Scrollbar.init(document.querySelector('#sidenav-scrollbar'), options);
                              }
                            </script>
                            <!-- Github buttons -->
                            <script async defer src="https://buttons.github.io/buttons.js"></script>
                            <!-- Control Center for Material Dashboard: parallax effects, scripts for the example pages etc -->
                            <script src="assets/js/material-dashboard.min.js?v=3.0.4"></script>
                          </body>
                          
                          </html>""";
        template = template.replace("{{entidad}}", entidad);
        template = template.replace("{{fields}}", campos);        
        template = template.replace("{{cancel}}", cancel);
        return template;
    }
}
