


async function normalizar(calle,altura,localidad){
return await fetch(`http://servicios.usig.buenosaires.gob.ar/normalizar/?direccion=${calle} ${altura} ,${localidad} `,{method : "GET"})
    .then(
    data => data.json())
}

export {normalizar}