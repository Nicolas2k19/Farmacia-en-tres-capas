let url = "api/eliminarFarmacia/"


const  eliminarFarmacia = async (id)=>{
    return await fetch(url+id,{
       "method" : "DELETE",
       "headers" : {
            "Content-Type": "application/json"
                    }
    })
    .then(data => data.json)
    .then(datos => console.log(datos))
}

export {eliminarFarmacia}