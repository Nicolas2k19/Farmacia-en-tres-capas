import {normalizar} from "../Acceso/usig.js"

async function posicionarMapa(calle,altura,localidad,map){
    let direccionNormalizada  = await normalizar(calle,altura,localidad);
    let direccion = direccionNormalizada.direccionesNormalizadas[0]

    console.log(direccion.coordenadas.x, direccion.coordenadas.y)

    map.setView([direccion.coordenadas.y,direccion.coordenadas.x], 13)
    let marker = L.marker([direccion.coordenadas.y,direccion.coordenadas.x])
    .addTo(map);
}




export {posicionarMapa}