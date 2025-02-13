
const  $dom = Document;
let lista = $dom.querySelectorAll("#farmacias");


 const  cargarFarmacias = async ()=>{

     await fetch("http://localhost:8080/obtenerFarmacias",(data)=>data)
     .then(response => response.json())
     .then(data => {crearElementoLista(data.nombre)})


}

const crearElementoLista = (texto)=>{
    let itemLista =  document.createElement('li.farmacia');
    itemLista.innerText(texto);
    lista.appendChild(itemLista);
}
 



window.onload((cargarFarmacias))


