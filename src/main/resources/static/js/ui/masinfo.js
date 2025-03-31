import { posicionarMapa } from "../../Controlador/masInfo.js";

let localidad = document.querySelector("#localidad").innerHTML
let calle = document.querySelector("#calle").innerHTML
let nroCalle = document.querySelector("#nro").innerHTML


let map = L.map('map').setView([51.505, -0.09], 4);

L.tileLayer('https://tile.openstreetmap.org/{z}/{x}/{y}.png', {
    maxZoom: 19,
    attribution: '&copy; <a href="http://www.openstreetmap.org/copyright">OpenStreetMap</a>'
}).addTo(map);



await posicionarMapa(calle,nroCalle,localidad,map)


